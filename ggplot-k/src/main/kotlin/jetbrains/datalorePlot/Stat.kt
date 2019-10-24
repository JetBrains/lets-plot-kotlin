package jetbrains.datalorePlot

import jetbrains.datalorePlot.intern.StatKind
import jetbrains.datalorePlot.intern.layer.StatOptions
import jetbrains.datalorePlot.intern.layer.stat.CountMapping
import jetbrains.datalorePlot.intern.layer.stat.CountParameters
import jetbrains.datalorePlot.intern.layer.stat.DensityMapping
import jetbrains.datalorePlot.intern.layer.stat.DensityParameters

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
}
