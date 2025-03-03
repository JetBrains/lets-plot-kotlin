/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

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