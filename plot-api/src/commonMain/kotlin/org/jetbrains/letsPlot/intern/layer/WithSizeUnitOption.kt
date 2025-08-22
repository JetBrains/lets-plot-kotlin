/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer

import org.jetbrains.letsPlot.core.spec.Option.Geom.Point
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * @property sizeUnit Relate the size to the length of the unit step along one of the axes.
 *  "x" uses the unit step along the x-axis, "y" uses the unit step along the y-axis.
 *  "min" uses the smaller of the unit steps along the x- and y-axes.
 *  "max" uses the larger of the unit steps along the x- and y-axes.
 *  If not specified, no fitting is performed.
 */
interface WithSizeUnitOption : OptionsCapsule {
    val sizeUnit: String?

    override fun seal() = Options.of(
        Point.SIZE_UNIT to sizeUnit
    )
}