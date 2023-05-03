/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.layer.GeomOptions
import org.jetbrains.letsPlot.intern.layer.WithColorOption
import org.jetbrains.letsPlot.intern.layer.WithFillOption
import org.jetbrains.letsPlot.intern.layer.WithSizeUnitOption
import org.jetbrains.letsPlot.intern.layer.geom.*

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
        override val colorBy: String? = null,
        override val fillBy: String? = null,
        mapping: PointMapping.() -> Unit = {}
    ) : PointAesthetics,
        WithSizeUnitOption,
        WithColorOption,
        WithFillOption,
        GeomOptions(
            GeomKind.POINT,
            PointMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<PointAesthetics>.seal() +
                super<WithSizeUnitOption>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }

    @Suppress("ClassName")
    class path(
        override val x: Number? = null,
        override val y: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        override val colorBy: String? = null,
        mapping: PathMapping.() -> Unit = {}
    ) : PathAesthetics,
        WithColorOption,
        GeomOptions(
            GeomKind.PATH,
            PathMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<PathAesthetics>.seal() + super<WithColorOption>.seal()
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
        override val colorBy: String? = null,
        override val fillBy: String? = null,
        mapping: AreaMapping.() -> Unit = {}
    ) : AreaAesthetics,
        WithColorOption,
        WithFillOption,
        GeomOptions(
            GeomKind.AREA,
            AreaMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<AreaAesthetics>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }

    @Suppress("ClassName")
    class histogram(
        override val x: Number? = null,
        override val y: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val size: Number? = null,
        override val colorBy: String? = null,
        override val fillBy: String? = null,
        mapping: HistogramMapping.() -> Unit = {}
    ) : HistogramAesthetics,
        WithColorOption,
        WithFillOption,
        GeomOptions(
            GeomKind.HISTOGRAM,
            HistogramMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<HistogramAesthetics>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }

    @Suppress("ClassName")
    class line(
        override val x: Number? = null,
        override val y: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        override val colorBy: String? = null,
        mapping: LineMapping.() -> Unit = {}
    ) : LineAesthetics,
        WithColorOption,
        GeomOptions(
            GeomKind.LINE,
            LineMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<LineAesthetics>.seal() + super<WithColorOption>.seal()
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
        override val colorBy: String? = null,
        override val fillBy: String? = null,
        mapping: BarMapping.() -> Unit = {}
    ) : BarAesthetics,
        WithColorOption,
        WithFillOption,
        GeomOptions(
            GeomKind.BAR,
            BarMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<BarAesthetics>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
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
        override val colorBy: String? = null,
        override val fillBy: String? = null,
        mapping: TileMapping.() -> Unit = {}
    ) : TileAesthetics,
        WithColorOption,
        WithFillOption,
        GeomOptions(
            GeomKind.TILE,
            TileMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<TileAesthetics>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }

    @Suppress("ClassName")
    class raster(
        override val x: Number? = null,
        override val y: Number? = null,
        override val alpha: Number? = null,
        override val fill: Any? = null,
        override val fillBy: String? = null,
        mapping: RasterMapping.() -> Unit = {}
    ) : RasterAesthetics,
        WithFillOption,
        GeomOptions(
            GeomKind.RASTER,
            RasterMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<RasterAesthetics>.seal() + super<WithFillOption>.seal()
    }

    @Suppress("ClassName", "SpellCheckingInspection")
    class abline(
        override val slope: Number? = null,
        override val intercept: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        override val colorBy: String? = null,
        mapping: ABLineMapping.() -> Unit = {}
    ) : ABLineAesthetics,
        WithColorOption,
        GeomOptions(
            GeomKind.AB_LINE,
            ABLineMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<ABLineAesthetics>.seal() + super<WithColorOption>.seal()
    }

    @Suppress("ClassName")
    class hline(
        @Suppress("SpellCheckingInspection")
        override val yintercept: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        override val colorBy: String? = null,
        mapping: HLineMapping.() -> Unit = {}
    ) : HLineAesthetics,
        WithColorOption,
        GeomOptions(
            GeomKind.H_LINE,
            HLineMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<HLineAesthetics>.seal() + super<WithColorOption>.seal()
    }

    @Suppress("ClassName")
    class vline(
        @Suppress("SpellCheckingInspection")
        override val xintercept: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        override val colorBy: String? = null,
        mapping: VLineMapping.() -> Unit = {}
    ) : VLineAesthetics,
        WithColorOption,
        GeomOptions(
            GeomKind.V_LINE,
            VLineMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<VLineAesthetics>.seal() + super<WithColorOption>.seal()
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
        override val colorBy: String? = null,
        override val fillBy: String? = null,
        mapping: RectMapping.() -> Unit = {}
    ) : RectAesthetics,
        WithColorOption,
        WithFillOption,
        GeomOptions(
            GeomKind.RECT,
            RectMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<RectAesthetics>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
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
        override val colorBy: String? = null,
        mapping: SegmentMapping.() -> Unit = {}
    ) : SegmentAesthetics,
        WithColorOption,
        GeomOptions(
            GeomKind.SEGMENT,
            SegmentMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<SegmentAesthetics>.seal() + super<WithColorOption>.seal()
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
        override val lineheight: Number? = null,
        override val labelFormat: String? = null,
        override val naText: String? = null,
        override val nudgeX: Number? = null,
        override val nudgeY: Number? = null,
        override val sizeUnit: String? = null,
        override val colorBy: String? = null,
        mapping: TextMapping.() -> Unit = {}
    ) : TextAesthetics,
        TextParameters,
        WithSizeUnitOption,
        WithColorOption,
        GeomOptions(
            GeomKind.TEXT,
            TextMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<TextAesthetics>.seal() +
                super<TextParameters>.seal() +
                super<WithSizeUnitOption>.seal() +
                super<WithColorOption>.seal()
    }

    @Suppress("ClassName")
    class label(
        override val x: Number? = null,
        override val y: Number? = null,
        override val label: String? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val size: Number? = null,
        override val family: String? = null,
        override val fontface: String? = null,
        override val hjust: Any? = null,
        override val vjust: Any? = null,
        override val angle: Number? = null,
        override val lineheight: Number? = null,
        override val labelFormat: String? = null,
        override val naText: String? = null,
        override val nudgeX: Number? = null,
        override val nudgeY: Number? = null,
        override val labelPadding: Number? = null,
        override val labelR: Number? = null,
        override val labelSize: Number? = null,
        override val sizeUnit: String? = null,
        override val colorBy: String? = null,
        override val fillBy: String? = null,
        mapping: LabelMapping.() -> Unit = {}
    ) : LabelAesthetics,
        LabelParameters,
        WithSizeUnitOption,
        WithColorOption,
        WithFillOption,
        GeomOptions(
            GeomKind.LABEL,
            LabelMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<LabelAesthetics>.seal() +
                super<LabelParameters>.seal() +
                super<WithSizeUnitOption>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
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
        override val whiskerWidth: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val size: Number? = null,
        override val linetype: Any? = null,
        override val shape: Any? = null,
        override val width: Number? = null,
        override val colorBy: String? = null,
        override val fillBy: String? = null,
        mapping: BoxplotMapping.() -> Unit = {}
    ) : BoxplotAesthetics,
        BoxplotParameters,
        WithColorOption,
        WithFillOption,
        GeomOptions(
            GeomKind.BOX_PLOT,
            BoxplotMapping().apply(mapping).seal()
        ) {

        override val parameters = this.seal()

        override fun seal() = super<BoxplotAesthetics>.seal() +
                super<BoxplotParameters>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
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
        override val colorBy: String? = null,
        mapping: ErrorBarMapping.() -> Unit = {}
    ) : ErrorBarAesthetics,
        WithColorOption,
        GeomOptions(
            GeomKind.ERROR_BAR,
            ErrorBarMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<ErrorBarAesthetics>.seal() + super<WithColorOption>.seal()
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
        override val size: Number? = null,
        override val colorBy: String? = null,
        override val fillBy: String? = null,
        mapping: CrossBarMapping.() -> Unit = {}
    ) : CrossBarAesthetics,
        WithColorOption,
        WithFillOption,
        GeomOptions(
            GeomKind.CROSS_BAR,
            CrossBarMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<CrossBarAesthetics>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
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
        override val colorBy: String? = null,
        override val fillBy: String? = null,
        mapping: PointRangeMapping.() -> Unit = {}
    ) : PointRangeAesthetics,
        WithColorOption,
        WithFillOption,
        GeomOptions(
            GeomKind.POINT_RANGE,
            PointRangeMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<PointRangeAesthetics>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
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
        override val colorBy: String? = null,
        mapping: LineRangeMapping.() -> Unit = {}
    ) : LineRangeAesthetics,
        WithColorOption,
        GeomOptions(
            GeomKind.LINE_RANGE,
            LineRangeMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<LineRangeAesthetics>.seal() + super<WithColorOption>.seal()
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
        override val colorBy: String? = null,
        override val fillBy: String? = null,
        mapping: RibbonMapping.() -> Unit = {}
    ) : RibbonAesthetics,
        WithColorOption,
        WithFillOption,
        GeomOptions(
            GeomKind.RIBBON,
            RibbonMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<RibbonAesthetics>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
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
        override val colorBy: String? = null,
        override val fillBy: String? = null,
        mapping: PolygonMapping.() -> Unit = {}
    ) : PolygonAesthetics,
        WithColorOption,
        WithFillOption,
        GeomOptions(
            GeomKind.POLYGON,
            PolygonMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<PolygonAesthetics>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
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
        override val colorBy: String? = null,
        override val fillBy: String? = null,
        mapping: PolygonMapping.() -> Unit = {}
    ) : PolygonAesthetics,
        WithColorOption,
        WithFillOption,
        GeomOptions(
            GeomKind.MAP,
            PolygonMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<PolygonAesthetics>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }

    @Suppress("ClassName")
    class step(
        override val x: Number? = null,
        override val y: Number? = null,
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        override val colorBy: String? = null,
        mapping: LineMapping.() -> Unit = {}
    ) : LineAesthetics,
        WithColorOption,
        GeomOptions(
            GeomKind.STEP,
            LineMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<LineAesthetics>.seal() + super<WithColorOption>.seal()
    }

    @Suppress("ClassName")
    class qq(
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val shape: Any? = null,
        override val size: Number? = null,
        override val colorBy: String? = null,
        override val fillBy: String? = null,
        mapping: QQMapping.() -> Unit = {}
    ) : QQAesthetics,
        WithColorOption,
        WithFillOption,
        GeomOptions(
            GeomKind.Q_Q,
            QQMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<QQAesthetics>.seal() +
                super<WithColorOption>.seal() +
                super<WithFillOption>.seal()
    }

    @Suppress("ClassName")
    class qqLine(
        override val alpha: Number? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Number? = null,
        override val colorBy: String? = null,
        mapping: QQLineMapping.() -> Unit = {}
    ) : QQLineAesthetics,
        WithColorOption,
        GeomOptions(
            GeomKind.Q_Q_LINE,
            QQLineMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal() = super<QQLineAesthetics>.seal() + super<WithColorOption>.seal()
    }

    @Suppress("ClassName")
    class pie(
        override val x: Number? = null,
        override val y: Number? = null,
        override val slice: Number? = null,
        override val explode: Number? = null,
        override val size: Number? = null,
        override val fill: Any? = null,
        override val alpha: Number? = null,
        override val hole: Number? = null,
        override val stroke: Number? = null,
        override val strokeColor: Any? = null,
        override val fillBy: String? = null,
        mapping: PieMapping.() -> Unit = {}
    ) : PieAesthetics,
        PieParameters,
        WithFillOption,
        GeomOptions(
            GeomKind.PIE,
            PieMapping().apply(mapping).seal()
        ) {

        override val parameters = this.seal()

        override fun seal() = super<PieAesthetics>.seal() +
                super<PieParameters>.seal() +
                super<WithFillOption>.seal()
    }
}