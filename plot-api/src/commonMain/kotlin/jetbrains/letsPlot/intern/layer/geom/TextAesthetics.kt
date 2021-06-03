/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.Options

interface TextAesthetics : jetbrains.letsPlot.intern.layer.OptionsCapsule {
    val x: Any?
    val y: Any?
    val label: Any?
    val alpha: Any?
    val color: Any?
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
        "size" to size,
        "family" to family,
        "fontface" to fontface,
        "hjust" to hjust,
        "vjust" to vjust,
        "angle" to angle
    )
}