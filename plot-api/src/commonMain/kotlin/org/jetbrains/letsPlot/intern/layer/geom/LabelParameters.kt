/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options

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