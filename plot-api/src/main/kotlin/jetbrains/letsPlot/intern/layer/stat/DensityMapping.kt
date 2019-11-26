package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.layer.WithGroupOption

class DensityMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var linetype: Any? = null,
    override var size: Any? = null,
    override var weight: Any? = null,
    override var group: Any? = null
) : DensityAesthetics, WithGroupOption {
    override fun seal() = super.seal() + group()
}
