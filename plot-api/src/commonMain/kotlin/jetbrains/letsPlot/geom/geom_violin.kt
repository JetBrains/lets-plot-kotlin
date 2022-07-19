/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.GeomKind
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.ViolinAesthetics
import jetbrains.letsPlot.intern.layer.geom.ViolinMapping
import jetbrains.letsPlot.intern.layer.stat.YDensityStatAesthetics
import jetbrains.letsPlot.intern.layer.stat.YDensityStatParameters
import jetbrains.letsPlot.positionDodge
import jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName", "SpellCheckingInspection")
/**
 * A violin plot is a mirrored density plot with an additional grouping as for a boxplot.
 *
 * ## Examples
 *
 * - [geom_violin.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_violin.ipynb)
 *
 * @param data
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to [letsPlot][jetbrains.letsPlot.letsPlot].
 * @param stat default="ydensity".
 *     The statistical transformation to use on the data for this layer.
 * @param position
 *     Position adjustment: Pos.identity, Pos.stack,  etc. - see [letsPlot][jetbrains.letsPlot.Pos].
 * @param showLegend default=True.
 *      False - do not show legend for this layer.
 * @param tooltips result of the call to the layerTooltips() function.
 *     Specifies appearance, style and content.
 * @param orientation Specifies the axis that the layer' stat and geom should run along.
 *     Possible values: 'x' (default), 'y'.
 * @param drawQuantiles list of float.
 *     Draw horizontal lines at the given quantiles of the density estimate.
 * @param x x-axis coordinates.
 * @param y y-axis coordinates.
 * @param violinWidth density scaled for the violin plot, according to area, counts or to a constant maximum width.
 * @param alpha transparency level of a layer
 *     Understands numbers between 0 and 1.
 * @param color (colour) color of a geometry lines.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param fill color of geometry filling.
 * @param linetype type of the line of tile's border.
 *     Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *     5 = "longdash", 6 = "twodash".
 * @param size lines width.
 *     Defines line width
 * @param width width of violin bounding box
 * @param weight used by "ydensity" stat to compute weighted density.
 * @param scale
 *     If "area" (default), all violins have the same area.
 *     If "count", areas are scaled proportionally to the number of observations.
 *     If "width", all violins have the same maximum width.
 * @param bw string or double.
 *     The method (or exact value) of bandwidth. Either a string (choose among "nrd0" and "nrd") or a double.
 * @param kernel
 *     The kernel we use to calculate the density function. Choose among "gaussian", "cosine", "optcosine",
 *     "rectangular" (or "uniform"), "triangular", "biweight" (or "quartic"), "epanechikov" (or "parabolic")
 * @param n
 *     The number of sampled points for plotting the function.
 * @param trim default=true.
 *     Trim the tails of the violins to the range of the data.
 * @param adjust
 *     Adjust the value of bandwidth by multiplying it. Changes how smooth the frequency curve is.
 * @param fullScanMax
 *     Maximum size of data to use density computation with 'full scan'.
 *     For bigger data, less accurate but more efficient density computation is applied.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geomViolin(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.yDensity(),
    // ToDo: fix in lets-plot: see comments in `geomBoxplot`.
//    position: PosOptions = Pos.dodge,
    position: PosOptions = positionDodge(0.95), // tmp fix
    showLegend: Boolean = true,
    sampling: jetbrains.letsPlot.intern.layer.SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    orientation: String? = null,
    private val drawQuantiles: Any? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val violinWidth: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val width: Number? = null,
    override val weight: Number? = null,
    override val scale: String? = null,
    override val bw: Any? = null,
    override val kernel: String? = null,
    override val n: Int? = null,
    override val trim: Boolean? = null,
    override val adjust: Number? = null,
    override val fullScanMax: Int? = null,
    mapping: ViolinMapping.() -> Unit = {}
) : ViolinAesthetics,
    YDensityStatAesthetics,
    YDensityStatParameters,
    jetbrains.letsPlot.intern.layer.LayerBase(
        mapping = ViolinMapping().apply(mapping).seal(),
        data = data,
        geom = jetbrains.letsPlot.intern.layer.GeomOptions(GeomKind.VIOLIN),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips,
        orientation = orientation
    ) {

    override fun seal() = super<ViolinAesthetics>.seal() +
            super<YDensityStatAesthetics>.seal() +
            super<YDensityStatParameters>.seal() +
            Options.of("draw_quantiles" to drawQuantiles)
}
