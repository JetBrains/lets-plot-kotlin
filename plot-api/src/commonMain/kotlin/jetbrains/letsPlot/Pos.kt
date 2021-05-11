/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.PosKind

object Pos {
    val identity = jetbrains.letsPlot.intern.layer.PosOptions(PosKind.IDENTITY)
    val stack = jetbrains.letsPlot.intern.layer.PosOptions(PosKind.STACK)
    val dodge = jetbrains.letsPlot.intern.layer.PosOptions(PosKind.DODGE)
    val fill = jetbrains.letsPlot.intern.layer.PosOptions(PosKind.FILL)
    val nudge = jetbrains.letsPlot.intern.layer.PosOptions(PosKind.NUDGE)
    val jitter = jetbrains.letsPlot.intern.layer.PosOptions(PosKind.JITTER)

    @Suppress("SpellCheckingInspection")
    val jitterdodge = jetbrains.letsPlot.intern.layer.PosOptions(PosKind.JITTER_DODGE)

    // parameter names
    internal const val DODGE_WIDTH = "width"
    internal const val JITTER_WIDTH = "width"
    internal const val JITTER_HEIGHT = "height"
    internal const val NUDGE_WIDTH = "x"
    internal const val NUDGE_HEIGHT = "y"
    internal const val JD_DODGE_WIDTH = "dodge_width"
    internal const val JD_JITTER_WIDTH = "jitter_width"
    internal const val JD_JITTER_HEIGHT = "jitter_height"
}

fun positionDodge(width: Number? = null) =
    jetbrains.letsPlot.intern.layer.PosOptions(
        PosKind.DODGE,
        Options.of(Pos.DODGE_WIDTH to width)
    )

fun positionJitter(width: Number? = null, height: Number? = null) =
    jetbrains.letsPlot.intern.layer.PosOptions(
        PosKind.JITTER,
        Options.of(
            Pos.JITTER_WIDTH to width,
            Pos.JITTER_HEIGHT to height
        )
    )

fun positionNudge(x: Number? = null, y: Number? = null) =
    jetbrains.letsPlot.intern.layer.PosOptions(
        PosKind.NUDGE,
        Options.of(
            Pos.NUDGE_WIDTH to x,
            Pos.NUDGE_HEIGHT to y
        )
    )

fun positionJitterDodge(
    dodgeWidth: Number? = null,
    jitterWidth: Number? = null,
    jitterHeight: Number? = null
) =
    jetbrains.letsPlot.intern.layer.PosOptions(
        PosKind.JITTER_DODGE,
        Options.of(
            Pos.JD_DODGE_WIDTH to dodgeWidth,
            Pos.JD_JITTER_WIDTH to jitterWidth,
            Pos.JD_JITTER_HEIGHT to jitterHeight
        )
    )
