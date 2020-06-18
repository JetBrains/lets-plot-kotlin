/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

interface ContourStatParameters : OptionsCapsule {
    val binCount: Any?
    val binWidth: Any?

    override fun seal() = Options.of(
        "bins" to binCount,
        "binwidth" to binWidth
    )
}