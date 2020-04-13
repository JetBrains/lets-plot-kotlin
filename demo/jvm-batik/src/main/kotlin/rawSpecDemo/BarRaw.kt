/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package rawSpecDemo

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.base.json.JsonSupport
import jetbrains.datalore.plot.MonolithicAwt
import jetbrains.datalore.vis.svg.SvgSvgElement
import jetbrains.datalore.vis.swing.BatikMapperComponent
import jetbrains.datalore.vis.swing.BatikMessageCallback
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.swing.WindowConstants

object BarRaw {
    // Setup
    private val SVG_COMPONENT_FACTORY_BATIK =
        { svg: SvgSvgElement -> BatikMapperComponent(svg, BATIK_MESSAGE_CALLBACK) }

    private val BATIK_MESSAGE_CALLBACK = object : BatikMessageCallback {
        override fun handleMessage(message: String) {
            println(message)
        }

        override fun handleException(e: Exception) {
            if (e is RuntimeException) {
                throw e
            }
            throw RuntimeException(e)
        }
    }

    private val AWT_EDT_EXECUTOR = { runnable: () -> Unit ->
        // Just invoke in the current thread.
        runnable.invoke()
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
            plotSize = DoubleVector(600.0, 300.0),
            componentFactory = SVG_COMPONENT_FACTORY_BATIK,
            executor = AWT_EDT_EXECUTOR
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