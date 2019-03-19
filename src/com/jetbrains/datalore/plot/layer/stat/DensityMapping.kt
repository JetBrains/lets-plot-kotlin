package com.jetbrains.datalore.plot.layer.stat

import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.layer.WithGroupOption
import com.jetbrains.datalore.plot.layer.geom.AreaAesthetics

interface DensityAesthetics : AreaAesthetics {
    val weight: Any?

    override fun seal(): Options {
        return super.seal() +
                Options.of("weight" to weight)
    }
}

class DensityMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var linetype: Any? = null,
    override var size: Any? = null,
    override var weight: Any? = null,
    override var group: Any? = null
) : DensityAesthetics, WithGroupOption {
    override fun seal() = super.seal() + group()
}
