package plotDemo

import com.jetbrains.datalore.plot.Plot
import com.jetbrains.datalore.plot.toSpec
import jetbrains.datalore.base.event.awt.AwtEventUtil
import jetbrains.datalore.base.geometry.DoubleRectangle
import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.base.observable.event.EventHandler
import jetbrains.datalore.base.observable.property.PropertyChangeEvent
import jetbrains.datalore.base.observable.property.ReadableProperty
import jetbrains.datalore.base.observable.property.ValueProperty
import jetbrains.datalore.visualization.base.svg.SvgColors
import jetbrains.datalore.visualization.base.svg.SvgRectElement
import jetbrains.datalore.visualization.base.swing.BatikMapperDemoFactory
import jetbrains.datalore.visualization.base.swing.SwingDemoFactory
import jetbrains.datalore.visualization.plot.Monolithic
import jetbrains.datalore.visualization.plot.base.event.MouseEventSpec
import jetbrains.datalore.visualization.plot.builder.PlotContainer
import java.awt.Color
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.util.concurrent.atomic.AtomicInteger
import javax.swing.JComponent
import javax.swing.SwingUtilities
import javax.swing.border.LineBorder


typealias PlotFactory = (plotSize: ReadableProperty<DoubleVector>) -> PlotContainer

object SwingDemoUtil {

    private const val PADDING = 20

    private fun toPlotSize(containerSize: Dimension) = DoubleVector(
        containerSize.width.toDouble() - 2 * PADDING,
        containerSize.height.toDouble() - 2 * PADDING
    )


    fun display(plot: Plot) {
        display(plot.toSpec())
    }

    fun display(plotSpec: MutableMap<String, Any>) {

        val plotFactory: PlotFactory = { plotSize ->
            val plot = Monolithic.createPlot(plotSpec, null)
            PlotContainer(plot, plotSize)
        }

        show(plotFactory, BatikMapperDemoFactory())
    }

    private fun show(plotFactory: PlotFactory, factory: SwingDemoFactory) {
        factory.createDemoFrame("Fit in the frame (try to resize)").show(false) {
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
                    SwingUtilities.invokeLater {
                        if (eventCount.decrementAndGet() == 0) {
                            val container = e.component as JComponent
                            container.invalidate()

                            // existing plot will be updated here
                            val newPlotSize = toPlotSize(e.component.size)
                            plotSizeProp.set(newPlotSize)
                            if (!plotCreated) {
                                plotCreated = true
                                createPlot(plotFactory, plotSizeProp, container, factory)
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
        factory: SwingDemoFactory
    ) {

        val plot = plotFactory(plotSizeProp)
        plot.ensureContentBuilt()
        val svg = plot.svg

        // make a blue frame
        val frameRect = SvgRectElement(DoubleRectangle(DoubleVector.ZERO, plotSizeProp.get()))
        frameRect.stroke().set(SvgColors.LIGHT_CORAL)
        frameRect.fill().set(SvgColors.NONE)
        svg.children().add(frameRect)

        val component = factory.createSvgComponent(svg)
        container.add(component)

        plotSizeProp.addHandler(object : EventHandler<PropertyChangeEvent<out DoubleVector>> {
            override fun onEvent(event: PropertyChangeEvent<out DoubleVector>) {
                frameRect.width().set(event.newValue!!.x)
                frameRect.height().set(event.newValue!!.y)
            }
        })

        // Bind mouse events
        val plotEdt = factory.createPlotEdtExecutor()
        component.addMouseListener(object : MouseAdapter() {
            override fun mouseExited(e: MouseEvent) {
                super.mouseExited(e)
                plotEdt {
                    plot.mouseEventPeer.dispatch(MouseEventSpec.MOUSE_LEFT, AwtEventUtil.translate(e))
                }
            }
        })
        component.addMouseMotionListener(object : MouseAdapter() {
            override fun mouseMoved(e: MouseEvent) {
                super.mouseMoved(e)
                plotEdt {
                    plot.mouseEventPeer.dispatch(MouseEventSpec.MOUSE_MOVED, AwtEventUtil.translate(e))
                }
            }
        })
    }
}