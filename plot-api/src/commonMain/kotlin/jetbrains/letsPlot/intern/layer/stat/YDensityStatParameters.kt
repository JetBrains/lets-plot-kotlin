/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.Options

interface YDensityStatParameters : DensityStatParameters {
    val scale: String?

    override fun seal() = super.seal() + Options.of(
        "scale" to scale
    )
}