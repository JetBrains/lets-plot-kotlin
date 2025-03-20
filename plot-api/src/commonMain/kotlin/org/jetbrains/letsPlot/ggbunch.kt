/*
 * Copyright (c) 2025. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.jetbrains.letsPlot.core.spec.Option.SubPlots
import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.intern.figure.SubPlotsFigure
import org.jetbrains.letsPlot.intern.figure.SubPlotsLayoutSpec
import org.jetbrains.letsPlot.intern.filterNonNullValues

/**
 *  Combines several plots into a single figure with custom layout.
 *
 *  ## Examples
 *
 * - [Map of Kotlin Island with Inset](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/demo/spatialdataset_kotlin_isl.ipynb)
 * - [Diamonds Magnifier](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.10.0/magnifier_inset.ipynb).
 *
 *  @param plots
 *   A list where each element is one of:
 *   - a plot specification
 *   - a subplots specification
 *   - Null
 *  @param regions Layout parameters for each plot.
 *   Each region is specified as list of 4 or 6 numbers
 *   [x, y, width, height, dx, dy] where:
 *   - x, y: Position of the plot's top-left corner in relative coordinates ([0,0] is top-left corner, [1,1] is bottom-right corner of the container).
 *   - width, height: Size of the plot relative to container dimensions (1 equal to the full container width/height).
 *   - dx, dy: Pixel offsets to move the region (defaults to 0).
 *
 *  @return SubPlotsFigure object.
 */
@Suppress("SpellCheckingInspection")
fun ggbunch(
    plots: List<Figure?>,
    regions: List<List<Number>>
): SubPlotsFigure {

    require(plots.isNotEmpty()) { "Supplots list is empty." }

    // Validate provided regions
    for ((i, region) in regions.withIndex()) {
        if (region.size !in setOf(4, 6)) {
            throw IllegalArgumentException("Region $i must have 4 or 6 values, got ${region.size}")
        }

        // Validate size is positive
        if (region.subList(2, 4).any { it.toDouble() <= 0 }) {
            throw IllegalArgumentException("Region $i sizes must be positive: $region")
        }
    }

    val layout = SubPlotsLayoutSpec(
        name = SubPlots.Layout.SUBPLOTS_FREE,
        options = mapOf(
            SubPlots.Free.REGIONS to regions,
        ).filterNonNullValues()
    )

    // Apply global theme if defined
    val features = listOfNotNull(LetsPlot.theme as? OptionsMap)

    return SubPlotsFigure(
        figures = plots,
        layout = layout,
        features = features
    )
}
