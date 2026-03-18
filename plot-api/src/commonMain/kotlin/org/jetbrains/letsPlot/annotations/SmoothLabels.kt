/*
 * Copyright (c) 2025. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.annotations

import org.jetbrains.letsPlot.core.spec.Option

/**
 * Configure annotations for `geomSmooth()` layers.
 *
 * `smoothLabels()` is a specialized annotation helper designed for smooth layers.
 * It shares the same API as [layerLabels] - supporting [line], [format], [size], and [inheritColor] -
 * and additionally provides regression-specific methods: [eq], [labelX], and [labelY].
 *
 * ## Supported variables and markers
 *
 * Use these in [line] templates via `@..name..` syntax:
 *
 * - `..r2..` - R² (coefficient of determination).
 * - `..adjr2..` - adjusted R².
 * - `..aic..` - Akaike Information Criterion.
 * - `..bic..` - Bayesian Information Criterion.
 * - `..f..` - F-statistic.
 * - `..df1..` - numerator degrees of freedom for the F-test.
 * - `..df2..` - denominator degrees of freedom for the F-test.
 * - `..p..` - p-value for the overall model F-test.
 * - `..method..` - smoothing method label (`lm` or `loess`).
 * - `..n..` - number of observations used in model fitting.
 * - `..cilevel..` - confidence level used for the R² confidence interval.
 * - `..cilow..` - lower bound of the R² confidence interval.
 * - `..cihigh..` - upper bound of the R² confidence interval.
 * - `~eq` - fitted equation (use in a [line] template to insert the model equation).
 *
 * ## Notes
 *
 * By default, annotation text color is automatically selected for good readability.
 *
 * Use `theme(labelText = elementText(...))` to customize the appearance of annotation text.
 * See also [elementText()][org.jetbrains.letsPlot.themes.elementText].
 *
 * Alternatively, the [inheritColor] method can be used to make annotation text
 * use the geometry's `color` aesthetic, overriding both the automatically selected text color
 * and any color specified via `theme(labelText = elementText(...))`.
 *
 * ## Examples
 *
 * - [smooth_summary.html](https://lets-plot.org/kotlin/examples/cookbook/smooth_summary.html)
 *
 * ```kotlin
 * val data = mapOf("x" to listOf(0, 1.5, 1.7, 2), "y" to listOf(0, 1, 1.8, 4))
 * letsPlot(data) { x = "x"; y = "y" } +
 *     geomPoint() +
 *     geomSmooth(
 *         deg = 2,
 *         labels = smoothLabels()
 *             .line("""\(R^2=\)@..r2..""")
 *             .line("~eq")
 *             .size(16)
 *     )
 * ```
 *
 */
@Suppress("ClassName")
class smoothLabels {

    private val parameters = HashMap<String, Any>().apply {
        this[KIND] = SMOOTH_KIND
    }

    internal val options: Any
        get() = parameters

    private constructor(other: smoothLabels) {
        this.parameters.putAll(other.parameters)
    }

    constructor()

    private fun addListOption(key: String, value: Any): smoothLabels {
        val newLabels = smoothLabels(this)
        val newOptions = newLabels.parameters.getOrPut(key) { mutableListOf<Any>() }
        @Suppress("UNCHECKED_CAST")
        (newOptions as MutableList<Any>).add(value)
        return newLabels
    }

    private fun setOption(key: String, value: Any): smoothLabels {
        val newLabels = smoothLabels(this)
        newLabels.parameters[key] = value
        return newLabels
    }

    private fun setNestedOption(key: String, value: Any): smoothLabels {
        val newLabels = smoothLabels(this)
        val newNested = HashMap<String, Any>()
        (newLabels.parameters[OPTIONS] as? Map<*, *>)?.forEach { (k, v) ->
            if (k is String && v != null) newNested[k] = v
        }
        newNested[key] = value
        newLabels.parameters[OPTIONS] = newNested
        return newLabels
    }

    /**
     * Defines the format for displaying the value.
     * The format will be applied to the corresponding value specified in the line template.
     *
     * @param field Variable name to apply the format to.
     *  Variable names are specified without prefix or with a `@` prefix.
     * @param format A number format (e.g. `".4f"`) or a string template (e.g. `"{.4f}"`).
     */
    fun format(field: String, format: String): smoothLabels {
        return addListOption(
            FORMATS, mapOf(
                FIELD to field,
                FORMAT to format
            )
        )
    }

