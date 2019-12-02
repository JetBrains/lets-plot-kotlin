package jetbrains.letsPlot.intern.layer.geom

import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

interface BoxplotParameters : OptionsCapsule {
    val outlierColor: Any?
    val outlierFill: Any?
    val outlierShape: Any?
    val outlierSize: Any?
    val varwidth: Any?

    override fun seal() = Options.of(
        Option.Geom.BoxplotOutlier.COLOR to outlierColor,
        Option.Geom.BoxplotOutlier.FILL to outlierFill,
        Option.Geom.BoxplotOutlier.SHAPE to outlierShape,
        Option.Geom.BoxplotOutlier.SIZE to outlierSize,
        "varwidth" to varwidth
    )
}
