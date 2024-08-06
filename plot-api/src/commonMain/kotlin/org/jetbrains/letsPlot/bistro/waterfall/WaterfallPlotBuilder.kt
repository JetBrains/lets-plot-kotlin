package org.jetbrains.letsPlot.bistro.waterfall

import org.jetbrains.letsPlot.core.spec.Option.Plot.BISTRO
import org.jetbrains.letsPlot.core.spec.back.transform.bistro.waterfall.Option.Waterfall
import org.jetbrains.letsPlot.intern.*
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.tooltips.TooltipOptions

internal class WaterfallPlotBuilder(
    private val data: Map<*, *>,
    private val x: String,
    private val y: String,
    private val measure: String?,
    private val group: String?,
    private val color: String?,
    private val fill: String?,
    private val size: Double?,
    private val alpha: Double?,
    private val linetype: Any?,
    private val width: Double?,
    private val showLegend: Boolean?,
    private val relativeTooltips: Any?,
    private val absoluteTooltips: Any?,
    private val calcTotal: Boolean?,
    private val totalTitle: String?,
    private val sortedValue: Boolean?,
    private val threshold: Double?,
    private val maxValues: Int?,
    private val hline: Any?,
    private val hlineOntop: Boolean?,
    private val connector: Any?,
    private val label: Any?,
    private val labelFormat: String?
) {
    fun build(): Plot {
        return letsPlot(data) + OptionsMap(
            kind = BISTRO,
            name = Waterfall.NAME,
            options = mapOf(
                Waterfall.X to x,
                Waterfall.Y to y,
                Waterfall.MEASURE to measure,
                Waterfall.GROUP to group,
                Waterfall.COLOR to color,
                Waterfall.FILL to fill,
                Waterfall.SIZE to size,
                Waterfall.ALPHA to alpha,
                Waterfall.LINE_TYPE to linetype,
                Waterfall.WIDTH to width,
                Waterfall.SHOW_LEGEND to showLegend,
                Waterfall.RELATIVE_TOOLTIPS to when (relativeTooltips) {
                    is TooltipOptions -> relativeTooltips.options
                    else -> relativeTooltips
                },
                Waterfall.ABSOLUTE_TOOLTIPS to when (absoluteTooltips) {
                    is TooltipOptions -> absoluteTooltips.options
                    else -> absoluteTooltips
                },
                Waterfall.CALCULATE_TOTAL to calcTotal,
                Waterfall.TOTAL_TITLE to totalTitle,
                Waterfall.SORTED_VALUE to sortedValue,
                Waterfall.THRESHOLD to threshold,
                Waterfall.MAX_VALUES to maxValues,
                Waterfall.H_LINE to hline,
                Waterfall.H_LINE_ON_TOP to hlineOntop,
                Waterfall.CONNECTOR to connector,
                Waterfall.LABEL to label,
                Waterfall.LABEL_FORMAT to labelFormat
            ).filterNonNullValues()
        )
    }
}