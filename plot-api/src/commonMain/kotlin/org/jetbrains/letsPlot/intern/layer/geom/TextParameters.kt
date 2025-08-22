/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Properties for parameters of [geomText()][org.jetbrains.letsPlot.geom.geomText].
 *
 * @property labelFormat Specifies the format pattern for displaying mapped values.
 * @property naText Text to show for missing values.
 * @property nudgeX Horizontal adjustment to nudge labels by.
 * @property nudgeY Vertical adjustment to nudge labels by.
 * @property nudgeUnit Units for x and y nudging.
 *  Possible values:
 *  - "identity": a unit of 1 corresponds to a difference of 1 in data space;
 *  - "size": a unit of 1 corresponds to the diameter of a point with `size=1`;
 *  - "px": the unit is measured in screen pixels.
 * @property checkOverlap Skips plotting text that overlaps previous text in the same layer.
 */
interface TextParameters : OptionsCapsule {
    val labelFormat: String?
    val naText: String?
    val nudgeX: Number?
    val nudgeY: Number?
    val nudgeUnit: String?
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
