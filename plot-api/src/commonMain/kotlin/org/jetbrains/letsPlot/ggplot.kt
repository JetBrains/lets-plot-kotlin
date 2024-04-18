/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.jetbrains.letsPlot.intern.GenericAesMapping
import org.jetbrains.letsPlot.intern.Plot

fun letsPlot(data: Map<*, *>? = null, mapping: GenericAesMapping.() -> Unit = {}): Plot {
    return Plot(
        data = data,
        mapping = GenericAesMapping().apply(mapping).seal(),
        features = LetsPlot.themeSettings
    )
}

fun ggplot(data: Map<*, *>? = null, mapping: GenericAesMapping.() -> Unit = {}) = letsPlot(data, mapping)

