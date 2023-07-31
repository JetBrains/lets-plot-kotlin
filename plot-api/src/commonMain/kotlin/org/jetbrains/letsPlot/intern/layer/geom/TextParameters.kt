/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface TextParameters : OptionsCapsule {
    val labelFormat: String?
    val naText: String?
    val nudgeX: Number?
    val nudgeY: Number?

    override fun seal() = Options.of(
        Option.Geom.Text.LABEL_FORMAT to labelFormat,
        Option.Geom.Text.NA_TEXT to naText,
        Option.Geom.Text.NUDGE_X to nudgeX,
        Option.Geom.Text.NUDGE_Y to nudgeY
    )
}
