/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface DotplotParameters : OptionsCapsule {
    val stackDir: String?
    val stackRatio: Number?
    val dotSize: Number?
    val stackGroups: Boolean?

    override fun seal() = Options.of(
        "stackdir" to stackDir,
        "stackratio" to stackRatio,
        "dotsize" to dotSize,
        "stackgroups" to stackGroups,
    )
}
