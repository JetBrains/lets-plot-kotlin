package jetbrains.datalorePlot.intern.layer.geom

import jetbrains.datalorePlot.intern.layer.WithGroupOption

class LineMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var linetype: Any? = null,
    override var size: Any? = null,
    override var group: Any? = null
) : LineAesthetics, WithGroupOption {
    override fun seal() = super.seal() + group()
}


