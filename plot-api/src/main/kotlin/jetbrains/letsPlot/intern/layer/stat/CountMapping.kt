package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.layer.WithGroupOption

class CountMapping(
    var x: Any? = null,
    var y: Any? = null,
    var alpha: Any? = null,
    var color: Any? = null,
    var fill: Any? = null,
    var width: Any? = null,
    var size: Any? = null,
    override var weight: Any? = null,
    override var group: Any? = null
) : CountAesthetics, WithGroupOption {
    override fun seal() = super.seal() + group()
}
