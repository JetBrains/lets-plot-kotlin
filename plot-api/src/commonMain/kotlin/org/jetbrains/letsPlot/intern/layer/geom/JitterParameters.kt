/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Parameters supported by [geomJitter()][org.jetbrains.letsPlot.geom.geomJitter].
 *
 * @property width Width for jitter, default = 0.4. Typically ranges between 0 and 0.5. 
 *  Values that are greater than 0.5 lead to overlapping of the points.
 * @property height Height for jitter, default = 0.4. Typically ranges between 0 and 0.5. 
 *  Values that are greater than 0.5 lead to overlapping of the points.
 */
interface JitterParameters : OptionsCapsule {
    val width: Number?
    val height: Number?

    override fun seal() = Options.of(
        Option.Geom.Jitter.WIDTH to width,
        Option.Geom.Jitter.HEIGHT to height
    )
}