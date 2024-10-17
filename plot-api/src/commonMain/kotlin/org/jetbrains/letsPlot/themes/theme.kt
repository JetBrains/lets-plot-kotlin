/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package org.jetbrains.letsPlot.themes

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.intern.filterNonNullValues

/**
 * Use `theme()` to modify individual components of a theme,
 * allowing you to control the appearance of all non-data components of the plot.
 *
 * All parameters responsible for styling of lines, rectangles (often called "background") and texts accept:
 * - string "blank" or the result of the `elementBlank()` function.
 * - the result of `elementLine()`, `elementRect()` or `elementText()` respectively.
 *
 * Settings passed via more specific parameters override settings passed via less specific parameters.
 * For example, parameter `axisLineX` is more specific than parameter `axisLine`.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/themes.ipynb)
 *
 * - [legend.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/legend.ipynb)
 *
 * - [theme_label_text.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/theme_label_text.ipynb)
 *
 * - [superscript_exponent.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/superscript_exponent.ipynb)
 *
 * - [theme_panel_inset.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/theme_panel_inset.ipynb)
 *
 * - [theme_plot_inset.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/theme_plot_inset.ipynb)
 *
 *
 * @param exponentFormat default="e" ("e", "pow"). Format for numeric labels in scientific notation.
 *  e for "e" notation (e.g. 1e+6);
 *  pow for "power" notation (e.g. 1x10^6). This will enable superscript formatting for the exponent.
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
 * @param panelBorderOntop Option to place border around plotting area over the data layers.
 *
 * @param panelGrid Grid lines.
 * @param panelGridMajor Grid lines.
 * @param panelGridMinor Grid lines.
 * @param panelGridMajorX Grid lines.
 * @param panelGridMinorX Grid lines.
 * @param panelGridMajorY Grid lines.
 * @param panelGridMinorY Grid lines.
 *
 * @param panelGridOntop Option to place major grid lines and minor grid lines over the data layers.
 * @param panelGridOntopX Option to place X-axis major grid lines and minor grid lines over the data layers.
 * @param panelGridOntopY Option to place Y-axis major grid lines and minor grid lines over the data layers.
 *
 * @param panelInset Inset for a panel.
 *  The inset behaves like a padding for `coordPolar(transofrmBkgr = false)` otherwise it behaves like a margin around the panel.
 *  The inset may be specified using a number or a list of numbers:
 *  - a number or list of one number - the same inset it applied to all four sides;
 *  - a list of two numbers - the first inset applies to the top and bottom, the second - to the left and right;
 *  - a list of three numbers - the first margin applies to the top, the second - to the right and left,
 *  the third - to the bottom;
 *  - a list of four numbers - the insets are applied to the top, right, bottom and left in that order.
 *  It is acceptable to use `null` for any side; in this case, the default value for the plot inset side will be used.
 *
 * @param plotBackground Background of the entire plot.
 * @param plotTitle Plot title.
 * @param plotSubtitle Plot subtitle.
 * @param plotCaption Plot caption.
 * @param plotMessage Plot message (e.g. sampling messages).
 *  Set an `elementBlank()` to show nothing.
 *  Set an `elementText()` to show sampling messages (`elementText()` options don't affect a message text).
 * @param plotMargin Margin around entire plot.
 *  Margins around the text.
 *  The margin may be specified using a number or a list of numbers:
 *  - a number or list of one number - the same margin it applied to all four sides;
 *  - a list of two numbers - the first margin applies to the top and bottom, the second - to the left and right;
 *  - a list of three numbers - the first margin applies to the top, the second - to the right and left,
 *  the third - to the bottom;
 *  - a list of four numbers - the margins are applied to the top, right, bottom and left in that order.
 *  It is acceptable to use `null` for any side; in this case, the default side value for the plot margin side will be used.
 * @param plotInset Inset for a plotting area, including the axes with their labels, but without titles.
 *  The inset may be specified using a number or a list of numbers:
 *  - a number or list of one number - the same inset it applied to all four sides;
 *  - a list of two numbers - the first inset applies to the top and bottom, the second - to the left and right;
 *  - a list of three numbers - the first inset applies to the top, the second - to the right and left, the third - to the bottom;
 *  - a list of four numbers - the insets are applied to the top, right, bottom and left in that order.

 *  It is acceptable to use None for any side; in this case, the default value for the plot inset side will be used.
 *
 * @param plotTitlePosition default = "panel" ("panel", "plot").
 *  Alignment of the plot title/subtitle.
 *  A value of "panel" means that title and subtitle are aligned to the plot panels.
 *  A value of "plot" means that title and subtitle are aligned to the entire plot (excluding margins).
 * @param plotCaptionPosition  default = "panel" ("panel", "plot").
 *  Alignment of the plot caption.
 *  A value of "panel" means that caption is aligned to the plot panels.
 *  A value of "plot" means that caption is aligned to the entire plot (excluding margins).
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
 *
 * @param labelText Annotation text.
 *  Annotations are currently supported for pie and bar charts.
 *  Set `elementText()` to specify annotation text parameters: font family and face, text size,
 *  text color (relevant for a pie chart - for those annotations that are outside the pie).
 *
 * @param geom Geometry colors.
 */
