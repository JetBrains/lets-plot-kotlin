/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package plotDemo

import frontendApi.RawObjectFrontendContext
import jetbrains.letsPlot.intern.Plot

class SwingJfxFrontendContext : RawObjectFrontendContext {
    override fun displayObject(o: Any) {
        when (o) {
            is Plot -> SwingDemoUtil.display(o)
            else -> throw IllegalArgumentException("Unsupported display object: ${o::class.simpleName}")
        }
    }
}