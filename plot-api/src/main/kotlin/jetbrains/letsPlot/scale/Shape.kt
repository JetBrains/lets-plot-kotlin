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
 * Scale for shapes
 *
 * @param solid boolean
 *      Are the shapes solid (default) True, or hollow (False)?
 * @param name string
 *      The name of the scale - used as the axis label or the legend title. If None, the default, the name of the scale
 *      is taken from the first mapping used for that aesthetic.
 * @param breaks list of numbers
 *      A numeric vector of positions (of ticks)
 * @param labels : list of strings
 *      A vector of labels (on ticks)
 * @param limits list
 *      Continuous scale: a numeric vector of length two providing limits of the scale.
 *      Discrete scale: a vector specifying the data range for the scale. and the default order of their display in guides.
 * @param naValue an aesthetic value which is used when data in not available.
 * @param guide A result returned by guide_legend() function or "none" to hide the guide.
 */
@Suppress("FunctionName")
fun scale_shape(
    solid: Boolean? = null,
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    naValue: Any? = null,
    guide: Any? = null
) = Scale(
    aesthetic = Aes.SHAPE,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    naValue = naValue,
    guide = guide,
    otherOptions = Options(
        mapOf(
            Option.Scale.SHAPE_SOLID to solid
        )
    )
)