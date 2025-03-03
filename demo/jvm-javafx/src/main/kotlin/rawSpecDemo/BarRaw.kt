/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package rawSpecDemo

import javafx.application.Platform
import org.jetbrains.letsPlot.awt.plot.MonolithicAwt
import org.jetbrains.letsPlot.commons.intern.json.JsonSupport
import org.jetbrains.letsPlot.core.util.sizing.SizingPolicy
import org.jetbrains.letsPlot.datamodel.svg.dom.SvgSvgElement
import org.jetbrains.letsPlot.jfx.plot.util.SceneMapperJfxPanel
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.swing.WindowConstants

object BarRaw {
    private val SVG_COMPONENT_FACTORY_JFX =
        { svg: SvgSvgElement -> SceneMapperJfxPanel(svg, stylesheets = emptyList()) }
    private val EXECUTOR_JFX = { r: () -> Unit ->
        if (Platform.isFxApplicationThread()) {
            r.invoke()
        } else {
            Platform.runLater(r)
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val specRaw = """
            {
                "kind": "plot",
                "data":  { "time": ["Lunch","Lunch", "Dinner", "Dinner", "Dinner"]},
                "mapping": {
                          "x": "time",
                          "y": "..count..",
                          "fill": "..count.."
                        },
                "layers": [{"geom": "bar"}],
                "scales": [
                            {
                               "aesthetic": "fill",
                               "discrete": true,
                               "scale_mapper_kind": "color_hue"
                            }
                        ]
            }"""

        val spec = JsonSupport.parseJson(specRaw)

        @Suppress("UNCHECKED_CAST")
        val component = MonolithicAwt.buildPlotFromRawSpecs(
            plotSpec = spec as MutableMap<String, Any>,
            sizingPolicy = SizingPolicy.fixed(600.0, 300.0),
            svgComponentFactory = SVG_COMPONENT_FACTORY_JFX,
            containerSize = null,
            executor = EXECUTOR_JFX
        ) {
            for (message in it) {
                println("PLOT MESSAGE: $message")
            }
        }

        SwingUtilities.invokeLater {
            val frame = JFrame("Plot from raw specs")
            frame.contentPane.add(component)
            frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            frame.pack()
            frame.isVisible = true
        }
    }
}