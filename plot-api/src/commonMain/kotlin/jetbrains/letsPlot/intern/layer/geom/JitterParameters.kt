/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.Options

interface JitterParameters : jetbrains.letsPlot.intern.layer.OptionsCapsule {
    val width: Number?
    val height: Number?

    override fun seal() = Options.of(
        Option.Geom.Jitter.WIDTH to width,
        Option.Geom.Jitter.HEIGHT to height
    )
}