/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.Options

interface QQStatAesthetics : jetbrains.letsPlot.intern.layer.OptionsCapsule {
    val sample: Any?

    override fun seal() = Options.of(
        "sample" to sample
    )
}