/*
 * Copyright (c) 2025. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by the point density stat.
 *
 * @property x X-axis coordinates.
 * @property y Y-axis coordinates.
 * @property weight Used by the stat to compute weighted density.
 */
interface PointDensityStatAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val weight: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "weight" to weight
    )
}