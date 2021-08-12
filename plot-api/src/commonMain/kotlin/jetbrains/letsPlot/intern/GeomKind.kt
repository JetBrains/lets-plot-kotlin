/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern

enum class GeomKind {
    BLANK,
    PATH,
    LINE,
    SMOOTH,
    BAR,
    HISTOGRAM,
    TILE,
    BIN_2D {
        override fun optionName() = "bin2d"
    },
    MAP,
    ERROR_BAR {
        override fun optionName() = "errorbar"
    },
    CROSS_BAR {
        override fun optionName() = "crossbar"
    },
    LINE_RANGE {
        override fun optionName() = "linerange"
    },
    POINT_RANGE {
        override fun optionName() = "pointrange"
    },
    POLYGON,
    AB_LINE {
        override fun optionName() = "abline"
    },
    H_LINE {
        override fun optionName() = "hline"
    },
    V_LINE {
        override fun optionName() = "vline"
    },
    BOX_PLOT {
        override fun optionName() = "boxplot"
    },
    LIVE_MAP,
    POINT,
    RIBBON,
    AREA,
    DENSITY,
    CONTOUR,
    CONTOURF,
    DENSITY2D,
    DENSITY2DF,
    JITTER,
    FREQPOLY,
    STEP,
    RECT,
    SEGMENT,
    TEXT,
    RASTER,
    IMAGE;

    open fun optionName() = name.lowercase()
}
