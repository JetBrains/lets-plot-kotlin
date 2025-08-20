/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Parameters for [geomJitter()][org.jetbrains.letsPlot.geom.geomJitter].
 *
 * @param width Degree of jitter in the x direction. If omitted, defaults to 40% of the resolution of the data.
 * @param height Degree of jitter in the y direction. If omitted, defaults to 40% of the resolution of the data.
 */
interface JitterParameters : OptionsCapsule {
    val width: Number?
    val height: Number?

    override fun seal() = Options.of(
        Option.Geom.Jitter.WIDTH to width,
        Option.Geom.Jitter.HEIGHT to height
    )
}