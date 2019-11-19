package jetbrains.datalorePlot

import jetbrains.datalorePlot.intern.GeomKind
import jetbrains.datalorePlot.intern.layer.GeomOptions
import jetbrains.datalorePlot.intern.layer.geom.*

/**
 * `Geom options` to pass as a value of `geom` parameter of `layer` functions like:
 *  ggplot() + stat_density(..., geom = Pos.point(), ... )
 */
object Geom {
    val blank = GeomOptions(
        GeomKind.BLANK
    )

    @Suppress("ClassName")
    class point(
        mapping: PointMapping.() -> Unit = {},
        override val x: Double? = null,
        override val y: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val shape: Any? = null,
        override val size: Double? = null,
        override val stroke: Double? = null
    ) : PointAesthetics,
        GeomOptions(
            GeomKind.POINT,
            PointMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class area(
        mapping: AreaMapping.() -> Unit = {},
        override val x: Double? = null,
        override val y: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val linetype: Any? = null,
        override val size: Double? = null
    ) : AreaAesthetics,
        GeomOptions(
            GeomKind.AREA,
            AreaMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class histogram(
        mapping: HistogramMapping.() -> Unit = {},
        override val x: Double? = null,
        override val y: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val size: Double? = null
    ) : HistogramAesthetics,
        GeomOptions(
            GeomKind.HISTOGRAM,
            HistogramMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class bar(
        mapping: BarMapping.() -> Unit = {},
        override val x: Double? = null,
        override val y: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val width: Double? = null,
        override val size: Double? = null
    ) : BarAesthetics,
        GeomOptions(
            GeomKind.BAR,
            BarMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class tile(
        mapping: TileMapping.() -> Unit = {},
        override val x: Double? = null,
        override val y: Double? = null,
        override val width: Double? = null,
        override val height: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val linetype: Any? = null,
        override val size: Double? = null
    ) : TileAesthetics,
        GeomOptions(
            GeomKind.TILE,
            TileMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }
}