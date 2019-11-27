package jetbrains.letsPlot

import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.OtherPlotFeature

fun theme(
    axis_title: Any? = null,
    axis_title_x: Any? = null,
    axis_title_y: Any? = null,
    axis_text: Any? = null,
    axis_text_x: Any? = null,
    axis_text_y: Any? = null,
    axis_ticks: Any? = null,
    axis_ticks_x: Any? = null,
    axis_ticks_y: Any? = null,
    axis_line: Any? = null,
    axis_line_x: Any? = null,
    axis_line_y: Any? = null,
    legend_position: Any? = null,
    otherOptions: Options = Options.empty()
) : OtherPlotFeature {
    @Suppress("UNCHECKED_CAST")
    val options = mapOf(
        "axis_title" to axis_title,
        "axis_title_x" to axis_title_x,
        "axis_title_y" to axis_title_y,
        "axis_text" to axis_text,
        "axis_text_x" to axis_text_x,
        "axis_text_y" to axis_text_y,
        "axis_ticks" to axis_ticks,
        "axis_ticks_x" to axis_ticks_x,
        "axis_ticks_y" to axis_ticks_y,
        "axis_line" to axis_line,
        "axis_line_x" to axis_line_x,
        "axis_line_y" to axis_line_y,
        "legend_position" to legend_position,
        "other_options" to otherOptions
    ).filter { it.value != null } as Map<String, Any>

    return OtherPlotFeature(Option.Plot.THEME, options)
}
