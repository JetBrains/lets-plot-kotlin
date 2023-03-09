/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.AreaRidgesAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.AreaRidgesMapping
import org.jetbrains.letsPlot.intern.layer.geom.AreaRidgesParameters
import org.jetbrains.letsPlot.intern.layer.stat.DensityRidgesStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.DensityRidgesStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName", "SpellCheckingInspection")
/**
 * Plots the sum of the `y` and `height` aesthetics versus `x`. Heights of the ridges are relatively scaled.
 *
 * ## Examples
 *
 * - [ridgeline_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/ridgeline_plot.ipynb)
 *
 * - [quantile_parameters.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.3.0/quantile_parameters.ipynb)
 *
 * @param data
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat default="densityridges".
 *     The statistical transformation to use on the data for this layer. Supported transformations:
 *     "identity" (leaves the data unchanged), "densityridges" (computes and draws kernel density estimate for each ridge).
 * @param position
 *     Position adjustment: Pos.identity, Pos.stack,  etc. - see [letsPlot][org.jetbrains.letsPlot.Pos].
 * @param showLegend default=true.
 *     false - do not show legend for this layer.
 * @param tooltips result of the call to the layerTooltips() function.
 *     Specifies appearance, style and content.
 * @param x x-axis coordinates.
 * @param y y-axis coordinates.
 * @param height height of the ridge. Assumed to be between 0 and 1, though this is not required.
 * @param quantile quantile values to draw quantile lines and fill quantiles of the geometry by color.
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
 * @param weight used by "densityridges" stat to compute weighted density.
 * @param scale number, default=1.0
 *     A multiplicative factor applied to height aesthetic.
 *     If `scale = 1.0`, the heights of a ridges are automatically scaled
 *     such that the ridge with `height = 1.0` just touches the one above.
 * @param minHeight number, default=0.0
 *     A height cutoff on the drawn ridges.
 *     All values that fall below this cutoff will be removed.
 * @param quantileLines boolean, default=false
 *     Show the quantile lines.
 * @param tailsCutoff number
 *     Extend domain of each ridge on `tailsCutoff * bw` if `trim=false`.
 *    `tailsCutoff=null` (default) extends domain to maximum (domain overall ridges).
 * @param quantiles list of numbers, default=[0.25, 0.5, 0.75]
 *     Draw horizontal lines at the given quantiles of the density estimate.
 * @param bw string or double.
 *     The method (or exact value) of bandwidth. Either a string (choose among "nrd0" and "nrd") or a double.
 * @param kernel
 *     The kernel we use to calculate the density function. Choose among "gaussian", "cosine", "optcosine",
 *     "rectangular" (or "uniform"), "triangular", "biweight" (or "quartic"), "epanechikov" (or "parabolic")
 * @param n
 *     The number of sampled points for plotting the function.
 * @param trim boolean, default=false
 *   Trim the tails of the ridges to the range of the data.
 * @param adjust
 *     Adjust the value of bandwidth by multiplying it. Changes how smooth the frequency curve is.
 * @param fullScanMax
 *     Maximum size of data to use density computation with 'full scan'.
 *     For bigger data, less accurate but more efficient density computation is applied.
 * @param colorBy String, {"fill", "color", "paint_a", "paint_b", "paint_c"}, default = "color".
 *  Defines the color aesthetic for the geometry.
 * @param fillBy String, {"fill", "color", "paint_a", "paint_b", "paint_c"}, default = "fill".
 *  Defines the fill aesthetic for the geometry.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geomAreaRidges(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.densityRidges(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val height: Number? = null,
    override val quantile: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val weight: Number? = null,
    override val scale: Number? = null,
    override val minHeight: Number? = null,
    override val quantileLines: Boolean? = null,
    override val tailsCutoff: Number? = null,
    override val quantiles: List<Number>? = null,
    override val bw: Any? = null,
    override val kernel: String? = null,
    override val n: Int? = null,
    override val trim: Boolean? = null,
    override val adjust: Number? = null,
    override val fullScanMax: Int? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: AreaRidgesMapping.() -> Unit = {}
) : AreaRidgesAesthetics,
    AreaRidgesParameters,
    DensityRidgesStatAesthetics,
    DensityRidgesStatParameters,
    WithColorOption,
    WithFillOption,
    LayerBase(
        mapping = AreaRidgesMapping().apply(mapping).seal(),
        data = data,
        geom = GeomOptions(GeomKind.AREA_RIDGES),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {

    override fun seal() = super<AreaRidgesAesthetics>.seal() +
            super<AreaRidgesParameters>.seal() +
            super<DensityRidgesStatAesthetics>.seal() +
            super<DensityRidgesStatParameters>.seal() +
            super<WithColorOption>.seal() +
            super<WithFillOption>.seal()
}