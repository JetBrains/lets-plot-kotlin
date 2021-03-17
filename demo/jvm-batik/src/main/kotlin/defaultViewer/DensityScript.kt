/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package defaultViewer

import jetbrains.letsPlot.geom.geom_density
import jetbrains.letsPlot.lets_plot

@Suppress("DuplicatedCode")
fun main() {
    val rand = java.util.Random()
    val n = 200
    val data = mapOf<String, Any>(
        "x" to List(n) { rand.nextGaussian() }
    )
    val p = lets_plot(data) +
            geom_density(
                color = "dark-green",
                fill = "green",
                alpha = .3,
                size = 2.0
            ) { x = "x" }
    p.show()
}
