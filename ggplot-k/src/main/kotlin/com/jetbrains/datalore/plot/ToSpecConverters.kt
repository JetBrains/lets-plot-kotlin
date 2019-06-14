package com.jetbrains.datalore.plot

import jetbrains.datalore.visualization.plot.config.Option
import jetbrains.datalore.visualization.plot.config.Option.Scale.AES
import jetbrains.datalore.visualization.plot.config.Option.Scale.BREAKS
import jetbrains.datalore.visualization.plot.config.Option.Scale.EXPAND
import jetbrains.datalore.visualization.plot.config.Option.Scale.GUIDE
import jetbrains.datalore.visualization.plot.config.Option.Scale.LABELS
import jetbrains.datalore.visualization.plot.config.Option.Scale.LIMITS
import jetbrains.datalore.visualization.plot.config.Option.Scale.NAME
import jetbrains.datalore.visualization.plot.config.Option.Scale.NA_VALUE

fun Plot.toSpec(): MutableMap<String, Any> {
    val spec = HashMap<String, Any>()
    val plot = this

    plot.data?.let {
        spec[Option.Plot.DATA] = asPlotData(plot.data)
    }

    spec[Option.Plot.MAPPING] = plot.mapping.map
    spec[Option.Plot.LAYERS] = plot.layers().map { it.toSpec() }
    spec[Option.Plot.SCALES] = plot.scales().map { it.toSpec() }

// TODO:
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

    // ToDo:
//    layer.sampling

    data?.let {
        spec[Option.Layer.DATA] = asPlotData(data)
    }

    spec[Option.Layer.GEOM] = geom.kind.optionName()
    spec[Option.Layer.STAT] = stat.kind.optionName()

    val posOptions = position
    spec[Option.Layer.POS] = if (posOptions.parameters.isEmpty()) {
        posOptions.kind.optionName()
    } else {
        // ToDo: 'pos' -> constant
        toFeatureSpec("pos", posOptions.kind.optionName(), posOptions.parameters.map)
    }

    spec[Option.Layer.MAPPING] = (mapping + geom.mapping + stat.mapping).map

    val allParameters = parameters + geom.parameters + stat.parameters
    spec.putAll(allParameters.map)
    if (!show_legend) {
        spec[Option.Layer.SHOW_LEGEND] = false
    }

    return spec
}


fun Scale.toSpec(): MutableMap<String, Any> {
    val spec = HashMap<String, Any>()

    spec[AES] = aesthetic.name
    name?.let { spec[NAME] = name }
    breaks?.let { spec[BREAKS] = breaks }
    labels?.let { spec[LABELS] = labels }
    limits?.let { spec[LIMITS] = limits }
    expand?.let { spec[EXPAND] = expand }
    na_value?.let { spec[NA_VALUE] = na_value }
    guide?.let { spec[GUIDE] = guide }
//    trans    ?.let{ spec[TRANS    ] = trans    }  // ToDo: add trans

    spec.putAll(otherOptions.map)
    return spec
}


private fun asPlotData(dataRaw: Any) = dataRaw  // placeholder

private fun toFeatureSpec(kind: String, name: String?, parameters: Map<String, Any>): MutableMap<String, Any> {
    val spec = HashMap<String, Any>()
    spec[Option.Meta.KIND] = kind
    // ToDo: 'name' -> constant
    name?.let { spec["name"] = name }

    spec.putAll(parameters)
    return spec
}