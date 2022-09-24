/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.export

import jetbrains.datalore.plot.PlotHtmlExport
import jetbrains.datalore.plot.PlotHtmlHelper.scriptUrl
import jetbrains.datalore.plot.PlotImageExport
import jetbrains.datalore.plot.PlotSvgExport
import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.intern.toSpec
import java.io.File
import java.lang.Double.NaN
import java.util.*

private const val DEF_EXPORT_DIR = "lets-plot-images"

/**
 * Exports plot to a file.
 * Supported formats: SVG, HTML, PNG, JPEG and TIFF.
 * Note: in some configurations raster formats might not be supported.
 *
 * The exported file is created in directory ${user.dir}/lets-plot-images
 * if not specified otherwise (see the `path` parameter).
 *
 * ## Examples
 *
 * - [export_to_file.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/export_to_file.ipynb)
 *
 * @param plot Plot or GGBunch to export.
 * @param filename The name of file. It mast end with file extention corresponding
 *      to one of the supported formats: svg, html (or htm), png, jpeg (or jpg) or tiff (or tif)
 * @param scale Scaling factor (only for raster formats). Default: 2.0
 * @param dpi Dot-per-Inch value to store in the exported file metadata (only for raster formats).
 *      By default: no metadata is stored.
 * @param path Path to a directory to save image files in.
 *      By default it is `${user.dir}/lets-plot-images`
 *
 * @return Absolute pathname of created file.
 */
@Suppress("SpellCheckingInspection")
fun ggsave(
    plot: Figure,
    filename: String,
    scale: Number = 2,
    dpi: Number? = null,
    path: String? = null
): String {

    @Suppress("NAME_SHADOWING")
    val filename = filename.trim()
    require(filename.indexOf('.') >= 0) {
        "File extension is missing: \"$filename\"."
    }
    require(filename.substringBeforeLast('.', "").isNotEmpty()) {
        "Malformed filename: \"$filename\"."
    }
    val ext = filename.substringAfterLast('.', "").lowercase(Locale.getDefault())
    require(ext.isNotEmpty()) { "Missing file extension: \"$filename\"." }

    val dir = path?.let { File(path) } ?: File(System.getProperty("user.dir"), DEF_EXPORT_DIR)
    dir.mkdir()
    val file = File(dir.canonicalPath, filename)

    // Plot specs
//    val spec: MutableMap<String, Any> = when (plot) {
//        is Plot -> plot.toSpec()
//        is GGBunch -> plot.toSpec()
//        else -> throw IllegalArgumentException("Unsupported figure type: ${plot::class.simpleName}")
//    }
    val spec: MutableMap<String, Any> = plot.toSpec()

    when (ext) {
        "svg" -> {
            val svg = PlotSvgExport.buildSvgImageFromRawSpecs(spec)
            file.createNewFile()
            file.writeText(svg)
        }
        "html", "htm" -> {
            val html = PlotHtmlExport.buildHtmlFromRawSpecs(
                spec,
                iFrame = true,
                scriptUrl = scriptUrl(VersionChecker.letsPlotJsVersion)
            )
            file.createNewFile()
            file.writeText(html)
        }
        "png", "jpeg", "jpg", "tiff", "tif" -> {
            exportRasterImage(
                spec,
                file,
                scalingFactor = scale.toDouble(),
                targetDPI = dpi?.toDouble() ?: NaN
            )
        }
        else -> throw java.lang.IllegalArgumentException(
            """
            Unsupported file extension: "$ext".
            Please use one of: "svg", "html", "htm", "png", "jpeg", "jpg", "tiff", "tif". 
        """.trimIndent()
        )
    }

    return file.canonicalPath
}

private fun exportRasterImage(
    spec: MutableMap<String, Any>,
    file: File,
    scalingFactor: Double,
    targetDPI: Double
) {
    // lets-plot-image-export.jar might not be present in the classpath.
    val imageBytes: ByteArray = try {

        val format = when (val ext = file.extension.lowercase(Locale.getDefault())) {
            "png" -> PlotImageExport.Format.PNG
            "jpeg", "jpg" -> PlotImageExport.Format.JPEG()
            "tiff", "tif" -> PlotImageExport.Format.TIFF
            else -> throw java.lang.IllegalArgumentException("Unsupported format: $ext")
        }

        val image = PlotImageExport.buildImageFromRawSpecs(
            plotSpec = spec,
            format = format,
            scalingFactor = scalingFactor,
            targetDPI = targetDPI
        )
        image.bytes

    } catch (e: Throwable) {
        when (e) {
            is ClassNotFoundException,
            is NoClassDefFoundError ->
                throw IllegalStateException(
                    """
                    
                    Can't export plot to raster formats: ${e::class.simpleName} "${e.message}".
                    Please add "lets-plot-image-export-<version>.jar" to your classpath. 
                """.trimIndent()
                )
            else -> throw e
        }
    }

    file.createNewFile()
    file.writeBytes(imageBytes)
}