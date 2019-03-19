package com.jetbrains.datalore.plot.dsl

import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.PosKind
import com.jetbrains.datalore.plot.layer.PosOptions

object Pos {
    val identity = PosOptions(PosKind.IDENTITY)
    val stack = PosOptions(PosKind.STACK)
    val dodge = PosOptions(PosKind.DODGE)
    val fill = PosOptions(PosKind.FILL)
    val nudge = PosOptions(PosKind.NUDGE)
    val jitter = PosOptions(PosKind.JITTER)
    val jitterdodge = PosOptions(PosKind.JITTER_DODGE)

    // parameter names
    private val DODGE_WIDTH = "width"
    private val JITTER_WIDTH = "width"
    private val JITTER_HEIGHT = "height"
    private val NUDGE_WIDTH = "x"
    private val NUDGE_HEIGHT = "y"
    private val JD_DODGE_WIDTH = "dodge_width"
    private val JD_JITTER_WIDTH = "jitter_width"
    private val JD_JITTER_HEIGHT = "jitter_height"

    fun position_dodge(width: Number? = null) = PosOptions(
        PosKind.DODGE,
        Options.of(DODGE_WIDTH to width)
    )

    fun position_jitter(width: Number? = null, height: Number? = null) = PosOptions(
        PosKind.JITTER,
        Options.of(
            JITTER_WIDTH to width,
            JITTER_HEIGHT to height
        )
    )

    fun position_nudge(x: Number? = null, y: Number? = null) = PosOptions(
        PosKind.NUDGE,
        Options.of(
            NUDGE_WIDTH to x,
            NUDGE_HEIGHT to y
        )
    )

    fun position_jitterdodge(dodge_width: Number? = null, jitter_width: Number? = null, jitter_height: Number? = null) =
        PosOptions(
            PosKind.JITTER_DODGE,
            Options.of(
                JD_DODGE_WIDTH to dodge_width,
                JD_JITTER_WIDTH to jitter_width,
                JD_JITTER_HEIGHT to jitter_height
            )
        )
}
