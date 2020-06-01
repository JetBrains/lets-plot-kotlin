/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo

import jetbrains.letsPlot.LetsPlot

object ScriptInBrowserContext {
    fun eval(title: String, script: () -> Unit) {
        val ctx = BrowserDemoFrontendContext(title)
        LetsPlot.frontendContext = ctx

        script()

        ctx.showAll()
    }
}