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
        override val binCount: Int = BinParameters.DEF_BIN_COUNT,
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
        override val bins: List<Int> = List(2) { Bin2dParameters.DEF_BINS },
        override val binwidth: List<Double?> = List(2) { Bin2dParameters.DEF_BINWIDTH },
        override val drop: Boolean = Bin2dParameters.DEF_DROP
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
}
