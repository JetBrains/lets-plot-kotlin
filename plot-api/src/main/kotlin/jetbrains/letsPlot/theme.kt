/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.OptionsMap

///**
// *  Element to draw nothing and assigns no space
// */
//@Suppress("FunctionName")
//fun element_blank() = "blank"

/**
 * Use theme() to modify individual components of a theme,
 * allowing you to control the appearance of all non-data components of the plot.
 */
@Suppress("ClassName", "FunctionName")
class theme() : OptionsMap(Option.Plot.THEME, HashMap()) {
    private constructor(other: theme) : this() {
        (this.options as MutableMap<String, Any>).putAll(other.options)
    }

    private fun withOption(name: String, value: Any): theme {
        val newTheme = theme(this)
        (newTheme.options as MutableMap<String, Any>)[name] = value
        return newTheme
    }

    fun axisTitle_blank() = withOption(AXIS_TITLE, VAL_ELEMENT_BLANK)
    fun axisTitleX_blank() = withOption(AXIS_TITLE_X, VAL_ELEMENT_BLANK)
    fun axisTitleY_blank() = withOption(AXIS_TITLE_Y, VAL_ELEMENT_BLANK)
    fun axisText_blank() = withOption(AXIS_TEXT, VAL_ELEMENT_BLANK)
    fun axisTextX_blank() = withOption(AXIS_TEXT_X, VAL_ELEMENT_BLANK)
    fun axisTextY_blank() = withOption(AXIS_TEXT_Y, VAL_ELEMENT_BLANK)
    fun axisTicks_blank() = withOption(AXIS_TICKS, VAL_ELEMENT_BLANK)
    fun axisTicksX_blank() = withOption(AXIS_TICKS_X, VAL_ELEMENT_BLANK)
    fun axisTicksY_blank() = withOption(AXIS_TICKS_Y, VAL_ELEMENT_BLANK)
    fun axisLine_blank() = withOption(AXIS_LINE, VAL_ELEMENT_BLANK)
    fun axisLineX_blank() = withOption(AXIS_LINE_X, VAL_ELEMENT_BLANK)
    fun axisLineY_blank() = withOption(AXIS_LINE_Y, VAL_ELEMENT_BLANK)
    fun legendPosition_none() = withOption(LEGEND_POSITION, VAL_LEGEND_POS_NONE)
    fun legendPosition_left() = withOption(LEGEND_POSITION, VAL_LEGEND_POS_LEFT)
    fun legendPosition_right() = withOption(LEGEND_POSITION, VAL_LEGEND_POS_RIGHT)
    fun legendPosition_bottom() = withOption(LEGEND_POSITION, VAL_LEGEND_POS_BOTTOM)
    fun legendPosition_top() = withOption(LEGEND_POSITION, VAL_LEGEND_POS_TOP)

    /**
     * Specifies the legend position relative to the plot drawing area.
     * Position (0, 0) corresponds to the left, lower corner of the plot.
     * Position (1, 1) corresponds to the right, upper corner of the plot.
     */
    @Suppress("SpellCheckingInspection")
    fun legendPosition(xpos: Number, ypos: Number): theme {
        return withOption(
            LEGEND_POSITION,
            listOf(xpos, ypos)
        )
    }

    fun legendJustification_center() = withOption(LEGEND_JUSTIFICATION, VAL_LEGEND_JUSTIFICATION_CENTER)

    /**
     * Specifies the anchor point for positioning legend inside plot.
     * Justification (0, 0) corresponds to the left, lower corner of the legend.
     * Justification (1, 1) corresponds to the right, upper corner of the legend.
     */
    @Suppress("SpellCheckingInspection")
    fun legendJustification(xanchor: Number, yanchor: Number): theme {
        return withOption(
            LEGEND_JUSTIFICATION,
            listOf(xanchor, yanchor)
        )
    }

    fun legendDirection_horizontal() = withOption(LEGEND_DIRECTION, VAL_LEGEND_DIRECTION_HORIZONTAL)
    fun legendDirection_vertical() = withOption(LEGEND_DIRECTION, VAL_LEGEND_DIRECTION_VERTICAL)


    companion object {
        // names
        private const val AXIS_TITLE = Option.Theme.AXIS_TITLE
        private const val AXIS_TITLE_X = "axis_title_x"
        private const val AXIS_TITLE_Y = "axis_title_y"
        private const val AXIS_TEXT = Option.Theme.AXIS_TEXT
        private const val AXIS_TEXT_X = "axis_text_x"
        private const val AXIS_TEXT_Y = "axis_text_y"
        private const val AXIS_TICKS = Option.Theme.AXIS_TICKS
        private const val AXIS_TICKS_X = "axis_ticks_x"
        private const val AXIS_TICKS_Y = "axis_ticks_y"
        private const val AXIS_LINE = Option.Theme.AXIS_LINE
        private const val AXIS_LINE_X = "axis_line_x"
        private const val AXIS_LINE_Y = "axis_line_y"
        private const val LEGEND_POSITION = Option.Theme.LEGEND_POSITION
        private const val LEGEND_JUSTIFICATION = Option.Theme.LEGEND_JUSTIFICATION
        private const val LEGEND_DIRECTION = Option.Theme.LEGEND_DIRECTION

        // values
        private const val VAL_ELEMENT_BLANK = "blank"
        private const val VAL_LEGEND_POS_NONE = "none"
        private const val VAL_LEGEND_POS_LEFT = "left"
        private const val VAL_LEGEND_POS_RIGHT = "right"
        private const val VAL_LEGEND_POS_BOTTOM = "bottom"
        private const val VAL_LEGEND_POS_TOP = "top"
        private const val VAL_LEGEND_JUSTIFICATION_CENTER = "center"
        private const val VAL_LEGEND_DIRECTION_HORIZONTAL = "horizontal"
        private const val VAL_LEGEND_DIRECTION_VERTICAL = "vertical"
    }
}
