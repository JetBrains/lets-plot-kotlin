package jetbrains.letsPlot

import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.OtherPlotFeature

@Suppress("SpellCheckingInspection")
fun ggtitle(title: String): OtherPlotFeature {
    return OtherPlotFeature(
        Option.Plot.TITLE,
        mapOf(
            "text" to title
        )
    )
}
