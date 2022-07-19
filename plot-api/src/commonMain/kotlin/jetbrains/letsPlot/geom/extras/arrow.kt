/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom.extras

import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.filterNonNullValues

/**
 * Describe arrows to add to a line.
 *
 * @param angle number.
 *     The angle of the arrow head in degrees (smaller numbers produce narrower, pointer arrows).
 *     Essentially describes the width of the arrow head.
 * @param length number.
 *     The length of the arrow head (px).
 * @param ends string.
 *     Indicating which ends of the line to draw arrow heads: {"last", "first", "both"}.
 * @param type string.
 *     Indicating whether the arrow head should be a closed triangle: {"open", "closed"}.
 */
fun arrow(
    angle: Number? = null,
    length: Number? = null,
    ends: String? = null,
    type: String? = null
): Map<String, Any> {
    return mapOf(
        "name" to Option.Geom.Segment.ARROW,
        Option.Arrow.ANGLE to angle,
        Option.Arrow.LENGTH to length,
        Option.Arrow.ENDS to ends,
        Option.Arrow.TYPE to type
    ).filterNonNullValues()
}