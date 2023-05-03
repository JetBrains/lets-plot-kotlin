/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface LollipopAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val size: Any?
    val stroke: Any?
    val linewidth: Any?
    val color: Any?
    val fill: Any?
    val alpha: Any?
    val shape: Any?
    val linetype: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "size" to size,
        "stroke" to stroke,
        "linewidth" to linewidth,
        "color" to color,
        "fill" to fill,
        "alpha" to alpha,
        "shape" to shape,
        "linetype" to linetype
    )
}