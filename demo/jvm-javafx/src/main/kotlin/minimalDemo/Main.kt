/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package minimalDemo

import javafx.application.Platform
import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.plot.MonolithicAwt
import jetbrains.datalore.plot.builder.presentation.Style
import jetbrains.datalore.vis.svg.SvgSvgElement
import jetbrains.datalore.vis.swing.SceneMapperJfxPanel
import jetbrains.letsPlot.geom.geom_histogram
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.intern.toSpec
import java.util.*
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.swing.WindowConstants

// Setup
private val SVG_COMPONENT_FACTORY_JFX =
    { svg: SvgSvgElement -> SceneMapperJfxPanel(svg, listOf(Style.JFX_PLOT_STYLESHEET)) }

private val JFX_EDT_EXECUTOR = { runnable: () -> Unit ->
    if (Platform.isFxApplicationThread()) {
        runnable.invoke()
    } else {
        Platform.runLater(runnable)
    }
}

fun main() {
    SwingUtilities.invokeLater {

        // Generate random data-points
        val rand = Random()
        val data = mapOf<String, Any>(
            "x" to List(500) { rand.nextGaussian() } + List(500) { rand.nextGaussian() + 1.0 },
            "c" to List(500) { "A" } + List(500) { "B" }
        )

        // Create plot specs using Lets-Plot Kotlin API
        val geom = geom_histogram(alpha = 0.3, size = 0.0) {
            x = "x"; fill = "c"
        }
        val p = ggplot(data) + geom + ggtitle("The normal distribution")

        // Create JFXPanel showing the plot.
        val plotSpec = p.toSpec()
        val plotSize = DoubleVector(600.0, 300.0)

        val component =
            MonolithicAwt.buildPlotFromRawSpecs(plotSpec, plotSize, SVG_COMPONENT_FACTORY_JFX, JFX_EDT_EXECUTOR) {
                for (message in it) {
                    println("PLOT MESSAGE: $message")
                }
            }

        // Show plot in Swing frame.
        val frame = JFrame("The Minimal")
        frame.contentPane.add(component)
        frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        frame.pack()
        frame.isVisible = true
    }
}

