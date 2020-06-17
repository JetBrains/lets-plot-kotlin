/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.intern.GeomKind
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.GeomOptions
import jetbrains.letsPlot.intern.layer.geom.*
import jetbrains.letsPlot.intern.layer.geom.Bin2dMapping
import jetbrains.letsPlot.intern.layer.geom.BoxplotAesthetics
import jetbrains.letsPlot.intern.layer.geom.ContourMapping
import jetbrains.letsPlot.intern.layer.stat.*

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
    class density(
        mapping: AreaMapping.() -> Unit = {},
        override val x: Double? = null,
        override val y: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val linetype: Any? = null,
        override val size: Double? = null,
        override val weight: Any? = null
    ) : DensityAesthetics,
        GeomOptions(
            GeomKind.DENSITY,
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

    @Suppress("ClassName", "SpellCheckingInspection")
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
        override val fatten: Double? = null,
        override val varWidth: Boolean? = null,
        @Suppress("SpellCheckingInspection")
        override val coef: Any? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val size: Double? = null,
        override val linetype: Any? = null,
        override val shape: Any? = null,
        override val width: Double? = null,
        override val weight: Any? = null
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

    @Suppress("ClassName", "SpellCheckingInspection")
    class errorbar(
        mapping: ErrorBarMapping.() -> Unit = {},
        override val x: Double? = null,
        override val ymin: Double? = null,
        override val ymax: Double? = null,
        override val width: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Double? = null
    ) : ErrorBarAesthetics,
        GeomOptions(
            GeomKind.ERROR_BAR,
            ErrorBarMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class crossbar(
        mapping: CrossBarMapping.() -> Unit = {},
        override val x: Double? = null,
        override val ymin: Double? = null,
        override val ymax: Double? = null,
        override val middle: Double? = null,
        override val width: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val linetype: Any? = null,
        override val shape: Any? = null,
        override val size: Any? = null
    ) : CrossBarAesthetics,
        GeomOptions(
            GeomKind.CROSS_BAR,
            CrossBarMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName", "SpellCheckingInspection")
    class pointrange(
        mapping: PointRangeMapping.() -> Unit = {},
        override val x: Double? = null,
        override val y: Double? = null,
        override val ymin: Double? = null,
        override val ymax: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val linetype: Any? = null,
        override val shape: Any? = null,
        override val size: Double? = null
    ) : PointRangeAesthetics,
        GeomOptions(
            GeomKind.POINT_RANGE,
            PointRangeMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName", "SpellCheckingInspection")
    class linerange(
        mapping: LineRangeMapping.() -> Unit = {},
        override val x: Double? = null,
        override val ymin: Double? = null,
        override val ymax: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Double? = null
    ) : LineRangeAesthetics,
        GeomOptions(
            GeomKind.LINE_RANGE,
            LineRangeMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class ribbon(
        mapping: RibbonMapping.() -> Unit = {},
        override val x: Double? = null,
        override val ymin: Double? = null,
        override val ymax: Double? = null,
        override val size: Double? = null,
        override val linetype: Any? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val alpha: Double? = null
    ) : RibbonAesthetics,
        GeomOptions(
            GeomKind.RIBBON,
            RibbonMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class polygon(
        mapping: PolygonMapping.() -> Unit = {},
        override val x: Any? = null,
        override val y: Any? = null,
        override val size: Any? = null,
        override val linetype: Any? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val alpha: Any? = null
        // TODO add map/map_join parameters support
    ) : PolygonAesthetics,
        GeomOptions(
            GeomKind.POLYGON,
            PolygonMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class freqpoly(
        mapping: LineMapping.() -> Unit = {},
        override val x: Double? = null,
        override val y: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Double? = null,
        override val binCount: Int = BinParameters.DEF_BIN_COUNT,
        override val binWidth: Double? = null,
        override val center: Double? = null,
        override val boundary: Double? = null
    ) : LineAesthetics,
        BinParameters,
        GeomOptions(
            GeomKind.FREQPOLY,
            LineMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
        override fun seal(): Options {
            return super<LineAesthetics>.seal() +
                    super<BinParameters>.seal()
        }
    }

    @Suppress("ClassName")
    class step(
        mapping: LineMapping.() -> Unit = {},
        override val x: Double? = null,
        override val y: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Double? = null
    ) : LineAesthetics,
        GeomOptions(
            GeomKind.STEP,
            LineMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class image(
        mapping: ImageMapping.() -> Unit = {},
        override val xmin: Any? = null,
        override val xmax: Any? = null,
        override val ymin: Any? = null,
        override val ymax: Any? = null
    ) : ImageAesthetics,
        GeomOptions(
            GeomKind.IMAGE,
            ImageMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class jitter(
        mapping: PointMapping.() -> Unit = {},
        override val x: Double? = null,
        override val y: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val shape: Any? = null,
        override val size: Double? = null,
        override val stroke: Double? = null,
        override val width: Double? = null,
        override val height: Double? = null
    ) : PointAesthetics,
        JitterParameters,
        GeomOptions(
            GeomKind.JITTER,
            PointMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
        override fun seal(): Options {
            return super<PointAesthetics>.seal() +
                    super<JitterParameters>.seal()
        }
    }

    @Suppress("ClassName")
    class bin2d(
        mapping: Bin2dMapping.() -> Unit = {},
        override val x: Double? = null,
        override val y: Double? = null,
        override val width: Double? = null,
        override val height: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val fill: Any? = null,
        override val linetype: Any? = null,
        override val size: Double? = null,
        override val weight: Any? = null,
        override val binCount: Pair<Int, Int>? = null,
        override val binWidth: Pair<Number?, Number?>? = null,
        override val drop: Boolean? = null
    ) : TileAesthetics,
        Bin2dAesthetics,
        Bin2dParameters,
        GeomOptions(
            GeomKind.BIN_2D,
            Bin2dMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()

        override fun seal(): Options {
            return super<TileAesthetics>.seal() +
                    super<Bin2dAesthetics>.seal() +
                    super<Bin2dParameters>.seal()
        }
    }

    @Suppress("ClassName")
    class contour(
        mapping: ContourMapping.() -> Unit = {},
        override val x: Double? = null,
        override val y: Double? = null,
        override val z: Double? = null,
        override val alpha: Double? = null,
        override val color: Any? = null,
        override val linetype: Any? = null,
        override val size: Double? = null,
        override val speed: Double? = null,
        override val flow: Double? = null,
        override val binCount: Int? = null,
        override val binWidth: Double? = null
    ) : PathAesthetics,
        ContourAesthetics,
        ContourParameters,
        GeomOptions(
            GeomKind.CONTOUR,
            ContourMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
        override fun seal(): Options {
            return super<PathAesthetics>.seal() +
                    super<ContourAesthetics>.seal() +
                    super<ContourParameters>.seal()
        }
    }
}