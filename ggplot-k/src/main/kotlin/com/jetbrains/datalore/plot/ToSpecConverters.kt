package com.jetbrains.datalore.plot

import jetbrains.datalore.visualization.plot.config.Option

fun Plot.toSpec(): MutableMap<String, Any> {
    val spec = HashMap<String, Any>()
    val plot = this

    plot.data?.let {
        spec[Option.Plot.DATA] = asPlotData(plot.data)
    }

    spec[Option.Plot.MAPPING] = plot.mapping.map
    spec[Option.Plot.LAYERS] = plot.layers().map { it.toSpec() }

// TODO:
//    const val SCALES = "scales"
//    const val TITLE = "ggtitle"
//    const val TITLE_TEXT = "text"
//    const val COORD = "coord"
//    const val FACET = "facet"
//    const val THEME = "theme"
//    const val SIZE = "ggsize"

    return spec
}

fun Layer.toSpec(): MutableMap<String, Any> {
    val spec = HashMap<String, Any>()
    val layer = this

    val geomOptions = layer.geom
    val statOptions = layer.stat
    spec[Option.Layer.GEOM] = geomOptions.kind.toString().toLowerCase()
    spec[Option.Layer.STAT] = statOptions.kind.toString().toLowerCase()

    spec[Option.Layer.MAPPING] = (mapping + geomOptions.mapping + statOptions.mapping).map

    val allParameters = parameters + geomOptions.parameters + statOptions.parameters
    spec.putAll(allParameters.map)

    return spec
}


private fun asPlotData(dataRaw: Any) = dataRaw  // placeholder