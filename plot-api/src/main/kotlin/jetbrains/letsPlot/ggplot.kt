/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("FunctionName")

package jetbrains.letsPlot

import jetbrains.letsPlot.intern.GenericAesMapping
import jetbrains.letsPlot.intern.Plot

fun lets_plot(data: Any? = null, mapping: GenericAesMapping.() -> Unit = {}): Plot {
    return Plot(
        data,
        GenericAesMapping().apply(mapping).seal(),
        emptyList()
    )
}

fun ggplot(data: Any? = null, mapping: GenericAesMapping.() -> Unit = {}) = lets_plot(data, mapping)

