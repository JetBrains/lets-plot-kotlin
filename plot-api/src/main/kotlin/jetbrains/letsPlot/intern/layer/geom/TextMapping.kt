package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.layer.WithGroupOption

class TextMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var label: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var size: Any? = null,
    override var family: Any? = null,
    override var fontface: Any? = null,
    override var hjust: Any? = null,
    override var vjust: Any? = null,
    override var angle: Any? = null,
    override var group: Any? = null
) : TextAesthetics, WithGroupOption {
    override fun seal() = super.seal() + group()
}


