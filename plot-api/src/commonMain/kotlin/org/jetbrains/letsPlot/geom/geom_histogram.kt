/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.layer.LayerBase
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.StatOptions
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.WithFillOption
import org.jetbrains.letsPlot.intern.layer.geom.HistogramAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.HistogramMapping
import org.jetbrains.letsPlot.intern.layer.stat.BinStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.BinStatParameters
import org.jetbrains.letsPlot.pos.positionStack
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName")
/**
 * Displays a 1d distribution by dividing variable mapped to x axis into bins and counting the number of observations in each bin.
 *
 * ## Examples
 *
 * - [distributions.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/distributions.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat default = `Stat.bin()`. The statistical transformation to use on the data for this layer.
 *  Supported transformations: `Stat.identity`, `Stat.bin()`, `Stat.count()`, etc. see [Stat][org.jetbrains.letsPlot.Stat].
 * @param position default = `positionStack()`. Position adjustment: `positionIdentity`, `positionStack()`, `positionDodge()`, etc.  
 *  see [Position](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.pos/).
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
 * @param y Y-axis value (this value will be used to multiply the bar heights), setting y to "..density.." produces
 *  normalized (density) histogram.
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of geometry lines.
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
 * @param size Defines bar line width.
 * @param weight Used by `Stat.bin()` to compute weighted sum instead of simple count.
 * @param bins Number of bins. Overridden by `binWidth`.
 * @param binWidth The width of the bins. The default is to use bin widths that cover 
 *  the entire range of the data. You should always override this value, exploring multiple widths 
 *  to find the best to illustrate the stories in your data.
 * @param center Specifies x-value to align bin centers to.
 * @param boundary Specifies x-value to align bin boundary (i.e. point between bins) to.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomHistogram(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.bin(),
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
    override val size: Number? = null,
    override val weight: Any? = null,
    override val bins: Int? = null,
    override val binWidth: Number? = null,
    override val center: Number? = null,
    override val boundary: Number? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: HistogramMapping.() -> Unit = {}

) : HistogramAesthetics,
    BinStatAesthetics,
    BinStatParameters,
    WithColorOption,
    WithFillOption,
    LayerBase(
        mapping = HistogramMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.histogram(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips,
        orientation = orientation
    ) {
    override fun seal() = super<HistogramAesthetics>.seal() +
            super<BinStatAesthetics>.seal() +
            super<BinStatParameters>.seal() +
            super<WithColorOption>.seal() +
            super<WithFillOption>.seal()
}


