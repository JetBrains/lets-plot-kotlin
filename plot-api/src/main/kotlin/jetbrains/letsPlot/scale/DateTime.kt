/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("FunctionName")

package jetbrains.letsPlot.scale

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.Scale
import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.config.Option.Scale.DATE_TIME


fun scale_x_datetime(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: Any? = null,
    naValue: Any? = null
) = Scale(
    Aes.X,
    name, breaks, labels, limits, expand, naValue,
    otherOptions = Options(
        mapOf(
            DATE_TIME to true
        )
    )
)
