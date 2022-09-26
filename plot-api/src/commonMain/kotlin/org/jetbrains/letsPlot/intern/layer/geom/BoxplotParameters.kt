/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import jetbrains.datalore.plot.config.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface BoxplotParameters : OptionsCapsule {
    val outlierColor: Any?
    val outlierFill: Any?
    val outlierShape: Any?
    val outlierSize: Number?
    val fatten: Number?
    val whiskerWidth: Number?

    override fun seal() = Options.of(
        Option.Geom.BoxplotOutlier.COLOR to outlierColor,
        Option.Geom.BoxplotOutlier.FILL to outlierFill,
        Option.Geom.BoxplotOutlier.SHAPE to outlierShape,
        Option.Geom.BoxplotOutlier.SIZE to outlierSize,
        Option.Geom.Boxplot.FATTEN to fatten,
        Option.Geom.Boxplot.WHISKER_WIDTH to whiskerWidth
    )
}
