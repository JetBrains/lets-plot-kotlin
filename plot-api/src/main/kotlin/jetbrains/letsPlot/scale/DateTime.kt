/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("FunctionName")

package jetbrains.letsPlot.scale

import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.config.Option.Scale.DATE_TIME
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.Scale


fun scale_x_datetime(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: Pair<Number?, Number?>? = null,
    expand: Any? = null,
    naValue: Any? = null
) = Scale(
    aesthetic = Aes.X,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits?.toList(),
    expand = expand,
    naValue = naValue,
    otherOptions = Options(
        mapOf(
            DATE_TIME to true
        )
    )
)
