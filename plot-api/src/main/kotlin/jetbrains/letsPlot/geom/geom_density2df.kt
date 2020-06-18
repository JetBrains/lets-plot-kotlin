/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.GeomKind
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.*
import jetbrains.letsPlot.intern.layer.geom.Density2dfMapping
import jetbrains.letsPlot.intern.layer.geom.PolygonAesthetics
import jetbrains.letsPlot.intern.layer.stat.Density2dStatAesthetics
import jetbrains.letsPlot.intern.layer.stat.Density2dStatParameters


@Suppress("ClassName")
/**
 * Fill density function contour.
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
 * @param kernel string, optional.
 *     The kernel we use to calculate the density function. Choose among "gaussian", "cosine", "optcosine",
 *     "rectangular" (or "uniform"), "triangular", "biweight" (or "quartic"), "epanechikov" (or "parabolic")
 * @param bw string or double array, optional.
 *     The method (or exact value) of bandwidth. Either a string (choose among "nrd0" and "nrd"), or a double array of length 2.
 * @param adjust double, optional.
 *     Adjust the value of bandwidth my multiplying it. Changes how smooth the frequency curve is.
 * @param n int array, optional.
 *     The number of sampled points for plotting the function (on x and y direction correspondingly)
 * @param binCount int, optional.
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

class geom_density2df(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.density2df(),
    position: PosOptions = Pos.identity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val size: Double? = null,
    override val linetype: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val alpha: Double? = null,
    override val weight: Double? = null,
    override val bw: Any? = null,
    override val kernel: String? = null,
    override val n: Any? = null,
    override val adjust: Double? = null,
    override val isContour: Boolean? = null,
    override val binCount: Int? = null,
    override val binWidth: Double? = null,
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
        sampling = sampling
    ) {
    override fun seal(): Options {
        return super<PolygonAesthetics>.seal() +
                super<Density2dStatAesthetics>.seal() +
                super<Density2dStatParameters>.seal()
    }
}