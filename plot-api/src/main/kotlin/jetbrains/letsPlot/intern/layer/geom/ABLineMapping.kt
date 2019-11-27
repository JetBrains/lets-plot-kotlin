package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.layer.WithGroupOption

class ABLineMapping(
    override var slope: Any? = null,
    override var intercept: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var linetype: Any? = null,
    override var size: Any? = null,
    override var group: Any? = null
) : ABLineAesthetics, WithGroupOption {
    override fun seal() = super.seal() + group()
}


