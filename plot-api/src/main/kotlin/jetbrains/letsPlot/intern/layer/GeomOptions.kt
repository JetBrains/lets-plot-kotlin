/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer

import jetbrains.letsPlot.intern.GeomKind
import jetbrains.letsPlot.intern.Options


open class GeomOptions(
    val kind: GeomKind,
    val mapping: Options = Options.empty()
) {
    open val parameters: Options = Options.empty()
}



