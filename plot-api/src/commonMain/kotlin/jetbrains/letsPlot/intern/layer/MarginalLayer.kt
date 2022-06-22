/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer

import jetbrains.letsPlot.intern.Layer
import jetbrains.letsPlot.intern.Options

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
    sampling = geomLayer.sampling,
    tooltips = geomLayer.tooltips,
    orientation = geomLayer.orientation
) {
    private val allGeomLayerParameters = geomLayer.seal()

    override fun seal() = marginalParameters + allGeomLayerParameters
}
