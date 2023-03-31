/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom.raster
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.layer.LayerBase
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.StatOptions
import org.jetbrains.letsPlot.intern.layer.WithFillOption
import org.jetbrains.letsPlot.intern.layer.geom.RasterAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.RasterMapping
import org.jetbrains.letsPlot.pos.positionIdentity

@Suppress("ClassName")
/**
 * Displays rectangles defined by the center of the tile (x, y).
 * This is a high performance special function for same-sized tiles.
 * Much faster than `geomTile()` but doesn't support width/height and color.
 *
 * ## Examples
 *
 * - [algebraic_curve.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/algebraic_curve.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat The statistical transformation to use on the data for this layer.
 *  Supported transformations: `Stat.identity`, `Stat.bin()`, `Stat.count()`, etc. see [Stat][org.jetbrains.letsPlot.Stat].
 * @param position Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc. see 
 *  [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone` .
 *  For more info see [sampling.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/sampling.md).
 * @param x X-axis coordinates of the center of rectangles.
 * @param y Coordinates of the center of rectangles.
 * @param alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @param fill Color of geometry filling.
 *  String in the following formats: 
 *  - RGB/RGBS (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red") 
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomRaster(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val alpha: Number? = null,
    override val fill: Any? = null,
    override val fillBy: String? = null,
    mapping: RasterMapping.() -> Unit = {}

) : RasterAesthetics,
    WithFillOption,
    LayerBase(
        mapping = RasterMapping().apply(mapping).seal(),
        data = data,
        geom = raster(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {
    override fun seal() = super<RasterAesthetics>.seal() +
            super<WithFillOption>.seal()
}

