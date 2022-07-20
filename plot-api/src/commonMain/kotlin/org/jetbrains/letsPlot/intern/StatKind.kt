/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern

enum class StatKind {
    IDENTITY,
    COUNT,
    DENSITY,
    DENSITY2D,
    DENSITY2DF,
    YDENSITY,
    DOTPLOT,
    YDOTPLOT,
    BIN,
    BIN2D,
    BOXPLOT,
    CONTOUR,
    CONTOURF,
    SMOOTH,
    QQ,
    QQ2,
    QQ_LINE,
    QQ2_LINE;

    fun optionName() = name.lowercase()
}