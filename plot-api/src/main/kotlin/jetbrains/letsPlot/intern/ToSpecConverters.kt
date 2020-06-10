/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern

import jetbrains.datalore.plot.config.Option
import jetbrains.datalore.plot.config.Option.Meta.KIND
import jetbrains.datalore.plot.config.Option.Meta.Kind.PLOT
import jetbrains.datalore.plot.config.Option.Scale.AES
import jetbrains.datalore.plot.config.Option.Scale.BREAKS
import jetbrains.datalore.plot.config.Option.Scale.EXPAND
import jetbrains.datalore.plot.config.Option.Scale.GUIDE
import jetbrains.datalore.plot.config.Option.Scale.LABELS
import jetbrains.datalore.plot.config.Option.Scale.LIMITS
import jetbrains.datalore.plot.config.Option.Scale.NAME
import jetbrains.datalore.plot.config.Option.Scale.NA_VALUE

fun Plot.toSpec(): MutableMap<String, Any> {
    val spec = HashMap<String, Any>()
    val plot = this

    spec[KIND] = PLOT

    plot.data?.let {
        spec[Option.PlotBase.DATA] = asPlotData(plot.data)
    }

    spec[Option.PlotBase.MAPPING] = plot.mapping.map
    spec[Option.Plot.LAYERS] = plot.layers().map { it.toSpec() }
    spec[Option.Plot.SCALES] = plot.scales().map { it.toSpec() }

    // Width of plot in percents of the available in frontend width.
    plot.widthScale?.let { spec["widthScale"] = it }

// TODO:
//    const val TITLE = "ggtitle"
//    const val TITLE_TEXT = "text"
//    const val COORD = "coord"
//    const val FACET = "facet"
//    const val THEME = "theme"    // done
//    const val SIZE = "ggsize"    // done

    for (plotFeature in plot.otherFeatures()) {
        spec[plotFeature.kind] = plotFeature.toSpec()
    }

    return spec
}

fun Layer.toSpec(): MutableMap<String, Any> {
    val spec = HashMap<String, Any>()

    data?.let {
        spec[Option.PlotBase.DATA] = asPlotData(data)
    }

    spec[Option.Layer.GEOM] = geom.kind.optionName()
    spec[Option.Layer.STAT] = stat.kind.optionName()

    val posOptions = position
    spec[Option.Layer.POS] = if (posOptions.parameters.isEmpty()) {
        posOptions.kind.optionName()
    } else {
        toFeatureSpec(Option.Meta.Kind.POS, posOptions.kind.optionName(), posOptions.parameters.map)
    }

    sampling?.let {
        spec[Option.Layer.SAMPLING] = sampling.mapping.map
    }

    spec[Option.PlotBase.MAPPING] = (mapping + geom.mapping + stat.mapping).map

    val allParameters = parameters + geom.parameters + stat.parameters
    spec.putAll(allParameters.map)
    if (!show_legend) {
        spec[Option.Layer.SHOW_LEGEND] = false
    }

    return spec
}

@Suppress("UNCHECKED_CAST")
fun Map<String, Any?>.filterNonNullValues(): Map<String, Any> {
    return filter { it.value != null } as Map<String, Any>
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

fun OtherPlotFeature.toSpec(): MutableMap<String, Any> {
    return HashMap(options)
}

private fun toFeatureSpec(kind: String, name: String?, parameters: Map<String, Any>): MutableMap<String, Any> {
    val spec = HashMap<String, Any>()
    spec[KIND] = kind
    // ToDo: 'name' -> constant
    name?.let { spec["name"] = name }

    spec.putAll(parameters)
    return spec
}

private fun asPlotData(rawData: Map<*, *>): Map<String, List<Any?>> {
    // ToDo: DateTime
    // ToDo: Char --> String

    fun valueAsList(key: String, rawValue: Any): List<Any?> {
        return when (rawValue) {
            is List<*> -> rawValue
            is Iterable<*> -> rawValue.toList()
            is Sequence<*> -> rawValue.asIterable().toList()
            is Array<*> -> rawValue.asList()
            is ByteArray -> rawValue.asList()
            is ShortArray -> rawValue.asList()
            is IntArray -> rawValue.asList()
            is LongArray -> rawValue.asList()
            is FloatArray -> rawValue.asList()
            is DoubleArray -> rawValue.asList()
            else -> throw IllegalArgumentException("Can't transform data[\"$key\"] to a list: \"${rawValue::class.simpleName}\"")
        }
    }

    val standardisedData = HashMap<String, List<Any?>>()
    for ((rawKey, rawValue) in rawData) {
        standardisedData[rawKey.toString()] = valueAsList(rawKey.toString(), rawValue!!)
    }
    return standardisedData
}

