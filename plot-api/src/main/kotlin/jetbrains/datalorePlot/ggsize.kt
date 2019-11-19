package jetbrains.datalorePlot

import jetbrains.datalore.plot.config.Option
import jetbrains.datalorePlot.intern.OtherPlotFeature

@Suppress("SpellCheckingInspection")
fun ggsize(width: Int, height: Int): OtherPlotFeature {
    return OtherPlotFeature(
        Option.Plot.SIZE,
        mapOf(
            "width" to width,
            "height" to height
        )
    )
}

