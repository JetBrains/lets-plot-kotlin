package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

/**
 * TODO
 */
class BracketDodgeMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var istart: Any? = null,
    override var iend: Any? = null,
    override var lenStart: Any? = null,
    override var lenEnd: Any? = null,
    override var label: Any? = null,
    override var size: Any? = null,
    override var linetype: Any? = null,
    override var color: Any? = null,
    override var alpha: Any? = null,
    override var family: Any? = null,
    override var fontface: Any? = null,
    override var hjust: Any? = null,
    override var vjust: Any? = null,
    override var angle: Any? = null,
    override var lineheight: Any? = null,
    override var segmentColor: Any? = null,
    override var segmentSize: Any? = null,
    override var segmentAlpha: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : BracketDodgeAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<BracketDodgeAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}