package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.layer.WithGroupOption

class PathMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override val linetype: Any? = null,
    override val size: Double? = null,
    override val speed: Double? = null,
    override val flow: Double? = null,
    override var group: Any? = null
) : PathAesthetics, WithGroupOption {
    override fun seal() = super.seal() + group()
}


