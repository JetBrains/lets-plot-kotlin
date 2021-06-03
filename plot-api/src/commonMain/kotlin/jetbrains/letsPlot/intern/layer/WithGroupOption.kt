/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer

import jetbrains.letsPlot.intern.Options

interface WithGroupOption {
    val group: Any?

    fun groupOption() = Options.of(
        "group" to group
    )
}