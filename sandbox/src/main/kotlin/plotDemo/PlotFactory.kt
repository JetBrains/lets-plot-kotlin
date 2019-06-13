package plotDemo

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.base.observable.property.ReadableProperty
import jetbrains.datalore.visualization.plot.builder.PlotContainer

interface PlotFactory {
    fun createPlot(plotSize: ReadableProperty<DoubleVector>): PlotContainer
}