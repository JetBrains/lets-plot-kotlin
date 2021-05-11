/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.tooltips

import jetbrains.datalore.plot.config.Option

/**
 * Hide tooltips.
 */
val tooltipsNone = TooltipOptions().none()

fun layerTooltips() = TooltipOptions()

class TooltipOptions() {
    private val parameters = HashMap<String, Any>()
    private var isNone = false

    val options: Any
        get() = if (isNone) {
            NO_TOOLTIPS
        } else {
            parameters
        }

    private constructor(other: TooltipOptions) : this() {
        this.parameters.putAll(other.parameters)
    }

    private fun addListOption(key: String, value: Any): TooltipOptions {
        val newTooltips = TooltipOptions(this)
        val newOptions = newTooltips.parameters.getOrPut(key, { mutableListOf<Any>() })
        @Suppress("UNCHECKED_CAST")
        (newOptions as MutableList<Any>).add(value)
        return newTooltips
    }

    private fun setOption(key: String, value: Any): TooltipOptions {
        val newTooltips = TooltipOptions(this)
        newTooltips.parameters[key] = value
        return newTooltips
    }

    /**
     * Defines the format for displaying the value.
     * The format will be applied to the mapped value in the default tooltip
     * or to the corresponding value specified in the line template.
     *
     * @param field Aesthetic or variable name to apply the format to.
     *     The field name starts with a '^' prefix for aesthetics, variable names are specified without prefix or with a '@' prefix.
     *     It's possible to set the format for all positional aesthetics:
     *        field = "^X" - for all positional x;
     *        field = "^Y" - for all positional y.
     * @param format A number format ('1.f') or a string template ('{.1f}').
     *     The numeric format for non-numeric value will be ignored.
     *     If you need to include a brace character in the literal text, it can be escaped by doubling: {{ and }}, e.g.,
     *       .format('^color', '{{ {.1f} }}') -> "{ 17.0 }"
     *       .format('model', '{} {{text}}') -> "mustang {text}"
     *     The string template in format will allow to change lines for the default tooltip without 'line' specifying.
     *     Also the template will change the line for outliers.
     *     Aes and var formats are not interchangeable, i.e. var format will not be applied to aes, mapped to this variable.
     */
    fun format(field: String, format: String): TooltipOptions {
        return addListOption(
            TOOLTIP_FORMATS, mapOf(
                FIELD to field,
                FORMAT to format
            )
        )
    }

    /**
     * Specifies the string template to use in the multi-line tooltip.
     *
     * @param template A line template to add the tooltip with a label.
     *     Variables and aesthetics can be accessed via a special syntax:
     *        - ^color for aes
     *        - @year for variable
     *        - @{number of cylinders} for variable with spaces in the name
     *        - @{square m^2} for variable with spaces and '^' symbol in the name
     *        - @nameWith^ for the variable with '^' symbol in its name
     *     A '^' symbol can be escaped with a backslash, a brace character in the literal text - by doubling:
     *        .line("text") -> "text"
     *        .line("{{text}}") -> "{text}"
     *        .line("@model") -> "mustang"
     *        .line("{{@model}}") -> "{mustang}"
     *     The specified 'line' for outlier will move it to the general multi-line tooltip.
     *     The default tooltip has a label before the value, usually containing the name of the mapped variable.
     *     It has it's own behaviour, like blank label for axis aesthetics.
     *     This default label can be set in template using a pair of symbols '@|'.
     *     The label can be overridden by specifying a string value before '|' symbol.
     *     Within the tooltip line the label is left-aligned, the formed by template string is right-aligned.
     *     If a label is not specified, the string will be centered in the tooltip. For example:
     *        - line("^color"): no label, value is centered;
     *        - line("|^color"): label is empty, value is right-aligned;
     *        - line("@|^color"): default label is used, value is right-aligned;
     *        - line("my label|^color"): label is specified, value is right-aligned.
     */
    fun line(template: String): TooltipOptions {
        return addListOption(TOOLTIP_LINES, template)
    }

    /**
     * Specifies a fixed position for the general tooltip.
     *
     * @param position Position of the plot to move the general tooltip:
     *         ["top_left" | "top_center" | "top_right" |
     *         "middle_left" | "middle_center" | "middle_right" |
     *         "bottom_left" | "bottom_center" | "bottom_right"]
     */
    fun anchor(position: String): TooltipOptions {
        return setOption(TOOLTIP_ANCHOR, position)
    }

    /**
     * Specifies a minimum width of a general tooltip in pixels.

     * @param value Minimum width of the general tooltip.
     */
    fun minWidth(value: Number): TooltipOptions {
        return setOption(TOOLTIP_MIN_WIDTH, value)
    }

    /**
     * Specifies a color of a general tooltip.
     *
     * @param value The color for the general tooltip.
     */
    fun color(value: String): TooltipOptions {
        return setOption(TOOLTIP_COLOR, value)
    }

    /**
     * Hide tooltips.
     */
    internal fun none(): TooltipOptions {
        isNone = true
        return this
    }

    companion object {
        private const val TOOLTIP_FORMATS = Option.Layer.TOOLTIP_FORMATS
        private const val TOOLTIP_ANCHOR = Option.Layer.TOOLTIP_ANCHOR
        private const val FIELD = Option.TooltipFormat.FIELD
        private const val FORMAT = Option.TooltipFormat.FORMAT

        private const val TOOLTIP_LINES = Option.Layer.TOOLTIP_LINES
        private const val TOOLTIP_MIN_WIDTH = Option.Layer.TOOLTIP_MIN_WIDTH
        private const val TOOLTIP_COLOR = Option.Layer.TOOLTIP_COLOR

        private const val NO_TOOLTIPS = Option.Layer.NONE
    }
}