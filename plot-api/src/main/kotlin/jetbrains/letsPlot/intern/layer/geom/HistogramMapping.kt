package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.layer.WithGroupOption
import jetbrains.letsPlot.intern.layer.stat.BinAesthetics

class HistogramMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var size: Any? = null,
    override var weight: Any? = null,
    override var group: Any? = null
) : HistogramAesthetics, BinAesthetics, WithGroupOption {
    override fun seal() = super<HistogramAesthetics>.seal() +
            super<BinAesthetics>.seal() +
            group()
}
