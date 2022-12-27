/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import jetbrains.datalore.plot.config.Option
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.util.Base64
import org.jetbrains.letsPlot.util.pngj.ImageInfo
import org.jetbrains.letsPlot.util.pngj.ImageLineByte
import org.jetbrains.letsPlot.util.pngj.OutputPngStream
import org.jetbrains.letsPlot.util.pngj.PngWriter


fun geomImshow(
    imageData: ImageData,
    norm: Boolean? = null,
    vmin: Number? = null,
    vmax: Number? = null,
    extent: List<Number>? = null,
): Layer {
    require(extent == null || extent.size == 4) { "Invalid `extent`: list of 4 numbers expected: ${extent!!.size}" }
    var raster = Raster.fromImageData(imageData)

    require(raster.nChannels in 1..4) {
        "Invalid image_data: num of channels expected to be 1 (G) or 2 (GA) for greyscale image, 3 (RGB) or 4 (RGBA) for color image, but was ${raster.nChannels}"
    }

    val cmap: String? = null // TODO: add palettes support

    if (raster.nChannels == 1) {
        var hasNan = raster.hasNan()
        val maxLum = if (!(hasNan && cmap != null)) 255 else 254  // index 255 reserved for NaN-s
        normalize2d(raster, norm, vmin?.toFloat(), vmax?.toFloat(), maxLum)
        hasNan = raster.hasNan()

        if (hasNan && cmap.isNullOrEmpty()) {
            // add alpha
            raster = raster.addChannel()

            require(raster.nChannels == 2)
            raster.updatePixels { pix ->
                pix[1] = when (pix[0].isNaN()) {
                    true -> vmin?.toFloat() ?: Float.NaN
                    false -> 255f
                }
            }
        } else if (hasNan && !cmap.isNullOrEmpty()) {
            raster.updateChannels { it.takeUnless { it.isNaN() } ?: 255f }
        }
    } else {
        if (raster.isDTypeF) {
            raster.updateChannels { it * 255f + 0.5f }
        }
    }

    raster.updateChannels { it.coerceIn(0.0f, 255.0f) }

    var (extX0, extX1, extY0, extY1) =
        extent?.map(Number::toDouble)
            ?: listOf(-.5, raster.width - .5, -.5, raster.height - .5)

    val flipColumns: Boolean = extX0 > extX1
    if (flipColumns) {
        extX0 = extX1.also { extX1 = extX0 }
    }

    val flipRows = extY0 > extY1
    if (flipRows) {
        extY0 = extY1.also { extY1 = extY0 }
    }

    val outputStream = OutputPngStream()
    val png = PngWriter(
        outputStream, ImageInfo(
            raster.width,
            raster.height,
            bitdepth = 8,
            alpha = (raster.nChannels == 4 || raster.nChannels == 2),
            greyscale = raster.nChannels < 3
        )
    )

    val iLine = ImageLineByte(png.imgInfo)
    val px = raster.pixel()
    val rows = (0 until raster.height).let { it.takeIf { !flipRows } ?: it.reversed() }
    val columns = (0 until raster.width).let { it.takeIf { !flipColumns } ?: it.reversed() }
    for (row in rows) {
        var p = 0
        for (col in columns) {
            px.atXY(col, row).channels().forEach {
                iLine.scanline[p++] = it.toInt().toByte()
            }
        }
        png.writeRow(iLine)
    }
    png.end()

    return object : Layer(
        geom = GeomOptions(GeomKind.IMAGE),
        data = null,
        mapping = Options.empty(),
        stat = Stat.identity,
        position = null,
        showLegend = false,
        sampling = null,
        orientation = null,
        tooltips = null,
        labels = null
    ) {
        override fun seal(): Options {
            return Options.of(
                Option.Geom.Image.HREF to "data:image/png;base64," + Base64.encode(outputStream.byteArray),
                Option.Geom.Image.XMIN to extX0,
                Option.Geom.Image.YMIN to extY0,
                Option.Geom.Image.XMAX to extX1,
                Option.Geom.Image.YMAX to extY1,
            )
        }
    }
}

