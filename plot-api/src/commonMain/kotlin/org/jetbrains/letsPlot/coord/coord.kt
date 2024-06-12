/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("SpellCheckingInspection", "unused")

package org.jetbrains.letsPlot.coord

import org.jetbrains.letsPlot.core.spec.Option

import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.intern.filterNonNullValues

/**
 * A fixed scale coordinate system forces a specified ratio between the physical representation of data units on the axes.
 *
 * @param ratio The ratio represents the number of units on the y-axis equivalent to one unit on the x-axis.
 *  ratio = 1, ensures that one unit on the x-axis is the same length as one unit on the y-axis.
 *  Ratios higher than one make units on the y-axis longer than units on the x-axis, and vice versa.
 * @param xlim Limits for x-axes.
 * @param ylim Limits for y-axes.
 * @param flip Flips the coordinate system axis
 *  so that horizontal axis becomes vertical and vice versa.
 */
fun coordFixed(
    ratio: Number? = null,
    xlim: Pair<Number?, Number?>? = null,
    ylim: Pair<Number?, Number?>? = null,
    flip: Boolean = false
): OptionsMap {
    return OptionsMap(
        Option.Plot.COORD,
        mapOf(
            "name" to Option.CoordName.FIXED,
            Option.Coord.RATIO to ratio,
            Option.Coord.X_LIM to xlim?.toList(),
            Option.Coord.Y_LIM to ylim?.toList(),
            Option.Coord.FLIPPED to flip
        ).filterNonNullValues()
    )
}

/**
 * The Cartesian coordinate system is the most familiar and common type of coordinate system.
 * Setting limits on the coordinate system will zoom the plot (like you're looking at it with a magnifying glass),
 * and will not change the underlying data like setting limits on a scale will.
 *
 * @param xlim Limits for x-axes.
 * @param ylim Limits for y-axes.
 * @param flip Flips the coordinate system axis
 *  so that horizontal axis becomes vertical and vice versa.
 */
fun coordCartesian(
    xlim: Pair<Number?, Number?>? = null,
    ylim: Pair<Number?, Number?>? = null,
    flip: Boolean = false
): OptionsMap {
    return OptionsMap(
        Option.Plot.COORD,
        mapOf(
            "name" to Option.CoordName.CARTESIAN,
            Option.Coord.X_LIM to xlim?.toList(),
            Option.Coord.Y_LIM to ylim?.toList(),
            Option.Coord.FLIPPED to flip
        ).filterNonNullValues()
    )
}

/**
 * Projects a portion of the earth, which is approximately spherical,
 * onto a flat 2D plane.
 * Map projections do not, in general, preserve straight lines, so this requires considerable computation.
 *
 * @param xlim Limits for x-axes.
 * @param ylim Limits for y-axes.
 * @param flip Flips the coordinate system axis
 *  so that horizontal axis becomes vertical and vice versa.
 */
fun coordMap(
    xlim: Pair<Number?, Number?>? = null,
    ylim: Pair<Number?, Number?>? = null,
    flip: Boolean = false
): OptionsMap {
    return OptionsMap(
        Option.Plot.COORD,
        mapOf(
            "name" to Option.CoordName.MAP,
            Option.Coord.X_LIM to xlim?.toList(),
            Option.Coord.Y_LIM to ylim?.toList(),
            Option.Coord.FLIPPED to flip
        ).filterNonNullValues()
    )
}

/**
 *
 * Flips axis of default coordinate system so that horizontal axis becomes vertical and vice versa.
 *
 * ## Examples
 *
 * - [coord_flip.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/coord_flip.ipynb)
 *
 * - [y_orientation.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/y_orientation.ipynb)
 *
 * @param xlim Limits for x-axes.
 * @param ylim Limits for y-axes.
 */
fun coordFlip(
    xlim: Pair<Number?, Number?>? = null,
    ylim: Pair<Number?, Number?>? = null
): OptionsMap {
    return OptionsMap(
        Option.Plot.COORD,
        mapOf(
            "name" to Option.CoordName.FLIP,
            Option.Coord.X_LIM to xlim?.toList(),
            Option.Coord.Y_LIM to ylim?.toList(),
            Option.Coord.FLIPPED to true
        ).filterNonNullValues()
    )
}

/**
 * Polar coordinate system. It is used for pie charts and polar plots.
 *
 * ## Examples
 *
 * - [coord_polar.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/coord_polar.ipynb)
 *
 * @param xlim Limits for x-axes.
 * @param ylim Limits for y-axes.
 * @param theta Aesthetic that is used to map angle, default = "x".
 *  Possible values: "x", "y".
 * @param start Offset relative to the starting angle (which is 12 o'clock), in radians, default = 0.
 * @param direction Specifies angle direction: 1 for clockwise, -1 for counterclockwise, default = 1.
 * @param transformBkgr If true, the background is transformed to a circle, rectangle otherwise, default = true.
 */
fun coordPolar(
    xlim: Pair<Number?, Number?>? = null,
    ylim: Pair<Number?, Number?>? = null,
    theta: String? = null,
    start: Number? = null,
    direction: Int? = null,
    transformBkgr: Boolean? = null
): OptionsMap {
    return OptionsMap(
        Option.Plot.COORD,
        mapOf(
            "name" to Option.CoordName.POLAR,
            Option.Coord.X_LIM to xlim?.toList(),
            Option.Coord.Y_LIM to ylim?.toList(),
            Option.Coord.THETA to theta,
            Option.Coord.START to start,
            Option.Coord.DIRECTION to direction,
            Option.Coord.TRANSFORM_BKGR to transformBkgr
        ).filterNonNullValues()
    )
}