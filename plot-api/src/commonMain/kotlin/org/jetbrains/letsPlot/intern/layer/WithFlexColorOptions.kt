/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * @property colorBy default = "color" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the color aesthetic for the layer.
 */
interface WithColorOption : OptionsCapsule {
    val colorBy: String?

    override fun seal() = Options.of(
        Option.Layer.COLOR_BY to colorBy
    )
}

/**
 * @property fillBy default = "fill" ("fill", "color", "paint_a", "paint_b", "paint_c").
 *  Defines the fill aesthetic for the layer.
 */
interface WithFillOption : OptionsCapsule {
    val fillBy: String?

    override fun seal() = Options.of(
        Option.Layer.FILL_BY to fillBy
    )
}
