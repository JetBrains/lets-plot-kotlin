/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.Options

@Suppress("SpellCheckingInspection")
interface ViolinAesthetics : jetbrains.letsPlot.intern.layer.OptionsCapsule {
    val x: Any?
    val y: Any?
    val violinwidth: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val linetype: Any?
    val size: Any?
    val width: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "violinwidth" to violinwidth,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "linetype" to linetype,
        "size" to size,
        "width" to width
    )
}