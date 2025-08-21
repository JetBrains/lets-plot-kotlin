/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Parameters supported by [geomBoxplot()][org.jetbrains.letsPlot.geom.geomBoxplot].
 *
 * @param fatten A multiplicative factor applied to size of the middle bar.
 * @param whiskerWidth A multiplicative factor applied to the box width to draw horizontal segments on whiskers.
 */
interface BoxplotParameters : OptionsCapsule {
    val fatten: Number?
    val whiskerWidth: Number?

    override fun seal() = Options.of(
        Option.Geom.Boxplot.FATTEN to fatten,
        Option.Geom.Boxplot.WHISKER_WIDTH to whiskerWidth
    )
}
