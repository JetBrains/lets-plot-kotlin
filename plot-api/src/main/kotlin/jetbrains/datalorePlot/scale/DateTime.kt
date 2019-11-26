@file:Suppress("FunctionName")

package jetbrains.datalorePlot.scale

import jetbrains.datalorePlot.intern.Options
import jetbrains.datalorePlot.intern.Scale
import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.config.Option.Scale.DATE_TIME


fun scale_x_datetime(
    name: String? = null,
    breaks: List<Any>? = null,
    labels: List<String>? = null,
    limits: List<Any>? = null,
    expand: Any? = null,
    na_value: Any? = null
) = Scale(
    Aes.X,
    name, breaks, labels, limits, expand, na_value,
    otherOptions = Options(
        mapOf(
            DATE_TIME to true
        )
    )
)
