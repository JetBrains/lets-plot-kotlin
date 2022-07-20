/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface QQLineAesthetics : OptionsCapsule {
    val alpha: Any?
    val color: Any?
    val linetype: Any?
    val size: Any?

    override fun seal() = Options.of(
        "alpha" to alpha,
        "color" to color,
        "linetype" to linetype,
        "size" to size
    )
}