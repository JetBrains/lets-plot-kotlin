/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.SamplingKind
import jetbrains.letsPlot.intern.filterNonNullValues

class SamplingOptions internal constructor(
    val kind: SamplingKind,
    private val options: Map<String, Any>
) {
    internal constructor(kind: SamplingKind, n: Int, seed: Int? = null, minSubsample: Int? = null) : this(
        kind,
        mapOf(
            "n" to n,
            "seed" to seed,
            "min_subsample" to minSubsample
        ).filterNonNullValues()
    )

    val isNone: Boolean = (kind == SamplingKind.NONE)

    val mapping: Options
        get() {
            check(!isNone) { "Not applicable to `none` sampling." }
            return Options(options) +
                    Options(mapOf("name" to kind.optionName()))
        }
}