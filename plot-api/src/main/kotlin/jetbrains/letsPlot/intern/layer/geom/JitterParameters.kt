/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

interface JitterParameters : OptionsCapsule {
    val width: Any?
    val height: Any?

    override fun seal() = Options.of(
        Option.Geom.Jitter.WIDTH to width,
        Option.Geom.Jitter.HEIGHT to height
    )
}