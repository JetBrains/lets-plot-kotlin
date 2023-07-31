/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface AreaRidgesParameters : OptionsCapsule {
    val scale: Number?
    val minHeight: Number?
    val quantileLines: Boolean?

    override fun seal() = Options.of(
        Option.Geom.AreaRidges.SCALE to scale,
        Option.Geom.AreaRidges.MIN_HEIGHT to minHeight,
        Option.Geom.AreaRidges.QUANTILE_LINES to quantileLines
    )
}