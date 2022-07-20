/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface DotplotAesthetics : OptionsCapsule {
    val x: Any?
    val binWidth: Any?
    val stackSize: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val size: Any?

    override fun seal() = Options.of(
        "x" to x,
        "binwidth" to binWidth,
        "stacksize" to stackSize,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "size" to size
    )
}