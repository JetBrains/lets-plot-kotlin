/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package naturalEarth

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.vis.swing.batik.PlotViewerWindowBatik
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