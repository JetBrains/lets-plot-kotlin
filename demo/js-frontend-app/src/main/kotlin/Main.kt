/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

import org.jetbrains.letsPlot.frontend.JsFrontendUtil
import org.jetbrains.letsPlot.geom.geomDensity
import org.jetbrains.letsPlot.letsPlot
import kotlinx.browser.document
import kotlinx.browser.window
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.sqrt
import kotlin.random.Random

fun main() {
    window.onload = { createContent() }
}

fun createContent() {
    val contentDiv = document.getElementById("content")

    val n = 200
    val data = mapOf<String, Any>(
        "x" to List(n) { nextGaussian() }
    )

    val p = letsPlot(data) + geomDensity(
        color = "dark-green",
        fill = "green",
        alpha = .3,
        size = 2.0
    ) { x = "x" }

    val plotDiv = JsFrontendUtil.createPlotDiv(p)
    contentDiv?.appendChild(plotDiv)
}

/**
 * The Box-Muller transform converts two independent uniform variates on (0, 1)
 * into two standard Gaussian variates (mean 0, variance 1).
 * https://en.wikipedia.org/wiki/Box%E2%80%93Muller_transform
 */
fun nextGaussian(): Double {
    var u = 0.0
    var v = 0.0
    while (u < 1.0e-7) u = Random.nextDouble()
    while (v < 1.0e-7) v = Random.nextDouble()
    return sqrt(-2.0 * ln(u)) * cos(2.0 * PI * v)
}
