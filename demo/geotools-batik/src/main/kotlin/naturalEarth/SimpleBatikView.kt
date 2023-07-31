/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package naturalEarth

import org.jetbrains.letsPlot.batik.plot.component.PlotViewerWindowBatik
import org.jetbrains.letsPlot.commons.geometry.DoubleVector
import java.awt.Dimension

object SimpleBatikView {
    fun show(plotSpec: MutableMap<String, Any>, plotSize: DoubleVector) {
        PlotViewerWindowBatik(
            "Spatial plot demo",
            rawSpec = plotSpec,
            windowSize = Dimension(plotSize.x.toInt(), plotSize.y.toInt()),
            preserveAspectRatio = false,
            repaintDelay = 300
        ).open()
    }
}