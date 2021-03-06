/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.Options

interface LineRangeAesthetics : jetbrains.letsPlot.intern.layer.OptionsCapsule {
    val x: Any?
    val ymin: Any?
    val ymax: Any?
    val alpha: Any?
    val color: Any?
    val linetype: Any?
    val size: Any?

    override fun seal() = Options.of(
        "x" to x,
        "ymin" to ymin,
        "ymax" to ymax,
        "alpha" to alpha,
        "color" to color,
        "linetype" to linetype,
        "size" to size
    )
}