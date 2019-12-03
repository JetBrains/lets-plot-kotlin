package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom.abline
import jetbrains.letsPlot.Pos.identity
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.ABLineAesthetics
import jetbrains.letsPlot.intern.layer.geom.ABLineMapping

@Suppress("ClassName")
/**
 * Add straight lines to a plot specified by slope and intercept.
 * @param data dictionary or pandas DataFrame, optional.
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to [lets_plot][jetbrains.letsPlot.lets_plot].
 * @param stat string, optional.
 *     The statistical transformation to use on the data for this layer, as a string. Supported transformations:
 *     "identity" (leaves the data unchanged), "count" (counts number of points with same x-axis coordinate),
 *     "bin" (counts number of points with x-axis coordinate in the same bin), "smooth" (performs smoothing -
 *     linear default)
 * @param position string, optional.
 *     Position adjustment, either as a string ("identity", "stack", "dodge", ...), or the result of a call to a
 *     position adjustment function.
 * @param slope
 *     The line slope.
 * @param intercept
 *      The value of y at the point where the line crosses the y axis.
 * @param alpha transparency level of a point
 *     Understands numbers between 0 and 1.
 * @param color (colour) color of a geometry.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param linetype type of the line.
 *     Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *     5 = "longdash", 6 = "twodash".
 * @param size line width.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geom_abline(
    data: Any? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = identity,
    show_legend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val slope: Double? = null,
    override val intercept: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Double? = null,
    mapping: ABLineMapping.() -> Unit = {}

) : ABLineAesthetics,
    LayerBase(
        mapping = ABLineMapping().apply(mapping).seal(),
        data = data,
        geom = abline(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )

