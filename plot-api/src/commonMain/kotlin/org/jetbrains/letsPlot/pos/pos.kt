/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("unused")

package org.jetbrains.letsPlot.pos

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.PosKind
import org.jetbrains.letsPlot.intern.layer.PosOptions

val positionIdentity = PosOptions(PosKind.IDENTITY)

/**
 * Adjusts position by dodging overlaps to the side.
 *
 * ## Examples
 *
 * - [error_bars.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/error_bars.ipynb)
 *
 * @param width Dodging width, when different to the width of the individual elements. This is useful when you want to align narrow geoms with wider geoms.
 *  The value of width is relative and typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the objects.
 */
fun positionDodge(width: Number? = null) =
    PosOptions(
        PosKind.DODGE,
        Options.of(Option.Pos.Dodge.WIDTH to width)
    )

/**
 * Adjusts position by dodging overlaps to the side.
 *
 * ## Examples
 *
 * - [horizontal_error_bars.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/horizontal_error_bars.ipynb)
 *
 * @param height Dodging height, when different to the height of the individual elements. This is useful when you want to align narrow geoms with taller geoms.
 *  The value of height is relative and typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the objects.
 */
fun positionDodgeV(height: Number? = null) =
    PosOptions(
        PosKind.DODGE_V,
        Options.of(Option.Pos.DodgeV.HEIGHT to height)
    )

/**
 * Adjusts position by assigning random noise to points. Better for discrete values.
 *
 * ## Examples
 *
 * - [scatter_plot.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/scatter_plot.ipynb)
 *
 * @param width Jittering width. The value of width is relative and typically ranges between 0 and 0.5.
 *  Values that are greater than 0.5 lead to overlapping of the points.
 * @param height Jittering height. The value of height is relative and typically ranges between 0 and 0.5.
 *  Values that are greater than 0.5 lead to overlapping of the points.
 * @param seed A random seed to make the jitter reproducible.
 *  If null (the default value), the seed is initialised with a random value.
 */
fun positionJitter(width: Number? = null, height: Number? = null, seed: Int? = null) =
    PosOptions(
        PosKind.JITTER,
        Options.of(
            Option.Pos.Jitter.WIDTH to width,
            Option.Pos.Jitter.HEIGHT to height,
            Option.Pos.Jitter.SEED to seed
        )
    )

/**
 * Adjusts position by nudging a given offset.
 *
 * @param x Nudging width.
 * @param y Nudging height.
 */
fun positionNudge(x: Number? = null, y: Number? = null) =
    PosOptions(
        PosKind.NUDGE,
        Options.of(
            Option.Pos.Nudge.WIDTH to x,
            Option.Pos.Nudge.HEIGHT to y
        )
    )

/**
 * This is primarily used for aligning points generated through `geomPoint()`
 * with dodged boxplots (e.g., a `geomBoxplot()` with a fill aesthetic supplied).
 *
 * @param dodgeWidth Bin width.
 *  The value is relative and typically ranges between 0 and 1.
 *  Values of `dodgeWidth` that are greater than 1 lead to overlapping of the objects.
 * @param jitterWidth Jittering width. The value of `jitterWidth` is relative and typically ranges between 0 and 0.5.
 *  Values that are greater than 0.5 lead to overlapping of the points.
 * @param jitterHeight Jittering height. The value of `jitterHeight` is relative and typically ranges between 0 and 0.5.
 *  Values that are greater than 0.5 lead to overlapping of the points.
 * @param seed A random seed to make the jitter reproducible.
 *  If null (the default value), the seed is initialised with a random value.
 */
fun positionJitterDodge(
    dodgeWidth: Number? = null,
    jitterWidth: Number? = null,
    jitterHeight: Number? = null,
    seed: Int? = null,
) = PosOptions(
    PosKind.JITTER_DODGE,
    Options.of(
        Option.Pos.JitterDodge.DODGE_WIDTH to dodgeWidth,
        Option.Pos.JitterDodge.JITTER_WIDTH to jitterWidth,
        Option.Pos.JitterDodge.JITTER_HEIGHT to jitterHeight,
        Option.Pos.JitterDodge.SEED to seed
    )
)

/**
 * Adjusts position by stacking overlapping objects on top of each other.
 *
 * ## Examples
 *
 * - [position_stack.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/position_stack.ipynb)
 *
 * - [position_stack.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/stackable_position_adjustments.ipynb)
 *
 * @param vjust Vertical adjustment for geoms that have a position (like points or lines), not a dimension (like bars or areas).
 *  Set to 0 to align with the bottom, 0.5 for the middle, and 1 for the top.
 * @param mode default = "groups" ("groups", "all").
 *  If "groups", objects inside one group are positioned as in `position = positionIdentity`,
 *  but each group is shifted to sum of heights of previous groups
 *  (where height of a group is a maximum of it's y values).
 *  If "all", each object will be shifted.
 *
 */
fun positionStack(vjust: Number? = null, mode: String? = null) =
    PosOptions(
        PosKind.STACK,
        Options.of(
            Option.Pos.Stack.VJUST to vjust,
            Option.Pos.Stack.MODE to mode
        ),
    )

/**
 * Adjusts position by stacking overlapping objects on top of each other
 * and standardise each stack to have constant height.
 *
 * ## Examples
 *
 * - [position_stack.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/position_stack.ipynb)
 *
 * - [position_stack.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/stackable_position_adjustments.ipynb)
 *
 * @param vjust Vertical adjustment for geoms that have a position (like points or lines), not a dimension (like bars or areas).
 *  Set to 0 to align with the bottom, 0.5 for the middle, and 1 for the top.
 *  If "groups", objects inside one group are positioned as in `position = positionIdentity`,
 *  but each group is shifted to sum of heights of previous groups
 *  (where height of a group is a maximum of it's y values).
 *  If "all", each object will be shifted.
 *
 */
fun positionFill(vjust: Number? = null, mode: String? = null) =
    PosOptions(
        PosKind.FILL,
        Options.of(
            Option.Pos.Fill.VJUST to vjust,
            Option.Pos.Fill.MODE to mode
        )
    )