@Suppress("ClassName", "FunctionName")
class theme(
    exponentFormat: Any? = null,

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
    @Suppress("SpellCheckingInspection")
    panelBorderOntop: Boolean? = null,

    panelGrid: Any? = null,
    panelGridMajor: Any? = null,
    panelGridMinor: Any? = null,
    panelGridMajorX: Any? = null,
    panelGridMinorX: Any? = null,
    panelGridMajorY: Any? = null,
    panelGridMinorY: Any? = null,

    @Suppress("SpellCheckingInspection")
    panelGridOntop: Boolean? = null,
    @Suppress("SpellCheckingInspection")
    panelGridOntopX: Boolean? = null,
    @Suppress("SpellCheckingInspection")
    panelGridOntopY: Boolean? = null,

    panelInset: Any? = null,

    plotBackground: Any? = null,
    plotTitle: Any? = null,
    plotSubtitle: Any? = null,
    plotCaption: Any? = null,
    plotMessage: Any? = null,
    plotMargin: Any? = null,
    plotInset: Any? = null,

    plotTitlePosition: Any? = null,
    plotCaptionPosition: Any? = null,

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
    tooltipTitleText: Any? = null,

    labelText: Any? = null,

    geom: Any? = null

) : OptionsMap(
    Option.Plot.THEME, mapOf(
        Option.Theme.EXPONENT_FORMAT to exponentFormat,

        Option.Theme.LINE to line,
        Option.Theme.RECT to rect,
        Option.Theme.TEXT to text,
        Option.Theme.TITLE to title,
        Option.Theme.AXIS to axis,

        Option.Theme.AXIS_ONTOP to axisOntop,
        Option.Theme.AXIS_ONTOP_X to axisOntopX,
        Option.Theme.AXIS_ONTOP_Y to axisOntopY,

        Option.Theme.AXIS_TITLE to axisTitle,
        Option.Theme.AXIS_TITLE_X to axisTitleX,
        Option.Theme.AXIS_TITLE_Y to axisTitleY,

        Option.Theme.AXIS_TEXT to axisText,
        Option.Theme.AXIS_TEXT_X to axisTextX,
        Option.Theme.AXIS_TEXT_Y to axisTextY,

        Option.Theme.AXIS_TICKS to axisTicks,
        Option.Theme.AXIS_TICKS_X to axisTicksX,
        Option.Theme.AXIS_TICKS_Y to axisTicksY,

        Option.Theme.AXIS_TICKS_LENGTH to axisTicksLength,
        Option.Theme.AXIS_TICKS_LENGTH_X to axisTicksLengthX,
        Option.Theme.AXIS_TICKS_LENGTH_Y to axisTicksLengthY,

        Option.Theme.AXIS_LINE to axisLine,
        Option.Theme.AXIS_LINE_X to axisLineX,
        Option.Theme.AXIS_LINE_Y to axisLineY,

        Option.Theme.LEGEND_BKGR_RECT to legendBackground,
        Option.Theme.LEGEND_TEXT to legendText,
        Option.Theme.LEGEND_TITLE to legendTitle,

        Option.Theme.PANEL_BKGR_RECT to panelBackground,
        Option.Theme.PANEL_BORDER_RECT to panelBorder,
        Option.Theme.PANEL_BORDER_ONTOP to panelBorderOntop,

        Option.Theme.PANEL_GRID to panelGrid,
        Option.Theme.PANEL_GRID_MAJOR to panelGridMajor,
        Option.Theme.PANEL_GRID_MINOR to panelGridMinor,
        Option.Theme.PANEL_GRID_MAJOR_X to panelGridMajorX,
        Option.Theme.PANEL_GRID_MINOR_X to panelGridMinorX,
        Option.Theme.PANEL_GRID_MAJOR_Y to panelGridMajorY,
        Option.Theme.PANEL_GRID_MINOR_Y to panelGridMinorY,

        Option.Theme.PANEL_GRID_ONTOP to panelGridOntop,
        Option.Theme.PANEL_GRID_ONTOP_X to panelGridOntopX,
        Option.Theme.PANEL_GRID_ONTOP_Y to panelGridOntopY,

        Option.Theme.PANEL_INSET to panelInset,

        Option.Theme.PLOT_BKGR_RECT to plotBackground,
        Option.Theme.PLOT_TITLE to plotTitle,
        Option.Theme.PLOT_SUBTITLE to plotSubtitle,
        Option.Theme.PLOT_CAPTION to plotCaption,
        Option.Theme.PLOT_MESSAGE to plotMessage,
        Option.Theme.PLOT_MARGIN to plotMargin,
        Option.Theme.PLOT_INSET to plotInset,

        Option.Theme.PLOT_TITLE_POSITION to plotTitlePosition,
        Option.Theme.PLOT_CAPTION_POSITION to plotCaptionPosition,

        Option.Theme.FACET_STRIP_BGR_RECT to stripBackground,
        Option.Theme.FACET_STRIP_TEXT to stripText,

        Option.Theme.AXIS_TOOLTIP to axisTooltip,
        Option.Theme.AXIS_TOOLTIP_X to axisTooltipX,
        Option.Theme.AXIS_TOOLTIP_Y to axisTooltipY,

        Option.Theme.AXIS_TOOLTIP_TEXT to axisTooltipText,
        Option.Theme.AXIS_TOOLTIP_TEXT_X to axisTooltipTextX,
        Option.Theme.AXIS_TOOLTIP_TEXT_Y to axisTooltipTextY,

        Option.Theme.TOOLTIP_RECT to tooltip,
        Option.Theme.TOOLTIP_TEXT to tooltipText,
        Option.Theme.TOOLTIP_TITLE_TEXT to tooltipTitleText,

        Option.Theme.ANNOTATION_TEXT to labelText,

        Option.Theme.GEOM to geom
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

    fun legendPositionNone() = withOption(Option.Theme.LEGEND_POSITION, VAL_LEGEND_POS_NONE)
    fun legendPositionLeft() = withOption(Option.Theme.LEGEND_POSITION, VAL_LEGEND_POS_LEFT)
    fun legendPositionRight() = withOption(Option.Theme.LEGEND_POSITION, VAL_LEGEND_POS_RIGHT)
    fun legendPositionBottom() = withOption(Option.Theme.LEGEND_POSITION, VAL_LEGEND_POS_BOTTOM)
    fun legendPositionTop() = withOption(Option.Theme.LEGEND_POSITION, VAL_LEGEND_POS_TOP)

    /**
     * Specifies the legend position relative to the plot drawing area.
     * Position (0, 0) corresponds to the left, lower corner of the plot.
     * Position (1, 1) corresponds to the right, upper corner of the plot.
     */
    @Suppress("SpellCheckingInspection")
    fun legendPosition(xpos: Number, ypos: Number): theme {
        return withOption(
            Option.Theme.LEGEND_POSITION,
            listOf(xpos, ypos)
        )
    }

    fun legendJustificationCenter() = withOption(Option.Theme.LEGEND_JUSTIFICATION, VAL_LEGEND_JUSTIFICATION_CENTER)

    /**
     * Specifies the anchor point for positioning legend inside plot.
     * Justification (0, 0) corresponds to the left, lower corner of the legend.
     * Justification (1, 1) corresponds to the right, upper corner of the legend.
     */
    fun legendJustification(xanchor: Number, yanchor: Number): theme {
        return withOption(
            Option.Theme.LEGEND_JUSTIFICATION,
            listOf(xanchor, yanchor)
        )
    }

    fun legendDirectionHorizontal() = withOption(Option.Theme.LEGEND_DIRECTION, VAL_LEGEND_DIRECTION_HORIZONTAL)
    fun legendDirectionVertical() = withOption(Option.Theme.LEGEND_DIRECTION, VAL_LEGEND_DIRECTION_VERTICAL)

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
 * - [themes.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/themes.ipynb)
 */
fun elementBlank() = mapOf(Option.Theme.Elem.BLANK to true)


/**
 * Specifies how non-data components of the plot are drawn.
 * This theme element draws borders and backgrounds.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/themes.ipynb)
 *
 * - [theme_linetype.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/theme_linetype.ipynb)
 *
 * @param fill Fill color. Accepts color core as string (HEX or rgb) or Color object.
 * @param color Border color. Accepts color core as string (HEX or rgb) or Color object.
 * @param size Border line width.
 * @param linetype Type of the line.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 * @param blank Mark as a 'blank' element.
 */
fun elementRect(
    fill: Any? = null,
    color: Any? = null,
    size: Number? = null,
    linetype: Any? = null,
    blank: Boolean = false,
) = mapOf(
    Option.Theme.Elem.FILL to fill,
    Option.Theme.Elem.COLOR to color,
    Option.Theme.Elem.SIZE to size,
    Option.Theme.Elem.LINETYPE to linetype,
    Option.Theme.Elem.BLANK to blank,
).filterNonNullValues()


/**
 * Specifies how non-data components of the plot are drawn.
 * This theme element draws lines.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/themes.ipynb)
 *
 * - [theme_linetype.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/theme_linetype.ipynb)
 *
 * @param color Line color. Accepts color core as string (HEX or rgb) or Color object.
 * @param size Line width.
 * @param linetype Type of the line.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern `offset to listOf(dash, gap, ...)` / `listOf(dash, gap, ...)`.
 * @param blank Mark as a 'blank' element.
 */
fun elementLine(
    color: Any? = null,
    size: Number? = null,
    linetype: Any? = null,
    blank: Boolean = false,
) = mapOf(
    Option.Theme.Elem.COLOR to color,
    Option.Theme.Elem.SIZE to size,
    Option.Theme.Elem.LINETYPE to linetype,
    Option.Theme.Elem.BLANK to blank,
).filterNonNullValues()


/**
 * Specifies how non-data components of the plot are drawn.
 * This theme element draws texts.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/themes.ipynb)
 *
 * - [axis_text_angle.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/axis_text_angle.ipynb)
 *
 * @param color Text color. Accepts color core as string (HEX or rgb) or Color object.
 * @param family Font family.
 * @param face Font face ("plain", "italic", "bold", "bold_italic").
 * @param size Text size in pt.
 * @param angle Angle to rotate the text (in degrees).
 * @param hjust Horizontal justification (in `[0, 1]`).
 *  - 0 - left-justified
 *  - 1 - right-justified
 *  - 0.5 - center-justified
 *  Can be used with values out of range, but behaviour is not specified.
 * @param vjust Vertical justification (in `[0, 1]`).
 *  - 0 - bottom-justified
 *  - 1 - top-justified
 *  - 0.5 - middle-justified
 *  Can be used with values out of range, but behaviour is not specified.
 * @param margin Margins around the text.
 *  Margins around the text.
 *  The margin may be specified using a number or a list of numbers:
 *  - a number or list of one number - the same margin it applied to all four sides;
 *  - a list of two numbers - the first margin applies to the top and bottom, the second - to the left and right;
 *  - a list of three numbers -  the first margin applies to the top, the second - to the right and left,
 *  the third - to the bottom;
 *  - a list of four numbers - the margins are applied to the top, right, bottom and left in that order.
 *  It is acceptable to use `null` for any side; in this case, the default side value for this element will be used.
 * @param blank Mark as a 'blank' element.
 */
fun elementText(
    color: Any? = null,
    family: Any? = null,
    face: Any? = null,
    size: Number? = null,
    angle: Number? = null,
    hjust: Number? = null,
    vjust: Number? = null,
    margin: Any? = null,
    blank: Boolean = false,
) = mapOf(
    Option.Theme.Elem.COLOR to color,
    Option.Theme.Elem.FONT_FAMILY to family,
    Option.Theme.Elem.FONT_FACE to face,
    Option.Theme.Elem.SIZE to size,
    Option.Theme.Elem.ANGLE to angle,
    Option.Theme.Elem.HJUST to hjust,
    Option.Theme.Elem.VJUST to vjust,
    Option.Theme.Elem.MARGIN to margin,
    Option.Theme.Elem.BLANK to blank,
).filterNonNullValues()

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated(
    message = "Please, replace with one number or a list of numbers.\n" +
            "See doc for the `plotMargin` parameter in `theme()`: https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.themes/theme",
    level = DeprecationLevel.WARNING
)
fun margin(t: Any? = null, r: Any? = null, b: Any? = null, l: Any? = null) = listOf(t, r, b, l)


/**
 * Specifies new values for the named colors.
 *
 * ## Examples
 *
 * - [geom_theme_colors.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/geom_theme_colors.ipynb)
 *
 * @param pen Color to use by name "pen".
 * @param brush Color to use by name "brush".
 * @param paper Color to use by name "paper".
 */
fun elementGeom(
    pen: Any? = null,
    brush: Any? = null,
    paper: Any? = null,
) = mapOf(
    Option.Theme.Geom.PEN to pen,
    Option.Theme.Geom.BRUSH to brush,
    Option.Theme.Geom.PAPER to paper
)