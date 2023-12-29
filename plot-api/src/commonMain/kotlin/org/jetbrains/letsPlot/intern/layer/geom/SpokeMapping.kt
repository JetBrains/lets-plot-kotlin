package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

class SpokeMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var angle: Any? = null,
    override var radius: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var linetype: Any? = null,
    override var size: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : SpokeAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<SpokeAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}
