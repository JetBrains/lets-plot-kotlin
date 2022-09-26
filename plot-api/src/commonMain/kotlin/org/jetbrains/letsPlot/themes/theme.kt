/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package org.jetbrains.letsPlot.themes

import jetbrains.datalore.plot.builder.defaultTheme.values.ThemeOption
import jetbrains.datalore.plot.config.Option
import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.intern.filterNonNullValues

/**
 * Use theme() to modify individual components of a theme,
 * allowing you to control the appearance of all non-data components of the plot.
 *
 * All parameters responsible for styling of lines, rectangles (often called 'background') and texts accept:
 * - string "blank" or the result of the `elementBlank()` function.
 * - the result of `elementLine()`, `elementRect()` or `elementText()` respectively.
 *
 * Settings passed via more specific parameters override settings passed via less specific parameters.
 * For example, parameter `axisLineX` is more specific than parameter `axisLine`.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/themes.ipynb)
 *
 * - [legend_and_axis.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/legend_and_axis.ipynb)
 *
 * @param line All line elements.
 * @param rect All rectangle elements.
 * @param text All text elements.
 * @param title All line elements: plot, axes, legends.
 * @param axis All axis elements: lines, ticks, texts, titles.
 *
 * @param axisOntop Option to place axis (lines, tick-marks and labels) over the data layers.
 * @param axisOntopX Option to place X-axis (lines, tick-marks and labels) over the data layers.
 * @param axisOntopY Option to place Y-axis (lines, tick-marks and labels) over the data layers.
 *
 * @param axisTitle Labels of axes.
 * @param axisTitleX Labels of axes.
 * @param axisTitleY Labels of axes.
 *
 * @param axisText Tick labels along axes.
 * @param axisTextX Tick labels along axes.
 * @param axisTextY Tick labels along axes.
 *
 * @param axisTicks Tick marks along axes.
 * @param axisTicksX Tick marks along axes.
 * @param axisTicksY Tick marks along axes.
 *
 * @param axisTicksLength Length of tick marks.
 * @param axisTicksLengthX Length of tick marks.
 * @param axisTicksLengthY Length of tick marks.
 *
 * @param axisLine Lines along axes.
 * @param axisLineX Lines along axes.
 * @param axisLineY Lines along axes.
 *
 * @param legendBackground Background of legend.
 * @param legendText Legend item labels.
 * @param legendTitle Title of legend.
 *
 * @param panelBackground Background of plotting area.
 * @param panelBorder Border around plotting area.
 *
 * @param panelGrid Grid lines.
 * @param panelGridMajor Grid lines.
 * @param panelGridMinor Grid lines.
 * @param panelGridMajorX Grid lines.
 * @param panelGridMinorX Grid lines.
 * @param panelGridMajorY Grid lines.
 * @param panelGridMinorY Grid lines.
 *
 * @param plotBackground Background of the entire plot.
 * @param plotTitle Plot title.
 * @param plotSubtitle Plot subtitle.
 * @param plotCaption Plot caption.
 *
 * @param stripBackground Background of facet labels.
 * @param stripText Facet labels.
 *
 * @param axisTooltip Axes tooltips.
 * @param axisTooltipX Axes tooltips.
 * @param axisTooltipY Axes tooltips.
 *
 * @param axisTooltipText Text in axes tooltips.
 * @param axisTooltipTextX Text in axes tooltips.
 * @param axisTooltipTextY Text in axes tooltips.
 *
 * @param tooltip General tooltip.
 * @param tooltipText Text in general tooltip.
 * @param tooltipTitleText Tooltip title text.
 */
