/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.PathAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.PathMapping
import org.jetbrains.letsPlot.intern.layer.stat.QQ2LineStatParameters
import org.jetbrains.letsPlot.intern.layer.stat.QQ2StatAesthetics
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Displays quantile-quantile fitting line.
 *
 * ## Notes
 *
 * The Q-Q line plot is used for comparing two probability distributions
 * by plotting line passed through the pair of corresponding quantiles.
 *
 * ## Examples
 *
 * - [qq_plots.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/qq_plots.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat default = `Stat.qq2Line()`. The statistical transformation to use on the data for this layer.
 *  Supported transformations: `Stat.identity`, `Stat.bin()`, `Stat.count()`, etc. see [Stat][org.jetbrains.letsPlot.Stat].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc. see
 *  [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param manualKey String or result of the call to the `layerKey()` function.
 *  The key to show in the manual legend. Specifies the text for the legend label or advanced settings using the `layerKey()` function.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.html](https://lets-plot.org/kotlin/sampling.html).
 * @param tooltips Result of the call to the `layerTooltips()` function.
 *  Specifies appearance, style and content.
 * @param quantiles default = [0.25, 0.75].
 *  Pair of quantiles to use when fitting the Q-Q line.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param linetype Type of the line.
 *  Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *  5 = "longdash", 6 = "twodash".
 *  For more info see: [aesthetics.html#line-types](https://lets-plot.org/kotlin/aesthetics.html#line-types).
 * @param size Line width.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomQQ2Line(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.qq2Line(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    manualKey: Any? = null,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val quantiles: Pair<Number, Number>? = null,
    override val colorBy: String? = null,
    mapping: PathMapping.() -> Unit = {}
) : PathAesthetics,
    QQ2StatAesthetics,
    QQ2LineStatParameters,
    WithColorOption,
    Layer(
        mapping = PathMapping().apply(mapping).seal(),
        data = data,
        geom = GeomOptions(GeomKind.Q_Q_2_LINE),
        stat = stat,
        position = position,
        showLegend = showLegend,
        manualKey = manualKey,
        sampling = sampling,
        tooltips = tooltips
    ) {

    override fun seal(): Options {
        return super<PathAesthetics>.seal() +
                super<QQ2StatAesthetics>.seal() +
                super<QQ2LineStatParameters>.seal() +
                super<WithColorOption>.seal()
    }
}