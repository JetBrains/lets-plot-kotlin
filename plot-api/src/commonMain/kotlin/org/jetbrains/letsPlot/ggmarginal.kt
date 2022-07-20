/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import jetbrains.datalore.plot.config.Option
import org.jetbrains.letsPlot.intern.*
import org.jetbrains.letsPlot.intern.DummyFeature
import org.jetbrains.letsPlot.intern.layer.MarginalLayer


/**
 * Converts given geometry layer to a marginal layer.
 * You can add one or more marginal layers to a plot to create a marginal plot.
 *
 * A marginal plot is a scatterplot (sometimes a 2D density plot or other bivariate plot) that has histograms,
 * boxplots, or other distribution visualization layers in the margins of the x- and y-axes.
 *
 * ## Examples
 *
 * - [marginal_layers.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/marginal_layers.ipynb)
 *
 * @param sides Which sides of the plot the marginal layer will appear on.
 *      It should be set to a string containing any of "trbl", for top, right, bottom, and left.
 * @param size number or list of numbers.
 *      Size of marginal geometry (width or height, depending on the margin side) as a fraction of the entire
 *      plotting area of the plot.
 *      The value should be in range [0.01..0.95]. The default is 0.1.
 * @param layer A marginal geometry layer.
 *      The result of calling of the `geomXxx()` / `statXxx()` function.
 *      Marginal plot works best with `density`,`histogram`,`boxplot`,`violin` ans `freqpoly` geometry layers.
 *
 * @return An object specifying a marginal geometry layer or a list of marginal geometry layers.
 */
@Suppress("SpellCheckingInspection")
fun ggmarginal(
    sides: String,
    size: Any? = null,
    layer: Layer
): Feature {

    require(sides.isNotBlank()) { SIDES_ARG_ERROR }
    require(sides.length <= 4) { SIDES_ARG_ERROR }

    val sizeList = when (size) {
        null -> emptyList()
        is Number -> List(4) { (size.toDouble()) }
        is Pair<*, *> -> size.toList()
        is Iterable<*> -> size.toList()
        else -> throw IllegalArgumentException("Invalid 'size' type: ${size::class.simpleName}. Expected: number, list or pair.")
    } + List<Any?>(4) { null }


    var result: Feature = DummyFeature
    for ((i, side) in sides.withIndex()) {
        val marginSize = toMarginSize(sizeList[i])
        result += toMarginal(side.lowercaseChar(), marginSize, layer)
    }

    return result
}

private fun toMarginSize(v: Any?): Double? {
    val size = v?.let {
        when (it) {
            is Number -> it.toDouble()
            else -> throw IllegalArgumentException("Invalid 'size' value type: ${it::class.simpleName}. Expected: number.")
        }
    }

    require(size == null || (size in 0.01..0.95)) { "Invalid 'size' value: $size. Should be in range [0.01..0.95]." }
    return size
}

private fun toMarginal(side: Char, size: Double?, layer: Layer): Layer {
    val sideLR = (side == 'l' || side == 'r')
    val sideTB = (side == 't' || side == 'b')
    require(sideLR || sideTB) { SIDES_ARG_ERROR }

    val layerKind: GeomKind = when (layer.stat.kind) {
        StatKind.BIN -> GeomKind.HISTOGRAM
        StatKind.DENSITY -> GeomKind.DENSITY
        StatKind.BOXPLOT -> GeomKind.BOX_PLOT
        StatKind.YDENSITY -> GeomKind.VIOLIN
        else -> null
    } ?: layer.geom.kind

    val xKind = layerKind in listOf(GeomKind.HISTOGRAM, GeomKind.DENSITY, GeomKind.FREQPOLY)
    val yKind = layerKind in listOf(GeomKind.BOX_PLOT, GeomKind.VIOLIN)

    // Layer auto-configuring
    val autoConfigParams: MutableMap<String, Any> = HashMap()
    if (xKind && sideLR) {
        autoConfigParams[Option.Layer.ORIENTATION] = "y"
    }
    if (yKind && sideTB) {
        autoConfigParams[Option.Layer.ORIENTATION] = "y"
    }

    // Fix one of axis variables for 'boxplot' and 'violin'
    if (yKind) {
        if (sideLR) {
            autoConfigParams["x"] = 0.0
        } else {
            autoConfigParams["y"] = 0.0
        }
    }

    // For 'histogram': set mapping of x or y to '..density..' for compatibility with 'density' geom.
    val autoConfigMapping: MutableMap<String, Any> = HashMap()
    if (layerKind == GeomKind.HISTOGRAM) {
        if (sideLR) {
            autoConfigMapping["x"] = "..density.."
        } else {
            autoConfigMapping["y"] = "..density.."
        }
    }

    val marginalParams = mapOf(
        Option.Layer.MARGINAL to true,
        Option.Layer.Marginal.SIDE to side,
        Option.Layer.Marginal.SIZE to size,
    )

    return MarginalLayer(
        layer,
        marginalMapping = Options(autoConfigMapping),
        marginalParameters = Options(autoConfigParams + marginalParams)
    )
}

private const val SIDES_ARG_ERROR = "'sides' must be a string containing 1 to 4 chars: 'l','r','t','b'."
