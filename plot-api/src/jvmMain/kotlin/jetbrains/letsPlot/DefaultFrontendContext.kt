/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.frontend.DefaultSwingBatikFrontendContext
import jetbrains.letsPlot.frontend.DefaultSwingJfxFrontendContext

actual fun createDefaultFrontendContext(): FrontendContext {
    return DefaultSwingBatikFrontendContext.tryCreate()
        ?: DefaultSwingJfxFrontendContext.tryCreate()
        ?: undefinedFrontendContext()
}
