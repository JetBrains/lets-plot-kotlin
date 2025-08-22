/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by the default stat of [geomDotplot()][org.jetbrains.letsPlot.geom.geomDotplot].
 *
 * @property x X-axis coordinates.
 */
interface DotplotStatAesthetics : OptionsCapsule {
    val x: Any?

    override fun seal() = Options.of(
        "x" to x
    )
}