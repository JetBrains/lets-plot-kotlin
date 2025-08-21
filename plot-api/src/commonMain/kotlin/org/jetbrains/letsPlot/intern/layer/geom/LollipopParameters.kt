/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Parameters supported by [geomLollipop()][org.jetbrains.letsPlot.geom.geomLollipop].
 *
 * @param fatten A multiplicative factor applied to size of the point.
 * @param slope The baseline slope.
 * @param intercept The value of y at the point where the baseline crosses the y-axis.
 * @param dir Direction of the lollipop stick. Possible values: "v" for vertical, "h" for horizontal, "s" for orthogonal to the baseline.
 */
interface LollipopParameters : OptionsCapsule {
    val fatten: Number?
    val slope: Number?
    val intercept: Number?
    val dir: String?

    override fun seal() = Options.of(
        Option.Geom.Lollipop.FATTEN to fatten,
        Option.Geom.Lollipop.SLOPE to slope,
        Option.Geom.Lollipop.INTERCEPT to intercept,
        Option.Geom.Lollipop.DIRECTION to dir
    )
}