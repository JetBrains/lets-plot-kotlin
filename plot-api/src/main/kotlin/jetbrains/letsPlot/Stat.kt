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
    class density2d(
        mapping: Density2dMapping.() -> Unit = {},
        override val bw: Any? = null,
        override val kernel: String? = null,
        override val n: Int? = null,
        override val adjust: Double? = null,
        override val contour: Boolean? = null,
        override val bins: Int? = null,
        override val binwidth: Double? = null
    ) : Density2dParameters,
        StatOptions(
            StatKind.DENSITY2D,
            mapping = Density2dMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class density2df(
        mapping: Density2dfMapping.() -> Unit = {},
        override val bw: Any? = null,
        override val kernel: String? = null,
        override val n: Int? = null,
        override val adjust: Double? = null,
        override val contour: Boolean? = null,
        override val bins: Int? = null,
        override val binwidth: Double? = null
    ) : Density2dParameters,
        StatOptions(
            StatKind.DENSITY2DF,
            mapping = Density2dfMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }
    @Suppress("ClassName")
    class count(
        mapping: CountMapping.() -> Unit = {}
    ) : CountParameters,
        StatOptions(
            StatKind.COUNT,
            mapping = CountMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class bin(
        mapping: BinMapping.() -> Unit = {},
        override val binCount: Int? = BinParameters.DEF_BIN_COUNT,
        override val binWidth: Double? = null,
        override val center: Double? = null,
        override val boundary: Double? = null
    ) : BinParameters,
        StatOptions(
            StatKind.BIN,
            mapping = BinMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class bin2d(
        mapping: Bin2dMapping.() -> Unit = {},
        override val bins: List<Int>? = null,
        override val binwidth: List<Double?>? = null,
        override val drop: Boolean? = null
    ) : Bin2dParameters,
        StatOptions(
            StatKind.BIN2D,
            mapping = Bin2dMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class boxplot(
        mapping: BoxplotMapping.() -> Unit = {},
        override val varwidth: Boolean? = null,
        override val coef: Double? = null
    ) : BoxplotParameters,
        StatOptions(
            StatKind.BOXPLOT,
            mapping = BoxplotMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class contour(
        mapping: ContourMapping.() -> Unit = {},
        override val bins: Int = ContourParameters.DEF_BIN_COUNT,
        override val binwidth: Double? = null
    ) : ContourParameters,
        StatOptions(
            StatKind.CONTOUR,
            mapping = ContourMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class contourf(
        mapping: ContourfMapping.() -> Unit = {},
        override val bins: Int = ContourfParameters.DEF_BIN_COUNT,
        override val binwidth: Double? = null
    ) : ContourfParameters,
        StatOptions(
            StatKind.CONTOURF,
            mapping = ContourfMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

    @Suppress("ClassName")
    class smooth(
        mapping: CountMapping.() -> Unit = {},
        override val method: String? = null,
        override val n: Int? = null,
        override val level: Double? = null,
        override val se: Boolean? = null,
        override val span: Double? = null,
        override val deg: Int? = null,
        override val seed: Long? = null,
        override val max_n: Int? = null
    ) : SmoothParameters,
        StatOptions(
            StatKind.SMOOTH,
            mapping = CountMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }
}
