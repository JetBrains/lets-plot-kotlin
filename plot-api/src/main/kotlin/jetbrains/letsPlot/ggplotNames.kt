/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("FunctionName")

package jetbrains.letsPlot

import jetbrains.letsPlot.intern.GenericAesMapping
import jetbrains.letsPlot.intern.Plot

@Deprecated("", replaceWith = ReplaceWith("letsPlot(data, mapping)"))
fun lets_plot(data: Map<*, *>? = null, mapping: GenericAesMapping.() -> Unit = {}): Plot = letsPlot(data, mapping)