/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import jetbrains.datalore.plot.config.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface TextParameters : OptionsCapsule {
    val labelFormat: String?
    val naText: String?

    override fun seal() = Options.of(
        Option.Geom.Text.LABEL_FORMAT to labelFormat,
        Option.Geom.Text.NA_TEXT to naText
    )
}
