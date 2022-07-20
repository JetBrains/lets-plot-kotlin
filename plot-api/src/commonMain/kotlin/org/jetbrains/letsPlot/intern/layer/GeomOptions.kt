/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer

import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.Options


open class GeomOptions(
    val kind: GeomKind,
    val mapping: Options = Options.empty()
) {
    open val parameters: Options = Options.empty()
}



