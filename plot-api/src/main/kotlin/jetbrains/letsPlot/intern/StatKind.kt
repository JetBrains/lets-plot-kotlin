/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern

enum class StatKind {
    IDENTITY,
    COUNT,
    DENSITY,
    BIN,
    BIN2D,
    BOXPLOT;

    fun optionName() = name.toLowerCase()
}