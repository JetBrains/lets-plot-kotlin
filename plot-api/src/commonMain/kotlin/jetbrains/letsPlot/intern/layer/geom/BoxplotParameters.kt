/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.Options

interface BoxplotParameters : jetbrains.letsPlot.intern.layer.OptionsCapsule {
    val outlierColor: Any?
    val outlierFill: Any?
    val outlierShape: Any?
    val outlierSize: Number?
    val fatten: Number?

    override fun seal() = Options.of(
        Option.Geom.BoxplotOutlier.COLOR to outlierColor,
        Option.Geom.BoxplotOutlier.FILL to outlierFill,
        Option.Geom.BoxplotOutlier.SHAPE to outlierShape,
        Option.Geom.BoxplotOutlier.SIZE to outlierSize,
        Option.Geom.Boxplot.FATTEN to fatten
    )
}
