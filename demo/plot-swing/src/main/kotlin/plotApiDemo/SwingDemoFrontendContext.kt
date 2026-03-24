/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package plotApiDemo

import org.jetbrains.letsPlot.FrontendContext
import java.awt.Dimension

class SwingDemoFrontendContext(
    private val title: String,
    private val maxCol: Int = 3,
    private val plotSize: Dimension? = null,
) : FrontendContext {

    private val plotSpecs = ArrayList<MutableMap<String, Any>>()

    override fun display(plotSpecRaw: MutableMap<String, Any>) {
        plotSpecs.add(plotSpecRaw)
    }

    fun showAll() {
        PlotSpecsDemoWindowSwing(
            title,
            specList = plotSpecs,
            maxCol = maxCol,
            plotSize = plotSize
        ).open()
    }
}