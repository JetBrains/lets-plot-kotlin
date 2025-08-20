/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Parameters for [geomAreaRidges()][org.jetbrains.letsPlot.geom.geomAreaRidges].
 *
 * @param scale Relative height scaling factor for ridges. Higher values create taller ridges.
 * @param minHeight Minimum height for ridges as a fraction of the overall ridgeline height.
 * @param quantileLines Whether to draw quantile lines within ridges.
 */
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