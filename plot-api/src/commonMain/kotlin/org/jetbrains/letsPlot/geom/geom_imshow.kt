/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.*
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.scale.scaleGrey
import org.jetbrains.letsPlot.util.Base64
import org.jetbrains.letsPlot.util.pngj.ImageInfo
import org.jetbrains.letsPlot.util.pngj.ImageLineByte
import org.jetbrains.letsPlot.util.pngj.OutputPngStream
import org.jetbrains.letsPlot.util.pngj.PngWriter


/**
 * Displays image specified by RasterData.
 *
 * This geom is not as flexible as `geomRaster()` or `geomTile()`
 * but vastly superior in the terms of rendering efficiency.
 *
 * Notes
 * -----
 * This geom doesn't understand any aesthetics.
 * It doesn't support color scales either.
 *
 * ## Examples
 *
 * - [image_101.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/imshow/docs/examples/jupyter-notebooks/f-4.2.0/image_101.ipynb)
 * - [image_extent.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/imshow/docs/examples/jupyter-notebooks/f-4.2.0/image_extent.ipynb)
 * - [image_fisher_boat.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/imshow/docs/examples/jupyter-notebooks/f-4.2.0/image_fisher_boat.ipynb)
 * - [image_grayscale.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/imshow/docs/examples/jupyter-notebooks/f-4.2.0/image_grayscale.ipynb)
 *
 * @param rasterData Specifies image type, size and pixel values. See [RasterData.create].
 *
 * @param norm default = true.
 *  - true - luminance values in grey-scale image will be scaled to `[0-255]` range using a linear scaler.
 *  - false - disables scaling of luminance values in grey-scale image.
 *  This parameter is ignored for RGB(A) images.
 * @param vmin default = null.
 *  Defines the data range used for luminance normalization in grey-scale images.
 *  This parameter is ignored for RGB(A) images or if parameter `norm = false`.
 * @param vmax default = null.
 *  Defines the data range used for luminance normalization in grey-scale images.
 *  This parameter is ignored for RGB(A) images or if parameter `norm = false`.
 * @param extent default = listOf(-0.5, ncol-0.5, -0.5, nrow-0.5).
 *  List of 4 numbers: (left, right, bottom, top).
 *  Defines image's bounding box in terms of the "data coordinates".
 *  - `left, right`: coordinates of pixels outer edge along the x-axis for pixels in the 1-st and the last column.
 *  - `bottom, top`: coordinates of pixels outer edge along the y-axis for pixels in the 1-st and the last row.
 * @param showLegend default = true.
 *  Greyscale images only.
 *  false - do not show legend for this layer.
 * @param colorBy default="paint_c" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Define the color-aesthetic used by the legend shown for a greyscale image.
 *
 * @return Layer object.
 */
fun geomImshow(
    rasterData: RasterData,
    norm: Boolean = true,
    vmin: Number? = null,
    vmax: Number? = null,
    extent: List<Number>? = null,
    showLegend: Boolean = true,
    colorBy: String = "paint_c",
): Feature {
    require(extent == null || extent.size == 4) { "Invalid `extent`: list of 4 numbers expected: ${extent!!.size}" }
    val colorAesthetics = listOf("fill", "color", "paint_a", "paint_b", "paint_c")
    require(colorBy in colorAesthetics) { "Invalid colorBy value \"$colorBy\". Use: \"color\", \"fill\", \"paint_a\", \"paint_b\" or \"paint_c\"." }

    var raster = rasterData.createRaster()

    require(raster.nChannels in 1..4) {
        "Invalid rasterData: num of channels expected to be 1 (G) or 2 (GA) for greyscale image, 3 (RGB) or 4 (RGBA) for color image, but was ${raster.nChannels}"
    }

    val cmap: String? = null // TODO: add palettes support

    val greyscale = raster.nChannels == 1
    var greyScaleDataMin: Double = Double.NaN
    var greyScaleDataMax: Double = Double.NaN

    if (greyscale) {
        var hasNan = raster.hasNan()
        val maxLum = if (!(hasNan && cmap != null)) 255 else 254  // index 255 reserved for NaN-s
        normalize2d(raster, norm, vmin?.toFloat(), vmax?.toFloat(), maxLum).let {
            greyScaleDataMin = it.first
            greyScaleDataMax = it.second
        }
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


    // Show Legend (color-bar) if applicable.
    val layerMapping: Options = if (greyscale && showLegend) {
        // Provide two imaginable data-points to build a legend.
        Options.of(
            colorBy to listOf(greyScaleDataMin, greyScaleDataMax)
        )
    } else {
        Options.empty()
    }

    val legendTitle = ""
    val colorScale: Scale? = if (greyscale && showLegend) {
        if (cmap != null) when (norm) {
            true -> null  // ToDo
            else -> null  // ToDo
        } else {
            val start = if (norm) 0.0 else greyScaleDataMin / 255
            val end = if (norm) 1.0 else greyScaleDataMax / 255
            scaleGrey(aesthetic = colorBy, start = start, end = end, name = legendTitle)
        }
    } else {
        null
    }


    val geomLayer = object : Layer(
        geom = GeomOptions(GeomKind.IMAGE),
        data = null,
        mapping = layerMapping,
        stat = Stat.identity,
        position = null,
        showLegend = showLegend,
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
                Option.Layer.COLOR_BY to colorBy  // for the legend
            )
        }
    }

    return colorScale?.let {
        geomLayer + it
    } ?: geomLayer
}

