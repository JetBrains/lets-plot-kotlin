/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.datalore.plot.config.Option

import jetbrains.letsPlot.intern.OptionsMap
import jetbrains.letsPlot.intern.filterNonNullValues

/**
 * A fixed scale coordinate system forces a specified ratio between the physical representation of data units on the axes.
 *
 * @param xlim A pair of numbers
 * @param ylim A pair of numbers
 *      Limits for the x and y axes.
 * @param ratio double
 *      The ratio represents the number of units on the y-axis equivalent to one unit on the x-axis.
 *      ratio = 1, ensures that one unit on the x-axis is the same length as one unit on the y-axis.
 *      Ratios higher than one make units on the y axis longer than units on the x-axis, and vice versa.
 */
@Suppress("FunctionName")
fun coord_fixed(
    ratio: Double? = null,
    xlim: Pair<Number?, Number?>? = null,
    ylim: Pair<Number?, Number?>? = null
): OptionsMap {
    return OptionsMap(
        Option.Plot.COORD,
        mapOf(
            "name" to Option.CoordName.FIXED,
            "ratio" to ratio,
            "xlim" to xlim?.toList(),
            "ylim" to ylim?.toList()
        ).filterNonNullValues()
    )
}
/**
 * The Cartesian coordinate system is the most familiar, and common, type of coordinate system.
 * Setting limits on the coordinate system will zoom the plot (like you're looking at it with a magnifying glass),
 * and will not change the underlying data like setting limits on a scale will.
 *
 * @param xlim A pair of numbers
 * @param ylim A pair of numbers
 *      Limits for the x and y axes.
 */
@Suppress("FunctionName")
fun coord_cartesian(
    xlim: Pair<Number?, Number?>? = null,
    ylim: Pair<Number?, Number?>? = null
): OptionsMap {
    return OptionsMap(
        Option.Plot.COORD,
        mapOf(
            "name" to Option.CoordName.CARTESIAN,
            "xlim" to xlim?.toList(),
            "ylim" to ylim?.toList()
        ).filterNonNullValues()
    )
}

/**
 * Projects a portion of the earth, which is approximately spherical,
 * onto a flat 2D plane.
 * Map projections do not, in general, preserve straight lines, so this requires considerable computation.
 *
 * @param xlim A pair of numbers
 * @param ylim A pair of numbers
 *      Limits for the x and y axes.
 */
@Suppress("FunctionName")
fun coord_map(
    xlim: Pair<Number?, Number?>? = null,
    ylim: Pair<Number?, Number?>? = null
): OptionsMap {
    return OptionsMap(
        Option.Plot.COORD,
        mapOf(
            "name" to Option.CoordName.MAP,
            "xlim" to xlim?.toList(),
            "ylim" to ylim?.toList()
        ).filterNonNullValues()
    )
}