/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface BoxplotParameters : OptionsCapsule {
    /** A multiplicative factor applied to size of the middle bar. */
    val fatten: Number?
    /** A multiplicative factor applied to the box width to draw horizontal segments on whiskers. */
    val whiskerWidth: Number?

    override fun seal() = Options.of(
        Option.Geom.Boxplot.FATTEN to fatten,
        Option.Geom.Boxplot.WHISKER_WIDTH to whiskerWidth
    )
}
