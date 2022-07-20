/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

@Suppress("SpellCheckingInspection")
interface ViolinAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val violinWidth: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val linetype: Any?
    val size: Any?
    val width: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "violinwidth" to violinWidth,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "linetype" to linetype,
        "size" to size,
        "width" to width
    )
}