package org.jetbrains.letsPlot.stat

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.PointRangeAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.PointRangeMapping
import org.jetbrains.letsPlot.intern.layer.stat.SummaryBinStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Displays a distribution by dividing variable mapped to x-axis into bins
 * and applying aggregation functions to each bin.
 *
 * ## Examples
 *
 * - [stat_summary_bin.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.4.2/stat_summary_bin.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param geom The geometry to display the binned summary stat for this layer, default is `Geom.pointrange()`,
 *  see [Geom][org.jetbrains.letsPlot.Geom].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc. see
 *  [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/sampling.md).
 * @param tooltips Result of the call to the `layerTooltips()` function.
 *  Specifies appearance, style and content.
 * @param orientation Specifies the axis that the layer's stat and geom should run along, default = "x".
 *  Possible values: "x", "y".
 * @param fn default = "mean" ("count", "sum", "mean", "median", "min", "max", "lq", "mq", "uq").
 *  Name of function computing stat variable `..y..`.
 *  Names "lq", "mq", "uq" correspond to lower, middle and upper quantiles, default = listOf(0.25, 0.5, 0.75).
 * @param fnMin default = "min" ("count", "sum", "mean", "median", "min", "max", "lq", "mq", "uq").
 *  Name of function computing stat variable `..ymin..`.
 *  Names "lq", "mq", "uq" correspond to lower, middle and upper quantiles, default = listOf(0.25, 0.5, 0.75).
 * @param fnMax default = "max" ("count", "sum", "mean", "median", "min", "max", "lq", "mq", "uq").
 *  Name of function computing stat variable `..ymax..`.
 *  Names "lq", "mq", "uq" correspond to lower, middle and upper quantiles, default = listOf(0.25, 0.5, 0.75).
 * @param quantiles default = listOf(0.25, 0.5, 0.75).
 *  A list of probabilities defining the quantile functions "lq", "mq" and "uq".
 *  Must contain exactly 3 values between 0 and 1.
 * @param bins Number of bins. Overridden by `binwidth`.
 * @param binWidth The width of the bins. The default is to use bin widths that cover
 *  the range of the data. You should always override this value,
 *  exploring multiple widths to find the best to illustrate the stories in your data.
 * @param center Specifies x-value to align bin centers to.
 * @param boundary Specifies x-value to align bin boundary (i.e. point between bins) to.
 * @param x X-axis coordinates.
 * @param y Position of mid-point.
 * @param ymin Lower bound for error bar.
 * @param ymax Upper bound for error bar.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  String in the following formats:
 *  - RGB/RGBA (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red")
 *  - role name ("pen", "paper" or "brush")
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param fill Fill color.
 *  String in the following formats:
 *  - RGB/RGBA (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red")
 *  - role name ("pen", "paper" or "brush")
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param size Lines width, size of mid-point.
 * @param stroke Width of the shape border. Applied only to the shapes having border.
 * @param linewidth Line width.
 * @param linetype Type of the line of border.
 *  Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *  5 = "longdash", 6 = "twodash"
 * @param shape Shape of the mid-point.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class statSummaryBin(
    data: Map<*, *>? = null,
    geom: GeomOptions = Geom.pointrange(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    orientation: String? = null,
    override val x: Any? = null,
    override val y: Any? = null,
    override val ymin: Any? = null,
    override val ymax: Any? = null,
    override val alpha: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val shape: Any? = null,
    override val size: Any? = null,
    override val stroke: Any? = null,
    override val linewidth: Any? = null,
    override val bins: Int? = null,
    override val binWidth: Number? = null,
    override val center: Number? = null,
    override val boundary: Number? = null,
    override val fn: String? = null,
    override val fnMin: String? = null,
    override val fnMax: String? = null,
    override val quantiles: List<Number>? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: PointRangeMapping.() -> Unit = {},
) : PointRangeAesthetics,
    SummaryBinStatParameters,
    WithColorOption,
    WithFillOption,
    Layer(
        mapping = PointRangeMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = Stat.summaryBin(),
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips,
        orientation = orientation
    ) {

    override fun seal(): Options {
        return super<PointRangeAesthetics>.seal() +
                super<SummaryBinStatParameters>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}