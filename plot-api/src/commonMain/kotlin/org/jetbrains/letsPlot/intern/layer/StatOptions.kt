/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.StatKind

open class StatOptions(
    val kind: StatKind,
    val mapping: Options = Options.empty()
) {
    open val parameters: Options = Options.empty()
}