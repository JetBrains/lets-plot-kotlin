/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.scale

import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.Scale

/**
 * Scales for alpha aesthetic
 *
 * @param range pair of numbers
 *      The range of the mapped aesthetics result.
 * @param name string
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks list of numbers
 *      A numeric vector of positions (of ticks)
 * @param labels list of strings
 *      A vector of labels (on ticks)
 * @param limits pair of numbers
 *      A numeric vector of length two providing limits of the scale.
 * @param naValue
 *      An aesthetic value which is used when data in not available.
 * @param guide
 *      A function used to create a guide (guide_colorbar(), guide_legend()) or its name ("colorbar", "legend");
 *      "none" will hide the guide.
 * @param trans string
 *      Name of built-in transformation ('identity', 'log10', 'reverse', 'sqrt').
 */
@Suppress("FunctionName")
fun scale_alpha(
    range: Pair<Number, Number>? = null,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    naValue: Number? = null,
    guide: Any? = null,
    trans: String? = null
) = Scale(
    aesthetic = Aes.ALPHA,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    guide = guide,
    trans = trans,
    otherOptions = Options(
        mapOf(
            Option.Scale.RANGE to range?.toList()
        )
    )
)