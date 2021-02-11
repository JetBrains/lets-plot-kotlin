/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.tooltips

import jetbrains.datalore.plot.config.Option

/**
 * Hide tooltips.
 */
val tooltips_none = TooltipOptions().none()

@Suppress("FunctionName")
fun layer_tooltips() = TooltipOptions()

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
     */
    fun line(template: String): TooltipOptions {
        return addListOption(TOOLTIP_LINES, template)
    }

    /**
     * Specifies a fixed position for the general tooltip.
     */
    fun anchor(position: String): TooltipOptions {
        return setOption(TOOLTIP_ANCHOR, position)
    }

    /**
     * Specifies a minimum width of a general tooltip in pixels.
     */
    fun minWidth(value: Number): TooltipOptions {
        return setOption(TOOLTIP_MIN_WIDTH, value)
    }

    /**
     * Specifies a color of a general tooltip.
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