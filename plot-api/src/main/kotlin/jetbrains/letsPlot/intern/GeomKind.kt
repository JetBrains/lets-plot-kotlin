package jetbrains.letsPlot.intern

enum class GeomKind {
    BLANK,
    PATH,
    LINE,
    SMOOTH,
    BAR,
    HISTOGRAM,
    TILE,
    MAP,
    ERROR_BAR,
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

    open fun optionName() = name.toLowerCase()
}
