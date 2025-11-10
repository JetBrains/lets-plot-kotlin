/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern

enum class StatKind {
    IDENTITY,
    COUNT,
    COUNT2D,
    SUM,
    DENSITY,
    DENSITY2D,
    DENSITY2DF,
    POINTDENSITY,
    DENSITYRIDGES,
    YDENSITY,
    SINA,
    DOTPLOT,
    YDOTPLOT,
    BIN,
    BIN2D,
    BINHEX,
    BOXPLOT,
    BOXPLOT_OUTLIER,
    CONTOUR,
    CONTOURF,
    SMOOTH,
    QQ,
    QQ2,
    QQ_LINE,
    QQ2_LINE,
    SUMMARY,
    SUMMARYBIN,
    ECDF;

    fun optionName() = name.lowercase()
}