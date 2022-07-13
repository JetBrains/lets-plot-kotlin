/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.intern.StatKind
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.stat.*

/**
 * Stat options to pass as a value of `stat` parameter of layer functions like:
 *
 * ```kotlin
 * val n = 100
 * val rand = java.util.Random(42)
 * val data = mapOf("x" to List(n) { rand.nextGaussian() })
 * letsPlot(data) +
 *     geomArea(stat = Stat.density()) { x="x" }
 * ```
 */
object Stat {
    val identity = StatOptions(
        StatKind.IDENTITY
    )

    @Suppress("ClassName")
    class density(
        override val bw: Any? = null,
        override val kernel: String? = null,
        override val n: Int? = null,
        override val trim: Boolean? = null,
        override val adjust: Number? = null,
        override val fullScanMax: Int? = null,
        mapping: DensityStatMapping.() -> Unit = {},
    ) : DensityStatParameters,
        StatOptions(
            StatKind.DENSITY,
            mapping = DensityStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class count(
        mapping: CountStatMapping.() -> Unit = {}
    ) : StatOptions(
        StatKind.COUNT,
        mapping = CountStatMapping().apply(mapping).seal()
    ) {
    }

    @Suppress("ClassName")
    class bin(
        override val bins: Int? = null,
        override val binWidth: Number? = null,
        override val center: Number? = null,
        override val boundary: Number? = null,
        mapping: BinStatMapping.() -> Unit = {}
    ) : BinStatParameters,
        StatOptions(
            StatKind.BIN,
            mapping = BinStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class boxplot(
        override val varWidth: Boolean? = null,
        @Suppress("SpellCheckingInspection")
        override val coef: Number? = null,
        mapping: BoxplotStatMapping.() -> Unit = {}
    ) : BoxplotStatParameters,
        StatOptions(
            StatKind.BOXPLOT,
            mapping = BoxplotStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class bin2D(
        override val bins: Pair<Int, Int>? = null,
        override val binWidth: Pair<Number?, Number?>? = null,
        override val drop: Boolean? = null,
        mapping: Bin2dStatMapping.() -> Unit = {}
    ) : Bin2dStatParameters,
        StatOptions(
            StatKind.BIN2D,
            mapping = Bin2dStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class contour(
        override val bins: Int? = null,
        override val binWidth: Number? = null,
        mapping: ContourStatMapping.() -> Unit = {}
    ) : ContourStatParameters,
        StatOptions(
            StatKind.CONTOUR,
            mapping = ContourStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class contourFilled(
        override val bins: Int? = null,
        override val binWidth: Number? = null,
        mapping: ContourStatMapping.() -> Unit = {}
    ) : ContourStatParameters,
        StatOptions(
            StatKind.CONTOURF,
            mapping = ContourStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class density2D(
        override val bw: Any? = null,
        override val kernel: String? = null,
        override val n: Int? = null,
        override val adjust: Number? = null,
        override val contour: Boolean? = null,
        override val bins: Int? = null,
        override val binWidth: Number? = null,
        mapping: Density2dStatMapping.() -> Unit = {}
    ) : Density2dStatParameters,
        StatOptions(
            StatKind.DENSITY2D,
            mapping = Density2dStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class density2DFilled(
        override val bw: Any? = null,
        override val kernel: String? = null,
        override val n: Int? = null,
        override val adjust: Number? = null,
        override val contour: Boolean? = null,
        override val bins: Int? = null,
        override val binWidth: Number? = null,
        mapping: Density2dStatMapping.() -> Unit = {}
    ) : Density2dStatParameters,
        StatOptions(
            StatKind.DENSITY2DF,
            mapping = Density2dStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class yDensity(
        override val scale: String? = null,
        override val bw: Any? = null,
        override val kernel: String? = null,
        override val n: Int? = null,
        override val trim: Boolean? = null,
        override val adjust: Number? = null,
        override val fullScanMax: Int? = null,
        mapping: YDensityStatMapping.() -> Unit = {}
    ) : YDensityStatParameters,
        StatOptions(
            StatKind.YDENSITY,
            mapping = YDensityStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class smooth(
        override val method: String? = null,
        override val n: Int? = null,
        override val level: Number? = null,
        override val se: Boolean? = null,
        override val span: Number? = null,
        override val deg: Int? = null,
        override val seed: Long? = null,
        override val maxN: Int? = null,
        mapping: SmoothStatMapping.() -> Unit = {}
    ) : SmoothStatParameters,
        StatOptions(
            StatKind.SMOOTH,
            mapping = SmoothStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }


    @Suppress("ClassName")
    class dotplot(
        override val bins: Int? = null,
        override val binWidth: Number? = null,
        override val center: Number? = null,
        override val boundary: Number? = null,
        override val method: String? = null,
        mapping: DotplotStatMapping.() -> Unit = {},
    ) : DotplotStatParameters,
        StatOptions(
            StatKind.DOTPLOT,
            mapping = DotplotStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }


    @Suppress("ClassName")
    class yDotplot(
        override val bins: Int? = null,
        override val binWidth: Number? = null,
        override val center: Number? = null,
        override val boundary: Number? = null,
        override val method: String? = null,
        mapping: YDotplotStatMapping.() -> Unit = {},
    ) : YDotplotStatParameters,
        StatOptions(
            StatKind.YDOTPLOT,
            mapping = YDotplotStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class qq(
        override val distribution: String? = null,
        override val dParams: List<Number>? = null,
        mapping: QQStatMapping.() -> Unit = {}
    ) : QQStatParameters,
        StatOptions(
            StatKind.QQ,
            mapping = QQStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class qqLine(
        override val distribution: String? = null,
        override val dParams: List<Number>? = null,
        override val quantiles: Pair<Number, Number>? = null,
        mapping: QQStatMapping.() -> Unit = {}
    ) : QQLineStatParameters,
        StatOptions(
            StatKind.QQ_LINE,
            mapping = QQStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class qq2(
        mapping: QQ2StatMapping.() -> Unit = {}
    ) : StatOptions(
            StatKind.QQ2,
            mapping = QQ2StatMapping().apply(mapping).seal()
        )

    @Suppress("ClassName")
    class qq2Line(
        override val quantiles: Pair<Number, Number>? = null,
        mapping: QQ2StatMapping.() -> Unit = {}
    ) : QQ2LineStatParameters,
        StatOptions(
            StatKind.QQ2_LINE,
            mapping = QQ2StatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    // Deprecated

    @Deprecated("", ReplaceWith("Stat.bin2D(bins, binWidth, drop) { mapping() }"), level = DeprecationLevel.ERROR)
    fun bin2d(
        bins: Pair<Int, Int>? = null,
        binWidth: Pair<Number?, Number?>? = null,
        drop: Boolean? = null,
        mapping: Bin2dStatMapping.() -> Unit
    ) = bin2D(bins, binWidth, drop, mapping)

    @Deprecated("", ReplaceWith("Stat.bin2D(bins, binWidth, drop)"), level = DeprecationLevel.ERROR)
    fun bin2d(
        bins: Pair<Int, Int>? = null,
        binWidth: Pair<Number?, Number?>? = null,
        drop: Boolean? = null
    ) = bin2D(bins, binWidth, drop)

    @Deprecated("", ReplaceWith("Stat.contourFilled(bins, binWidth) { mapping() }"), level = DeprecationLevel.ERROR)
    fun contourf(
        bins: Int? = null,
        binWidth: Number? = null,
        mapping: ContourStatMapping.() -> Unit
    ) = contourFilled(bins, binWidth, mapping)

    @Deprecated("", ReplaceWith("Stat.contourFilled(bins, binWidth)"), level = DeprecationLevel.ERROR)
    fun contourf(
        bins: Int? = null,
        binWidth: Number? = null
    ) = contourFilled(bins, binWidth)

    @Deprecated("", ReplaceWith("Stat.density2D(bw, kernel, n, adjust, contour, bins, binWidth) { mapping() }"), level = DeprecationLevel.ERROR)
    fun density2d(
        bw: Any? = null,
        kernel: String? = null,
        n: Int? = null,
        adjust: Number? = null,
        contour: Boolean? = null,
        bins: Int? = null,
        binWidth: Number? = null,
        mapping: Density2dStatMapping.() -> Unit
    ) = density2D(bw, kernel, n, adjust, contour, bins, binWidth, mapping)

    @Deprecated("", ReplaceWith("Stat.density2D(bw, kernel, n, adjust, contour, bins, binWidth)"), level = DeprecationLevel.ERROR)
    fun density2d(
        bw: Any? = null,
        kernel: String? = null,
        n: Int? = null,
        adjust: Number? = null,
        contour: Boolean? = null,
        bins: Int? = null,
        binWidth: Number? = null
    ) = density2D(bw, kernel, n, adjust, contour, bins, binWidth)

    @Deprecated("", ReplaceWith("Stat.density2DFilled(bw, kernel, n, adjust, contour, bins, binWidth) { mapping() }"), level = DeprecationLevel.ERROR)
    fun density2df(
        bw: Any? = null,
        kernel: String? = null,
        n: Int? = null,
        adjust: Number? = null,
        contour: Boolean? = null,
        bins: Int? = null,
        binWidth: Number? = null,
        mapping: Density2dStatMapping.() -> Unit
    ) = density2DFilled(bw, kernel, n, adjust, contour, bins, binWidth, mapping)

    @Deprecated("", ReplaceWith("Stat.density2DFilled(bw, kernel, n, adjust, contour, bins, binWidth)"), level = DeprecationLevel.ERROR)
    fun density2df(
        bw: Any? = null,
        kernel: String? = null,
        n: Int? = null,
        adjust: Number? = null,
        contour: Boolean? = null,
        bins: Int? = null,
        binWidth: Number? = null
    ) = density2DFilled(bw, kernel, n, adjust, contour, bins, binWidth)
}