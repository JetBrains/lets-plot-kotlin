/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo

import jetbrains.letsPlot.LetsPlot
import java.awt.Dimension

object ScriptInBatikContext {
    fun eval(
        title: String,
        maxCol: Int = 3,
        plotSize: Dimension? = null,
        script: () -> Unit
    ) {
        val ctx = SwingBatikDemoFrontendContext(title, maxCol, plotSize)
        LetsPlot.frontendContext = ctx

        script()

        ctx.showAll()
    }
}