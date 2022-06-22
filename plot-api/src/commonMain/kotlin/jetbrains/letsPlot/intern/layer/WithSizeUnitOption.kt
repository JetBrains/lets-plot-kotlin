/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer

import jetbrains.datalore.plot.config.Option.Geom.Point
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.OptionsCapsule

interface WithSizeUnitOption : OptionsCapsule {
    val sizeUnit: String?

    override fun seal() = Options.of(
        Point.SIZE_UNIT to sizeUnit
    )
}