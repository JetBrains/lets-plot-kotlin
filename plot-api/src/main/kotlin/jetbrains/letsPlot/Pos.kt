/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.PosKind
import jetbrains.letsPlot.intern.layer.PosOptions

object Pos {
    val identity = PosOptions(PosKind.IDENTITY)
    val stack = PosOptions(PosKind.STACK)
    val dodge = PosOptions(PosKind.DODGE)
    val fill = PosOptions(PosKind.FILL)
    val nudge = PosOptions(PosKind.NUDGE)
    val jitter = PosOptions(PosKind.JITTER)

    @Suppress("SpellCheckingInspection")
    val jitterdodge = PosOptions(PosKind.JITTER_DODGE)

    // parameter names
    private const val DODGE_WIDTH = "width"
    private const val JITTER_WIDTH = "width"
    private const val JITTER_HEIGHT = "height"
    private const val NUDGE_WIDTH = "x"
    private const val NUDGE_HEIGHT = "y"
    private const val JD_DODGE_WIDTH = "dodge_width"
    private const val JD_JITTER_WIDTH = "jitter_width"
    private const val JD_JITTER_HEIGHT = "jitter_height"

    @Suppress("FunctionName")
    fun position_dodge(width: Number? = null) = PosOptions(
        PosKind.DODGE,
        Options.of(DODGE_WIDTH to width)
    )

    @Suppress("FunctionName")
    fun position_jitter(width: Number? = null, height: Number? = null) = PosOptions(
        PosKind.JITTER,
        Options.of(
            JITTER_WIDTH to width,
            JITTER_HEIGHT to height
        )
    )

    @Suppress("FunctionName")
    fun position_nudge(x: Number? = null, y: Number? = null) = PosOptions(
        PosKind.NUDGE,
        Options.of(
            NUDGE_WIDTH to x,
            NUDGE_HEIGHT to y
        )
    )

    @Suppress("FunctionName", "SpellCheckingInspection")
    fun position_jitterdodge(dodgeWidth: Number? = null, jitterWidth: Number? = null, jitterHeight: Number? = null) =
        PosOptions(
            PosKind.JITTER_DODGE,
            Options.of(
                JD_DODGE_WIDTH to dodgeWidth,
                JD_JITTER_WIDTH to jitterWidth,
                JD_JITTER_HEIGHT to jitterHeight
            )
        )
}