/**
 * Updates channels in the given `Raster` object.
 *
 * @return The used `min` and `max` data values (i.e. values before normalization)
 */
private fun normalize2d(raster: Raster, norm: Boolean, vMin: Float?, vMax: Float?, maxLum: Int): Pair<Double, Double> {
    @Suppress("NAME_SHADOWING")
    val vMin = vMin ?: raster.pixels.filterNot(Float::isNaN).min()

    @Suppress("NAME_SHADOWING")
    val vMax = vMax ?: raster.pixels.filterNot(Float::isNaN).max()

    require(vMin <= vMax) { "vmin value must be less then vmax value, was: $vMin > $vMax" }
    raster.updateChannels { it.coerceIn(vMin, vMax) }

    if (norm == false) {
        // no normalization - just round values to the nearest int.
        raster.updateChannels { it + 0.5f }
    } else {
        @Suppress("IntroduceWhenSubject")
        when {
            vMin == vMax -> raster.updateChannels { 127f }
            else -> {
                val ratio = maxLum / (vMax - vMin)
                raster.updateChannels { (it - vMin) * ratio + 0.5f }
            }
        }
    }

    return Pair(vMin.toDouble(), vMax.toDouble())
}

class RasterData private constructor(
    private val data: Any,
    private val width: Int,
    private val height: Int,
    private val nChannels: Int
) {
    companion object {
        /**
         * Creates [RasterData] from 2D or 3D collection.
         * @param iterable 2D or 3D collection.
         *  - (M, N): an image with scalar data. The values are mapped to colors (greys by default) using normalization. See parameters `norm`, `vmin`, `vmax`.
         *  - (M, N, 3): an image with RGB values (0-1 float or 0-255 int).
         *  - (M, N, 4): an image with RGBA values (0-1 float or 0-255 int).
         */
        fun create(iterable: Iterable<Iterable<*>>): RasterData {
            val l0 = if (iterable is Collection) iterable else iterable.toList()
            val l1 = l0.flatten()

            @Suppress("UNCHECKED_CAST")
            val l2: List<Number> = when (l1[0] is Iterable<*>) {
                true -> (l1 as Iterable<Iterable<*>>).flatten() as List<Number>
                false -> l1 as List<Number>
            }

            val height = l0.size
            val width = l1.size / height
            val nChannels = l2.size / (width * height)
            return RasterData(l2, width, height, nChannels)
        }

        /**
         * Creates [RasterData] from 2D or 3D array.
         * @param arr 2D or 3D array.
         *  - (M, N): an image with scalar data. The values are mapped to colors (greys by default) using normalization. See parameters `norm`, `vmin`, `vmax`.
         *  - (M, N, 3): an image with RGB values (0-1 float or 0-255 int).
         *  - (M, N, 4): an image with RGBA values (0-1 float or 0-255 int).
         */
        fun create(arr: Array<out Array<*>>): RasterData {
            val l0 = arr.flatten()

            @Suppress("UNCHECKED_CAST")
            val l1: List<Number> = when (l0[0] is Array<*>) {
                true -> (l0 as List<Array<*>>).map(Array<*>::asList).flatten() as List<Number>
                false -> l0 as List<Number>
            }

            val height = arr.size
            val width = l0.size / height
            val nChannels = l1.size / (width * height)
            return RasterData(l1, width, height, nChannels)
        }

        /**
         * Creates [RasterData] from 1D array with pixel data.
         * @param arr Array of Numbers (0-1 float or 0-255 int).
         *  Expected size width * height * nChannels.
         * @param width Width of the image in pixels.
         * @param height Height of the image in pixels.
         * @param nChannels Number of channels per pixel.
         *  - 1: an image with scalar data. The values are mapped to colors (greys by default) using normalization. See parameters `norm`, `vmin`, `vmax`.
         *  - 3: an image with RGB values (0-1 float or 0-255 int).
         *  - 4: an image with RGBA values (0-1 float or 0-255 int).
         */
        fun create(arr: ByteArray, width: Int, height: Int, nChannels: Int) = RasterData(arr, width, height, nChannels)

        /**
         * Creates [RasterData] from 1D array with pixel data.
         * @param arr Array of numbers (0-1 float or 0-255 int).
         *  Expected size width * height * nChannels.
         * @param width Width of the image in pixels.
         * @param height Height of the image in pixels.
         * @param nChannels Number of channels per pixel.
         *  - 1: an image with scalar data. The values are mapped to colors (greys by default) using normalization. See parameters `norm`, `vmin`, `vmax`.
         *  - 3: an image with RGB values (0-1 float or 0-255 int).
         *  - 4: an image with RGBA values (0-1 float or 0-255 int).
         */
        fun create(arr: IntArray, width: Int, height: Int, nChannels: Int) = RasterData(arr, width, height, nChannels)

        /**
         * Creates [RasterData] from 1D array with pixel data.
         * @param arr Array of numbers (0-1 float or 0-255 int).
         *  Expected size width * height * nChannels.
         * @param width Width of the image in pixels.
         * @param height Height of the image in pixels.
         * @param nChannels Number of channels per pixel.
         *  - 1: an image with scalar data. The values are mapped to colors (greys by default) using normalization. See parameters `norm`, `vmin`, `vmax`.
         *  - 3: an image with RGB values (0-1 float or 0-255 int).
         *  - 4: an image with RGBA values (0-1 float or 0-255 int).
         */
        fun create(arr: FloatArray, width: Int, height: Int, nChannels: Int) = RasterData(arr, width, height, nChannels)

        /**
         * Creates [RasterData] from 1D array with pixel data.
         * @param arr Array of numbers (0-1 float or 0-255 int).
         *  Expected size width * height * nChannels.
         * @param width Width of the image in pixels.
         * @param height Height of the image in pixels.
         * @param nChannels Number of channels per pixel.
         *  - 1: an image with scalar data. The values are mapped to colors (greys by default) using normalization. See parameters `norm`, `vmin`, `vmax`.
         *  - 3: an image with RGB values (0-1 float or 0-255 int).
         *  - 4: an image with RGBA values (0-1 float or 0-255 int).
         */
        fun create(arr: DoubleArray, width: Int, height: Int, nChannels: Int) =
            RasterData(arr, width, height, nChannels)

        /**
         * Creates [RasterData] from 1D array with pixel data.
         * @param arr Array of numbers (0-1 float or 0-255 int).
         *  Expected size width * height * nChannels.
         * @param width Width of the image in pixels.
         * @param height Height of the image in pixels.
         * @param nChannels Number of channels per pixel.
         *  - 1: an image with scalar data. The values are mapped to colors (greys by default) using normalization. See parameters `norm`, `vmin`, `vmax`.
         *  - 3: an image with RGB values (0-1 float or 0-255 int).
         *  - 4: an image with RGBA values (0-1 float or 0-255 int).
         */
        fun create(arr: Array<Number>, width: Int, height: Int, nChannels: Int) =
            RasterData(arr, width, height, nChannels)
    }

    override fun toString() = "RasterData($width x $height x $nChannels)"

    internal fun createRaster(): Raster {
        val isDTypeF: Boolean
        val pixelData: FloatArray = when (data) {
            is Array<*> -> {
                isDTypeF = data[0].let { it is Float || it is Double }
                FloatArray(height * width * nChannels).also { arr ->
                    data.forEachIndexed { i, v -> arr[i] = toFloat(v) }
                }
            }

            is FloatArray -> {
                isDTypeF = true
                FloatArray(height * width * nChannels).also(data::copyInto)
            }

            is ByteArray -> {
                isDTypeF = false
                FloatArray(height * width * nChannels).also { arr ->
                    data.forEachIndexed { i, v -> arr[i] = toFloat(v) }
                }
            }

            is IntArray -> {
                isDTypeF = false
                FloatArray(height * width * nChannels).also { arr ->
                    data.forEachIndexed { i, v -> arr[i] = toFloat(v) }
                }
            }

            is DoubleArray -> {
                isDTypeF = true
                FloatArray(height * width * nChannels).also { arr ->
                    data.forEachIndexed { i, v -> arr[i] = toFloat(v) }
                }
            }

            is List<*> -> {
                isDTypeF = data[0].let { it is Float || it is Double }
                FloatArray(height * width * nChannels).also { arr ->
                    data.forEachIndexed { i, v -> arr[i] = toFloat(v) }
                }
            }

            else -> error("Invalid bitmap: unsupported data type `${data::class.simpleName}`")
        }

        return Raster(width, height, nChannels, isDTypeF, pixelData)
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

internal class Raster(
    val width: Int,
    val height: Int,
    val nChannels: Int,
    val isDTypeF: Boolean,
    val pixels: FloatArray
) {
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

    fun addChannel(): Raster {
        val newPixels = FloatArray(width * height * (nChannels + 1))
        var newI = 0
        for (i in pixels.indices) {
            newPixels[newI++] = pixels[i]
            if (newI % nChannels == 0) {
                newPixels[newI++] = Float.NaN
            }
        }
        return Raster(width, height, nChannels + 1, isDTypeF, newPixels)
    }

    fun pixel() = Pixel()
    fun hasNan() = pixels.any(Float::isNaN)
}
