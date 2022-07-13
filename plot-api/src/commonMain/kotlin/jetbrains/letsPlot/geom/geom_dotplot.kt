/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.GeomKind
import jetbrains.letsPlot.intern.layer.*
import jetbrains.letsPlot.intern.layer.geom.DotplotAesthetics
import jetbrains.letsPlot.intern.layer.geom.DotplotMapping
import jetbrains.letsPlot.intern.layer.geom.DotplotParameters
import jetbrains.letsPlot.intern.layer.stat.DotplotStatAesthetics
import jetbrains.letsPlot.intern.layer.stat.DotplotStatParameters
import jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName", "SpellCheckingInspection")
/**
 * 
 * Dotplot represents individual observations in a batch of data with circular dots.
 * The diameter of a dot corresponds to the maximum width or bin width, depending on the binning algorithm.
 *
 * ## Examples
 *
 * - [geom_dotplot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_dotplot.ipynb)
 *
 * @param data
 *      The data to be displayed in this layer. If None, the default, the data
 *      is inherited from the plot data as specified in the call to [letsPlot][jetbrains.letsPlot.letsPlot].
 * @param stat default: Stat.dotplot().
 *      The statistical transformation to use on the data for this layer.
 *      The only other 'stat' supported by 'dotplot' is `Stat.identity`.
 * @param showLegend default=True.
 *      False - do not show legend for this layer.
 * @param tooltips result of the call to the layerTooltips() function.
 *      Specifies appearance, style and content.
 * @param x x-axis coordinates.
 * @param bins When method is "histodot", this specifies number of bins (default=30). Overridden by `binWidth`.
 * @param center When method is "histodot", this specifies x-value to align bin centers to.
 * @param boundary When method is "histodot", this specifies x-value to align bin boundary
 *      (i.e. point between bins) to.
 * @param method default: "dotdensity"
 *      Use "dotdensity" for dot-density binning,
 *      or "histodot" for fixed bin widths (like in geom_histogram).
 * @param binWidth
 *      When method is "dotdensity", this specifies maximum bin width.
 *      When method is "histodot", this specifies bin width.
 * @param stackDir Which direction to stack the dots.
 *      Values: "up", "down", "center", "centerwhole". Default: "up".
 * @param stackRatio Default: 1.0
 *      How close to stack the dots.
 *      Use smaller values for closer, overlapping dots.
 * @param dotSize Default: 1.0
 *      The diameter of the dots relative to binwidth.
 * @param stackGroups Whether dots should be stacked across groups.
 *      In effect is replacemets for option `position = Pos.stack`.
 *      Note: unlike other geoms, 'dotplot' doesn't have 'position' option.
 *
 * @param alpha transparency level of a layer
 *     Understands numbers between 0 and 1.
 * @param color (colour) color of a geometry lines.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param fill color of geometry filling.
 * @param size lines width.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geomDotplot(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.dotplot(),
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val x: Number? = null,
    override val bins: Int? = null,
    override val center: Number? = null,
    override val boundary: Number? = null,
    override val method: String? = null,
    override var binWidth: Number? = null,
    override var stackSize: Number? = null,
    override val stackDir: String? = null,
    override val stackRatio: Number? = null,
    override val dotSize: Number? = null,
    override val stackGroups: Boolean? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val size: Number? = null,
    mapping: DotplotMapping.() -> Unit = {},
) : DotplotAesthetics,
    DotplotParameters,
    DotplotStatAesthetics,
    DotplotStatParameters,
    LayerBase(
        mapping = DotplotMapping().apply(mapping).seal(),
        data = data,
        geom = GeomOptions(GeomKind.DOTPLOT),
        stat = stat,
        position = Pos.identity,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {

    override fun seal() = super<DotplotAesthetics>.seal() +
            super<DotplotParameters>.seal() +
            super<DotplotStatAesthetics>.seal() +
            super<DotplotStatParameters>.seal()
}
