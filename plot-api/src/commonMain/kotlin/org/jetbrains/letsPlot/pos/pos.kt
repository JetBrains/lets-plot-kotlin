/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("unused")

package org.jetbrains.letsPlot.pos

import jetbrains.datalore.plot.config.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.PosKind
import org.jetbrains.letsPlot.intern.layer.PosOptions

/**
 * Pos options to pass as a value of `position` parameter of layer functions like:
 *
 * ```kotlin
 * val n = 100
 * val m = 5
 * val k = 2
 * val rand = java.util.Random(42)
 * val data = mapOf(
 *     "v" to List(n) { rand.nextInt(m) },
 *     "c" to List(n) { rand.nextInt(k) }
 * )
 * letsPlot(data) +
 *     geomBar(position=Pos.dodge) { x="v"; fill=asDiscrete("c") }
 * ```
 */
private object Pos {
//    val identity = PosOptions(PosKind.IDENTITY)
//    val stack = PosOptions(PosKind.STACK)
//    val dodge = PosOptions(PosKind.DODGE)
//    val fill = PosOptions(PosKind.FILL)
//    val nudge = PosOptions(PosKind.NUDGE)
//    val jitter = PosOptions(PosKind.JITTER)

//    @Suppress("SpellCheckingInspection")
//    val jitterdodge = PosOptions(PosKind.JITTER_DODGE)

}

val positionIdentity = PosOptions(PosKind.IDENTITY)
val positionStack = positionStack()
val positionFill = positionFill()


/**
 * Adjust position by dodging overlaps to the side.
 *
 * ## Examples
 *
 * - [error_bars.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/error_bars.ipynb)
 *
 * @param width Dodging width, when different to the width of the individual elements. This is useful when you want to align narrow geoms with wider geoms.
 * The value of width is relative and typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the objects.
 */
fun positionDodge(width: Number? = null) =
    PosOptions(
        PosKind.DODGE,
        Options.of(Option.Pos.Dodge.WIDTH to width)
    )

/**
 * Adjust position by assigning random noise to points. Better for discrete values.
 *
 * ## Examples
 *
 * - [scatter_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/scatter_plot.ipynb)
 *
 * @param width Jittering width. The value of width is relative and typically ranges between 0 and 0.5.
 * Values that are greater than 0.5 lead to overlapping of the points.
 * @param height Jittering height. The value of height is relative and typically ranges between 0 and 0.5.
 * Values that are greater than 0.5 lead to overlapping of the points.
 */
fun positionJitter(width: Number? = null, height: Number? = null) =
    PosOptions(
        PosKind.JITTER,
        Options.of(
            Option.Pos.Jitter.WIDTH to width,
            Option.Pos.Jitter.HEIGHT to height
        )
    )

fun positionNudge(x: Number? = null, y: Number? = null) =
    PosOptions(
        PosKind.NUDGE,
        Options.of(
            Option.Pos.Nudge.WIDTH to x,
            Option.Pos.Nudge.HEIGHT to y
        )
    )

fun positionJitterDodge(
    dodgeWidth: Number? = null,
    jitterWidth: Number? = null,
    jitterHeight: Number? = null
) =
    PosOptions(
        PosKind.JITTER_DODGE,
        Options.of(
            Option.Pos.JitterDodge.DODGE_WIDTH to dodgeWidth,
            Option.Pos.JitterDodge.JITTER_WIDTH to jitterWidth,
            Option.Pos.JitterDodge.JITTER_HEIGHT to jitterHeight
        )
    )

/**
 * Adjust position by stacking overlapping objects on top of each other.
 *
 * ## Examples
 *
 * - [position_stack.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/position_stack.ipynb)
 *
 * @param vjust Vertical adjustment for geoms that have a position (like points or lines), not a dimension (like bars or areas).
 * Set to 0 to align with the bottom, 0.5 for the middle, and 1 for the top.
 */
fun positionStack(vjust: Number? = null) =
    PosOptions(
        PosKind.STACK,
        Options.of(Option.Pos.Stack.VJUST to vjust)
    )

/**
 * Adjust position by stacking overlapping objects on top of each other
 * and standardise each stack to have constant height.
 *
 * ## Examples
 *
 * - [position_stack.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/position_stack.ipynb)
 *
 * @param vjust Vertical adjustment for geoms that have a position (like points or lines), not a dimension (like bars or areas).
 * Set to 0 to align with the bottom, 0.5 for the middle, and 1 for the top.
 */
fun positionFill(vjust: Number? = null) =
    PosOptions(
        PosKind.FILL,
        Options.of(Option.Pos.Fill.VJUST to vjust)
    )