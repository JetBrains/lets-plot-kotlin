/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Pos
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.geom.Density2dMapping
import org.jetbrains.letsPlot.intern.layer.geom.PathAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.ContourStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.Density2dStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.Density2dStatParameters
import org.jetbrains.letsPlot.tooltips.TooltipOptions
import org.jetbrains.letsPlot.intern.layer.*

@Suppress("ClassName")
/**
 * Display density function contour.
 *
 * ## Examples
 *
 * - [density_2d.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/density_2d.ipynb)
 *
 * @param data
 *     The data to be displayed in this layer. If None, the default, the data
 *     is inherited from the plot data as specified in the call to [letsPlot][org.jetbrains.letsPlot.letsPlot].
 * @param stat
 *     The statistical transformation to use on the data for this layer. Supported transformations:
 *     "identity" (leaves the data unchanged), "count" (counts number of points with same x-axis coordinate),
 *     "bin" (counts number of points with x-axis coordinate in the same bin), "smooth" (performs smoothing -
 *     linear default).
 *     Statistic types: [letsPlot][org.jetbrains.letsPlot.Stat].
 * @param position
 *     Position adjustment: Pos.identity, Pos.stack,  etc. - see [letsPlot][org.jetbrains.letsPlot.Pos].
 *
 * @param tooltips result of the call to the layerTooltips() function.
 *     Specifies appearance, style and content.
 * @param kernel
 *     The kernel we use to calculate the density function. Choose among "gaussian", "cosine", "optcosine",
 *     "rectangular" (or "uniform"), "triangular", "biweight" (or "quartic"), "epanechikov" (or "parabolic")
 * @param bw string or double array.
 *     The method (or exact value) of bandwidth. Either a string (choose among "nrd0" and "nrd"), or a double array of length 2.
 * @param n
 *     The number of sampled points for plotting the function (on x and y direction correspondingly)
 * @param adjust
 *     Adjust the value of bandwidth by multiplying it. Changes how smooth the frequency curve is.
 * @param contour
 *      If TRUE, contour the results of the 2d density estimation.
 * @param bins
 *     Number of levels.
 * @param binWidth
 *     Distance between levels.
 * @param x x-axis coordinates of the center of rectangles, forming a tessellation.
 * @param y y-axis coordinates of the center of rectangles, forming a tessellation.
 * @param alpha transparency level of a point
 *     Understands numbers between 0 and 1.
 * @param color color of a geometry.
 *     Can be continuous or discrete. For continuous value this will be a color gradient between two colors.
 * @param linetype type of the line.
 *     Codes and names: 0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash",
 *     5 = "longdash", 6 = "twodash".
 * @param size line width.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */

class geomDensity2D(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.density2D(),
    position: PosOptions = Pos.identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val z: Number? = null,
    override val alpha: Number? = null,
    override val color: Any? = null,
    override val linetype: Any? = null,
    override val size: Number? = null,
    override val speed: Number? = null,
    override val flow: Number? = null,
    override val weight: Number? = null,
    override val bw: Any? = null,
    override val kernel: String? = null,
    override val n: Int? = null,
    override val adjust: Number? = null,
    override val contour: Boolean? = null,
    override val bins: Int? = null,
    override val binWidth: Number? = null,
    mapping: Density2dMapping.() -> Unit = {}
) : PathAesthetics,
    ContourStatAesthetics,
    Density2dStatAesthetics,
    Density2dStatParameters,
    LayerBase(
        mapping = Density2dMapping().apply(mapping).seal(),
        data = data,
        geom = GeomOptions(GeomKind.DENSITY2D),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super<PathAesthetics>.seal() +
                super<ContourStatAesthetics>.seal() +
                super<Density2dStatAesthetics>.seal() +
                super<Density2dStatParameters>.seal()
    }
}