package plotDemo

import com.jetbrains.datalore.plot.Plot
import com.jetbrains.datalore.plot.toSpec
import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.base.observable.property.ReadableProperty
import jetbrains.datalore.visualization.base.swing.BatikMapperDemoFactory
import jetbrains.datalore.visualization.plot.Monolithic
import jetbrains.datalore.visualization.plot.builder.PlotContainer


abstract class DemoScriptBase {
    fun display(plot: Plot) {
        display(plot.toSpec())
    }

    fun display(plotSpec: MutableMap<String, Any>) {

        val plotFactory: PlotFactory = object : PlotFactory {
            override fun createPlot(plotSize: ReadableProperty<DoubleVector>): PlotContainer {
                val plot = Monolithic.createPlot(plotSpec, null)
                return PlotContainer(plot, plotSize)
            }
        }

        SwingDemoUtil.show(plotFactory, BatikMapperDemoFactory())
    }
}