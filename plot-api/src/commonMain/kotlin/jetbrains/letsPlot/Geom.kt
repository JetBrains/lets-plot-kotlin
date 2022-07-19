/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.intern.GeomKind
import jetbrains.letsPlot.intern.layer.GeomOptions
import jetbrains.letsPlot.intern.layer.WithSizeUnitOption
import jetbrains.letsPlot.intern.layer.geom.*

/**
 * Geom options to pass as a value of `geom` parameter of layer functions like:
 *
 * ```kotlin
 * val n = 100
 * val rand = java.util.Random(42)
 * val data = mapOf("x" to List(n) { rand.nextGaussian() })
 * letsPlot(data) +
 *     statDensity(geom = Geom.area()) { x="x" }
 * ```
 */
object Geom {
//    val blank = GeomOptions(
//        GeomKind.BLANK
//    )

    @Suppress("ClassName")
    class point(
        override val x: Number? = null,
        override val y: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val shape: Any? = null,
        override val size: Number? = null,
        override val stroke: Number? = null,
        override val sizeUnit: String? = null,
        mapping: PointMapping.() -> Unit = {}
    ) : PointAesthetics,
        WithSizeUnitOption,
        GeomOptions(
            GeomKind.POINT,
            PointMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<PointAesthetics>.seal() + super<WithSizeUnitOption>.seal()
    }

