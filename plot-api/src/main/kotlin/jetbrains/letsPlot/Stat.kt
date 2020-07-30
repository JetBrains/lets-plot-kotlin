/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.intern.StatKind
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.stat.*

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
        mapping: DensityStatMapping.() -> Unit = {}
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
    class bin2d(
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
        override val binWidth: Double? = null,
        mapping: ContourStatMapping.() -> Unit = {}
    ) : ContourStatParameters,
        StatOptions(
            StatKind.CONTOUR,
            mapping = ContourStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class contourf(
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
    class density2d(
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
    class density2df(
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
}
