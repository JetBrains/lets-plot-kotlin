/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.intern.GenericAesMapping
import jetbrains.letsPlot.intern.Plot

fun letsPlot(data: Map<*, *>? = null, mapping: GenericAesMapping.() -> Unit = {}): Plot {
    return Plot(
        data,
        GenericAesMapping().apply(mapping).seal(),
        emptyList()
    )
}

fun ggplot(data: Map<*, *>? = null, mapping: GenericAesMapping.() -> Unit = {}) = letsPlot(data, mapping)

