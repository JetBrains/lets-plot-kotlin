/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface LabelAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val label: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val size: Any?
    val family: Any?
    val fontface: Any?
    val hjust: Any?
    val vjust: Any?
    val angle: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "label" to label,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "size" to size,
        "family" to family,
        "fontface" to fontface,
        "hjust" to hjust,
        "vjust" to vjust,
        "angle" to angle
    )
}