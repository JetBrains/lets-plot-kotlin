/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.geom.PolygonAesthetics

// TODO same aesthetics as the Polygon has
interface ContourfAesthetics : PolygonAesthetics {
    val z: Any?

    override fun seal() = super.seal() + Options.of(
        "z" to z
    )
}