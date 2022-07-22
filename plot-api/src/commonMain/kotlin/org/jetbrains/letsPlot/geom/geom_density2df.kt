/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.*
import org.jetbrains.letsPlot.intern.layer.geom.Density2dfMapping
import org.jetbrains.letsPlot.intern.layer.geom.PolygonAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.Density2dStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.Density2dStatParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.tooltips.TooltipOptions


@Suppress("ClassName")
/**
 * Fill density function contour.
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
 * @param n int array, optional.
 *     The number of sampled points for plotting the function (on x and y direction correspondingly)
 * @param adjust
 *     Adjust the value of bandwidth by multiplying it. Changes how smooth the frequency curve is.
 * @param contour
 *      If TRUE, contour the results of the 2d density estimation.
 * @param bins int, optional.
 *     Number of levels.
 * @param binWidth double, optional.
 *     Distance between levels.
 * @param x x-axis coordinates.
 * @param y y-axis coordinates.
 * @param alpha transparency level of a point
 *     Understands numbers between 0 and 1.
 * @param fill color of geometry filling.
 * @param mapping set of aesthetic mappings.
 *     Aesthetic mappings describe the way that variables in the data are
 *     mapped to plot "aesthetics".
 */

class geomDensity2DFilled(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.density2DFilled(),
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    override val x: Number? = null,
    override val y: Number? = null,
    override val size: Number? = null,
    override val linetype: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val alpha: Number? = null,
    override val weight: Number? = null,
    override val bw: Any? = null,
    override val kernel: String? = null,
    override val n: Int? = null,
    override val adjust: Number? = null,
    override val contour: Boolean? = null,
    override val bins: Int? = null,
    override val binWidth: Number? = null,
    mapping: Density2dfMapping.() -> Unit = {}
) : PolygonAesthetics,
    Density2dStatAesthetics,
    Density2dStatParameters,
    LayerBase(
        mapping = Density2dfMapping().apply(mapping).seal(),
        data = data,
        geom = GeomOptions(GeomKind.DENSITY2DF),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling,
        tooltips = tooltips
    ) {
    override fun seal(): Options {
        return super<PolygonAesthetics>.seal() +
                super<Density2dStatAesthetics>.seal() +
                super<Density2dStatParameters>.seal()
    }
}