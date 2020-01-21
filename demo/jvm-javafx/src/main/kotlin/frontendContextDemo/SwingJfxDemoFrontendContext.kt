/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo

import javafx.application.Platform
import jetbrains.datalore.plot.MonolithicAwt
import jetbrains.datalore.plot.builder.presentation.Style
import jetbrains.datalore.vis.svg.SvgSvgElement
import jetbrains.datalore.vis.swing.SceneMapperJfxPanel
import jetbrains.letsPlot.FrontendContext
import java.awt.Color
import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.*

class SwingJfxDemoFrontendContext(private val title: String) : FrontendContext {
    private val plotSpecs = ArrayList<MutableMap<String, Any>>()

    override fun display(plotSpecRaw: MutableMap<String, Any>) {
        plotSpecs.add(plotSpecRaw)
    }

    fun showAll() {
        SwingUtilities.invokeLater {
            val frame = JFrame(title)

            val panel = JPanel()
            panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)

            // build plots
            for (plotSpec in plotSpecs) {
                val component =
                    MonolithicAwt.buildPlotFromRawSpecs(
                        plotSpec, null,
                        SVG_COMPONENT_FACTORY_JFX,
                        EXECUTOR_JFX,
                        COMPUTATION_MESSAGES_HANDLER
                    )

                val decorated = object : JPanel(FlowLayout(0, 0, 0)) {
                    override fun getMinimumSize(): Dimension {
                        return preferredSize
                    }

                    override fun getMaximumSize(): Dimension {
                        return preferredSize
                    }
                }

                decorated.border = BorderFactory.createLineBorder(Color.blue, 1)
                decorated.add(component)
                panel.add(Box.createRigidArea(Dimension(0, 5)))
                panel.add(decorated)
            }

            frame.contentPane.add(JScrollPane(panel))
            frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            frame.size = FRAME_SIZE
            frame.isVisible = true
        }
    }

    companion object {
        private val SVG_COMPONENT_FACTORY_JFX =
            { svg: SvgSvgElement -> SceneMapperJfxPanel(svg, listOf(Style.JFX_PLOT_STYLESHEET)) }
        private val EXECUTOR_JFX = { r: () -> Unit ->
            if (Platform.isFxApplicationThread()) {
                r.invoke()
            } else {
                Platform.runLater(r)
            }
        }
        private val COMPUTATION_MESSAGES_HANDLER: (List<String>) -> Unit = {
            for (message in it) {
                println("PLOT MESSAGE: $message")
            }
        }

        private val FRAME_SIZE = Dimension(700, 700)
    }
}