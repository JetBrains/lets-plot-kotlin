/*
 * Copyright (c) 2026. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Text halo (outline) properties shared by
 * [geomText()][org.jetbrains.letsPlot.geom.geomText] and
 * [geomTextRepel()][org.jetbrains.letsPlot.geom.geomTextRepel].
 *
 * @property haloWidth Width of the halo (outline) around the text. A halo is rendered only when `haloWidth > 0`.
 * @property haloColor Color of the halo around the text.
 *  Setting `haloColor` alone has no visible effect unless `haloWidth > 0`.
 *  When omitted, the panel background color is used (falling back to the plot background color
 *  when the panel draws no rectangle).
 */
interface TextHaloParameters : OptionsCapsule {
    val haloWidth: Number?
    val haloColor: Any?

    override fun seal() = Options.of(
        Option.Geom.Text.HALO_WIDTH to haloWidth,
        Option.Geom.Text.HALO_COLOR to haloColor
    )
}
