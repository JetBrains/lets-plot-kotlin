/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.bistro.corr

import jetbrains.letsPlot.bistro.corr.CorrUtil.correlations
import jetbrains.letsPlot.bistro.corr.CorrUtil.correlationsToDataframe
import jetbrains.letsPlot.bistro.corr.Method.correlationPearson
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.intern.Scale
import jetbrains.letsPlot.intern.asPlotData
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.lets_plot
import jetbrains.letsPlot.scale.*
import jetbrains.letsPlot.theme
import kotlin.math.max
import kotlin.math.min

/**
 * Correlation plot builder.
 *
 * The terminal 'build()' method will create a fully configured 'Plot' (i.e. Figure) object.
 *
 * @param data Dataframe to compute correlations on.
 * @param title Plot title.
 * @param showLegend Whether to show a legend.
 * @param flip Whether to flip the y axis.
 * @param threshold Minimal correlation abs value to be included in result. Must be in interval [0.0, 1.0]
 */
class CorrPlot(
    private val data: Map<*, *>,
    private val title: String? = null,
    private val showLegend: Boolean = true,
    private val flip: Boolean = true,
    private val threshold: Double = DEF_THRESHOLD
) {
    private var format = ".2f"
    private var colorScale = colorGradient(DEF_LOW_COLOR, DEF_MID_COLOR, DEF_HIGH_COLOR)
    private var fillScale = fillGradient(DEF_LOW_COLOR, DEF_MID_COLOR, DEF_HIGH_COLOR)
    private val points = LayerParams()
    private val tiles = LayerParams()
    private val labels = LayerParams()
//        var labels_map_size = None

    /**
     * Add points layer to corr plot.
     *
     * @param type Type of the matrix: "upper", "lower" or "full".
     *             Default - contextual.
     * @param diag Whether to fill the main diagonal with values or not.
     *             Default - contextual.
     */
    fun points(type: String? = null, diag: Boolean? = null): CorrPlot {
        type?.run {
            require(type in listOf("upper", "lower", "full")) {
                """The option 'type' must be "upper", "lower" or "full" but was: "$type""""
            }
        }
        points.type = type
        points.diag = diag
        return this
    }

    fun build(): Plot {
        if (!(points.added || tiles.added || labels.added)) {
            return lets_plot()
        }

        val canDropDiag = !OptionsConfigurator.configure(tiles, points, labels, flip)

        val originalVariables = data.keys.map { it.toString() }.toList()

        // Compute correlations
        @Suppress("NAME_SHADOWING")
        val data = asPlotData(data)
        val correlations = correlations(data, ::correlationPearson)
        // variables in the 'original' order
        val varsInMatrix = correlations.keys.map { it.first }.toSet()
        val varsInOrder = originalVariables.filter { varsInMatrix.contains(it) }

        var plot = lets_plot() + colorScale + fillScale

        if (points.added) {
            val dataframe = correlationsToDataframe(
                points,
                correlations,
                varsInOrder,
                threshold,
                canDropDiag
            )
            plot += geom_point(
                data = dataframe,
                showLegend = showLegend,
                sizeUnit = "x",
            ) {
                x = CorrVar.X
                y = CorrVar.Y
                size = CorrVar.CORR_ABS
                color = CorrVar.CORR
            }
        }

        val plotSize = plotSize(varsInOrder, title != null, showLegend)
        plot += ggsize(plotSize.first, plotSize.second)

        title?.run { plot += ggtitle(title) }
        return addCommonParams(plot, tiles.added, flip)
    }

    companion object {
        private const val LEGEND_NAME = "Corr"
        private val SCALE_BREAKS = listOf(-1.0, -0.5, 0.0, 0.5, 1.0)
        private val SCALE_LABELS = listOf("-1", "-0.5", "0", "0.5", "1")
        private val SCALE_LIMITS = -1.0 to 1.0

        private const val DEF_THRESHOLD = 0.0
        private const val DEF_LOW_COLOR = "red"
        private const val DEF_MID_COLOR = "light_gray"
        private const val DEF_HIGH_COLOR = "blue"

        private const val COLUMN_WIDTH = 60
        private const val MIN_PLOT_WIDTH = 400
        private const val MAX_PLOT_WIDTH = 900
        private const val PLOT_PROPORTION = 3.0 / 4.0

        private fun colorGradient(low: String, mid: String, high: String): Scale {
            return scale_color_gradient2(
                name = LEGEND_NAME,
                low = low, mid = mid, high = high,
                breaks = SCALE_BREAKS,
                labels = SCALE_LABELS,
                limits = SCALE_LIMITS,
                naValue = "rgba(0,0,0,0)"
            )
        }

        private fun fillGradient(low: String, mid: String, high: String): Scale {
            return scale_fill_gradient2(
                name = LEGEND_NAME,
                low = low, mid = mid, high = high,
                breaks = SCALE_BREAKS,
                labels = SCALE_LABELS,
                limits = SCALE_LIMITS,
                naValue = "rgba(0,0,0,0)"
            )
        }

        private fun addCommonParams(
            plot: Plot,
            hasTiles: Boolean,
            flipY: Boolean
        ): Plot {
            @Suppress("NAME_SHADOWING")
            var plot = plot
            plot += theme()
                .axisTitle_blank()
                .axisLine_blank()

            plot += scale_size_identity(naValue = 0, guide = "none")

            // Smaller 'additive' expand for tiles (normally: 0.6)
            val scaleXYExpand = if (hasTiles) listOf(0.0, 0.1) else null
            plot += scale_x_discrete(expand = scaleXYExpand)
            plot += scale_y_discrete(expand = scaleXYExpand, reverse = flipY)
            return plot
        }

        private fun plotSize(variables: List<String>, hasTitle: Boolean, hasLegend: Boolean): Pair<Int, Int> {
            val colCount = variables.size
            // magic values
            val labelLen = variables.maxByOrNull { it.length }?.length ?: 0
            val labelWidth = (labelLen * 5.7).toInt()
            val titleHeight = if (hasTitle) 20 else 0
            val legendWidth = if (hasLegend) 70 else 0

            val geomWidth = min(MAX_PLOT_WIDTH, max(MIN_PLOT_WIDTH, colCount * COLUMN_WIDTH))
            val labelHeight = if (geomWidth / colCount < labelWidth * 1.5) labelWidth / 2 else 20

            val width = geomWidth + labelWidth + legendWidth
            val height = geomWidth + titleHeight + labelHeight

            return width to height
        }
    }
}