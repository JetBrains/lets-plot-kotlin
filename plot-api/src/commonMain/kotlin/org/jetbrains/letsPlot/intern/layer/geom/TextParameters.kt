/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface TextParameters : OptionsCapsule {
    /** Specifies the format pattern for displaying mapped values. */
    val labelFormat: String?
    /** Text to show for missing values. */
    val naText: String?
    /** Horizontal adjustment to nudge labels by. */
    val nudgeX: Number?
    /** Vertical adjustment to nudge labels by. */
    val nudgeY: Number?
    /** Units for x and y nudging. Possible values: "identity", "size", "px". */
    val nudgeUnit: String?
    /** Skips plotting text that overlaps previous text in the same layer. */
    val checkOverlap: Boolean?

    override fun seal() = Options.of(
        Option.Geom.Text.LABEL_FORMAT to labelFormat,
        Option.Geom.Text.NA_TEXT to naText,
        Option.Geom.Text.NUDGE_X to nudgeX,
        Option.Geom.Text.NUDGE_Y to nudgeY,
        Option.Geom.Text.NUDGE_UNIT to nudgeUnit,
        Option.Geom.Text.CHECK_OVERLAP to checkOverlap
    )
}
