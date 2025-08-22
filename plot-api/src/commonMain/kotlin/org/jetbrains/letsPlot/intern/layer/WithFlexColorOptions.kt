/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * @property colorBy Variable name to use for color mapping.
 */
interface WithColorOption : OptionsCapsule {
    val colorBy: String?

    override fun seal() = Options.of(
        Option.Layer.COLOR_BY to colorBy
    )
}

/**
 * @property fillBy Variable name to use for fill mapping.
 */
interface WithFillOption : OptionsCapsule {
    val fillBy: String?

    override fun seal() = Options.of(
        Option.Layer.FILL_BY to fillBy
    )
}
