/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

class SummaryStatMapping(
    val x: Any? = null,
    val y: Any? = null
) : OptionsCapsule {
    override fun seal() = Options.of(
        "x" to x,
        "y" to y
    )
}