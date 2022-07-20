/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer

import org.jetbrains.letsPlot.intern.Layer
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.tooltips.TooltipOptions

// ToDo: It seems we don't need 'LayerBase'
abstract class LayerBase(
    mapping: Options,
    data: Map<*, *>? = null,
    geom: GeomOptions,
    stat: StatOptions,
    position: PosOptions?,
    showLegend: Boolean,
    sampling: SamplingOptions? = null,
    tooltips: TooltipOptions? = null,
    orientation: String? = null
) : Layer(
    mapping = mapping,
    data = data,
    geom = geom,
    stat = stat,
    position = position,
    showLegend = showLegend,
    sampling = sampling,
    tooltips = tooltips,
    orientation = orientation
)
//), OptionsCapsule {
//
//    // layer parameters has precedence over geom and stat
//    override val parameters by lazy { geom.parameters + stat.parameters + this.seal() }
//}