private fun normalize2d(raster: Raster, norm: Boolean?, vMin: Float?, vMax: Float?, maxLum: Int) {
    @Suppress("NAME_SHADOWING")
    val vMin = vMin ?: raster.pixels.filterNot(Float::isNaN).min()

    @Suppress("NAME_SHADOWING")
    val vMax = vMax ?: raster.pixels.filterNot(Float::isNaN).max()

    require(vMin <= vMax) { "vmin value must be less then vmax value, was: $vMin > $vMax" }
    raster.updateChannels { it.coerceIn(vMin, vMax) }

    if (norm == false) {
        // no normalization - just round values to the nearest int.
        raster.updateChannels { it + 0.5f }
        return
    }

    when {
        vMin == vMax -> raster.updateChannels { 127f }
        else -> {
            val ratio = maxLum / (vMax - vMin)
            raster.updateChannels { (it - vMin) * ratio + 0.5f }
        }
    }
}

class ImageData private constructor(
    val pixels: Any,
    val shape: Shape?
) {
    class Shape(
        val width: Int,
        val height: Int,
        val nChannels: Int
    )

    companion object {
        fun fromArray(pixels: Any, width: Int, height: Int, nChannels: Int): ImageData {
            return ImageData(pixels, Shape(width, height, nChannels))
        }

        fun fromMatrix(pixels: List<*>): ImageData {
            return ImageData(pixels, null)
        }
    }

    override fun toString(): String =
        "ImageData: ${pixels::class.simpleName}, shape: " + when (shape) {
        null -> "null"
        else -> "${shape.width} x ${shape.height} x ${shape.nChannels}"
    }
}

