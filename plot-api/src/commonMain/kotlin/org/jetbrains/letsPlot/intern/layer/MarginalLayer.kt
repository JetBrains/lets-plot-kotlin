/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer

import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options

/**
 * @param geomLayer The base geometric layer.
 * @param marginalMapping Additional aesthetic mappings for marginal plots.
 * @param marginalParameters Additional parameters for marginal plots.
 */
internal class MarginalLayer(
    geomLayer: Layer,
    marginalMapping: Options,
    private val marginalParameters: Options
) : Layer(
    mapping = marginalMapping + geomLayer.mapping,
    data = geomLayer.data,
    geom = geomLayer.geom,
    stat = geomLayer.stat,
    position = geomLayer.position,
    showLegend = geomLayer.showLegend,
    inheritAes = geomLayer.inheritAes,
    sampling = geomLayer.sampling,
    tooltips = geomLayer.tooltips,
    labels = null,
    orientation = geomLayer.orientation
) {
    private val allGeomLayerParameters = geomLayer.seal()

    override fun seal() = marginalParameters + allGeomLayerParameters
}
