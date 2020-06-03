/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.Options

interface JitterAesthetics : PointAesthetics {
    val width: Any?
    val height: Any?

    override fun seal() = super.seal() + Options.of(
        "width" to width,
        "height" to height
    )
}