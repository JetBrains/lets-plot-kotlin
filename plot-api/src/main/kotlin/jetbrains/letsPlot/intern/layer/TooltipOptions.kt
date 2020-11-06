/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer

import jetbrains.datalore.plot.config.Option

@Suppress("FunctionName")
fun layer_tooltips() = TooltipOptions()

class TooltipOptions() : HashMap<String, List<Any>>() {
    private constructor(other: TooltipOptions) : this() {
        this.putAll(other)
    }

    private fun addOption(key: String, value: Any): TooltipOptions {
        val newTooltips = TooltipOptions(this)
        val newOptions = newTooltips.getOrPut(key, { mutableListOf() })
        (newOptions as MutableList<Any>).add(value)
        return newTooltips
    }

    /**
     * Defines the format for displaying the value.
     * The format will be applied to the mapped value in the default tooltip
     * or to the corresponding value specified in the line template.
     */
    fun format(field: String, format: String): TooltipOptions {
        return addOption(
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
        return addOption(TOOLTIP_LINES, template)
    }

    companion object {
        private const val TOOLTIP_FORMATS = Option.Layer.TOOLTIP_FORMATS
        private const val FIELD = Option.TooltipFormat.FIELD
        private const val FORMAT = Option.TooltipFormat.FORMAT

        private const val TOOLTIP_LINES = Option.Layer.TOOLTIP_LINES
    }
}