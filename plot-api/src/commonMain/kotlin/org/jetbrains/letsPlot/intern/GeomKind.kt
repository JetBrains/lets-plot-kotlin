/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern

enum class GeomKind {
//    BLANK,  not implemented
    PATH,
    LINE,
    SMOOTH,
    BAR,
    HISTOGRAM,
    DOTPLOT,
    Y_DOTPLOT {
        override fun optionName() = "ydotplot"
    },
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
    VIOLIN,
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
    Q_Q {
        override fun optionName() = "qq"
    },
    Q_Q_2 {
        override fun optionName() = "qq2"
    },
    Q_Q_LINE {
        override fun optionName() = "qq_line"
    },
    Q_Q_2_LINE {
        override fun optionName() = "qq2_line"
    },
    FREQPOLY,
    STEP,
    RECT,
    SEGMENT,
    TEXT,
    RASTER,
    IMAGE;

    open fun optionName() = name.lowercase()
}
