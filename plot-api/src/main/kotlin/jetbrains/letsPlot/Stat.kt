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
        mapping: DensityMapping.() -> Unit = {},
        override val bw: Any? = null,
        override val kernel: String? = null,
        override val n: Int? = null,
        override val trim: Boolean? = null,
        override val adjust: Double? = null
    ) : DensityParameters,
        StatOptions(
            StatKind.DENSITY,
            mapping = DensityMapping().apply(mapping).seal()
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
        mapping: BinStatMapping.() -> Unit = {},
        override val binCount: Int = BinStatParameters.DEF_BIN_COUNT,
        override val binWidth: Double? = null,
        override val center: Double? = null,
        override val boundary: Double? = null
    ) : BinStatParameters,
        StatOptions(
            StatKind.BIN,
            mapping = BinStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class boxplot(
        mapping: BoxplotStatMapping.() -> Unit = {},
        override val varWidth: Boolean? = null,
        @Suppress("SpellCheckingInspection")
        override val coef: Double? = null
    ) : BoxplotStatParameters,
        StatOptions(
            StatKind.BOXPLOT,
            mapping = BoxplotStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class bin2d(
        mapping: Bin2dStatMapping.() -> Unit = {},
        override val binCount: Pair<Int, Int>? = null,
        override val binWidth: Pair<Number?, Number?>? = null,
        override val drop: Boolean? = null
    ) : Bin2dStatParameters,
        StatOptions(
            StatKind.BIN2D,
            mapping = Bin2dStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class contour(
        mapping: ContourStatMapping.() -> Unit = {},
        override val binCount: Int? = null,
        override val binWidth: Double? = null
    ) : ContourStatParameters,
        StatOptions(
            StatKind.CONTOUR,
            mapping = ContourStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class contourf(
        mapping: ContourStatMapping.() -> Unit = {},
        override val binCount: Int? = null,
        override val binWidth: Number? = null
    ) : ContourStatParameters,
        StatOptions(
            StatKind.CONTOURF,
            mapping = ContourStatMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class density2d(
        mapping: Density2dMapping.() -> Unit = {},
        override val bw: Any? = null,
        override val kernel: String? = null,
        override val n: Any? = null,
        override val adjust: Double? = null,
        override val isContour: Boolean? = null,
        override val binCount: Int? = null,
        override val binWidth: Double? = null
    ) : Density2dParameters,
        StatOptions(
            StatKind.DENSITY2D,
            mapping = Density2dMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class density2df(
        mapping: Density2dMapping.() -> Unit = {},
        override val bw: Any? = null,
        override val kernel: String? = null,
        override val n: Any? = null,
        override val adjust: Double? = null,
        override val isContour: Boolean? = null,
        override val binCount: Int? = null,
        override val binWidth: Double? = null
    ) : Density2dParameters,
        StatOptions(
            StatKind.DENSITY2DF,
            mapping = Density2dMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }
}
