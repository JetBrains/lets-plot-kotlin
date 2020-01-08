/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package plotDemo

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.base.observable.property.ReadableProperty
import jetbrains.datalore.base.observable.property.ValueProperty
import jetbrains.datalore.plot.DemoAndTest
import jetbrains.datalore.plot.MonolithicAwt
import jetbrains.datalore.plot.builder.PlotContainer
import jetbrains.datalore.plot.builder.presentation.Style
import jetbrains.datalore.vis.demoUtils.jfx.SceneMapperDemoFactory
import jetbrains.datalore.vis.demoUtils.swing.SwingDemoFactory
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.intern.toSpec
import java.awt.Color
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.util.concurrent.atomic.AtomicInteger
import javax.swing.BorderFactory
import javax.swing.JComponent
import javax.swing.SwingUtilities
import javax.swing.border.LineBorder


typealias PlotFactory = (plotSize: ReadableProperty<DoubleVector>) -> PlotContainer

object SwingDemoUtil {

    const val PADDING = 20

    fun toPlotSize(containerSize: Dimension) = DoubleVector(
        containerSize.width.toDouble() - 2 * PADDING,
        containerSize.height.toDouble() - 2 * PADDING
    )


    fun display(plot: Plot) {
        display(plot.toSpec())
    }

    fun display(plotSpec: MutableMap<String, Any>) {

        val plotFactory: PlotFactory = { plotSize ->
            val plot = DemoAndTest.createPlot(plotSpec)
            PlotContainer(plot, plotSize)
        }

        show(plotFactory, SceneMapperDemoFactory(Style.JFX_PLOT_STYLESHEET))
    }

    private fun show(plotFactory: PlotFactory, swingFactory: SwingDemoFactory) {
        swingFactory.createDemoFrame("Fit in the frame (try to resize)").show(false) {
            val panel = this

            panel.removeAll()
//            panel.border = EmptyBorder(PADDING, PADDING, PADDING, PADDING)
            panel.border = LineBorder(Color.LIGHT_GRAY, PADDING)
            panel.layout = FlowLayout(FlowLayout.CENTER, 0, 0)

            this.addComponentListener(object : ComponentAdapter() {
                private val eventCount: AtomicInteger = AtomicInteger(0)
                private var plotCreated = false
                private val plotSizeProp = ValueProperty(DoubleVector.ZERO)
                override fun componentResized(e: ComponentEvent) {
                    eventCount.incrementAndGet()

                    val executor: (() -> Unit) -> Unit = if (plotCreated) {
                        // Supposedly, Java FX has already been initialized at this time
                        swingFactory.createPlotEdtExecutor()
                    } else {
                        { runnable: () -> Unit -> SwingUtilities.invokeLater(runnable) }
                    }

                    executor {
                        if (eventCount.decrementAndGet() == 0) {
                            val container = e.component as JComponent
                            container.invalidate()

                            // existing plot will be updated here
                            val newPlotSize = toPlotSize(e.component.size)
                            plotSizeProp.set(newPlotSize)
                            if (!plotCreated) {
                                plotCreated = true
                                createPlot(plotFactory, plotSizeProp, container, swingFactory)
                            }

                            container.revalidate()
//                            container.repaint()
                        }
                    }
                }
            })
        }
    }

    private fun createPlot(
        plotFactory: PlotFactory,
        plotSizeProp: ReadableProperty<DoubleVector>,
        container: JComponent,
        swingFactory: SwingDemoFactory
    ) {

        val plot = plotFactory(plotSizeProp)
        val component = MonolithicAwt.buildPlotSvgComponent(
            plot,
            swingFactory::createSvgComponent,
            swingFactory.createPlotEdtExecutor()
        )
        component.border = BorderFactory.createLineBorder(Color.BLUE, 1)
        container.add(component)
    }
}