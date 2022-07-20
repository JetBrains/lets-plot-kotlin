/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import jetbrains.datalore.plot.config.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface JitterParameters : OptionsCapsule {
    val width: Number?
    val height: Number?

    override fun seal() = Options.of(
        Option.Geom.Jitter.WIDTH to width,
        Option.Geom.Jitter.HEIGHT to height
    )
}