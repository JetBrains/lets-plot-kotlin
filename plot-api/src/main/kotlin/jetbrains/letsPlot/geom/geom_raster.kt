package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom.raster
import jetbrains.letsPlot.Pos.identity
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.RasterAesthetics
import jetbrains.letsPlot.intern.layer.geom.RasterMapping

@Suppress("ClassName")
/**
 * Rectangles with x, y values mapped to center.
 * Much faster than geom_tile but doesn't support width/height and color.
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
 * @param x x-axis coordinates of the center of rectangles.
 * @param y coordinates of the center of rectangles.
 * @param alpha transparency level of a layer.
 * @param fill color of geometry filling.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geom_raster(
    data: Any? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = identity,
    show_legend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val alpha: Double? = null,
    override val fill: Any? = null,
    mapping: RasterMapping.() -> Unit = {}

) : RasterAesthetics,
    LayerBase(
        mapping = RasterMapping().apply(mapping).seal(),
        data = data,
        geom = raster(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )


