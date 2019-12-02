package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom.boxplot
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.BoxplotAesthetics
import jetbrains.letsPlot.intern.layer.geom.BoxplotMapping
import jetbrains.letsPlot.intern.layer.geom.BoxplotParameters

@Suppress("ClassName", "unused")
class geom_boxplot(
    data: Any? = null,
    stat: StatOptions = Stat.boxplot(),
    position: PosOptions = Pos.dodge,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val x: Double? = null,
    override val lower: Double? = null,
    override val middle: Double? = null,
    override val upper: Double? = null,
    override val ymin: Double? = null,
    override val ymax: Double? = null,
    override val outlierColor: Any? = null,
    override val outlierFill: Any? = null,
    override val outlierShape: Any? = null,
    override val outlierSize: Double? = null,
    override val varwidth: Boolean? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val size: Double? = null,
    override val linetype: Any? = null,
    override val shape: Any? = null,
    override val width: Any? = null,
    mapping: BoxplotMapping.() -> Unit = {}

) : BoxplotAesthetics,
    BoxplotParameters,
    LayerBase(
        mapping = BoxplotMapping().apply(mapping).seal(),
        data = data,
        geom = boxplot(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    ) {
    override fun seal(): Options {
        return super<BoxplotAesthetics>.seal() +
                super<BoxplotParameters>.seal()
    }
}
