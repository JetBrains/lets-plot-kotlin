package jetbrains.letsPlot.intern.layer.stat

import jetbrains.datalore.plot.base.stat.BoxplotStat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

interface BoxplotParameters : OptionsCapsule {
    val varwidth: Any?
    val coef: Any?

    override fun seal() = Options.of(
        BoxplotStat.P_VARWIDTH to varwidth,
        BoxplotStat.P_COEF to coef
    )
}
