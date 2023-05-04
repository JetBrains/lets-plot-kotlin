/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.LayerBase
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.StatOptions
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.geom.QQLineAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.QQLineMapping
import org.jetbrains.letsPlot.intern.layer.stat.QQLineStatParameters
import org.jetbrains.letsPlot.intern.layer.stat.QQStatAesthetics
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Displays quantile-quantile fitting line.
 *
 * ## Examples
 *
 * - [qq_plots.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/qq_plots.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat default = `Stat.qqLine()`. The statistical transformation to use on the data for this layer.
 *  Supported transformations: `Stat.identity`, `Stat.bin()`, `Stat.count()`, etc. see [Stat][org.jetbrains.letsPlot.Stat].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc. see 
 *  [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/)
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/sampling.md).
 * @param tooltips Result of the call to the layerTooltips() function.
 *  Specifies appearance, style and content.
 * @param distribution default = "norm".
 *  Distribution function to use: "norm", "uniform", "t", "gamma", "exp", "chi2".
 * @param dParams Additional parameters passed on to distribution function.
 *  - If `distribution` is "norm" then `dParams` is a pair (mean, std) (default = listOf(0.0, 1.0)).
 *  - If `distribution` is "uniform" then `dParams` is a pair (a, b) (default = listOf(0.0, 1.0)).
 *  - If `distribution` is "t" then `dParams` is an integer number (d) (default = listOf(1)).
 *  - If `distribution` is "gamma" then `dParams` is a pair (alpha, beta) (default = listOf(1.0, 1.0)).
 *  - If `distribution` is "exp" then `dParams` is a float number (lambda) (default = listOf(1.0)).
 *  - If `distribution` is "chi2" then `dParams` is an integer number (k) (default = listOf(1)).
 * @param quantiles default = [0.25, 0.75].
 *  Pair of quantiles to use when fitting the Q-Q line.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param sample Y-axis value.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  String in the following formats: 
 *  - RGB/RGBA (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red") 
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param linetype Type of the line.
 *  Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *  5 = "longdash", 6 = "twodash".
 * @param size Line width.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomQQLine(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.qqLine(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val sample: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val distribution: String? = null,
    override val dParams: List<Number>? = null,
    override val quantiles: Pair<Number, Number>? = null,
    override val colorBy: String? = null,
    mapping: QQLineMapping.() -> Unit = {}
) : QQLineAesthetics,
    QQStatAesthetics,
    QQLineStatParameters,
    WithColorOption,
    LayerBase(
        mapping = QQLineMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.qqLine(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {

    override fun seal(): Options {
        return super<QQLineAesthetics>.seal() +
                super<QQStatAesthetics>.seal() +
                super<QQLineStatParameters>.seal() +
                super<WithColorOption>.seal()
    }
}