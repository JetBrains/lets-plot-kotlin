/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options

/**
 * Parameters supported by [geomLabel()][org.jetbrains.letsPlot.geom.geomLabel].
 *
 * @property labelPadding Amount of padding around label. Defaults to 0.25 of font size.
 * @property labelR Radius of rounded corners. Defaults to 0.15 of label height.
 * @property labelSize Size of label border.
 * @property alphaStroke Enables the applying of 'alpha' to 'color' (label text and border).
 */
interface LabelParameters : TextParameters {
    val labelPadding: Number?
    val labelR: Number?
    val labelSize: Number?
    val alphaStroke: Boolean?

    override fun seal() = super.seal() + Options.of(
        Option.Geom.Label.LABEL_PADDING to labelPadding,
        Option.Geom.Label.LABEL_R to labelR,
        Option.Geom.Label.LABEL_SIZE to labelSize,
        Option.Geom.Label.ALPHA_STROKE to alphaStroke
    )
}