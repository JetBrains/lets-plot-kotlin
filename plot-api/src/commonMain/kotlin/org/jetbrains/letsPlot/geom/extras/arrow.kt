/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom.extras

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.filterNonNullValues

/**
 * Describes arrows to add to a line.
 *
 * @param angle The angle of the arrow head in degrees (smaller numbers produce narrower, pointer arrows).
 *  Essentially describes the width of the arrow head.
 * @param length The length of the arrow head (px).
 * @param ends Indicating which ends of the line to draw arrow heads: ("last", "first", "both").
 * @param type Indicating whether the arrow head should be a closed triangle: ("open", "closed").
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