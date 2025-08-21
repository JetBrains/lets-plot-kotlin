/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic parameters supported by [geomVLine()][org.jetbrains.letsPlot.geom.geomVLine].
 *
 * @property xintercept Line x-intercept.
 * @property alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @property color Color of the geometry.
 * @property linetype Type of the line.
 * @property size Lines width.
 */
interface VLineAesthetics : OptionsCapsule {
    @Suppress("SpellCheckingInspection")
    val xintercept: Any?
    val alpha: Any?
    val color: Any?
    val linetype: Any?
    val size: Any?

    @Suppress("SpellCheckingInspection")
    override fun seal() = Options.of(
        "xintercept" to xintercept,
        "alpha" to alpha,
        "color" to color,
        "linetype" to linetype,
        "size" to size
    )
}