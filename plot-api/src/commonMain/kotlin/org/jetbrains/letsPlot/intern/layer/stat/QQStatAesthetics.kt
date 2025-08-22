/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [statQQ()][org.jetbrains.letsPlot.stat.statQQ].
 *
 * @property sample Y-axis value.
 */
interface QQStatAesthetics : OptionsCapsule {
    val sample: Any?

    override fun seal() = Options.of(
        "sample" to sample
    )
}