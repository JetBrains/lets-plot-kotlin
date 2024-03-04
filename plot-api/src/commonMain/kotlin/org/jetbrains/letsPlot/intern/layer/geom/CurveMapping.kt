package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

class CurveMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var xend: Any? = null,
    override var yend: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var linetype: Any? = null,
    override var size: Any? = null,
    override var sizeStart: Any? = null,
    override var sizeEnd: Any? = null,
    override var strokeStart: Any? = null,
    override var strokeEnd: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null,
) : CurveAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<CurveAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}

