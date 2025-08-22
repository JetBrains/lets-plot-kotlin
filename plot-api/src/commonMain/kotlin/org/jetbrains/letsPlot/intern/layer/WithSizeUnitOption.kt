/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer

import org.jetbrains.letsPlot.core.spec.Option.Geom.Point
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * @property sizeUnit Unit for size values.
 */
interface WithSizeUnitOption : OptionsCapsule {
    val sizeUnit: String?

    override fun seal() = Options.of(
        Point.SIZE_UNIT to sizeUnit
    )
}