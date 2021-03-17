/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package minimalDemo

import jetbrains.datalore.base.registration.Disposable
import jetbrains.datalore.plot.MonolithicCommon
import jetbrains.datalore.vis.swing.batik.DefaultPlotPanelBatik
import jetbrains.letsPlot.geom.geom_density
import jetbrains.letsPlot.geom.geom_histogram
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.intern.toSpec
import jetbrains.letsPlot.lets_plot
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.*
import javax.swing.JFrame.EXIT_ON_CLOSE

fun main() {

    val rand = java.util.Random()
    val n = 200
    val data = mapOf<String, Any>(
        "x" to List(n) { rand.nextGaussian() }
    )

    val plots = mapOf(
        "Density" to lets_plot(data) + geom_density(
            color = "dark-green",
            fill = "green",
            alpha = .3,
            size = 2.0
        ) { x = "x" },
        "Count" to lets_plot(data) + geom_histogram(
            color = "dark-green",
            fill = "green",
            alpha = .3,
            size = 2.0
        ) { x = "x" },

        )

    val selectedPlotKey = plots.keys.first()
    val controller = Controller(
        plots,
        selectedPlotKey,
        false
    )

    val window = JFrame("Example App")
    window.defaultCloseOperation = EXIT_ON_CLOSE
    window.contentPane.layout = BoxLayout(window.contentPane, BoxLayout.Y_AXIS)

    // Add controls
    val controlsPanel = Box.createHorizontalBox().apply {
        // Plot selector
        val plotButtonGroup = ButtonGroup()
        for (key in plots.keys) {
            plotButtonGroup.add(
                JRadioButton(key, key == selectedPlotKey).apply {
                    addActionListener {
                        controller.plotKey = this.text
                    }
                }
            )
        }

        this.add(Box.createHorizontalBox().apply {
            border = BorderFactory.createTitledBorder("Plot")
            for (elem in plotButtonGroup.elements) {
                add(elem)
            }
        })

        // Preserve aspect ratio selector
        val aspectRadioButtonGroup = ButtonGroup()
        aspectRadioButtonGroup.add(JRadioButton("Original", false).apply {
            addActionListener {
                controller.preserveAspectRadio = true
            }
        })
        aspectRadioButtonGroup.add(JRadioButton("Fit container", true).apply {
            addActionListener {
                controller.preserveAspectRadio = false
            }
        })

        this.add(Box.createHorizontalBox().apply {
            border = BorderFactory.createTitledBorder("Aspect ratio")
            for (elem in aspectRadioButtonGroup.elements) {
                add(elem)
            }
        })
    }
    window.contentPane.add(controlsPanel)

    // Add plot panel
    val plotContainerPanel = JPanel(GridLayout())
    window.contentPane.add(plotContainerPanel)

    controller.plotContainerPanel = plotContainerPanel
    controller.rebuildPlotComponent()

    SwingUtilities.invokeLater {
        window.pack()
        window.size = Dimension(850, 400)
        window.setLocationRelativeTo(null)
        window.isVisible = true
    }
}

private class Controller(
    private val plots: Map<String, Plot>,
    initialPlotKey: String,
    initialPreserveAspectRadio: Boolean
) {
    var plotContainerPanel: JPanel? = null
    var plotKey: String = initialPlotKey
        set(value) {
            field = value
            rebuildPlotComponent()
        }
    var preserveAspectRadio: Boolean = initialPreserveAspectRadio
        set(value) {
            field = value
            rebuildPlotComponent()
        }

    fun rebuildPlotComponent() {
        plotContainerPanel?.let {
            val container = plotContainerPanel!!
            // cleanup
            for (component in container.components) {
                if (component is Disposable) {
                    component.dispose()
                }
            }
            container.removeAll()

            // build
            container.add(createPlotPanel())
            container.revalidate()
        }
    }

    fun createPlotPanel(): JPanel {
        val rawSpec = plots[plotKey]!!.toSpec()
        val processedSpec = MonolithicCommon.processRawSpecs(rawSpec, frontendOnly = false)

        return DefaultPlotPanelBatik(
            processedSpec = processedSpec,
            preserveAspectRatio = preserveAspectRadio,
            preferredSizeFromPlot = false,
            repaintDelay = 10,
        ) { messages ->
            for (message in messages) {
                println("[Example App] $message")
            }
        }
    }
}