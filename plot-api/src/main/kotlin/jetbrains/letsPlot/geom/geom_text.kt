package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom.text
import jetbrains.letsPlot.Pos.identity
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.TextAesthetics
import jetbrains.letsPlot.intern.layer.geom.TextMapping

@Suppress("ClassName")
class geom_text(
    data: Any? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = identity,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val x: Double? = null,
    override val y: Double? = null,
    override val label: String? = null,
    override val alpha: Double? = null,
    override val color: Any? = null,
    override val size: Double? = null,
    override val family: String? = null,
    override val fontface: String? = null,
    override val hjust: Any? = null,
    override val vjust: Any? = null,
    override val angle: Double? = null,
    mapping: TextMapping.() -> Unit = {}

) : TextAesthetics,
    LayerBase(
        mapping = TextMapping().apply(mapping).seal(),
        data = data,
        geom = text(),
        stat = stat,
        position = position,
        show_legend = show_legend,
        sampling = sampling
    )