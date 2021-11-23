/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.bistro.corr

import jetbrains.letsPlot.*
import jetbrains.letsPlot.bistro.corr.CorrUtil.correlations
import jetbrains.letsPlot.bistro.corr.CorrUtil.correlationsFromCoefficients
import jetbrains.letsPlot.bistro.corr.CorrUtil.correlationsToDataframe
import jetbrains.letsPlot.bistro.corr.CorrUtil.isCoefficientsMatrix
import jetbrains.letsPlot.bistro.corr.CorrUtil.matrixXYSeries
import jetbrains.letsPlot.bistro.corr.Method.correlationPearson
import jetbrains.letsPlot.bistro.corr.OptionsConfigurator.getKeepMatrixDiag
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.geom.geomText
import jetbrains.letsPlot.geom.geomTile
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.intern.Scale
import jetbrains.letsPlot.intern.asPlotData
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.sampling.samplingNone
import jetbrains.letsPlot.scale.*
import jetbrains.letsPlot.tooltips.layerTooltips
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
 * @param adjustSize A scaler to adjust the plot size which was computed by `CorrPlot` automatically.
 */
class CorrPlot private constructor(
    private val data: Map<*, *>,
    private val title: String? = null,
    private val showLegend: Boolean = true,
    private val flip: Boolean = true,
    private val threshold: Double = DEF_THRESHOLD,
    private val adjustSize: Double = 1.0,
    private val tiles: LayerParams,
    private val points: LayerParams,
    private val labels: LayerParams,
    private val colorScale: Scale,
    private val fillScale: Scale
) {
    constructor(
        data: Map<*, *>,
        title: String? = null,
        showLegend: Boolean = true,
        flip: Boolean = true,
        threshold: Double = DEF_THRESHOLD,
        adjustSize: Double = 1.0,
    ) : this(
        data,
        title,
        showLegend,
        flip,
        threshold,
        adjustSize,
        tiles = LayerParams(),
        points = LayerParams(),
        labels = LayerParams(),
        colorScale = colorGradient(DEF_LOW_COLOR, DEF_MID_COLOR, DEF_HIGH_COLOR),
        fillScale = fillGradient(DEF_LOW_COLOR, DEF_MID_COLOR, DEF_HIGH_COLOR)
    )

//    private var colorScale = colorGradient(DEF_LOW_COLOR, DEF_MID_COLOR, DEF_HIGH_COLOR)
//    private var fillScale = fillGradient(DEF_LOW_COLOR, DEF_MID_COLOR, DEF_HIGH_COLOR)

    private fun copy(): CorrPlot {
        return CorrPlot(
            data, title, showLegend, flip, threshold, adjustSize,
            tiles.copy(),
            points.copy(),
            labels.copy(),
            colorScale,
            fillScale
        )
    }

    private fun copyUpdateColors(
        colorScale: Scale,
        fillScale: Scale
    ): CorrPlot {
        return CorrPlot(
            data, title, showLegend, flip, threshold, adjustSize,
            tiles.copy(),
            points.copy(),
            labels.copy(),
            colorScale,
            fillScale
        )
    }

    /**
     * Add tiles layer to corr plot.
     *
     * @param type Type of the matrix: "upper", "lower" or "full".
     *             Default - contextual.
     * @param diag Whether to fill the main diagonal with values or not.
     *             Default - contextual.
     */
    fun tiles(type: String? = null, diag: Boolean? = null): CorrPlot {
        checkTypeArg(type)
        return this.copy().apply {
            tiles.type = type
            tiles.diag = diag
        }
    }

    /**
     * Add points layer to corr plot.
     *
     * @param type Type of the matrix: "upper", "lower" or "full".
     *             Default - contextual.
     * @param diag Whether to fill the main diagonal with values or not.
     *             Default - contextual.
     */
    fun points(type: String? = null, diag: Boolean? = null): CorrPlot {
        checkTypeArg(type)
        return this.copy().apply {
            points.type = type
            points.diag = diag
        }
    }

    /**
     * Add labels layer to corr plot.
     *
     * @param type Type of the matrix: "upper", "lower" or "full".
     *             Default - contextual.
     * @param diag Whether to fill the main diagonal with values or not.
     *             Default - contextual.
     * @param mapSize If True, then absolute value of correlation is mapped to text size.
     *                If False - the text size is constant.
     *                Default - contextual.
     * @param mapSize If True, then absolute value of correlation is mapped to text size.
     *                If False - the text size is constant.
     *                Default - contextual.
     * @param color Set text color.
     *              Default - contextual.
     */
    fun labels(
        type: String? = null, diag: Boolean? = null,
        mapSize: Boolean? = null, color: String? = null
    ): CorrPlot {
        checkTypeArg(type)
        return this.copy().apply {
            labels.type = type
            labels.diag = diag
            labels.mapSize = mapSize
            labels.color = color
        }
    }

    /**
     * Use gradient colors
     */
    fun paletteGradient(low: String, mid: String, high: String): CorrPlot {
        return this.copyUpdateColors(
            colorScale = colorGradient(low, mid, high),
            fillScale = fillGradient(low, mid, high)
        )
    }

    /**
     * Use Brewer 'BrBG' colors
     */
    fun paletteBrBG() = setBrewerPalette("BrBG")

    /**
     * Use Brewer 'PiYG' colors
     */
    fun palettePiYG() = setBrewerPalette("PiYG")

    /**
     * Use Brewer 'PRGn' colors
     */
    fun palettePRGn() = setBrewerPalette("PRGn")

    /**
     * Use Brewer 'PuOr' colors
     */
    fun palettePuOr() = setBrewerPalette("PuOr")

    /**
     * Use Brewer 'RdBu' colors
     */
    fun paletteRdBu() = setBrewerPalette("RdBu")

    /**
     * Use Brewer 'RdGy' colors
     */
    fun paletteRdGy() = setBrewerPalette("RdGy")

    /**
     * Use Brewer 'RdYlBu' colors
     */
    fun paletteRdYlBu() = setBrewerPalette("RdYlBu")

    /**
     * Use Brewer 'RdYlGn' colors
     */
    fun paletteRdYlGn() = setBrewerPalette("RdYlGn")

    /**
     * Use Brewer 'Spectral' colors
     */
    fun paletteSpectral() = setBrewerPalette("Spectral")


    private fun setBrewerPalette(palette: String): CorrPlot {
        return this.copyUpdateColors(
            colorScale = colorBrewer(palette),
            fillScale = fillBrewer(palette)
        )
    }

    fun build(): Plot {
        if (!(tiles.added || points.added || labels.added)) {
            return letsPlot()
        }

        OptionsConfigurator.configure(tiles, points, labels, flip)

        val originalVariables = data.keys.map { it.toString() }.toList()

        // Compute correlations
        @Suppress("NAME_SHADOWING")
        val data = asPlotData(data)
        val correlations = when (isCoefficientsMatrix(data)) {
            true -> correlationsFromCoefficients(data)
            false -> correlations(data, Method::correlationPearson)
        }

        // variables in the 'original' order
        val varsInMatrix = correlations.keys.map { it.first }.toSet()
        val varsInOrder = originalVariables.filter { varsInMatrix.contains(it) }

        val keepDiag = getKeepMatrixDiag(tiles, points, labels)
        val combinedType = OptionsConfigurator.getCombinedMatrixType(tiles, points, labels)

        var plot = letsPlot() + colorScale + fillScale

        // Add layers
        val tooltips = (layerTooltips()
            .format(field = "@${CorrVar.CORR}", format = VALUE_FORMAT)
            .line("@${CorrVar.CORR}"))

        if (tiles.added) {
            val layerData = layerData(
                tiles,
                correlations,
                varsInOrder,
                keepDiag = keepDiag || combinedType == "full",
                threshold
            )
            plot += geomTile(
                data = layerData,
                showLegend = showLegend,
                tooltips = tooltips,
                sampling = samplingNone,
                size = 0.0, width = 1.002, height = 1.002
            ) {
                x = CorrVar.X
                y = CorrVar.Y
                fill = CorrVar.CORR
            }
        }

        if (points.added) {
            val layerData = layerData(
                points,
                correlations,
                varsInOrder,
                keepDiag = keepDiag || combinedType == "full",
                threshold
            )
            plot += geomPoint(
                data = layerData,
                showLegend = showLegend,
                sizeUnit = "x",
                tooltips = tooltips,
                sampling = samplingNone
            ) {
                x = CorrVar.X
                y = CorrVar.Y
                size = CorrVar.CORR_ABS
                color = CorrVar.CORR
            }
        }

        if (labels.added) {
            val layerData = layerData(
                labels,
                correlations,
                varsInOrder,
                keepDiag = keepDiag || combinedType == "full",
                threshold
            )
            plot += geomText(
                data = layerData,
                showLegend = showLegend,
                naText = "",
                labelFormat = VALUE_FORMAT,
                sizeUnit = "x",
                tooltips = tooltips,
                sampling = samplingNone,
                size = if (labels.mapSize == true) null else 1.0,
                color = labels.color
            ) {
                x = CorrVar.X
                y = CorrVar.Y
                label = CorrVar.CORR
                size = CorrVar.CORR_ABS
                color = CorrVar.CORR
            }
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

        val onlyTiles = tiles.added && !(points.added || labels.added)
        return addCommonParams(plot, plotX, plotY, onlyTiles, flip)
    }

    companion object {
        private const val VALUE_FORMAT = ".2f"

        private const val LEGEND_NAME = ""
        private val SCALE_BREAKS = listOf(-1.0, -0.5, 0.0, 0.5, 1.0)
        private val SCALE_LABELS = listOf("-1", "-0.5", "0", "0.5", "1")
        private val SCALE_LIMITS = -1.0 to 1.0

        private const val DEF_THRESHOLD = 0.0
        private const val DEF_LOW_COLOR = "#B3412C" //"red"
        private const val DEF_MID_COLOR = "#EDEDED" //"light_gray"
        private const val DEF_HIGH_COLOR = "#326C81" // "blue"

        private const val COLUMN_WIDTH = 40
        private const val MIN_PLOT_WIDTH = 150
        private const val MAX_PLOT_WIDTH = 700
//        private const val PLOT_PROPORTION = 3.0 / 4.0

        private fun checkTypeArg(type: String?) {
            type?.run {
                require(type in listOf("upper", "lower", "full")) {
                    """The option 'type' must be "upper", "lower" or "full" but was: "$type""""
                }
            }
        }

        private fun colorGradient(low: String, mid: String, high: String): Scale {
            return scaleColorGradient2(
                low = low, mid = mid, high = high,
                name = LEGEND_NAME,
                breaks = SCALE_BREAKS,
                labels = SCALE_LABELS,
                limits = SCALE_LIMITS,
                naValue = "rgba(0,0,0,0)"
            )
        }

        private fun fillGradient(low: String, mid: String, high: String): Scale {
            return scaleFillGradient2(
                low = low, mid = mid, high = high,
                name = LEGEND_NAME,
                breaks = SCALE_BREAKS,
                labels = SCALE_LABELS,
                limits = SCALE_LIMITS,
                naValue = "rgba(0,0,0,0)"
            )
        }

        private fun colorBrewer(palette: String): Scale {
            return scaleColorBrewer(
                palette = palette,
                name = LEGEND_NAME,
                breaks = SCALE_BREAKS,
                labels = SCALE_LABELS,
                limits = SCALE_LIMITS,
                naValue = "rgba(0,0,0,0)"
            )
        }

        private fun fillBrewer(palette: String): Scale {
            return scaleFillBrewer(
                palette = palette,
                name = LEGEND_NAME,
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
            onlyTiles: Boolean,
            flipY: Boolean
        ): Plot {
            @Suppress("NAME_SHADOWING")
            var plot = plot
            plot += theme(
                axisTitle = elementBlank(),
                axisLine = elementBlank(),
                axisTicksX = elementLine(blank = false),
                axisTicksY = elementLine(blank = false),
                panelGrid = elementBlank(),
            )

            plot += scaleSizeIdentity(naValue = 0, guide = "none")

            // Smaller 'additive' expand for tiles (normally: 0.6)
//            val expand = if (onlyTiles) listOf(0.0, 0.1) else null
            val expand = listOf(0.0, 0.0)

            plot += scaleXDiscrete(breaks = xValues, limits = xValues, expand = expand)


            // ToDo: 'reverse' doesn't work if 'limits' are set. Should be fixed in 1.6.0
//            plot += scaleYDiscrete(limits = yValues, expand = scaleXYExpand, reverse = flipY)
            plot += scaleYDiscrete(
                breaks = yValues,
                limits = if (flipY) yValues.asReversed() else yValues,
                expand = expand
            )

            val xLim = Pair(-0.6, xValues.size - 1 + 0.6)
            val yLim = Pair(-0.6, yValues.size - 1 + 0.6)
            if (onlyTiles) {
                plot += coordCartesian(xlim = xLim, ylim = yLim)
            } else {
                plot += coordFixed(xlim = xLim, ylim = yLim)
            }
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
            val geomWidth = (min(MAX_PLOT_WIDTH, max(MIN_PLOT_WIDTH, (colCount * COLUMN_WIDTH))) * adjustSize).toInt()

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