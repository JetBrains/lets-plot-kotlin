/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Parameters for [geomPie()][org.jetbrains.letsPlot.geom.geomPie].
 *
 * @param hole Size of the pie hole as a fraction of the pie radius (0 = no hole, 1 = maximum hole).
 * @param strokeSide Which side of pie slice border to draw ("outer", "inner", "both").
 * @param spacerWidth Width of spacers between pie slices.
 * @param spacerColor Color of spacers between pie slices.
 * @param start Starting angle for the first pie slice in radians (0 = 3 o'clock position).
 * @param direction Direction for pie slice ordering (1 = clockwise, -1 = counterclockwise).
 */
interface PieParameters : OptionsCapsule {
    val hole: Number?
    val strokeSide: String?
    val spacerWidth: Number?
    val spacerColor: Any?
    val start: Number?
    val direction: Int?

    override fun seal() = Options.of(
        Option.Geom.Pie.HOLE to hole,
        Option.Geom.Pie.STROKE_SIDE to strokeSide,
        Option.Geom.Pie.SPACER_WIDTH to spacerWidth,
        Option.Geom.Pie.SPACER_COLOR to spacerColor,
        Option.Geom.Pie.START to start,
        Option.Geom.Pie.DIRECTION to direction
    )
}