/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.scale

import org.jetbrains.letsPlot.core.plot.base.Aes
import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.Scale

/**
 * General purpose scale for continuous data.
 * Use it to adjust most common properties of a default scale for given aesthetic.
 *
 * @param aesthetic Aesthetic or a list of aesthetics that this scale works with.
 * @param name The name of the scale - used as the axis label or the legend title. If null, the default, the name of the scale
 *  is taken from the first mapping used for that aesthetic.
 * @param breaks A list of data values specifying the positions of ticks,
 *  or a dictionary which maps the tick labels to the breaks values.
 * @param labels A list of labels on ticks, or a dictionary which maps the breaks values to the tick labels.
 * @param lablim The maximum label length (in characters) before trimming is applied.
 * @param limits A Pair of Numbers specifying the data range for the scale.
 *  Use null to refer to default min/max.
 * @param naValue Missing values will be replaced with this value.
 * @param format Specifies the format pattern for labels on the scale.
 * @param guide Guide to use for this scale.
 *  It can either be a string ("colorbar", "legend") or a call to a guide function (`guideColorbar()`, `guideLegend()`)
 *  specifying additional arguments.
 *  "none" will hide the guide.
 * @param trans Name of built-in transformation ("identity", "log10", "log2", "symlog", "reverse", "sqrt").
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$".
 * For more info see: [formats.html](https://lets-plot.org/kotlin/formats.html)
 *
 * Examples:
 * - ".2f" -> "12.45";
 * - "Score: {.2f}" -> "Score: 12.45";
 * - "Score: {}" -> "Score: 12.454789".
 *
 * @param scaleMapperKind The type of the scale:
 *  ("identity", "color_gradient", "color_gradient2", "color_gradientn", "color_hue", "color_grey", "color_brewer", "color_cmap", "size_area").
 *  If null (the default) and the scale is color, then "color_gradient" will be used.
 * @param otherOptions Additional parameters for the specified scale type.
 */
fun scaleContinuous(
    aesthetic: Any,
    name: String? = null,
    breaks: Any? = null,
    labels: Any? = null,
    lablim: Int? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null,
    scaleMapperKind: String? = null,
    otherOptions: Map<String, Any?>? = null
) = Scale(
        aesthetic = aesthetic,
        name = name,
        breaks = breaks,
        labels = labels,
        lablim = lablim,
        limits = limits,
        naValue = naValue,
        format = format,
        guide = guide,
        trans = trans,
        otherOptions = Options(
            mapOf(
                Option.Scale.SCALE_MAPPER_KIND to scaleMapperKind.let {
                    if (scaleMapperKind == null && isColorScale(aesthetic)) {
                        Option.Scale.MapperKind.COLOR_GRADIENT
                    } else {
                        it
                    }
                }
            ) + (otherOptions ?: emptyMap())
        )
    )

private fun isColorScale(aesthetic: Any): Boolean {
    val aesList = when (aesthetic) {
        is List<*> -> aesthetic.map(Scale::toAes)
        else -> listOf(Scale.toAes(aesthetic))
    }
    return aesList.all(Aes.Companion::isColor)
}