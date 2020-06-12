/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.OtherPlotFeature
import jetbrains.letsPlot.intern.filterNonNullValues

/**
* Use theme() to modify individual components of a theme,
* allowing you to control the appearance of all non-data components of the plot.
*
* @param axisTitle result of element_text() or (element_blank() | "blank") to draw nothing and assign no space.
* label of axes
* 
* @param axisTitleX result of element_text() or (element_blank() | "blank") to draw nothing and assign no space.
* x axis label
* 
* @param axisTitleY result of element_text() or (element_blank() | "blank") to draw nothing and assign no space.
* y axis label
* 
* @param axisText result of element_text() or (element_blank() | "blank") to draw nothing and assign no space.
* tick labels along axes
* 
* @param axisTextX result of element_text() or (element_blank() | "blank") to draw nothing and assign no space.
* x axis tick labels
* 
* @param axisTextY result of element_text() or (element_blank() | "blank") to draw nothing and assign no space.
* y axis tick labels
* 
* @param axisTicks result of element_line() or (element_blank() | "blank") to draw nothing and assign no space.
* tick marks along axes
* 
* @param axisTicksX result of element_line() or (element_blank() | "blank") to draw nothing and assign no space.
* x axis tick marks
* 
* @param axisTicksY result of element_line() or (element_blank() | "blank") to draw nothing and assign no space.
* y axis tick marks
* 
* @param axisLine result of element_line() or (element_blank() | "blank") to draw nothing and assign no space.
* lines along axes
* 
* @param axisLineX result of element_line() or (element_blank() | "blank") to draw nothing and assign no space.
* line along x axis
* 
* @param axisLineY result of element_line() or (element_blank() | "blank") to draw nothing and assign no space.
* line along y axis
 *
 * @param legendPosition ("none" | "left" | "right" | "bottom" | "top") or two-element numeric vector.
* the position of legends
* 
 */
fun theme(
    axisTitle: String? = null,
    axisTitleX: String? = null,
    axisTitleY: String? = null,
    axisText: String? = null,
    axisTextX: String? = null,
    axisTextY: String? = null,
    axisTicks: String? = null,
    axisTicksX: String? = null,
    axisTicksY: String? = null,
    axisLine: String? = null,
    axisLineX: String? = null,
    axisLineY: String? = null,
    legendPosition: String? = null,
    otherOptions: Options = Options.empty()
) : OtherPlotFeature {
    @Suppress("UNCHECKED_CAST")
    val options = mapOf(
        "axis_title" to axisTitle,
        "axis_title_x" to axisTitleX,
        "axis_title_y" to axisTitleY,
        "axis_text" to axisText,
        "axis_text_x" to axisTextX,
        "axis_text_y" to axisTextY,
        "axis_ticks" to axisTicks,
        "axis_ticks_x" to axisTicksX,
        "axis_ticks_y" to axisTicksY,
        "axis_line" to axisLine,
        "axis_line_x" to axisLineX,
        "axis_line_y" to axisLineY,
        "legend_position" to legendPosition,
        "other_options" to otherOptions
    ).filterNonNullValues()

    return OtherPlotFeature(Option.Plot.THEME, options)
}

/**
 *  Element to draw nothing and assigns no space
 */
@Suppress("FunctionName")
fun element_blank() = "blank"
