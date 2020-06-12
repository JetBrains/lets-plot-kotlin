/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat.identity
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.TileAesthetics
import jetbrains.letsPlot.intern.layer.geom.TileMapping

@Suppress("ClassName")
/**
 * Rectangles with x, y values mapped to center
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
 * @param y y-axis coordinates of the center of rectangles.
 * @param width width of a tile.
 * @param height height of a tile.
 * @param alpha transparency level of a layer.
 * @param color (colour) color of a geometry lines.
 * @param fill color of geometry filling.
 * @param size lines width.
 * @param linetype type of the line.
 *     Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *     5 = "longdash", 6 = "twodash".
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */
class geom_tile(
    data: Map<*, *>? = null,
    stat: StatOptions = identity,
    position: PosOptions = Pos.identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val width: Double? = null,
    override val height: Double? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Double? = null,
    mapping: TileMapping.() -> Unit = {}

) : TileAesthetics,
    LayerBase(
        mapping = TileMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.tile(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling
    )


