/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.layer.GeomOptions
import org.jetbrains.letsPlot.intern.layer.LayerBase
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.WithFillOption
import org.jetbrains.letsPlot.intern.layer.geom.DotplotAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.DotplotMapping
import org.jetbrains.letsPlot.intern.layer.geom.DotplotParameters
import org.jetbrains.letsPlot.intern.layer.stat.DotplotStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.DotplotStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions

@Suppress("ClassName", "SpellCheckingInspection")
/**
 * 
 * Dotplot represents individual observations in a batch of data with circular dots.
 * The diameter of a dot corresponds to the maximum width or bin width, depending on the binning algorithm.
 *
 * ## Examples
 *
 * - [geom_dotplot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/geom_dotplot.ipynb)
 *
 * @param data The data to be displayed in this layer. If null, the default, the data
 *  is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param showLegend default = true.
 *  false - do not show legend for this layer.
 * @param sampling Result of the call to the `samplingXxx()` function.
 *  To prevent any sampling for this layer pass value `samplingNone`.
 *  For more info see [sampling.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/sampling.md).
 * @param tooltips Result of the call to the `layerTooltips()` function.
 *  Specifies appearance, style and content.
 * @param x X-axis coordinates.
 * @param bins When method is "histodot", this specifies number of bins (default = 30). Overridden by `binWidth`.
 * @param center When method is "histodot", this specifies x-value to align bin centers to.
 * @param boundary When method is "histodot", this specifies x-value to align bin boundary
 *  (i.e. point between bins) to.
 * @param method default = "dotdensity".
 *  Use "dotdensity" for dot-density binning,
 *  or "histodot" for fixed bin widths (like in geomHistogram).
 * @param binWidth When method is "dotdensity", this specifies maximum bin width.
 *  When method is "histodot", this specifies bin width.
 * @param stackDir Which direction to stack the dots.
 *  Values: "up", "down", "center", "centerwhole" (default = "up").
 * @param stackRatio default = 1.0.
 *  How close to stack the dots.
 *  Use smaller values for closer, overlapping dots.
 * @param dotSize default = 1.0.
 *  The diameter of the dots relative to binWidth.
 * @param stackGroups Stack dots across groups when method = "histodot".
 * @param stroke Width of the dot border.
 * @param alpha Transparency level of a layer.
 *  Understands numbers between 0 and 1.
 * @param color Color of geometry lines.
 *  String in the following formats: 
 *  - RGB/RGBA (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red") 
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param fill Fill color.
 *  String in the following formats: 
 *  - RGB/RGBA (e.g. "rgb(0, 0, 255)")
 *  - HEX (e.g. "#0000FF")
 *  - color name (e.g. "red") 
 *
 *  Or an instance of the `java.awt.Color` class.
 * @param colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the geometry.
 * @param fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the geometry.
 * @param mapping Set of aesthetic mappings.
 *  Aesthetic mappings describe the way that variables in the data are
 *  mapped to plot "aesthetics".
 */
class geomDotplot(
    data: Map<*, *>? = null,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val x: Number? = null,
    override val bins: Int? = null,
    override val center: Number? = null,
    override val boundary: Number? = null,
    override val method: String? = null,
    override var binWidth: Number? = null,
    override var stackSize: Number? = null,
    override val stackDir: String? = null,
    override val stackRatio: Number? = null,
    override val dotSize: Number? = null,
    override val stackGroups: Boolean? = null,
    override val stroke: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val colorBy: String? = null,
    override val fillBy: String? = null,
    mapping: DotplotMapping.() -> Unit = {},
) : DotplotAesthetics,
    DotplotParameters,
    DotplotStatAesthetics,
    DotplotStatParameters,
    WithColorOption,
    WithFillOption,
    LayerBase(
        mapping = DotplotMapping().apply(mapping).seal(),
        data = data,
        geom = GeomOptions(GeomKind.DOTPLOT),
        stat = Stat.dotplot(),
        position = positionIdentity,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {

    override fun seal() = super<DotplotAesthetics>.seal() +
            super<DotplotParameters>.seal() +
            super<DotplotStatAesthetics>.seal() +
            super<DotplotStatParameters>.seal() +
            super<WithColorOption>.seal() +
            super<WithFillOption>.seal()
}