internal class Raster(
    val width: Int,
    val height: Int,
    val nChannels: Int,
    val isDTypeF: Boolean,
    pixelDataProvider: () -> FloatArray
) {
    val pixels: FloatArray by lazy { pixelDataProvider() }

    inner class Pixel {
        private var pxIndex: Int = 0

        fun atXY(x: Int, y: Int): Pixel {
            pxIndex = (y * width + x) * nChannels
            return this
        }

        operator fun get(chIndex: Int): Float = pixels[pxIndex + chIndex]
        operator fun set(chIndex: Int, v: Float) {
            pixels[pxIndex + chIndex] = v
        }

        fun channels() = when (nChannels) {
            1 -> listOf(get(0))
            2 -> listOf(get(0), get(1))
            3 -> listOf(get(0), get(1), get(2))
            4 -> listOf(get(0), get(1), get(2), get(3))
            else -> error("Unsupported number of channels: $nChannels")
        }
    }

    fun updatePixels(f: (Pixel) -> Unit) {
        val pixel = Pixel()
        for (y in 0 until height) {
            for (x in 0 until width) {
                f(pixel.atXY(x, y))
            }
        }
    }

    fun updateChannels(f: (Float) -> Float) {
        return updatePixels { ch ->
            if (nChannels >= 1) ch[0] = f(ch[0])
            if (nChannels >= 2) ch[1] = f(ch[1])
            if (nChannels >= 3) ch[2] = f(ch[2])
            if (nChannels >= 4) ch[3] = f(ch[3])
        }
    }

    fun addChannel() = Raster(width, height, nChannels + 1, isDTypeF) {
        val newPixels = FloatArray(width * height * (nChannels + 1))
        var newI = 0
        for (i in pixels.indices) {
            newPixels[newI++] = pixels[i]
            if (newI % nChannels == 0) {
                newPixels[newI++] = Float.NaN
            }
        }
        newPixels
    }

    fun pixel() = Pixel()
    fun hasNan() = pixels.any(Float::isNaN)

    internal companion object {
        fun fromImageData(imageData: ImageData): Raster {
            if (imageData.shape == null) {
                val height: Int
                val width: Int
                val nch: Int

                if ((imageData.pixels as? List<*>)?.isNotEmpty() == true) {
                    if ((imageData.pixels[0] as? List<*>)?.isNotEmpty() == true) {
                        @Suppress("UNCHECKED_CAST")
                        imageData.pixels as List<List<*>>

                        height = imageData.pixels.size
                        width = imageData.pixels[0].size

                        if ((imageData.pixels[0][0] as? List<*>)?.isNotEmpty() == true) {
                            @Suppress("UNCHECKED_CAST")
                            imageData.pixels as List<List<List<Number>>>

                            nch = imageData.pixels[0][0].size
                            val isDTypeF = imageData.pixels[0][0][0].let { it is Double || it is Float }

                            return Raster(width, height, nch, isDTypeF) {
                                val arr = FloatArray(height * width * nch)

                                var i = 0
                                for (row in imageData.pixels) {
                                    for (pix in row) {
                                        for (ch in pix) {
                                            arr[i++] = toFloat(ch)
                                        }
                                    }
                                }

                                arr
                            }
                        } else if (imageData.pixels[0][0] is Number) {
                            @Suppress("UNCHECKED_CAST")
                            imageData.pixels as List<List<Number>>

                            nch = 1
                            val isDTypeF = imageData.pixels[0][0].let { it is Double || it is Float }

                            return Raster(width, height, nch, isDTypeF) {
                                val arr = FloatArray(height * width * nch)

                                var i = 0
                                for (row in imageData.pixels) {
                                    for (ch in row) {
                                        arr[i++] = toFloat(ch)
                                    }
                                }
                                arr
                            }
                        }
                    }
                }
                error("Invalid bitmap: without shape a 2d or 3d array is expected")
            } else {
                val isDTypeF: Boolean
                val nChannels = imageData.shape.nChannels
                val height = imageData.shape.height
                val width = imageData.shape.width
                val pixelDataProvider: () -> FloatArray = when (imageData.pixels) {
                    is FloatArray -> {
                        isDTypeF = true

                        fun(): FloatArray {
                            val arr = FloatArray(height * width * nChannels)
                            imageData.pixels.copyInto(arr)
                            return arr
                        }
                    }

                    is ByteArray -> {
                        isDTypeF = false

                        fun(): FloatArray {
                            val arr = FloatArray(height * width * nChannels)
                            imageData.pixels.forEachIndexed { i, v -> arr[i] = toFloat(v) }
                            return arr
                        }
                    }

                    is IntArray -> {
                        isDTypeF = false

                        fun(): FloatArray {
                            val arr = FloatArray(height * width * nChannels)
                            imageData.pixels.forEachIndexed { i, v ->
                                var offset = i * nChannels
                                if (nChannels >= 3) {
                                    arr[offset++] = toFloat((v shr 16) and 0xFF)
                                }
                                if (nChannels >= 2) {
                                    arr[offset++] = toFloat((v shr 8) and 0xFF)
                                }
                                if (nChannels >= 1) {
                                    arr[offset++] = toFloat(v and 0xFF)
                                }
                                if (nChannels == 4) {
                                    arr[offset] = toFloat((v shr 24) and 0xFF)
                                }
                            }
                            return arr
                        }
                    }

                    is DoubleArray -> {
                        isDTypeF = true

                        fun(): FloatArray {
                            val arr = FloatArray(height * width * nChannels)
                            imageData.pixels.forEachIndexed { i, v -> arr[i] = toFloat(v) }
                            return arr
                        }
                    }

                    is List<*> -> {
                        isDTypeF = imageData.pixels[0].let { it is Float || it is Double }

                        fun(): FloatArray {
                            val arr = FloatArray(height * width * nChannels)
                            imageData.pixels.forEachIndexed { i, v -> arr[i] = toFloat(v) }
                            return arr
                        }
                    }

                    is Array<*> -> {
                        isDTypeF = imageData.pixels[0].let { it is Float || it is Double }
                        fun(): FloatArray {
                            val arr = FloatArray(height * width * nChannels)
                            imageData.pixels.forEachIndexed { i, v -> arr[i] = toFloat(v) }
                            return arr
                        }

                    }

                    else -> error("Invalid bitmap: unsupported data type `${imageData.pixels::class.simpleName}`")
                }

                return Raster(width, height, nChannels, isDTypeF, pixelDataProvider)
            }
        }

        private fun toFloat(v: Any?): Float {
            return when (v) {
                is Float -> v
                is Byte -> v.toUByte().toFloat() // for bytes 128, 129 method .toFloat() returns -1f, -2f etc
                is Number -> v.toFloat()
                else -> error("Invalid bitmap: should contain only numbers")
            }
        }
    }
}
