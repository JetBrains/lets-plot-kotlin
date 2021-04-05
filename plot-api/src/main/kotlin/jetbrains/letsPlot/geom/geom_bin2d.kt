/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.tooltips.TooltipOptions
import jetbrains.letsPlot.intern.layer.geom.Bin2dMapping
import jetbrains.letsPlot.intern.layer.geom.TileAesthetics
import jetbrains.letsPlot.intern.layer.stat.Bin2dStatAesthetics
import jetbrains.letsPlot.intern.layer.stat.Bin2dStatParameters


@Suppress("ClassName")
/**
 * Divide the plane into a grid and color the bins by the count of cases in them.
 *
 * @param data dictionary or pandas DataFrame, optional.
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to [letsPlot][jetbrains.letsPlot.letsPlot].
 * @param stat string, default: "bin".
 *     The statistical transformation to use on the data for this layer.
 * @param position string, optional.
 *     Position adjustment, either as a string ("identity", "stack", "dodge", ...), or the result of a call to a
 *     position adjustment function.
 * @param tooltips result of the call to the layerTooltips() function.
 *     Specifies appearance, style and content.
 * @param bins pair of numbers, default: (30,30)
 *     Number of bins in both directions, vertical and horizontal.  Overridden by binwidth.
 * @param binWidth pair of numbers, optional
 *     The width of the bins in both directions, vertical and horizontal. Overrides `bins`.
 *     The default is to use bin widths that cover the entire range of the data.
 * @param drop : bool, optional, default: True
 *     Specifies whether to remove all bins with 0 counts.
 * @param x x-axis value.
 * @param y y-axis value.
 * @param width width of a tile.
 * @param height height of a tile.
 * @param alpha number in `[0..1`]
 *     Transparency level of a layer.
 * @param color (colour) color of a geometry lines.
 * @param fill color of geometry filling.
 * @param linetype type of the line.
 *     Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *     5 = "longdash", 6 = "twodash".
 * @param size lines width.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geomBin2d(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.bin2d(),
    position: PosOptions = Pos.identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val width: Double? = null,
    override val height: Double? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val weight: Any? = null,
    override val bins: Pair<Int, Int>? = null,
    override val binWidth: Pair<Number?, Number?>? = null,
    override val drop: Boolean? = null,
    mapping: Bin2dMapping.() -> Unit = {}
) : TileAesthetics,
    Bin2dStatAesthetics,
    Bin2dStatParameters,
    LayerBase(
        mapping = Bin2dMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.tile(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super<TileAesthetics>.seal() +
                super<Bin2dStatAesthetics>.seal() +
                super<Bin2dStatParameters>.seal()
    }
}