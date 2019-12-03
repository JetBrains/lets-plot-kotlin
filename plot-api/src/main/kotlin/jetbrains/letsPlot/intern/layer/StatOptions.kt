/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.StatKind

open class StatOptions(
    val kind: StatKind,
    val mapping: Options = Options.empty()
) {
    open val parameters: Options = Options.empty()
}