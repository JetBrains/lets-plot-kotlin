/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.export

import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.awt.plot.PlotSvgExport
import org.jetbrains.letsPlot.core.util.PlotHtmlExport
import org.jetbrains.letsPlot.core.util.PlotHtmlHelper.scriptUrl
import org.jetbrains.letsPlot.intern.toSpec
import java.lang.Double.NaN
import java.nio.file.Path
import java.util.*
import kotlin.io.path.*

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
 *      Default: no metadata is stored.
 * @param path Path to a directory to save image files in.
 *      Default: `${user.dir}/lets-plot-images`
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

    val dir = path?.let { Path(path) } ?: Path(System.getProperty("user.dir"), DEF_EXPORT_DIR)
    dir.createDirectories()
    val file = dir.resolve(filename)

    val spec: MutableMap<String, Any> = plot.toSpec()

    when (ext) {
        "svg" -> {
            val svg = PlotSvgExport.buildSvgImageFromRawSpecs(spec)
            if (file.notExists()) {
                file.createFile()
            }
            file.writeText(svg)
        }

        "html", "htm" -> {
            val html = PlotHtmlExport.buildHtmlFromRawSpecs(
                spec,
                iFrame = true,
                scriptUrl = scriptUrl(VersionChecker.letsPlotJsVersion)
            )
            if (file.notExists()) {
                file.createFile()
            }
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

    return file.toRealPath().toString()
}

private fun exportRasterImage(
    spec: MutableMap<String, Any>,
    file: Path,
    scalingFactor: Double,
    targetDPI: Double
) {
    // lets-plot-image-export.jar might not be present in the classpath.
    val imageBytes: ByteArray = try {

        val format = when (val ext = file.extension.lowercase(Locale.getDefault())) {
            "png" -> org.jetbrains.letsPlot.core.plot.export.PlotImageExport.Format.PNG
            "jpeg", "jpg" -> org.jetbrains.letsPlot.core.plot.export.PlotImageExport.Format.JPEG()
            "tiff", "tif" -> org.jetbrains.letsPlot.core.plot.export.PlotImageExport.Format.TIFF
            else -> throw java.lang.IllegalArgumentException("Unsupported format: $ext")
        }

        val image = org.jetbrains.letsPlot.core.plot.export.PlotImageExport.buildImageFromRawSpecs(
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

    if (file.notExists()) {
        file.createFile()
    }
    file.writeBytes(imageBytes)
}
