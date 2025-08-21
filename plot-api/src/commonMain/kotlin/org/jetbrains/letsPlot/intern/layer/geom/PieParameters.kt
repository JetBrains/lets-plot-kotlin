/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Parameters supported by [geomPie()][org.jetbrains.letsPlot.geom.geomPie].
 *
 * @property hole A multiplicative factor applied to the pie diameter to draw donut-like chart. Understands numbers between 0 and 1.
 * @property strokeSide Defines which arcs of pie sector should have a stroke.
 * @property spacerWidth Line width between sectors. Spacers are not applied to exploded sectors and to sides of adjacent sectors.
 * @property spacerColor Color for spacers between sectors. By default, the "paper" color is used. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @property start Specify the angle at which the first sector starts. Accept values between 0 and 360. Default is a negative angle of the first sector.
 * @property direction Specify angle direction, 1=clockwise, -1=counter-clockwise.
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