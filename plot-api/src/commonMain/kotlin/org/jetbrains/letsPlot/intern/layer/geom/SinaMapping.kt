package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

class SinaMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var violinWidth: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var shape: Any? = null,
    override var size: Any? = null,
    override var stroke: Any? = null,
    override var width: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : SinaAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<SinaAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}