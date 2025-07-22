package org.jetbrains.letsPlot.intern.layer.stat

import org.jetbrains.letsPlot.core.spec.Option.Stat
import org.jetbrains.letsPlot.intern.Options

interface SinaStatParameters : DensityStatParameters {
    val scale: String?
    val tailsCutoff: Number?

    override fun seal() = super.seal() + Options.of(
        Stat.Sina.TAILS_CUTOFF to tailsCutoff,
        Stat.YDensity.SCALE to scale // TODO: Replace to Stat.Sina.SCALE
    )
}