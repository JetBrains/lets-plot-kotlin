/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.samples

import jetbrains.letsPlot.geom.geomABLine
import jetbrains.letsPlot.letsPlot

class GeomABLine {
    fun elementaryExample() {
        letsPlot() + geomABLine(slope = 0.0, intercept = 0.0)
    }
}