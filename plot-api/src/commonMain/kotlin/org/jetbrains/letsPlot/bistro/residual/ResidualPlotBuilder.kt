/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.bistro.residual

import jetbrains.datalore.plot.server.config.transform.bistro.corr.DataUtil
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.intern.GenericAesMapping
import org.jetbrains.letsPlot.intern.Plot
import org.jetbrains.letsPlot.intern.asPlotData
import org.jetbrains.letsPlot.letsPlot

internal class ResidualPlotBuilder(
    private val data: Map<*, *>,
    private val x: String,
    private val y: String,
) {
    fun build(): Plot {
        val statData = ResidualUtil.appendResiduals(DataUtil.standardiseData(data), x, y)
        val mapping: GenericAesMapping.() -> Unit = {
            x = this@ResidualPlotBuilder.x
            y = ResidualVar.RESIDUAL
        }
        return letsPlot(asPlotData(statData), mapping) + geomPoint()
    }
}