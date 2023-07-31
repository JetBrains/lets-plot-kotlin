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
    val stroke: Number?
    val strokeColor: Any?

    override fun seal() = Options.of(
        Option.Geom.Pie.HOLE to hole,
//        Option.Geom.Pie.STROKE to stroke,
//        Option.Geom.Pie.STROKE_COLOR to strokeColor
        // ToDo:
//    const val SPACER_WIDTH = "spacer_width"
//    const val SPACER_COLOR = "spacer_color"
//    const val STROKE_SIDE = "stroke_side"

    )
}