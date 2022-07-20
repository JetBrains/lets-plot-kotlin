/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface RasterAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val alpha: Any?
    val fill: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "alpha" to alpha,
        "fill" to fill
    )
}