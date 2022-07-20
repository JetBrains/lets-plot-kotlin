/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface BinStatParameters : OptionsCapsule {
    val bins: Int?
    val binWidth: Number?
    val center: Number?
    val boundary: Number?

    override fun seal() = Options.of(
        "bins" to bins,
        "binwidth" to binWidth,
        "center" to center,
        "boundary" to boundary
    )
}
