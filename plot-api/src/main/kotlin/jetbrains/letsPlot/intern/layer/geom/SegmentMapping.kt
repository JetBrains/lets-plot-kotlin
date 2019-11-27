package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.layer.WithGroupOption

class SegmentMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var xend: Any? = null,
    override var yend: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override val linetype: Any? = null,
    override val size: Double? = null,
    override val speed: Double? = null,
    override val flow: Double? = null,
    override var group: Any? = null
) : SegmentAesthetics, WithGroupOption {
    override fun seal() = super.seal() + group()
}


