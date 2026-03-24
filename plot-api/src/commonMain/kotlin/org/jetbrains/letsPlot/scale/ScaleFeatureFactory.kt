/*
 * Copyright (c) 2026. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.scale

import org.jetbrains.letsPlot.core.plot.base.Aes
import org.jetbrains.letsPlot.intern.ColorScale
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.Scale

internal fun createScaleFeature(
    aesthetic: Any,
    name: String? = null,
    breaks: Any? = null,
    labels: Any? = null,
    lablim: Int? = null,
    limits: Any? = null,
    expand: Any? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null,
    position: String? = null,
    otherOptions: Options = Options.empty()
): Scale {
    return if (isColorScale(aesthetic)) {
        ColorScale(aesthetic, name, breaks, labels, lablim, limits, expand, naValue, format, guide, trans, position, otherOptions)
    } else {
        Scale(aesthetic, name, breaks, labels, lablim, limits, expand, naValue, format, guide, trans, position, otherOptions)
    }
}

internal fun isColorScale(aesthetic: Any): Boolean {
    val aesList = when (aesthetic) {
        is List<*> -> aesthetic.map(Scale::toAes)
        else -> listOf(Scale.toAes(aesthetic))
    }
    return aesList.all(Aes.Companion::isColor)
}
