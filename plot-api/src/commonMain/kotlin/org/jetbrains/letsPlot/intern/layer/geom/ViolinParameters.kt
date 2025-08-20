/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Parameters for [geomViolin()][org.jetbrains.letsPlot.geom.geomViolin].
 *
 * @param showHalf Control which half of the violin plot to show (-1 = left, 0 = both, 1 = right).
 * @param quantileLines Whether to draw quantile lines within violins.
 */
interface ViolinParameters : OptionsCapsule {
    val showHalf: Number?
    val quantileLines: Boolean?

    override fun seal() = Options.of(
        Option.Geom.Violin.SHOW_HALF to showHalf,
        Option.Geom.Violin.QUANTILE_LINES to quantileLines,
    )
}