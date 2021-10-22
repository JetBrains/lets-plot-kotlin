/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.datalore.plot.builder.defaultTheme.values.ThemeOption
import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.OptionsMap

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

    // ToDo: axisBlank(), axisTooltipBlank()

    // ToDo: deprecate "xxxBlank()" methods.
    fun axisTitleBlank() = withOption(AXIS_TITLE, VAL_ELEMENT_BLANK)
    fun axisTitleXBlank() = withOption(AXIS_TITLE_X, VAL_ELEMENT_BLANK)
    fun axisTitleYBlank() = withOption(AXIS_TITLE_Y, VAL_ELEMENT_BLANK)
    fun axisTextBlank() = withOption(AXIS_TEXT, VAL_ELEMENT_BLANK)
    fun axisTextXBlank() = withOption(AXIS_TEXT_X, VAL_ELEMENT_BLANK)
    fun axisTextYBlank() = withOption(AXIS_TEXT_Y, VAL_ELEMENT_BLANK)
    fun axisTicksBlank() = withOption(AXIS_TICKS, VAL_ELEMENT_BLANK)
    fun axisTicksXBlank() = withOption(AXIS_TICKS_X, VAL_ELEMENT_BLANK)
    fun axisTicksYBlank() = withOption(AXIS_TICKS_Y, VAL_ELEMENT_BLANK)
    fun axisLineBlank() = withOption(AXIS_LINE, VAL_ELEMENT_BLANK)
    fun axisLineXBlank() = withOption(AXIS_LINE_X, VAL_ELEMENT_BLANK)
    fun axisLineYBlank() = withOption(AXIS_LINE_Y, VAL_ELEMENT_BLANK)

    fun legendPositionNone() = withOption(LEGEND_POSITION, VAL_LEGEND_POS_NONE)
    fun legendPositionLeft() = withOption(LEGEND_POSITION, VAL_LEGEND_POS_LEFT)
    fun legendPositionRight() = withOption(LEGEND_POSITION, VAL_LEGEND_POS_RIGHT)
    fun legendPositionBottom() = withOption(LEGEND_POSITION, VAL_LEGEND_POS_BOTTOM)
    fun legendPositionTop() = withOption(LEGEND_POSITION, VAL_LEGEND_POS_TOP)

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

    fun legendJustificationCenter() = withOption(LEGEND_JUSTIFICATION, VAL_LEGEND_JUSTIFICATION_CENTER)

    /**
     * Specifies the anchor point for positioning legend inside plot.
     * Justification (0, 0) corresponds to the left, lower corner of the legend.
     * Justification (1, 1) corresponds to the right, upper corner of the legend.
     */
    fun legendJustification(xanchor: Number, yanchor: Number): theme {
        return withOption(
            LEGEND_JUSTIFICATION,
            listOf(xanchor, yanchor)
        )
    }

    fun legendDirectionHorizontal() = withOption(LEGEND_DIRECTION, VAL_LEGEND_DIRECTION_HORIZONTAL)
    fun legendDirectionVertical() = withOption(LEGEND_DIRECTION, VAL_LEGEND_DIRECTION_VERTICAL)


    // Deprecated functions

    @Deprecated("", ReplaceWith("axisTitleBlank()"))
    fun axisTitle_blank() = axisTitleBlank()

    @Deprecated("", ReplaceWith("axisTitleXBlank()"))
    fun axisTitleX_blank() = axisTitleXBlank()

    @Deprecated("", ReplaceWith("axisTitleYBlank()"))
    fun axisTitleY_blank() = axisTitleYBlank()

    @Deprecated("", ReplaceWith("axisTextBlank()"))
    fun axisText_blank() = axisTextBlank()

    @Deprecated("", ReplaceWith("axisTextXBlank()"))
    fun axisTextX_blank() = axisTextXBlank()

    @Deprecated("", ReplaceWith("axisTextYBlank()"))
    fun axisTextY_blank() = axisTextYBlank()

    @Deprecated("", ReplaceWith("axisTicksBlank()"))
    fun axisTicks_blank() = axisTicksBlank()

    @Deprecated("", ReplaceWith("axisTicksXBlank()"))
    fun axisTicksX_blank() = axisTicksXBlank()

    @Deprecated("", ReplaceWith("axisTicksYBlank()"))
    fun axisTicksY_blank() = axisTicksYBlank()

    @Deprecated("", ReplaceWith("axisLineBlank()"))
    fun axisLine_blank() = axisLineBlank()

    @Deprecated("", ReplaceWith("axisTitleBlank()"))
    fun axisLineX_blank() = axisLineXBlank()

    @Deprecated("", ReplaceWith("axisLineYBlank()"))
    fun axisLineY_blank() = axisLineYBlank()

    @Deprecated("", ReplaceWith("legendPositionNone()"))
    fun legendPosition_none() = legendPositionNone()

    @Deprecated("", ReplaceWith("legendPositionLeft()"))
    fun legendPosition_left() = legendPositionLeft()

    @Deprecated("", ReplaceWith("legendPositionRight()"))
    fun legendPosition_right() = legendPositionRight()

    @Deprecated("", ReplaceWith("legendPositionBottom()"))
    fun legendPosition_bottom() = legendPositionBottom()

    @Deprecated("", ReplaceWith("legendPositionTop()"))
    fun legendPosition_top() = legendPositionTop()

    @Deprecated("", ReplaceWith("legendJustificationCenter()"))
    fun legendJustification_center() = legendJustificationCenter()

    @Deprecated("", ReplaceWith("legendDirectionHorizontal()"))
    fun legendDirection_horizontal() = legendDirectionHorizontal()

    @Deprecated("", ReplaceWith("legendDirectionVertical()"))
    fun legendDirection_vertical() = legendDirectionVertical()

    companion object {
        // names
        private const val AXIS_TITLE = ThemeOption.AXIS_TITLE
        private const val AXIS_TITLE_X = "axis_title_x"
        private const val AXIS_TITLE_Y = "axis_title_y"
        private const val AXIS_TEXT = ThemeOption.AXIS_TEXT
        private const val AXIS_TEXT_X = "axis_text_x"
        private const val AXIS_TEXT_Y = "axis_text_y"
        private const val AXIS_TICKS = ThemeOption.AXIS_TICKS
        private const val AXIS_TICKS_X = "axis_ticks_x"
        private const val AXIS_TICKS_Y = "axis_ticks_y"
        private const val AXIS_LINE = ThemeOption.AXIS_LINE
        private const val AXIS_LINE_X = "axis_line_x"
        private const val AXIS_LINE_Y = "axis_line_y"
        private const val LEGEND_POSITION = ThemeOption.LEGEND_POSITION
        private const val LEGEND_JUSTIFICATION = ThemeOption.LEGEND_JUSTIFICATION
        private const val LEGEND_DIRECTION = ThemeOption.LEGEND_DIRECTION

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
