/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import jetbrains.datalore.plot.config.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface PieParameters : OptionsCapsule {
    val fillBy: String?
    val hole: Number?
    val stroke: Number?
    val strokeColor: Any?

    override fun seal() = Options.of(
        Option.Geom.Pie.FILL_BY to fillBy,
        Option.Geom.Pie.HOLE to hole,
        Option.Geom.Pie.STROKE to stroke,
        Option.Geom.Pie.STROKE_COLOR to strokeColor
    )
}