package jetbrains.datalorePlot

import jetbrains.datalorePlot.intern.StatKind
import jetbrains.datalorePlot.intern.layer.StatOptions
import jetbrains.datalorePlot.intern.layer.stat.*

object Stat {
    val identity = StatOptions(
        StatKind.IDENTITY
    )

    @Suppress("ClassName")
    class density(
        mapping: DensityMapping.() -> Unit = {},
        override val bw: Any? = null,
        override val kernel: Any? = null,
        override val n: Any? = null,
        override val trim: Any? = null
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
}
