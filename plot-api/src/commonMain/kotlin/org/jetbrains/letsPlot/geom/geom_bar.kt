/*
 * Copyright (c) 2021. JetBrains s.r.o.
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
import org.jetbrains.letsPlot.intern.layer.WithFillOption
import org.jetbrains.letsPlot.intern.layer.geom.BarAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.BarMapping
import org.jetbrains.letsPlot.intern.layer.stat.CountStatAesthetics
import org.jetbrains.letsPlot.pos.positionStack
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Displays a bar chart which makes the height of the bar proportional to the number of observed variable values, mapped to x-axis.
 *
 * ## Examples
 *
 * - [error_bars.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/error_bars.ipynb)
 *
 * - [stat_count_2d.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/stat_count_2d.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat default = `Stat.count()`. The statistical transformation to use on the data for this layer.
 *  Supported transformations: `Stat.identity`, `Stat.bin()`, `Stat.count()`, etc. see [Stat][org.jetbrains.letsPlot.Stat].
 * @param position default = `positionStack()`. Position adjustment: `positionIdentity`, 
 *  `positionStack()`, `positionDodge()`, etc. see [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/sampling.md).
 * @param tooltips Result of the call to the `layerTooltips()` function.
 *  Specifies appearance, style and content.
 * @param orientation Specifies the axis that the layer's stat and geom should run along, default = "x".
 *  Possible values: "x", "y".
 * @param x X-axis value (this value will produce cases or bins for bars).
 * @param y Y-axis value (this value will be used to multiply the case's or bin's counts).
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of the geometry.
 *  String in the following formats: 
 *  - RGB/RGBS (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red") 
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param fill Color of geometry filling.
 *  String in the following formats: 
 *  - RGB/RGBS (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red") 
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param width Width of a bar.
 * @param size Defines bar line width.
 * @param weight Used by `Stat.count()` stat to compute weighted sum instead of simple count.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomBar(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.count(),
    position: PosOptions = positionStack(),
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    orientation: String? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val width: Number? = null,
    override val size: Number? = null,
    override val weight: Number? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: BarMapping.() -> Unit = {}

) : BarAesthetics,
    CountStatAesthetics,
    WithColorOption,
    WithFillOption,
    LayerBase(
        mapping = BarMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.bar(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips,
        orientation = orientation
    ) {
    override fun seal(): Options {
        return super<BarAesthetics>.seal() +
                super<CountStatAesthetics>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }
}


