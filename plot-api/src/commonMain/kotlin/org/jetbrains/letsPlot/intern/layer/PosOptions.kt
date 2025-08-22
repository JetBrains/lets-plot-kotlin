/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.PosKind

/**
 * @param kind The position adjustment kind.
 * @param parameters Options for position adjustment parameters.
 */
class PosOptions(
    val kind: PosKind,
    val parameters: Options = Options.empty()
)




