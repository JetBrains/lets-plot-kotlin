/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.figure

import jetbrains.datalore.plot.config.Option.SubPlots

class SubPlotsLayoutSpec(
    private val name: String,
    private val options: Map<String, Any>
) {
    fun toSpec(): Map<String, Any> {
        return mapOf(
            SubPlots.Layout.NAME to name,
        ) + options
    }
}