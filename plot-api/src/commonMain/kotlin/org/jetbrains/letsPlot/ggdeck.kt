/*
 * Copyright (c) 2026. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.jetbrains.letsPlot.core.spec.Option.SubPlots
import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.intern.figure.SubPlotsFigure
import org.jetbrains.letsPlot.intern.figure.SubPlotsLayoutSpec
import org.jetbrains.letsPlot.intern.filterNonNullValues

/**
 *  Overlay several plots on one figure, with aligned drawing areas.
 *
 * ## Examples
 *
 * - [ggdeck_dual_axis.html](https://lets-plot.org/kotlin/examples/cookbook/ggdeck_dual_axis.html)
 *
 * - [ggdeck_plot_overlay.html](https://lets-plot.org/kotlin/examples/cookbook/ggdeck_plot_overlay.html)
 *
 *  @param plots Collection of plots.
 *   The first plot is the bottom layer, subsequent plots are drawn on top.
 *  @param scaleShare default = "x".
 *   Controls sharing of scale limits between overlaid plots.
 *   - "x" - share X-axis limits (the default; useful for secondary Y-axis).
 *   - "y" - share Y-axis limits.
 *   - "all" - share both X and Y limits.
 *   - "none" - do not share limits.
 *
 *  @return SubPlotsFigure object.
 */
@Suppress("SpellCheckingInspection")
fun ggdeck(
    plots: Iterable<Figure>,
    scaleShare: String? = null,
): SubPlotsFigure {

    val plotsList = plots.toList()
    require(plotsList.isNotEmpty()) { "Plots list is empty." }

    val layout = SubPlotsLayoutSpec(
        name = SubPlots.Layout.SUBPLOTS_DECK,
        options = mapOf(
            SubPlots.Deck.SCALE_SHARE to scaleShare,
        ).filterNonNullValues()
    )

    val features = listOfNotNull(LetsPlot.theme as? OptionsMap)

    return SubPlotsFigure(
        figures = plotsList,
        layout = layout,
        features = features
    )
}
