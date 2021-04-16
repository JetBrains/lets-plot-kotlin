/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("FunctionName", "SpellCheckingInspection")

package jetbrains.letsPlot

import jetbrains.letsPlot.intern.GenericAesMapping

@Deprecated("", ReplaceWith("letsPlot(data) { mapping() }"))
fun lets_plot(data: Map<*, *>? = null, mapping: (GenericAesMapping.() -> Unit)) = letsPlot(data, mapping)

@Deprecated("", ReplaceWith("letsPlot(data)"))
fun lets_plot(data: Map<*, *>? = null) = letsPlot(data)

@Deprecated("", ReplaceWith("asDiscrete(variable, label)"))
fun as_discrete(variable: String, label: String? = null) = asDiscrete(variable, label)

// Coordinates

@Deprecated("", ReplaceWith("coordFixed(ratio, xlim, ylim)"))
fun coord_fixed(
    ratio: Double? = null,
    xlim: Pair<Number?, Number?>? = null,
    ylim: Pair<Number?, Number?>? = null
) = coordFixed(ratio, xlim, ylim)

@Deprecated("", ReplaceWith("coordCartesian(xlim, ylim)"))
fun coord_cartesian(
    xlim: Pair<Number?, Number?>? = null,
    ylim: Pair<Number?, Number?>? = null
) = coordCartesian(xlim, ylim)

@Deprecated("", ReplaceWith("coordMap(xlim, ylim)"))
fun coord_map(
    xlim: Pair<Number?, Number?>? = null,
    ylim: Pair<Number?, Number?>? = null
) = coordMap(xlim, ylim)

// Positions

@Deprecated("", ReplaceWith("positionDodge(width)"))
fun position_dodge(width: Number? = null) = positionDodge(width)

@Deprecated("", ReplaceWith("positionJitter(width, height)"))
fun position_jitter(width: Number? = null, height: Number? = null) = positionJitter(width, height)

@Deprecated("", ReplaceWith("positionNudge(x, y)"))
fun position_nudge(x: Number? = null, y: Number? = null) = positionNudge(x, y)

@Deprecated("", ReplaceWith("positionJitterDodge(dodgeWidth, jitterWidth, jitterHeight)"))
fun position_jitterdodge(
    dodgeWidth: Number? = null,
    jitterWidth: Number? = null,
    jitterHeight: Number? = null
) = positionJitterDodge(dodgeWidth, jitterWidth, jitterHeight)