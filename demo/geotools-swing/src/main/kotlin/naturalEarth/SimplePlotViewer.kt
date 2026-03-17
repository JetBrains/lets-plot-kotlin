/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package naturalEarth

import org.jetbrains.letsPlot.awt.plot.util.SimplePlotViewerWindow
import org.jetbrains.letsPlot.commons.geometry.DoubleVector
import java.awt.Dimension

object SimplePlotViewer {
    fun show(plotSpec: MutableMap<String, Any>, plotSize: DoubleVector) {
        SimplePlotViewerWindow(
            "Spatial plot demo",
            rawSpec = plotSpec,
            windowSize = Dimension(plotSize.x.toInt(), plotSize.y.toInt()),
            preserveAspectRatio = false,
            repaintDelay = 300
        ).open()
    }
}