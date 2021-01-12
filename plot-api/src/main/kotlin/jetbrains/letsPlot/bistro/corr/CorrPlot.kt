/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.bistro.corr

import jetbrains.letsPlot.bistro.corr.CorrUtil.correlations
import jetbrains.letsPlot.bistro.corr.CorrUtil.correlationsToDataframe
import jetbrains.letsPlot.bistro.corr.CorrUtil.matrixXYSeries
import jetbrains.letsPlot.bistro.corr.Method.correlationPearson
import jetbrains.letsPlot.bistro.corr.OptionsConfigurator.getKeepMatrixDiag
import jetbrains.letsPlot.coord_fixed
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.intern.Scale
import jetbrains.letsPlot.intern.asPlotData
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.lets_plot
import jetbrains.letsPlot.scale.*
import jetbrains.letsPlot.theme
import jetbrains.letsPlot.tooltips.layer_tooltips
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
    private val threshold: Double = DEF_THRESHOLD,
    private val adjustSize: Double = 1.0
) {
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
        if (!(tiles.added || points.added || labels.added)) {
            return lets_plot()
        }

        OptionsConfigurator.configure(tiles, points, labels, flip)

        val originalVariables = data.keys.map { it.toString() }.toList()

        // Compute correlations
        @Suppress("NAME_SHADOWING")
        val data = asPlotData(data)
        val correlations = correlations(data, ::correlationPearson)
        // variables in the 'original' order
        val varsInMatrix = correlations.keys.map { it.first }.toSet()
        val varsInOrder = originalVariables.filter { varsInMatrix.contains(it) }

        val keepDiag = getKeepMatrixDiag(tiles, points, labels)
        val combinedType = OptionsConfigurator.getCombinedMatrixType(tiles, points, labels)

        var plot = lets_plot() + colorScale + fillScale

        // Add layers
        val tooltips = (layer_tooltips()
            .format(field = "@${CorrVar.CORR}", format = VALUE_FORMAT)
            .line("@${CorrVar.CORR}"))

        if (points.added) {
//            val diag = points.diag!!
//            val type = points.type!!
//
//            val (xs, ys) = matrixXYSeries(
//                correlations, varsInOrder, type,
//                nullDiag = !(keepDiag || combinedType == "full"),
//                threshold,
//                dropDiagNA = false,
//                dropOtherNA = false
//            )
//
//            val matrix = CorrUtil.CorrMatrix(
//                correlations,
//                nullDiag = !diag,
//                threshold
//            )
//
//            val dataframe = correlationsToDataframe(
//                matrix,
//                xs, ys
//            )
            val layerData = layerData(
                points,
                correlations,
                varsInOrder,
                keepDiag = keepDiag || combinedType == "full",
                threshold
            )
            plot += geom_point(
                data = layerData,
                showLegend = showLegend,
                sizeUnit = "x",
                tooltips = tooltips
            ) {
                x = CorrVar.X
                y = CorrVar.Y
                size = CorrVar.CORR_ABS
                color = CorrVar.CORR
            }
            plot += coord_fixed(ratio = 1.0)
        }

        // Actual labels on axis.
        val (xs, ys) = matrixXYSeries(
            correlations, varsInOrder, combinedType, !keepDiag, threshold,
            dropDiagNA = !keepDiag,
            dropOtherNA = combinedType == "full"
        )
        val plotSize = plotSize(xs, ys, title != null, showLegend, adjustSize)
        plot += ggsize(plotSize.first, plotSize.second)

        title?.run { plot += ggtitle(title) }

        // preserve the original order on x/y scales
        val xsSet = xs.distinct().toSet()
        val ysSet = ys.distinct().toSet()
        val plotX = varsInOrder.filter { it in xsSet }
        val plotY = varsInOrder.filter { it in ysSet }

        return addCommonParams(plot, plotX, plotY, tiles.added, flip)
    }

    companion object {
        private const val VALUE_FORMAT = ".2f"

        private const val LEGEND_NAME = "Corr"
        private val SCALE_BREAKS = listOf(-1.0, -0.5, 0.0, 0.5, 1.0)
        private val SCALE_LABELS = listOf("-1", "-0.5", "0", "0.5", "1")
        private val SCALE_LIMITS = -1.0 to 1.0

        private const val DEF_THRESHOLD = 0.0
        private const val DEF_LOW_COLOR = "#B3412C" //"red"
        private const val DEF_MID_COLOR = "#EDEDED" //"light_gray"
        private const val DEF_HIGH_COLOR = "#326C81" // "blue"

        private const val COLUMN_WIDTH = 40
        private const val MIN_PLOT_WIDTH = 400
        private const val MAX_PLOT_WIDTH = 900
//        private const val PLOT_PROPORTION = 3.0 / 4.0

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
            xValues: List<String>,
            yValues: List<String>,
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
            plot += scale_x_discrete(breaks = xValues, limits = xValues, expand = scaleXYExpand)

            // ToDo: 'reverse' doesn't work if 'limits' are set. Should be fixed in 1.6.0
//            plot += scale_y_discrete(limits = yValues, expand = scaleXYExpand, reverse = flipY)
            plot += scale_y_discrete(
                breaks = yValues,
                limits = if (flipY) yValues.asReversed() else yValues,
                expand = scaleXYExpand
            )
            return plot
        }

        private fun plotSize(
            xs: List<String>,
            ys: List<String>,
            hasTitle: Boolean,
            hasLegend: Boolean,
            adjustSize: Double
        ): Pair<Int, Int> {
            val colCount = xs.distinct().size

            // magic values
            val titleHeight = if (hasTitle) 20 else 0
            val legendWidth = if (hasLegend) 70 else 0
            val geomWidth = min(MAX_PLOT_WIDTH, max(MIN_PLOT_WIDTH, (colCount * COLUMN_WIDTH * adjustSize).toInt()))

            fun axisLabelWidth(labs: List<String>): Int {
                val labelLen = labs.maxByOrNull { it.length }?.length ?: 0
                return (labelLen * 5.7).toInt()
            }

            val labelWidthX = axisLabelWidth(xs)
            val labelWidthY = axisLabelWidth(ys)
            val colWidth = geomWidth / colCount
            val labelHeightY = if (labelWidthY * 1.0 > colWidth) labelWidthY / 2 else 20

            val width = geomWidth + labelWidthX + legendWidth
            val height = geomWidth + titleHeight + labelHeightY

            return width to height
        }

        private fun layerData(
            params: LayerParams,
            correlations: Map<Pair<String, String>, Double>,
            varsInOrder: List<String>,
            keepDiag: Boolean,
            threshold: Double
        ): Map<String, List<Any?>> {
            val diag = params.diag!!
            val type = params.type!!

            val (xs, ys) = matrixXYSeries(
                correlations, varsInOrder, type,
                nullDiag = !(keepDiag),
                threshold,
                dropDiagNA = false,
                dropOtherNA = false
            )

            val matrix = CorrUtil.CorrMatrix(
                correlations,
                nullDiag = !diag,
                threshold
            )

            return correlationsToDataframe(
                matrix,
                xs, ys
            )
        }
    }
}