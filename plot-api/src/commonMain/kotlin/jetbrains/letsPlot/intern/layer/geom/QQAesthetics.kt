/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.OptionsCapsule

interface QQAesthetics : OptionsCapsule {
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val shape: Any?
    val size: Any?

    override fun seal() = Options.of(
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "shape" to shape,
        "size" to size
    )
}