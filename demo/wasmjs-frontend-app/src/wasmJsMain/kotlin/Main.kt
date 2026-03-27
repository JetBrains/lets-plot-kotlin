/*
 * Copyright (c) 2026. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

import kotlinx.browser.document
import org.jetbrains.letsPlot.geom.geomDensity
import org.jetbrains.letsPlot.ggsize
import org.jetbrains.letsPlot.intern.toSpec
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.scaleFillBrewer
import org.jetbrains.letsPlot.themes.elementBlank
import org.jetbrains.letsPlot.themes.theme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.sqrt
import kotlin.random.Random

fun main() {
    createContent()
}

private fun createContent() {
    val contentDiv = document.getElementById("content")

    val random = Random(12)
    val data = mapOf<String, Any>(
        "cond" to (List(200) { "A" } + List(200) { "B" }),
        "rating" to (
                List(200) { normal(random, mean = 0.0, stdDev = 1.0) } +
                        List(200) { normal(random, mean = 1.0, stdDev = 1.5) }
                )
    )

    val p = letsPlot(data) { x = "rating"; fill = "cond" } +
            ggsize(700, 300) +
            geomDensity(
                color = "dark-green",
                alpha = 0.7
            ) +
            scaleFillBrewer(type = "seq") +
            theme(panelGridMajorX = elementBlank())

    val plotDiv = MonolithicWasmJs.buildPlotFromRawSpecs(
        plotSpec = p.toSpec()
    )
    contentDiv?.appendChild(plotDiv)
}

private fun normal(random: Random, mean: Double, stdDev: Double): Double {
    val z0 = nextGaussian(random)
    return mean + stdDev * z0
}

/**
 * The Box-Muller transform converts two independent uniform variates on (0, 1)
 * into two standard Gaussian variates (mean 0, variance 1).
 * https://en.wikipedia.org/wiki/Box%E2%80%93Muller_transform
 */
private fun nextGaussian(random: Random): Double {
    var u = 0.0
    var v = 0.0
    while (u < 1.0e-7) u = random.nextDouble()
    while (v < 1.0e-7) v = random.nextDouble()
    return sqrt(-2.0 * ln(u)) * cos(2.0 * PI * v)
}
