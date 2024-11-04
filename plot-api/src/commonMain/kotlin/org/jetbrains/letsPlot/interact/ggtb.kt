/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.interact

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.OptionsMap

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
 * - The toolbar is not compatible with GGBunch.
 * - The toolbar cannot be used with plots using a polar coordinate system.
 *
 * ## Examples
 *
 * - [interact_pan_zoom.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/demo/interact_pan_zoom.ipynb)
 *
 * @return OptionsMap - toolbar feature specification.
 */
@Suppress("SpellCheckingInspection")
fun ggtb(): OptionsMap {
    return OptionsMap(
        kind = Option.Meta.Kind.GG_TOOLBAR,
        options = emptyMap()
    )
}
