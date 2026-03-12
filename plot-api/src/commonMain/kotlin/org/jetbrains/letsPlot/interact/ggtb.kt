/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.interact

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.intern.filterNonNullValues

/**
 * Add a toolbar to a chart.
 *
 * This function adds a toolbar containing three tool-buttons (pan, rubber-band zoom,
 * and center-point zoom) to a chart. Each tool uses mouse-drag for its
 * specific functionality. Additionally, the mouse wheel can be used for zooming
 * in and out, regardless of the selected tool.
 *
 * The toolbar includes:
 *
 * - Pan: Drag to move the plot.
 * - Rubber-band zoom: Drag to define a rectangular area to zoom into.
 * - Center-point zoom: Drag up or down to zoom in or out from a center point.
 * - Reset button: Click to reset the plot and tools to their original state.
 *
 * Double-clicking anywhere on the plot resets it to its original coordinates,
 * regardless of whether a tool is selected or not.
 *
 * Limitations:
 *
 * - The toolbar does not work with interactive maps.
 * - The toolbar cannot be used with plots using a polar coordinate system.
 *
 * ## Examples
 *
 * - [interact_pan_zoom.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/demo/interact_pan_zoom.ipynb)
 *
 * @param sizeZoomin Controls how zooming in affects the size of geometry objects on the plot.
 * Currently, works only with the [geomPoint()][org.jetbrains.letsPlot.geom.geomPoint] layer and layers based on it
 * ([geomJitter()][org.jetbrains.letsPlot.geom.geomJitter], [geomSina()][org.jetbrains.letsPlot.geom.geomSina], etc.).
 *
 * - `0` – size never increases.
 * - `-1` – size will be increasing without limits.
 * - `n` – the number of times the size of objects will increase (relative to the initial state of the plot).
 *   Farther zooming will no longer affect the size.
 *
 * @param sizeBasis default = "max" ("x", "y", "min", "max").
 * Defines the axis along which the scaling factor for geometry objects will be calculated.
 *
 * - `"x"` – size changes only when zooming in/out along the x-axis.
 * - `"y"` – size changes only when zooming in/out along the y-axis.
 * - `"min"` – size changes when zooming in/out along any axis, but the change is determined by the axis
 *   with the minimum zoom factor.
 * - `"max"` – size changes when zooming in/out along any axis, but the change is determined by the axis
 *   with the maximum zoom factor.
 *
 * @return OptionsMap - toolbar feature specification.
 */
@Suppress("SpellCheckingInspection")
fun ggtb(
    sizeZoomin: Int? = null,
    sizeBasis: String? = null
): OptionsMap {
    return OptionsMap(
        kind = Option.Meta.Kind.GG_TOOLBAR,
        mapOf(
            Option.GGToolbar.SIZE_ZOOMIN to sizeZoomin,
            Option.GGToolbar.SIZE_BASIS to sizeBasis
        ).filterNonNullValues()
    )
}
