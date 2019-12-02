package jetbrains.letsPlot

import jetbrains.letsPlot.intern.GeomKind
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.GeomOptions
import jetbrains.letsPlot.intern.layer.geom.*

/**
 * `Geom options` to pass as a value of `geom` parameter of `layer` functions like:
 *  lets_plot() + stat_density(..., geom = Pos.point(), ... )
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
    class path(
        mapping: PathMapping.() -> Unit = {},
        override val x: Double? = null,
        override val y: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Double? = null,
        override val speed: Double? = null,
        override val flow: Double? = null
    ) : PathAesthetics,
        GeomOptions(
            GeomKind.PATH,
            PathMapping().apply(mapping).seal()
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
    class line(
        mapping: LineMapping.() -> Unit = {},
        override val x: Double? = null,
        override val y: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Double? = null
    ) : LineAesthetics,
        GeomOptions(
            GeomKind.LINE,
            LineMapping().apply(mapping).seal()
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

    @Suppress("ClassName")
    class raster(
        mapping: RasterMapping.() -> Unit = {},
        override val x: Double? = null,
        override val y: Double? = null,
        override val alpha: Double? = null,
        override val fill: Any? = null
    ) : RasterAesthetics,
        GeomOptions(
            GeomKind.RASTER,
            RasterMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class abline(
        mapping: ABLineMapping.() -> Unit = {},
        override val slope: Double? = null,
        override val intercept: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Double? = null
    ) : ABLineAesthetics,
        GeomOptions(
            GeomKind.AB_LINE,
            ABLineMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class hline(
        mapping: HLineMapping.() -> Unit = {},
        override val yintercept: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Double? = null
    ) : HLineAesthetics,
        GeomOptions(
            GeomKind.H_LINE,
            HLineMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class vline(
        mapping: VLineMapping.() -> Unit = {},
        override val xintercept: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Double? = null
    ) : VLineAesthetics,
        GeomOptions(
            GeomKind.V_LINE,
            VLineMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class rect(
        mapping: RectMapping.() -> Unit = {},
        override val xmin: Double? = null,
        override val xmax: Double? = null,
        override val ymin: Double? = null,
        override val ymax: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Double? = null,
        override val fill: Any? = null
    ) : RectAesthetics,
        GeomOptions(
            GeomKind.RECT,
            RectMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class segment(
        mapping: SegmentMapping.() -> Unit = {},
        override val x: Double? = null,
        override val y: Double? = null,
        override val xend: Double? = null,
        override val yend: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Double? = null,
        override val speed: Double? = null,
        override val flow: Double? = null
    ) : SegmentAesthetics,
        GeomOptions(
            GeomKind.SEGMENT,
            SegmentMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class text(
        mapping: TextMapping.() -> Unit = {},
        override val x: Double? = null,
        override val y: Double? = null,
        override val label: String? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val size: Double? = null,
        override val family: String? = null,
        override val fontface: String? = null,
        override val hjust: Any? = null,
        override val vjust: Any? = null,
        override val angle: Double? = null
    ) : TextAesthetics,
        GeomOptions(
            GeomKind.TEXT,
            TextMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class boxplot(
        mapping: BoxplotMapping.() -> Unit = {},
        override val x: Double? = null,
        override val lower: Double? = null,
        override val middle: Double? = null,
        override val upper: Double? = null,
        override val ymin: Double? = null,
        override val ymax: Double? = null,
        override val outlierColor: Any? = null,
        override val outlierFill: Any? = null,
        override val outlierShape: Any? = null,
        override val outlierSize: Double? = null,
        override val varwidth: Boolean? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val size: Double? = null,
        override val linetype: Any? = null,
        override val shape: Any? = null,
        override val width: Double? = null
    ) : BoxplotAesthetics,
        BoxplotParameters,
        GeomOptions(
            GeomKind.BOX_PLOT,
            BoxplotMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal(): Options {
            return super<BoxplotAesthetics>.seal() +
                    super<BoxplotParameters>.seal()
        }
    }
}