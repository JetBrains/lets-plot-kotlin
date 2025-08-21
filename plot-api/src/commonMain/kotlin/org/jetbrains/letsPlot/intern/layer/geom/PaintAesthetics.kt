/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic parameters for auxiliary paint channels.
 *
 * @param paint_a Auxiliary paint channel A that can be used as either `color` or `fill` as needed. Map a variable here for composite/multi-channel color with a matching scale.
 * @param paint_b Auxiliary paint channel B. See `paint_a`.
 * @param paint_c Auxiliary paint channel C. See `paint_a`.
 */
@Suppress("PropertyName")
interface PaintAesthetics : OptionsCapsule {
    val paint_a: Any?
    val paint_b: Any?
    val paint_c: Any?

    override fun seal() = Options.of(
        "paint_a" to paint_a,
        "paint_b" to paint_b,
        "paint_c" to paint_c
    )
}