/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer

import jetbrains.letsPlot.intern.Layer
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.tooltips.TooltipOptions

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
), OptionsCapsule {

    override val parameters by lazy { geom.parameters + stat.parameters + this.seal() }
}