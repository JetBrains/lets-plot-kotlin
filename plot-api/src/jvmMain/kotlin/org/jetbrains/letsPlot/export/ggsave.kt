/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.export

import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.awt.plot.PlotSvgExport
import org.jetbrains.letsPlot.commons.geometry.DoubleVector
import org.jetbrains.letsPlot.core.util.PlotExportCommon
import org.jetbrains.letsPlot.core.util.PlotHtmlExport
import org.jetbrains.letsPlot.core.util.PlotHtmlHelper.scriptUrl
import org.jetbrains.letsPlot.intern.toSpec
import java.nio.file.Path
import java.util.*
import kotlin.io.path.*

private const val DEF_EXPORT_DIR = "lets-plot-images"

/**
 * Exports plot to a file.
 * Supported formats: SVG, HTML, PNG, JPEG, and TIFF.
 * Note: in some configurations raster formats might not be supported.
 *
 * If `path` is not specified, the output file will be saved in
 * `${user.dir}/lets-plot-images`.
 *
 * ## Notes
 *
 * Large plot dimensions without units require explicit unit specification.
 * When `w` or `h` value exceeds 20 without specifying units (e.g., `ggsave(p, 300, 400)`),
 * we ask to specify units explicitly:
 * `ggsave(p, 300, 400, unit="px")` or `ggsave(p, 3, 4, unit="in")`.
 *
 * The output format is inferred from the file extension.
 *
 * **For PNG, JPEG, and TIFF:**
 *
 * - If `w`, `h`, `unit`, and `dpi` are all specified:
 *
 *     - The plot's pixel size (default or set via [ggsize()][org.jetbrains.letsPlot.ggsize]) is ignored.
 *     - The output size is computed from the given dimensions and DPI.
 *
 *         - The plot is resized to fit the specified `w` x `h` area, which may affect the layout.
 *
 * - If only `dpi` is specified:
 *
 *     - The plot's pixel size (default or set via [ggsize()][org.jetbrains.letsPlot.ggsize]) is converted to inches assuming the standard display PPI of 96 PPI.
 *     - The output size is computed from this size and `dpi`.
 *
 *         - The plot maintains its aspect ratio, preserving layout, tick labels, and other visual elements.
 *         - Useful for printing - the plot will appear nearly the same size as on screen.
 *
 * - If `w` and `h` are not specified:
 *
 *     - The `scale` parameter is used to determine the output size.
 *
 *         - The plot maintains its aspect ratio, preserving layout, tick labels, and other visual elements.
 *         - Useful for generating high-resolution images suitable for publication.
 *
 * **For SVG:**
 *
 * - If `w`, `h`, and `unit` are specified:
 *
 *     - The plot's pixel size (default or set via [ggsize()][org.jetbrains.letsPlot.ggsize]) is ignored.
 *     - The output size is set from the given values.
 *
 * **For HTML:**
 *
 * - If `w` and `h` are specified:
 *
 *     - The plot's pixel size (default or set via [ggsize()][org.jetbrains.letsPlot.ggsize]) is ignored.
 *     - The output size is determined directly from the specified `w` and `h`, which are treated as pixel values.
 *
 * ## Examples
 *
 * - [export_to_file.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/export_to_file.ipynb)
 *
 * @param plot Plot to export.
 * @param filename Name of the file. Must include an extension matching
 *  one of the supported formats: `.svg`, `.html`/`.htm`, `.png`, `.jpeg`/`.jpg`, or `.tiff`/`.tif`
 * @param scale Scaling factor (only for raster formats). Default: 2.0
 * @param w Width of the output image in units.
 * @param h Height of the output image in units.
 * @param unit Unit of the output image. One of: `"in"`, `"cm"`, `"mm"`, `"px"`.
 *  Only applicable when exporting to SVG, PNG, JPG, or TIFF. For HTML, the unit is always `"px"` (pixels).
 *  Default:
 *  "px" (pixels) for SVG,
 *  "in" (inches) for the raster formats (PNG, JPG, and TIFF).
 * @param dpi Resolution in dots per inch.
 *  Only applicable when exporting to the raster formats: PNG, JPG, or TIFF.
 *  The default value depends on the `unit`:
 *
 *  - for `"px"` it is `96` (output image will have the same pixel size as `w` and `h` values)
 *  - for physical units (`"in"`, `"cm"`, `"mm"`) it is `300`.
 * @param path Path to a directory to save image files in.
 *  Default: `${user.dir}/lets-plot-images`
 *
 * @return Absolute pathname of the created file.
 *
 */
@Suppress("SpellCheckingInspection")
fun ggsave(
    plot: Figure,
    filename: String,
    scale: Number? = null,
    dpi: Number? = null,
    path: String? = null,
    w: Number? = null,
    h: Number? = null,
    unit: String? = null
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
    val sizeUnit = PlotExportCommon.SizeUnit.fromName(unit ?: "")
    val plotSize = toDoubleVector(w, h)

    when (ext) {
        "svg" -> {
            val svg = PlotSvgExport.buildSvgImageFromRawSpecs(
                spec,
                plotSize = plotSize,
                sizeUnit = sizeUnit ?: PlotExportCommon.SizeUnit.PX,
            )
            if (file.notExists()) {
                file.createFile()
            }
            file.writeText(svg)
        }

        "html", "htm" -> {
            val html = PlotHtmlExport.buildHtmlFromRawSpecs(
                spec,
                scriptUrl = scriptUrl(VersionChecker.letsPlotJsVersion),
                iFrame = true,
                plotSize = plotSize
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
                scalingFactor = scale,
                plotSize = plotSize,
                unit = sizeUnit,
                targetDPI = dpi
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


private fun toDoubleVector(x: Number?, y: Number?): DoubleVector? {
    return if (x != null && y != null) {
        DoubleVector(x.toDouble(), y.toDouble())
    } else {
        null
    }
}


private fun exportRasterImage(
    spec: MutableMap<String, Any>,
    file: Path,
    scalingFactor: Number? = null,
    plotSize: DoubleVector? = null,
    unit: PlotExportCommon.SizeUnit? = null,
    targetDPI: Number? = null
) {
    // As of lets-plot version 4.7.3
    // PlotImageExport uses java.awt.image.BufferedImage
    // Check if java.awt is present.
    try {
        Class.forName("java.awt.image.BufferedImage")
    } catch (e: ClassNotFoundException) {
        throw IllegalStateException(
            """
            
            Can't export plot to raster formats: 'java.awt.image.BufferedImage' is not available in this environment.
            Raster image export requires 'java.awt.image.BufferedImage' which is not present in Android JRE or headless environments.
        """.trimIndent()
        )
    }

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
            plotSize = plotSize,
            unit = unit,
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
                    A Gradle dependency example:
                    
                        implementation("org.jetbrains.lets-plot:lets-plot-image-export:<version>")
                    
                    where <version> is the version of the Lets-Plot Multiplatform artifact you are using.
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
