/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.bistro.corr

import jetbrains.datalore.plot.config.Option
import jetbrains.datalore.plot.config.Option.Plot.BISTRO
import jetbrains.letsPlot.intern.OptionsMap
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.intern.filterNonNullValues
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.letsPlot


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
    private val showLegend: Boolean? = null,
    private val flip: Boolean? = null,
    private val threshold: Double? = null,
    private val adjustSize: Double? = null,
    private val tiles: LayerParams,
    private val points: LayerParams,
    private val labels: LayerParams,
    private val palette: String? = null,
    private val low: String? = null,
    private val mid: String? = null,
    private val high: String? = null
) {
    constructor(
        data: Map<*, *>,
        title: String? = null,
        showLegend: Boolean? = null,
        flip: Boolean? = null,
        threshold: Double? = null,
        adjustSize: Double? = null,
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
        palette = null,
        low = null,
        mid = null,
        high = null
    )

    private fun copy(): CorrPlot {
        return CorrPlot(
            data, title, showLegend, flip, threshold, adjustSize,
            tiles.copy(),
            points.copy(),
            labels.copy(),
            palette,
            low,
            mid,
            high
        )
    }

    private fun copyUpdateColors(
        palette: String?,
        low: String?,
        mid: String?,
        high: String?
    ): CorrPlot {
        return CorrPlot(
            data, title, showLegend, flip, threshold, adjustSize,
            tiles.copy(),
            points.copy(),
            labels.copy(),
            palette, low, mid, high
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
        return this.copyUpdateColors("gradient", low, mid, high)
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
        return this.copyUpdateColors(palette, null, null, null)
    }

    fun build(): Plot {
        if (!(tiles.added || points.added || labels.added)) {
            return letsPlot()
        }

        val tileLayer = when {
            tiles.added -> mapOf(
                Option.Layer.TYPE to tiles.type,
                Option.Layer.DIAG to tiles.diag
            )
            else -> null
        }

        val pointLayer = when {
            points.added -> mapOf(
                Option.Layer.TYPE to points.type,
                Option.Layer.DIAG to points.diag
            )
            else -> null
        }

        val labelLayer = when {
            labels.added -> mapOf(
                Option.Layer.TYPE to labels.type,
                Option.Layer.DIAG to labels.diag,
                Option.Layer.COLOR to labels.color,
                Option.Layer.MAP_SIZE to labels.mapSize
            )
            else -> null
        }

        var plot = letsPlot(data)
        title?.let { plot += ggtitle(it) }

        return plot + OptionsMap(
            kind = BISTRO,
            name = "corr",
            options = mapOf(
                Option.COEFFICIENTS to isCorrMatrix(data),
                Option.SHOW_LEGEND to showLegend,
                Option.FLIP to flip,
                Option.THRESHOLD to threshold,
                Option.PALETTE to palette,
                Option.GRADIENT_LOW to low,
                Option.GRADIENT_MID to mid,
                Option.GRADIENT_HIGH to high,
                Option.POINT_LAYER to pointLayer,
                Option.TILE_LAYER to tileLayer,
                Option.LABEL_LAYER to labelLayer
            ).filterNonNullValues()
        )
    }

    companion object {
        private fun checkTypeArg(type: String?) {
            type?.run {
                require(type in listOf("upper", "lower", "full")) {
                    """The option 'type' must be "upper", "lower" or "full" but was: "$type""""
                }
            }
        }

        private fun isCorrMatrix(data: Map<*, *>): Boolean {
            if (data.isEmpty()) {
                return false
            }

            val vectors = data.values.mapNotNull { it as? List<*> }
            if (vectors.size != data.size) {
                return false
            }

            if (vectors.any { it.size != data.size }) {
                return false
            }

            val values = vectors.asSequence().flatMap { it }
            if (values.all { it is Double && it >= -1.0 && it <= 1.0 } ) {
                return true
            }

            return false
        }
    }

    object Option {
        const val COEFFICIENTS = "coefficients"
        const val TITLE = "title"
        const val SHOW_LEGEND = "show_legend"
        const val FLIP = "flip"
        const val THRESHOLD = "threshold"
        const val ADJUST_SIZE = "adjust_size"
        const val PALETTE = "palette"
        const val GRADIENT_LOW = "low"
        const val GRADIENT_MID = "mid"
        const val GRADIENT_HIGH = "high"

        const val POINT_LAYER = "point_params"
        const val TILE_LAYER = "tile_params"
        const val LABEL_LAYER = "label_params"

        object Layer {
            const val TYPE = "type"
            const val DIAG = "diag"
            const val COLOR = "color"
            const val MAP_SIZE = "map_size"

            object Type {
                const val FULL = "full"
                const val LOWER = "lower"
                const val UPPER = "upper"
            }
        }
    }
}
