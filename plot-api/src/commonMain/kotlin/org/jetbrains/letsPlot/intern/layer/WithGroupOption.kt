/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer

import org.jetbrains.letsPlot.intern.Options

/**
 * @property group Variable or value for grouping data points.
 */
interface WithGroupOption {
    val group: Any?

    fun groupOption() = Options.of(
        "group" to group
    )
}