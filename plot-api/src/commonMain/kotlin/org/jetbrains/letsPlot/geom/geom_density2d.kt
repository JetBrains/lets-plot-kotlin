/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.Density2dMapping
import org.jetbrains.letsPlot.intern.layer.geom.PathAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.ContourStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.Density2dStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.Density2dStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Displays density function contour.
 *
 * By default, this geom uses `coordFixed()`.
 * However, this may not be the best choice when the values on the X/Y axis have significantly different magnitudes.
 * In such cases, try using `coordCartesian()`.
 *
 * ## Notes
 *
 * Computed variables:
 *
 * - ..group.. : number of density estimate contour line.
 * - ..level.. : calculated value of the density estimate for given contour line.
 *
 * ## Examples
 *
 * - [density_2d.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/density_2d.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat default = `Stat.density2D()`. The statistical transformation to use on the data for this layer.
 *  Supported transformations: `Stat.identity`, `Stat.bin()`, `Stat.count()`, etc. see [Stat][org.jetbrains.letsPlot.Stat].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc.
 *  see [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param inheritAes default = true.
 *  false - do not combine the layer aesthetic mappings with the plot shared mappings.
 * @param manualKey String or result of the call to the `layerKey()` function.
 *  The key to show in the manual legend. Specifies the text for the legend label or advanced settings using the `layerKey()` function.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.html](https://lets-plot.org/kotlin/sampling.html).
 * @param tooltips Result of the call to the `layerTooltips()` function.
 *  Specifies appearance, style and content.
 * @param kernel The kernel used to calculate the density function. Choose among "gaussian", "cosine", "optcosine",
 *  "rectangular" (or "uniform"), "triangular", "biweight" (or "quartic"), "epanechikov" (or "parabolic").
 * @param bw The method (or exact value) of bandwidth. Either a String (choose among "nrd0" and "nrd"),
 *  or a Double array of length 2.
 * @param n The number of sampled points for plotting the function (on x and y direction correspondingly).
 * @param adjust Adjusts the value of bandwidth by multiplying it. Changes how smooth the frequency curve is.
 * @param contour If true, contour the results of the 2d density estimation.
 * @param bins Number of levels.
 * @param binWidth Distance between levels.
 * @param weight Used by density stat to compute weighted density.
 * @param x X-axis coordinates of the center of rectangles, forming a tessellation.
 * @param y Y-axis coordinates of the center of rectangles, forming a tessellation.
 * @param z Value at point (x, y).
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype The type of the line.
 *  Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *  5 = "longdash", 6 = "twodash".
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Line width.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomDensity2D(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.density2D(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    inheritAes: Boolean? = null,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val z: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val weight: Number? = null,
    override val bw: Any? = null,
    override val kernel: String? = null,
    override val n: Int? = null,
    override val adjust: Number? = null,
    override val contour: Boolean? = null,
    override val bins: Int? = null,
    override val binWidth: Number? = null,
    override val colorBy: String? = null,
    mapping: Density2dMapping.() -> Unit = {}
) : PathAesthetics,
    ContourStatAesthetics,
    Density2dStatAesthetics,
    Density2dStatParameters,
    WithColorOption,
    Layer(
        mapping = Density2dMapping().apply(mapping).seal(),
        data = data,
        geom = GeomOptions(GeomKind.DENSITY2D),
        stat = stat,
        position = position,
        showLegend = showLegend,
        inheritAes = inheritAes,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super<PathAesthetics>.seal() +
                super<ContourStatAesthetics>.seal() +
                super<Density2dStatAesthetics>.seal() +
                super<Density2dStatParameters>.seal() +
                super<WithColorOption>.seal()
    }
}