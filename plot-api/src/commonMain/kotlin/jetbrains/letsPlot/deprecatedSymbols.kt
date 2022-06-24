/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("FunctionName", "SpellCheckingInspection", "unused")

package jetbrains.letsPlot

import jetbrains.letsPlot.intern.GenericAesMapping

@Deprecated("", ReplaceWith("letsPlot(data) { mapping() }"), level = DeprecationLevel.ERROR)
fun lets_plot(data: Map<*, *>? = null, mapping: (GenericAesMapping.() -> Unit)) = letsPlot(data, mapping)

@Deprecated("", ReplaceWith("letsPlot(data)"), level = DeprecationLevel.ERROR)
fun lets_plot(data: Map<*, *>? = null) = letsPlot(data)

@Deprecated("", ReplaceWith("asDiscrete(variable, label)"), level = DeprecationLevel.ERROR)
fun as_discrete(variable: String, label: String? = null) = asDiscrete(variable, label)

// Coordinates

@Deprecated("", ReplaceWith("coordFixed(ratio, xlim, ylim)"), level = DeprecationLevel.ERROR)
fun coord_fixed(
    ratio: Number? = null,
    xlim: Pair<Number?, Number?>? = null,
    ylim: Pair<Number?, Number?>? = null
) = coordFixed(ratio, xlim, ylim)

@Deprecated("", ReplaceWith("coordCartesian(xlim, ylim)"), level = DeprecationLevel.ERROR)
fun coord_cartesian(
    xlim: Pair<Number?, Number?>? = null,
    ylim: Pair<Number?, Number?>? = null
) = coordCartesian(xlim, ylim)

@Deprecated("", ReplaceWith("coordMap(xlim, ylim)"), level = DeprecationLevel.ERROR)
fun coord_map(
    xlim: Pair<Number?, Number?>? = null,
    ylim: Pair<Number?, Number?>? = null
) = coordMap(xlim, ylim)

// Positions

@Deprecated("", ReplaceWith("positionDodge(width)"), level = DeprecationLevel.ERROR)
fun position_dodge(width: Number? = null) = positionDodge(width)

@Deprecated("", ReplaceWith("positionJitter(width, height)"), level = DeprecationLevel.ERROR)
fun position_jitter(width: Number? = null, height: Number? = null) = positionJitter(width, height)

@Deprecated("", ReplaceWith("positionNudge(x, y)"), level = DeprecationLevel.ERROR)
fun position_nudge(x: Number? = null, y: Number? = null) = positionNudge(x, y)

@Deprecated("", ReplaceWith("positionJitterDodge(dodgeWidth, jitterWidth, jitterHeight)"), level = DeprecationLevel.ERROR)
fun position_jitterdodge(
    dodgeWidth: Number? = null,
    jitterWidth: Number? = null,
    jitterHeight: Number? = null
) = positionJitterDodge(dodgeWidth, jitterWidth, jitterHeight)