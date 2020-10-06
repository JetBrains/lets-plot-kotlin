/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package naturalEarth

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.plot.MonolithicAwt
import jetbrains.datalore.vis.svg.SvgSvgElement
import jetbrains.datalore.vis.swing.BatikMapperComponent
import jetbrains.datalore.vis.swing.BatikMessageCallback
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.swing.WindowConstants

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

object SimpleBatikDemo {
    fun show(plotSpec: MutableMap<String, Any>, plotSize: DoubleVector) {

        SwingUtilities.invokeLater {
            val component =
                MonolithicAwt.buildPlotFromRawSpecs(plotSpec, plotSize, SVG_COMPONENT_FACTORY_BATIK, AWT_EDT_EXECUTOR) {
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
}