    /**
     * Specifies a line template to show in the annotation.
     *
     * Variables can be accessed via `@..name..` syntax:
     * - `@..r2..` - R² value
     * - `@..adjr2..` - adjusted R² value
     *
     * Use `~eq` as a placeholder for the auto-generated equation.
     *
     * @param template A line template string.
     */
    fun line(template: String): smoothLabels {
        return addListOption(LINES, template)
    }

    /**
     * Specifies the text size in the annotation.
     *
     * @param value Text size.
     */
    fun size(value: Number): smoothLabels {
        return setOption(ANNOTATION_SIZE, value)
    }

    /**
     * Use the layer's color for the annotation text.
     *
     * When enabled, the annotation text will inherit the color from the
     * layer it's associated with.
     */
    fun inheritColor(): smoothLabels {
        return setOption(USE_LAYER_COLOR, true)
    }

    /**
     * Configures the auto-generated equation (`~eq` marker).
     *
     * @param lhs Left-hand side variable name in the equation (e.g. `"y"`).
     * @param rhs Right-hand side variable name in the equation (e.g. `"x"`).
     * @param format Number format for the equation coefficients (e.g. `".3f"`).
     * @param threshold Coefficients with absolute value below this threshold are hidden.
     */
    fun eq(
        lhs: String? = null,
        rhs: String? = null,
        format: String? = null,
        threshold: Number? = null
    ): smoothLabels {
        val eqOptions = mapOf(
            EQ_LHS to lhs,
            EQ_RHS to rhs,
            EQ_FORMAT to format?.let { listOf(it) },
            EQ_THRESHOLD to threshold
        ).filterValues { it != null }
        return setNestedOption(EQ, eqOptions)
    }

    /**
     * Sets horizontal anchor positions for each group's annotation label.
     *
     * @param positions A list of horizontal positions, one per group.
     *  Accepted values: `"left"`, `"right"`, `"middle"`.
     */
    fun labelX(positions: List<String>): smoothLabels {
        return setNestedOption(LABEL_X, positions)
    }

    /**
     * Sets horizontal anchor positions for each group's annotation label.
     *
     * @param positions Horizontal positions, one per group.
     *  Accepted values: `"left"`, `"right"`, `"middle"`.
     */
    fun labelX(vararg positions: String): smoothLabels {
        return setNestedOption(LABEL_X, positions.toList())
    }

    /**
     * Sets vertical anchor positions for each group's annotation label.
     *
     * @param positions A list of vertical positions, one per group.
     *  Accepted values: `"top"`, `"bottom"`, `"middle"`.
     */
    fun labelY(positions: List<String>): smoothLabels {
        return setNestedOption(LABEL_Y, positions)
    }

    /**
     * Sets vertical anchor positions for each group's annotation label.
     *
     * @param positions Vertical positions, one per group.
     *  Accepted values: `"top"`, `"bottom"`, `"middle"`.
     */
    fun labelY(vararg positions: String): smoothLabels {
        return setNestedOption(LABEL_Y, positions.toList())
    }

    private companion object {
        private const val KIND = Option.LinesSpec.KIND
        private const val SMOOTH_KIND = Option.LinesSpec.Kind.SMOOTH_STAT_SUMMARY_ANNOTATION
        private const val OPTIONS = Option.LinesSpec.OPTIONS
        private const val FORMATS = Option.LinesSpec.FORMATS
        private const val FIELD = Option.LinesSpec.Format.FIELD
        private const val FORMAT = Option.LinesSpec.Format.FORMAT
        private const val LINES = Option.LinesSpec.LINES
        private const val ANNOTATION_SIZE = Option.AnnotationSpec.ANNOTATION_SIZE
        private const val USE_LAYER_COLOR = Option.AnnotationSpec.USE_LAYER_COLOR
        private const val EQ = Option.SmoothOptions.EQ
        private const val EQ_LHS = Option.SmoothOptions.Eq.LHS
        private const val EQ_RHS = Option.SmoothOptions.Eq.RHS
        private const val EQ_FORMAT = Option.SmoothOptions.Eq.FORMAT
        private const val EQ_THRESHOLD = Option.SmoothOptions.Eq.THRESHOLD
        private const val LABEL_X = Option.SmoothOptions.LABEL_X
        private const val LABEL_Y = Option.SmoothOptions.LABEL_Y
    }
}