@Suppress("ClassName", "FunctionName")
class theme(
    line: Any? = null,
    rect: Any? = null,
    text: Any? = null,
    title: Any? = null,
    axis: Any? = null,

    @Suppress("SpellCheckingInspection")
    axisOntop: Boolean? = null,
    @Suppress("SpellCheckingInspection")
    axisOntopX: Boolean? = null,
    @Suppress("SpellCheckingInspection")
    axisOntopY: Boolean? = null,

    axisTitle: Any? = null,
    axisTitleX: Any? = null,
    axisTitleY: Any? = null,

    axisText: Any? = null,
    axisTextX: Any? = null,
    axisTextY: Any? = null,

    axisTicks: Any? = null,
    axisTicksX: Any? = null,
    axisTicksY: Any? = null,

    axisTicksLength: Number? = null,
    axisTicksLengthX: Number? = null,
    axisTicksLengthY: Number? = null,

    axisLine: Any? = null,
    axisLineX: Any? = null,
    axisLineY: Any? = null,

    legendBackground: Any? = null,
    legendText: Any? = null,
    legendTitle: Any? = null,

    panelBackground: Any? = null,
    panelBorder: Any? = null,

    panelGrid: Any? = null,
    panelGridMajor: Any? = null,
    panelGridMinor: Any? = null,
    panelGridMajorX: Any? = null,
    panelGridMinorX: Any? = null,
    panelGridMajorY: Any? = null,
    panelGridMinorY: Any? = null,

    plotBackground: Any? = null,
    plotTitle: Any? = null,
    plotSubtitle: Any? = null,
    plotCaption: Any? = null,

    stripBackground: Any? = null,
    stripText: Any? = null,

    axisTooltip: Any? = null,
    axisTooltipX: Any? = null,
    axisTooltipY: Any? = null,

    axisTooltipText: Any? = null,
    axisTooltipTextX: Any? = null,
    axisTooltipTextY: Any? = null,

    tooltip: Any? = null,
    tooltipText: Any? = null,
    tooltipTitleText: Any? = null

) : OptionsMap(
    Option.Plot.THEME, mapOf(
        ThemeOption.LINE to line,
        ThemeOption.RECT to rect,
        ThemeOption.TEXT to text,
        ThemeOption.TITLE to title,
        ThemeOption.AXIS to axis,

        ThemeOption.AXIS_ONTOP to axisOntop,
        ThemeOption.AXIS_ONTOP_X to axisOntopX,
        ThemeOption.AXIS_ONTOP_Y to axisOntopY,

        ThemeOption.AXIS_TITLE to axisTitle,
        ThemeOption.AXIS_TITLE_X to axisTitleX,
        ThemeOption.AXIS_TITLE_Y to axisTitleY,

        ThemeOption.AXIS_TEXT to axisText,
        ThemeOption.AXIS_TEXT_X to axisTextX,
        ThemeOption.AXIS_TEXT_Y to axisTextY,

        ThemeOption.AXIS_TICKS to axisTicks,
        ThemeOption.AXIS_TICKS_X to axisTicksX,
        ThemeOption.AXIS_TICKS_Y to axisTicksY,

        ThemeOption.AXIS_TICKS_LENGTH to axisTicksLength,
        ThemeOption.AXIS_TICKS_LENGTH_X to axisTicksLengthX,
        ThemeOption.AXIS_TICKS_LENGTH_Y to axisTicksLengthY,

        ThemeOption.AXIS_LINE to axisLine,
        ThemeOption.AXIS_LINE_X to axisLineX,
        ThemeOption.AXIS_LINE_Y to axisLineY,

        ThemeOption.LEGEND_BKGR_RECT to legendBackground,
        ThemeOption.LEGEND_TEXT to legendText,
        ThemeOption.LEGEND_TITLE to legendTitle,

        ThemeOption.PANEL_BKGR_RECT to panelBackground,
        ThemeOption.PANEL_BORDER_RECT to panelBorder,

        ThemeOption.PANEL_GRID to panelGrid,
        ThemeOption.PANEL_GRID_MAJOR to panelGridMajor,
        ThemeOption.PANEL_GRID_MINOR to panelGridMinor,
        ThemeOption.PANEL_GRID_MAJOR_X to panelGridMajorX,
        ThemeOption.PANEL_GRID_MINOR_X to panelGridMinorX,
        ThemeOption.PANEL_GRID_MAJOR_Y to panelGridMajorY,
        ThemeOption.PANEL_GRID_MINOR_Y to panelGridMinorY,

        ThemeOption.PLOT_BKGR_RECT to plotBackground,
        ThemeOption.PLOT_TITLE to plotTitle,
        ThemeOption.PLOT_SUBTITLE to plotSubtitle,
        ThemeOption.PLOT_CAPTION to plotCaption,

        ThemeOption.FACET_STRIP_BGR_RECT to stripBackground,
        ThemeOption.FACET_STRIP_TEXT to stripText,

        ThemeOption.AXIS_TOOLTIP to axisTooltip,
        ThemeOption.AXIS_TOOLTIP_X to axisTooltipX,
        ThemeOption.AXIS_TOOLTIP_Y to axisTooltipY,

        ThemeOption.AXIS_TOOLTIP_TEXT to axisTooltipText,
        ThemeOption.AXIS_TOOLTIP_TEXT_X to axisTooltipTextX,
        ThemeOption.AXIS_TOOLTIP_TEXT_Y to axisTooltipTextY,

        ThemeOption.TOOLTIP_RECT to tooltip,
        ThemeOption.TOOLTIP_TEXT to tooltipText,
        ThemeOption.TOOLTIP_TITLE_TEXT to tooltipTitleText
    )
        .filterNonNullValues()
        .toMutableMap()
) {

    private constructor(other: theme) : this() {
        (this.options as MutableMap<String, Any>).putAll(other.options)
    }

    private fun withOption(name: String, value: Any): theme {
        val newTheme = theme(this)
        (newTheme.options as MutableMap<String, Any>)[name] = value
        return newTheme
    }

    fun legendPositionNone() = withOption(ThemeOption.LEGEND_POSITION, VAL_LEGEND_POS_NONE)
    fun legendPositionLeft() = withOption(ThemeOption.LEGEND_POSITION, VAL_LEGEND_POS_LEFT)
    fun legendPositionRight() = withOption(ThemeOption.LEGEND_POSITION, VAL_LEGEND_POS_RIGHT)
    fun legendPositionBottom() = withOption(ThemeOption.LEGEND_POSITION, VAL_LEGEND_POS_BOTTOM)
    fun legendPositionTop() = withOption(ThemeOption.LEGEND_POSITION, VAL_LEGEND_POS_TOP)

    /**
     * Specifies the legend position relative to the plot drawing area.
     * Position (0, 0) corresponds to the left, lower corner of the plot.
     * Position (1, 1) corresponds to the right, upper corner of the plot.
     */
    @Suppress("SpellCheckingInspection")
    fun legendPosition(xpos: Number, ypos: Number): theme {
        return withOption(
            ThemeOption.LEGEND_POSITION,
            listOf(xpos, ypos)
        )
    }

    fun legendJustificationCenter() = withOption(ThemeOption.LEGEND_JUSTIFICATION, VAL_LEGEND_JUSTIFICATION_CENTER)

    /**
     * Specifies the anchor point for positioning legend inside plot.
     * Justification (0, 0) corresponds to the left, lower corner of the legend.
     * Justification (1, 1) corresponds to the right, upper corner of the legend.
     */
    fun legendJustification(xanchor: Number, yanchor: Number): theme {
        return withOption(
            ThemeOption.LEGEND_JUSTIFICATION,
            listOf(xanchor, yanchor)
        )
    }

    fun legendDirectionHorizontal() = withOption(ThemeOption.LEGEND_DIRECTION, VAL_LEGEND_DIRECTION_HORIZONTAL)
    fun legendDirectionVertical() = withOption(ThemeOption.LEGEND_DIRECTION, VAL_LEGEND_DIRECTION_VERTICAL)

    private companion object {
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

/**
 * Specifies how non-data components of the plot are drawn.
 * This theme element draws nothing, and assigns no space.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/themes.ipynb)
 */
fun elementBlank() = mapOf(ThemeOption.Elem.BLANK to true)


/**
 * Specifies how non-data components of the plot are drawn.
 * This theme element draws borders and backgrounds.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/themes.ipynb)
 *
 * @param fill Fill color. Accepts color core as string (HEX or rgb) or Color object.
 * @param color Border color. Accepts color core as string (HEX or rgb) or Color object.
 * @param size Border line width.
 * @param blank Mark as a 'blank' element.
 */
fun elementRect(
    fill: Any? = null,
    color: Any? = null,
    size: Number? = null,
    blank: Boolean = false,
) = mapOf(
    ThemeOption.Elem.FILL to fill,
    ThemeOption.Elem.COLOR to color,
    ThemeOption.Elem.SIZE to size,
    ThemeOption.Elem.BLANK to blank,
).filterNonNullValues()


/**
 * Specifies how non-data components of the plot are drawn.
 * This theme element draws lines.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/themes.ipynb)
 *
 * @param color Line color. Accepts color core as string (HEX or rgb) or Color object.
 * @param size Line width.
 * @param blank Mark as a 'blank' element.
 */
fun elementLine(
    color: Any? = null,
    size: Number? = null,
    blank: Boolean = false,
) = mapOf(
    ThemeOption.Elem.COLOR to color,
    ThemeOption.Elem.SIZE to size,
    ThemeOption.Elem.BLANK to blank,
).filterNonNullValues()


/**
 * Specifies how non-data components of the plot are drawn.
 * This theme element draws texts.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/themes.ipynb)
 *
 * @param color Text color. Accepts color core as string (HEX or rgb) or Color object.
 * @param family Font family.
 * @param face Font face ("plain", "italic", "bold", "bold_italic").
 * @param size Text size in pt.
 * @param hjust Horizontal justification (in [0, 1]).
 *     0 - left-justified
 *     1 - right-justified
 *     0.5 - center-justified
 *     Can be used with values out of range, but behaviour is not specified.
 * @param vjust Vertical justification (in [0, 1]).
 *     0 - bottom-justified
 *     1 - top-justified
 *     0.5 - middle-justified
 *     Can be used with values out of range, but behaviour is not specified.
 * @param margin Margins around the text.
 *     See `margin()` for more details.
 * @param blank Mark as a 'blank' element.
 */
fun elementText(
    color: Any? = null,
    family: Any? = null,
    face: Any? = null,
    size: Number? = null,
    hjust: Number? = null,
    vjust: Number? = null,
    margin: Any? = null,
    blank: Boolean = false,
) = mapOf(
    ThemeOption.Elem.COLOR to color,
    ThemeOption.Elem.FONT_FAMILY to family,
    ThemeOption.Elem.FONT_FACE to face,
    ThemeOption.Elem.SIZE to size,
    ThemeOption.Elem.HJUST to hjust,
    ThemeOption.Elem.VJUST to vjust,
    ThemeOption.Elem.MARGIN to margin,
    ThemeOption.Elem.BLANK to blank,
).filterNonNullValues()

fun margin(t: Any? = null, r: Any? = null, b: Any? = null, l: Any? = null) =
    mapOf("t" to t, "r" to r, "b" to b, "l" to l).filterNonNullValues()