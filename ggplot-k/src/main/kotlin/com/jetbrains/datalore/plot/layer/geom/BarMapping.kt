package com.jetbrains.datalore.plot.layer.geom

import com.jetbrains.datalore.plot.layer.WithGroupOption

class BarMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var width: Any? = null,
    override var size: Any? = null,
    override var group: Any? = null
) : BarAesthetics, WithGroupOption {
    override fun seal() = super.seal() + group()
}


