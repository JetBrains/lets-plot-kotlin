package jetbrains.datalorePlot.stat

import jetbrains.datalorePlot.intern.Options
import jetbrains.datalorePlot.Geom.area
import jetbrains.datalorePlot.Pos.stack
import jetbrains.datalorePlot.Stat.density
import jetbrains.datalorePlot.intern.layer.GeomOptions
import jetbrains.datalorePlot.intern.layer.LayerBase
import jetbrains.datalorePlot.intern.layer.PosOptions
import jetbrains.datalorePlot.intern.layer.stat.DensityAesthetics
import jetbrains.datalorePlot.intern.layer.stat.DensityMapping
import jetbrains.datalorePlot.intern.layer.stat.DensityParameters

@Suppress("ClassName")
class stat_density(
    data: Any? = null,
    geom: GeomOptions = area(),
    position: PosOptions = stack,
    show_legend: Boolean = true,
    sampling: Any? = null,
    override val x: Any? = null,
    override val y: Any? = null,
    override val alpha: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Any? = null,
    override val weight: Any? = null,
    override val bw: Any? = null,
    override val kernel: Any? = null,
    override val n: Any? = null,
    override val trim: Any? = null,
    mapping: DensityMapping.() -> Unit = {}

) : DensityAesthetics, DensityParameters,
    LayerBase(
        mapping = DensityMapping().apply(mapping).seal(),
        data = data,
        geom = geom,
        stat = density(),
        position = position,
        show_legend = show_legend,
        sampling = sampling

    ) {

    override fun seal(): Options {
        return super<DensityAesthetics>.seal() +
                super<DensityParameters>.seal()
    }
}

