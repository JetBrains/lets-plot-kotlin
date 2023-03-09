/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface PieAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val slice: Any?
    val explode: Any?
    val size: Any?
    val fill: Any?
    val alpha: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "slice" to slice,
        "explode" to explode,
        "size" to size,
        "fill" to fill,
        "alpha" to alpha
    )
}