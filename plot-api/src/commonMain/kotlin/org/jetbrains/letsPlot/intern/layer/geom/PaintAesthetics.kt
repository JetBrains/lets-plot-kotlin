/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Additional "color" aesthetics.
 * Provide support for additional "color" or "fill" scales as needed.
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