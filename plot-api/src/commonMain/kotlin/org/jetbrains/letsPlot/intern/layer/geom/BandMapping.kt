package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

class BandMapping(
    override var xmin: Any? = null,
    override var xmax: Any? = null,
    override var ymin: Any? = null,
    override var ymax: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var size: Any? = null,
    override var linetype: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : BandAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<BandAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}