    @Suppress("ClassName")
    class path(
        override val x: Number? = null,
        override val y: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        override val speed: Number? = null,
        override val flow: Number? = null,
        mapping: PathMapping.() -> Unit = {}
    ) : PathAesthetics,
        GeomOptions(
            GeomKind.PATH,
            PathMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class area(
        override val x: Number? = null,
        override val y: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        mapping: AreaMapping.() -> Unit = {}
    ) : AreaAesthetics,
        GeomOptions(
            GeomKind.AREA,
            AreaMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class histogram(
        override val x: Number? = null,
        override val y: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val size: Number? = null,
        mapping: HistogramMapping.() -> Unit = {}
    ) : HistogramAesthetics,
        GeomOptions(
            GeomKind.HISTOGRAM,
            HistogramMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class line(
        override val x: Number? = null,
        override val y: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        mapping: LineMapping.() -> Unit = {}
    ) : LineAesthetics,
        GeomOptions(
            GeomKind.LINE,
            LineMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class bar(
        override val x: Number? = null,
        override val y: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val width: Number? = null,
        override val size: Number? = null,
        mapping: BarMapping.() -> Unit = {}
    ) : BarAesthetics,
        GeomOptions(
            GeomKind.BAR,
            BarMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class tile(
        override val x: Number? = null,
        override val y: Number? = null,
        override val width: Number? = null,
        override val height: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        mapping: TileMapping.() -> Unit = {}
    ) : TileAesthetics,
        GeomOptions(
            GeomKind.TILE,
            TileMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class raster(
        override val x: Number? = null,
        override val y: Number? = null,
        override val alpha: Number? = null,
        override val fill: Any? = null,
        mapping: RasterMapping.() -> Unit = {}
    ) : RasterAesthetics,
        GeomOptions(
            GeomKind.RASTER,
            RasterMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName", "SpellCheckingInspection")
    class abline(
        override val slope: Number? = null,
        override val intercept: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        mapping: ABLineMapping.() -> Unit = {}
    ) : ABLineAesthetics,
        GeomOptions(
            GeomKind.AB_LINE,
            ABLineMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class hline(
        @Suppress("SpellCheckingInspection")
        override val yintercept: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        mapping: HLineMapping.() -> Unit = {}
    ) : HLineAesthetics,
        GeomOptions(
            GeomKind.H_LINE,
            HLineMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class vline(
        @Suppress("SpellCheckingInspection")
        override val xintercept: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        mapping: VLineMapping.() -> Unit = {}
    ) : VLineAesthetics,
        GeomOptions(
            GeomKind.V_LINE,
            VLineMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class rect(
        override val xmin: Number? = null,
        override val xmax: Number? = null,
        override val ymin: Number? = null,
        override val ymax: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        override val fill: Any? = null,
        mapping: RectMapping.() -> Unit = {}
    ) : RectAesthetics,
        GeomOptions(
            GeomKind.RECT,
            RectMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class segment(
        override val x: Number? = null,
        override val y: Number? = null,
        override val xend: Number? = null,
        override val yend: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        override val speed: Number? = null,
        override val flow: Number? = null,
        mapping: SegmentMapping.() -> Unit = {}
    ) : SegmentAesthetics,
        GeomOptions(
            GeomKind.SEGMENT,
            SegmentMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class text(
        override val x: Number? = null,
        override val y: Number? = null,
        override val label: String? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val size: Number? = null,
        override val family: String? = null,
        override val fontface: String? = null,
        override val hjust: Any? = null,
        override val vjust: Any? = null,
        override val angle: Number? = null,
        override val labelFormat: String? = null,
        override val naText: String? = null,
        override val sizeUnit: String? = null,
        mapping: TextMapping.() -> Unit = {}
    ) : TextAesthetics,
        TextParameters,
        WithSizeUnitOption,
        GeomOptions(
            GeomKind.TEXT,
            TextMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<TextAesthetics>.seal() +
                super<TextParameters>.seal() + super<WithSizeUnitOption>.seal()
    }

    @Suppress("ClassName")
    class boxplot(
        override val x: Number? = null,
        override val y: Number? = null,
        override val lower: Number? = null,
        override val middle: Number? = null,
        override val upper: Number? = null,
        override val ymin: Number? = null,
        override val ymax: Number? = null,
        override val outlierColor: Any? = null,
        override val outlierFill: Any? = null,
        override val outlierShape: Any? = null,
        override val outlierSize: Number? = null,
        override val fatten: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val size: Number? = null,
        override val linetype: Any? = null,
        override val shape: Any? = null,
        override val width: Number? = null,
        mapping: BoxplotMapping.() -> Unit = {}
    ) : BoxplotAesthetics,
        BoxplotParameters,
        GeomOptions(
            GeomKind.BOX_PLOT,
            BoxplotMapping().apply(mapping).seal()
        ) {

        override val parameters = this.seal()

        override fun seal() = super<BoxplotAesthetics>.seal() +
                super<BoxplotParameters>.seal()
    }

    @Suppress("ClassName", "SpellCheckingInspection")
    class errorbar(
        override val x: Number? = null,
        override val ymin: Number? = null,
        override val ymax: Number? = null,
        override val width: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        mapping: ErrorBarMapping.() -> Unit = {}
    ) : ErrorBarAesthetics,
        GeomOptions(
            GeomKind.ERROR_BAR,
            ErrorBarMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class crossbar(
        override val x: Number? = null,
        override val ymin: Number? = null,
        override val ymax: Number? = null,
        override val middle: Number? = null,
        override val width: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val linetype: Any? = null,
        override val shape: Any? = null,
        override val size: Number? = null,
        mapping: CrossBarMapping.() -> Unit = {}
    ) : CrossBarAesthetics,
        GeomOptions(
            GeomKind.CROSS_BAR,
            CrossBarMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName", "SpellCheckingInspection")
    class pointrange(
        override val x: Number? = null,
        override val y: Number? = null,
        override val ymin: Number? = null,
        override val ymax: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val linetype: Any? = null,
        override val shape: Any? = null,
        override val size: Number? = null,
        mapping: PointRangeMapping.() -> Unit = {}
    ) : PointRangeAesthetics,
        GeomOptions(
            GeomKind.POINT_RANGE,
            PointRangeMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName", "SpellCheckingInspection")
    class linerange(
        override val x: Number? = null,
        override val ymin: Number? = null,
        override val ymax: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        mapping: LineRangeMapping.() -> Unit = {}
    ) : LineRangeAesthetics,
        GeomOptions(
            GeomKind.LINE_RANGE,
            LineRangeMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class ribbon(
        override val x: Number? = null,
        override val ymin: Number? = null,
        override val ymax: Number? = null,
        override val size: Number? = null,
        override val linetype: Any? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val alpha: Number? = null,
        mapping: RibbonMapping.() -> Unit = {}
    ) : RibbonAesthetics,
        GeomOptions(
            GeomKind.RIBBON,
            RibbonMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class polygon(
        override val x: Number? = null,
        override val y: Number? = null,
        override val size: Number? = null,
        override val linetype: Any? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val alpha: Number? = null,
        mapping: PolygonMapping.() -> Unit = {}
    ) : PolygonAesthetics,
        GeomOptions(
            GeomKind.POLYGON,
            PolygonMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class map(
        override val x: Number? = null,
        override val y: Number? = null,
        override val size: Number? = null,
        override val linetype: Any? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val alpha: Number? = null,
        mapping: PolygonMapping.() -> Unit = {}
    ) : PolygonAesthetics,
        GeomOptions(
            GeomKind.MAP,
            PolygonMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class step(
        override val x: Number? = null,
        override val y: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        mapping: LineMapping.() -> Unit = {}
    ) : LineAesthetics,
        GeomOptions(
            GeomKind.STEP,
            LineMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class qq(
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val shape: Any? = null,
        override val size: Number? = null,
        mapping: QQMapping.() -> Unit = {}
    ) : QQAesthetics,
        GeomOptions(
            GeomKind.Q_Q,
            QQMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class qqLine(
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        mapping: QQLineMapping.() -> Unit = {}
    ) : QQLineAesthetics,
        GeomOptions(
            GeomKind.Q_Q_LINE,
            QQLineMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }
}