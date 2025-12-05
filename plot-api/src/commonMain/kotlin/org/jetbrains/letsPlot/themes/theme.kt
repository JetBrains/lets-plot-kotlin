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
 * See also the [Plot Layout Diagrams](https://lets-plot.org/kotlin/presentation-options.html#plot-layout-diagrams) section.
 *
 * Most parameters in `theme()` control styling of text, line, and rectangular elements.
 *
 * All styling parameters accept:
 * - String "blank" or result of `elementBlank()` to hide the element
 * - Additionally, based on element type:
 *   - Text elements: result of `elementText()`
 *   - Rectangular elements: result of `elementRect()`
 *   - Line elements: result of `elementLine()`
 *
 * Parameter naming follows a pattern:
 * - Parameters controlling text elements end with "Text" or "Title" (e.g., `axisText`, `plotTitle`)
 * - Parameters controlling rectangular elements end with "Background" or "Rect" (e.g., `panelBackground`)
 * - Parameters controlling line elements end with "Line", "Grid", "Ticks", or "Border" (e.g., `axisLine`, `panelGrid`)
 *
 * Settings passed via more specific parameters override settings passed via less specific parameters.
 * For example, parameter `axisLineX` is more specific than parameter `axisLine`.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://datalore.jetbrains.com/report/static/HZqq77cegYd.E7get_WnChZ/rjq52BpjPak2geihq3ol1h)
 *
 * - [legend.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/legend.ipynb)
 *
 * - [superscript_exponent.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/superscript_exponent.ipynb)
 *
 * @param exponentFormat default="pow" ("e", "pow", "pow_full").
 *  Controls the appearance of numbers formatted with "e" or "g" types.
 *
 *  Value is either a string - style, or a list: `listOf(style, lower_exp_bound, upper_exp_bound)`
 *  where `style` can be:
 *
 *  - `"e"` : e-notation (e.g., 1e+6)
 *  - `"pow"` : superscript powers of 10 in shortened form (e.g., 10^6)
 *  - `"pow_full"` : superscript powers of 10 with coefficient (e.g., 1×10^6)
 *
 *  For `"g"` type formatting, scientific notation is applied when the number's exponent
 *  is less than or equal to the `lower_exp_bound` (-7 by default) or greater than or equal
 *  to the `upper_exp_bound` (6 by default, but can be affected by `precision` in format specifier).
 *
 *  See: [Formatting](https://lets-plot.org/kotlin/formats.html)
 *
 * @param line Style settings for all line elements.
 * @param rect Style settings for all rectangular elements.
 * @param text Style settings for all text elements.
 * @param title Style settings for all title elements: plot, axes, legends.
 * @param axis Style settings for all axis elements: lines, ticks, texts, titles.
 *
 * @param axisOntop Option to place axis (lines, tick-marks and labels) over the data layers.
 * @param axisOntopX Option to place X-axis (lines, tick-marks and labels) over the data layers.
 * @param axisOntopY Option to place Y-axis (lines, tick-marks and labels) over the data layers.
 *
 * @param axisTitle Style settings for axis titles.
 * @param axisTitleX Style settings for axis titles.
 * @param axisTitleY Style settings for axis titles.
 *
 * @param axisText Style settings for tick labels along axes.
 * @param axisTextX Style settings for tick labels along axes.
 * @param axisTextY Style settings for tick labels along axes.
 *
 * @param axisTicks Style settings for tick marks along axes.
 * @param axisTicksX Style settings for tick marks along axes.
 * @param axisTicksY Style settings for tick marks along axes.
 *
 * @param axisTicksLength Length of tick marks.
 * @param axisTicksLengthX Length of tick marks.
 * @param axisTicksLengthY Length of tick marks.
 *
 * @param axisTextSpacing Spacing between the axis label text and its tick mark.
 * @param axisTextSpacingX Spacing between the axis label text and its tick mark.
 * @param axisTextSpacingY Spacing between the axis label text and its tick mark.
 *
 * @param axisLine Style settings for lines along axes.
 * @param axisLineX Style settings for lines along axes.
 * @param axisLineY Style settings for lines along axes.
 *
 * @param legendBackground Style settings for background of legend.
 * @param legendText Style settings for legend item labels.
 * @param legendTitle Style settings for legend title.
 * @param legendMargin Margin around each legend.
 *  The margin may be specified using a number or a list of numbers:
 *  - a single number or a list of one number - the same margin is applied to all four sides;
 *  - a list of two numbers - the first margin applies to the top and bottom, the second - to the left and right;
 *  - a list of three numbers - the first margin applies to the top, the second - to the right and left,
 *  the third - to the bottom;
 *  - a list of four numbers - the margins are applied to the top, right, bottom and left in that order.
 *  It is acceptable to use `null` for any side; in this case, the default side value for the legend margin side will be used.
 * @param legendSpacing Spacing between legends.
 * @param legendSpacingX Spacing between legends in the horizontal direction.
 * @param legendSpacingY Spacing between legends in the vertical direction.
 * @param legendKey Style settings for legend key background.
 * @param legendKeySize Size of legend keys.
 * @param legendKeyWidth Key background width.
 * @param legendKeyHeight Key background height.
 * @param legendKeySpacing Spacing between legend keys
 * @param legendKeySpacingX Spacing between legend keys in the horizontal direction.
 * @param legendKeySpacingY Spacing between legend keys in the vertical direction.
 * @param legendBoxSpacing Spacing between plotting area and legend box.
 * @param legendTicks Style settings for tick marks in colorbars.
 * @param legendTicksLength Length of tick marks in colorbar.
 *
 * @param panelBackground Style settings for background of plotting area.
 * @param panelBorder Style settings for border around plotting area.
 * @param panelBorderOntop Option to place border around plotting area over the data layers.
 *
 * @param panelGrid Style settings for grid lines.
 * @param panelGridMajor Style settings for major grid lines.
 * @param panelGridMinor Style settings for minor grid lines.
 * @param panelGridMajorX Style settings for major grid lines.
 * @param panelGridMinorX Style settings for minor grid lines.
 * @param panelGridMajorY Style settings for major grid lines.
 * @param panelGridMinorY Style settings for minor grid lines.
 *
 * @param panelGridOntop Option to place major grid lines and minor grid lines over the data layers.
 * @param panelGridOntopX Option to place X-axis major grid lines and minor grid lines over the data layers.
 * @param panelGridOntopY Option to place Y-axis major grid lines and minor grid lines over the data layers.
 *
 * @param panelInset Inset for a panel.
 *  The inset behaves like a padding for `coordPolar(transformBkgr = false)` otherwise it behaves like a margin around the panel.
 *  The inset may be specified using a number or a list of numbers:
 *  - a single number or a list of one number - the same inset is applied to all four sides;
 *  - a list of two numbers - the first inset applies to the top and bottom, the second - to the left and right;
 *  - a list of three numbers - the first margin applies to the top, the second - to the right and left,
 *  the third - to the bottom;
 *  - a list of four numbers - the insets are applied to the top, right, bottom and left in that order.
 *  It is acceptable to use `null` for any side; in this case, the default value for the panel inset side will be used.
 *
 * @param plotBackground Style settings for overall plot background.
 * @param plotTitle Style settings for plot title.
 * @param plotSubtitle Style settings for plot subtitle.
 * @param plotCaption Style settings for plot caption.
 * @param plotMessage Style settings for plot message (e.g. sampling messages).
 *  Set an `elementBlank()` to show nothing.
 *  Set an `elementText()` to show sampling messages (`elementText()` options do not affect the message text).
 * @param plotMargin Margin around entire plot.
 *  The margin may be specified using a number or a list of numbers:
 *  - a single number or a list of one number - the same margin is applied to all four sides;
 *  - a list of two numbers - the first margin applies to the top and bottom, the second - to the left and right;
 *  - a list of three numbers - the first margin applies to the top, the second - to the right and left,
 *  the third - to the bottom;
 *  - a list of four numbers - the margins are applied to the top, right, bottom and left in that order.
 *  It is acceptable to use `null` for any side; in this case, the default side value for the plot margin side will be used.
 * @param plotInset Inset for a plotting area, including the axes with their labels, but without titles.
 *  The inset may be specified using a number or a list of numbers:
 *  - a single number or a list of one number - the same inset is applied to all four sides;
 *  - a list of two numbers - the first inset applies to the top and bottom, the second - to the left and right;
 *  - a list of three numbers - the first inset applies to the top, the second - to the right and left, the third - to the bottom;
 *  - a list of four numbers - the insets are applied to the top, right, bottom and left in that order.

 *  It is acceptable to use `null` for any side; in this case, the default value for the plot inset side will be used.
 *
 * @param plotTitlePosition default = "panel" ("panel", "plot").
 *  Alignment of the plot title/subtitle.
 *  A value of "panel" means that title and subtitle are aligned to the plot panels.
 *  A value of "plot" means that title and subtitle are aligned to the entire plot (excluding margins).
 * @param plotCaptionPosition  default = "panel" ("panel", "plot").
 *  Alignment of the plot caption.
 *  A value of "panel" means that caption is aligned to the plot panels.
 *  A value of "plot" means that caption is aligned to the entire plot (excluding margins).
 * @param stripBackground Style settings for facet strip background.
 * @param stripBackgroundX Style settings for horizontal facet strip background.
 * @param stripBackgroundY Style settings for vertical facet strip background.
 * @param stripText Style settings for facet labels.
 * @param stripTextX Style settings for horizontal facet labels.
 * @param stripTextY Style settings for vertical facet labels.
 * @param stripSpacing Spacing between facet labels and the plotting area.
 * @param stripSpacingX Spacing between facet labels and the plotting area in horizontal direction, inherited from ``stripSpacing``.
 * @param stripSpacingY Spacing between facet labels and the plotting area in vertical direction, inherited from ``stripSpacing``.
 * @param panelSpacing Spacing between panels in facets.
 * @param panelSpacingX Spacing between panels in facets in horizontal direction, inherited from ``panelSpacing``.
 * @param panelSpacingY Spacing between panels in facets in vertical direction, inherited from ``panelSpacing``.
 *
 * @param axisTooltip Style settings for axes tooltips.
 * @param axisTooltipX Style settings for axes tooltips.
 * @param axisTooltipY Style settings for axes tooltips.
 *
 * @param axisTooltipText Style settings for text in axes tooltips.
 * @param axisTooltipTextX Style settings for text in axes tooltips.
 * @param axisTooltipTextY Style settings for text in axes tooltips.
 *
 * @param tooltip Style settings for general tooltip.
 * @param tooltipText Style settings for text in general tooltip.
 * @param tooltipTitleText Style settings for tooltip title text.
 *
 * @param labelText Style settings for annotation text.
 *  Annotations are currently supported for pie and bar charts.
 *  Set `elementText()` to specify annotation text parameters: font family and face, text size,
 *  text color (relevant for a pie chart - for those annotations that are outside the pie).
 *
 * @param geom Color settings for geometries.
 */
@Suppress("ClassName")
class theme(
    exponentFormat: Any? = null,

    line: Any? = null,
    rect: Any? = null,
    text: Any? = null,
    title: Any? = null,
    axis: Any? = null,

    axisOntop: Boolean? = null,
    axisOntopX: Boolean? = null,
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

    axisTextSpacing: Number? = null,
    axisTextSpacingX: Number? = null,
    axisTextSpacingY: Number? = null,

    axisLine: Any? = null,
    axisLineX: Any? = null,
    axisLineY: Any? = null,

    legendBackground: Any? = null,
    legendText: Any? = null,
    legendTitle: Any? = null,
    legendMargin: Any? = null,
    legendSpacing: Number? = null,
    legendSpacingX: Number? = null,
    legendSpacingY: Number? = null,

    legendKey: Any? = null,
    legendKeySize: Number? = null,
    legendKeyWidth: Number? = null,
    legendKeyHeight: Number? = null,
    legendKeySpacing: Number? = null,
    legendKeySpacingX: Number? = null,
    legendKeySpacingY: Number? = null,

    legendBoxSpacing: Number? = null,

    legendTicks: Any? = null,
    legendTicksLength: Number? = null,

    panelBackground: Any? = null,
    panelBorder: Any? = null,
    panelBorderOntop: Boolean? = null,

    panelGrid: Any? = null,
    panelGridMajor: Any? = null,
    panelGridMinor: Any? = null,
    panelGridMajorX: Any? = null,
    panelGridMinorX: Any? = null,
    panelGridMajorY: Any? = null,
    panelGridMinorY: Any? = null,

    panelGridOntop: Boolean? = null,
    panelGridOntopX: Boolean? = null,
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
    stripBackgroundX: Any? = null,
    stripBackgroundY: Any? = null,
    stripText: Any? = null,
    stripTextX: Any? = null,
    stripTextY: Any? = null,
    stripSpacing: Number? = null,
    stripSpacingX: Number? = null,
    stripSpacingY: Number? = null,
    panelSpacing: Number? = null,
    panelSpacingX: Number? = null,
    panelSpacingY: Number? = null,

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

        Option.Theme.AXIS_TEXT_SPACING to axisTextSpacing,
        Option.Theme.AXIS_TEXT_SPACING_X to axisTextSpacingX,
        Option.Theme.AXIS_TEXT_SPACING_Y to axisTextSpacingY,

        Option.Theme.AXIS_LINE to axisLine,
        Option.Theme.AXIS_LINE_X to axisLineX,
        Option.Theme.AXIS_LINE_Y to axisLineY,

        Option.Theme.LEGEND_BKGR_RECT to legendBackground,
        Option.Theme.LEGEND_TEXT to legendText,
        Option.Theme.LEGEND_TITLE to legendTitle,
        Option.Theme.LEGEND_MARGIN to legendMargin,
        Option.Theme.LEGEND_SPACING to legendSpacing,
        Option.Theme.LEGEND_SPACING_X to legendSpacingX,
        Option.Theme.LEGEND_SPACING_Y to legendSpacingY,

        Option.Theme.LEGEND_KEY_RECT to legendKey,
        Option.Theme.LEGEND_KEY_SIZE to legendKeySize,
        Option.Theme.LEGEND_KEY_WIDTH to legendKeyWidth,
        Option.Theme.LEGEND_KEY_HEIGHT to legendKeyHeight,
        Option.Theme.LEGEND_KEY_SPACING to legendKeySpacing,
        Option.Theme.LEGEND_KEY_SPACING_X to legendKeySpacingX,
        Option.Theme.LEGEND_KEY_SPACING_Y to legendKeySpacingY,

        Option.Theme.LEGEND_BOX_SPACING to legendBoxSpacing,

        Option.Theme.LEGEND_TICKS to legendTicks,
        Option.Theme.LEGEND_TICKS_LENGTH to legendTicksLength,

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
        Option.Theme.FACET_STRIP_BGR_RECT_X to stripBackgroundX,
        Option.Theme.FACET_STRIP_BGR_RECT_Y to stripBackgroundY,
        Option.Theme.FACET_STRIP_TEXT to stripText,
        Option.Theme.FACET_STRIP_TEXT_X to stripTextX,
        Option.Theme.FACET_STRIP_TEXT_Y to stripTextY,
        Option.Theme.FACET_STRIP_SPACING to stripSpacing,
        Option.Theme.FACET_STRIP_SPACING_X to stripSpacingX,
        Option.Theme.FACET_STRIP_SPACING_Y to stripSpacingY,
        Option.Theme.FACET_PANEL_SPACING to panelSpacing,
        Option.Theme.FACET_PANEL_SPACING_X to panelSpacingX,
        Option.Theme.FACET_PANEL_SPACING_Y to panelSpacingY,

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

    fun legendPositionNone() = withOption(Option.Theme.LEGEND_POSITION, Option.Theme.Legend.POSITION_NONE)
    fun legendPositionLeft() = withOption(Option.Theme.LEGEND_POSITION, Option.Theme.Legend.POSITION_LEFT)
    fun legendPositionRight() = withOption(Option.Theme.LEGEND_POSITION, Option.Theme.Legend.POSITION_RIGHT)
    fun legendPositionBottom() = withOption(Option.Theme.LEGEND_POSITION, Option.Theme.Legend.POSITION_BOTTOM)
    fun legendPositionTop() = withOption(Option.Theme.LEGEND_POSITION, Option.Theme.Legend.POSITION_TOP)

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

    fun legendJustificationCenter() =
        withOption(Option.Theme.LEGEND_JUSTIFICATION, Option.Theme.Legend.JUSTIFICATION_CENTER)

    fun legendJustificationLeft() =
        withOption(Option.Theme.LEGEND_JUSTIFICATION, Option.Theme.Legend.JUSTIFICATION_LEFT)

    fun legendJustificationRight() =
        withOption(Option.Theme.LEGEND_JUSTIFICATION, Option.Theme.Legend.JUSTIFICATION_RIGHT)

    fun legendJustificationTop() =
        withOption(Option.Theme.LEGEND_JUSTIFICATION, Option.Theme.Legend.JUSTIFICATION_TOP)

    fun legendJustificationBottom() =
        withOption(Option.Theme.LEGEND_JUSTIFICATION, Option.Theme.Legend.JUSTIFICATION_BOTTOM)

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

    fun legendDirectionHorizontal() =
        withOption(Option.Theme.LEGEND_DIRECTION, Option.Theme.Legend.DIRECTION_HORIZONTAL)

    fun legendDirectionVertical() = withOption(Option.Theme.LEGEND_DIRECTION, Option.Theme.Legend.DIRECTION_VERTICAL)

    /**
     *  Arrangement of multiple legends: horizontal or vertical.
     */
    fun legendBoxHorizontal() = withOption(Option.Theme.LEGEND_BOX, Option.Theme.Legend.ARRANGEMENT_HORIZONTAL)
    fun legendBoxVertical() = withOption(Option.Theme.LEGEND_BOX, Option.Theme.Legend.ARRANGEMENT_VERTICAL)

    /**
     *  Justification of each legend within the overall bounding box, when there are multiple legends.
     */
    fun legendBoxJustificationLeft() = withOption(Option.Theme.LEGEND_BOX_JUST, Option.Theme.Legend.JUSTIFICATION_LEFT)
    fun legendBoxJustificationRight() =
        withOption(Option.Theme.LEGEND_BOX_JUST, Option.Theme.Legend.JUSTIFICATION_RIGHT)

    fun legendBoxJustificationTop() = withOption(Option.Theme.LEGEND_BOX_JUST, Option.Theme.Legend.JUSTIFICATION_TOP)
    fun legendBoxJustificationBottom() =
        withOption(Option.Theme.LEGEND_BOX_JUST, Option.Theme.Legend.JUSTIFICATION_BOTTOM)

    fun legendBoxJustificationCenter() =
        withOption(Option.Theme.LEGEND_BOX_JUST, Option.Theme.Legend.JUSTIFICATION_CENTER)
}

/**
 * Theme element that draws nothing and allocates no space for non-data components of the plot.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://datalore.jetbrains.com/report/static/HZqq77cegYd.E7get_WnChZ/rjq52BpjPak2geihq3ol1h)
 */
fun elementBlank() = mapOf(Option.Theme.Elem.BLANK to true)


/**
 * Theme element that draws rectangular non-data components of the plot: borders and backgrounds.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://datalore.jetbrains.com/report/static/HZqq77cegYd.E7get_WnChZ/rjq52BpjPak2geihq3ol1h)
 *
 * @param fill Fill color. Accepts color code as a string (HEX or rgb) or Color object.
 * @param color Border color. Accepts color code as a string (HEX or rgb) or Color object.
 * @param size Border line width.
 * @param linetype Type of the line.
 *  Accept codes or names (0 = "blank", 1 = "solid", 2 = "dashed", 3 = "dotted", 4 = "dotdash", 5 = "longdash", 6 = "twodash"),
 *  a hex string (up to 8 digits for dash-gap lengths),
 *  or a pattern like `offset to listOf(dash, gap, ...)` or just `listOf(dash, gap, ...)`.
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
 * Theme element that draws line-based non-data components of the plot.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://datalore.jetbrains.com/report/static/HZqq77cegYd.E7get_WnChZ/rjq52BpjPak2geihq3ol1h)
 *
 * @param color Line color. Accepts color code as a string (HEX or rgb) or Color object.
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
 * Theme element that draws text for non-data components of the plot.
 *
 * ## Examples
 *
 * - [themes.ipynb](https://datalore.jetbrains.com/report/static/HZqq77cegYd.E7get_WnChZ/rjq52BpjPak2geihq3ol1h)
 *
 * - [axis_text_angle.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/axis_text_angle.ipynb)
 *
 * @param color Text color. Accepts color code as a string (HEX or rgb) or Color object.
 * @param family Font family.
 * @param face Font face ("plain", "italic", "bold", "bold_italic").
 * @param size Text size in px.
 * @param angle Angle to rotate the text (in degrees).
 * @param hjust Horizontal justification (in `[0, 1]`).
 *  - 0 - left-justified
 *  - 1 - right-justified
 *  - 0.5 - center-justified
 *  Values outside this range are allowed, but the behavior is unspecified.
 * @param vjust Vertical justification (in `[0, 1]`).
 *  - 0 - bottom-justified
 *  - 1 - top-justified
 *  - 0.5 - middle-justified
 *  Values outside this range are allowed, but the behavior is unspecified.
 * @param margin Margins around the text.
 *  The margin may be specified using a number or a list of numbers:
 *  - a single number or a list of one number - the same margin is applied to all four sides;
 *  - a list of two numbers - the first margin applies to the top and bottom, the second - to the left and right;
 *  - a list of three numbers - the first margin applies to the top, the second - to the right and left,
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


/**
 * Theme element that draws text with Markdown support for non-data components of the plot.
 *
 * Supported features:
 *
 * - Emphasis (*, **, ***, _, __, ___)
 * - Coloring with inline style (`<span style='color:red'>text</span>`)
 * - Links with anchor tags (`<a href="https://lets-plot.org">Lets-Plot</a>`). Supports target attribute (default is `"_blank"`)
 * - Multiple lines using double space and a newline delimiter (  `\n`)
 *
 * ## Examples
 *
 * - [markdown.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/markdown.ipynb)
 *
 * - [themes.ipynb](https://datalore.jetbrains.com/report/static/HZqq77cegYd.E7get_WnChZ/rjq52BpjPak2geihq3ol1h)
 *
 * @param color Text color. Accepts color code as a string (HEX or rgb) or Color object.
 * @param family Font family.
 * @param face Font face ("plain", "italic", "bold", "bold_italic").
 * @param size Text size in px.
 * @param angle Angle to rotate the text (in degrees).
 * @param hjust Horizontal justification (in `[0, 1]`).
 *  - 0 - left-justified
 *  - 1 - right-justified
 *  - 0.5 - center-justified
 *  Values outside this range are allowed, but the behavior is unspecified.
 * @param vjust Vertical justification (in `[0, 1]`).
 *  - 0 - bottom-justified
 *  - 1 - top-justified
 *  - 0.5 - middle-justified
 *  Values outside this range are allowed, but the behavior is unspecified.
 * @param margin Margins around the text.
 *  The margin may be specified using a number or a list of numbers:
 *  - a single number or a list of one number - the same margin is applied to all four sides;
 *  - a list of two numbers - the first margin applies to the top and bottom, the second - to the left and right;
 *  - a list of three numbers - the first margin applies to the top, the second - to the right and left,
 *  the third - to the bottom;
 *  - a list of four numbers - the margins are applied to the top, right, bottom and left in that order.
 *  It is acceptable to use `null` for any side; in this case, the default side value for this element will be used.
 * @param blank Mark as a 'blank' element.
 */
fun elementMarkdown(
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
    "markdown" to true,
    Option.Theme.Elem.COLOR to color,
    Option.Theme.Elem.FONT_FAMILY to family,
    Option.Theme.Elem.FONT_FACE to face,
    Option.Theme.Elem.SIZE to size,
    Option.Theme.Elem.ANGLE to angle,
    Option.Theme.Elem.HJUST to hjust,
    Option.Theme.Elem.VJUST to vjust,
    Option.Theme.Elem.MARGIN to margin,
    Option.Theme.Elem.BLANK to blank
).filterNonNullValues()

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated(
    message = "Please, replace with one number or a list of numbers.\n" +
            "See doc for the `plotMargin` parameter in `theme()`: https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.themes/theme",
    level = DeprecationLevel.WARNING
)
fun margin(t: Any? = null, r: Any? = null, b: Any? = null, l: Any? = null) = listOf(t, r, b, l)


/**
 * Theme element that specifies custom values for named geom colors used in plot elements.
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