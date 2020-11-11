/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern

import jetbrains.datalore.plot.config.Option
import jetbrains.datalore.plot.config.Option.Meta.DATA_META
import jetbrains.datalore.plot.config.Option.Meta.KIND
import jetbrains.datalore.plot.config.Option.Meta.Kind.PLOT
import jetbrains.datalore.plot.config.Option.Scale.AES
import jetbrains.datalore.plot.config.Option.Scale.BREAKS
import jetbrains.datalore.plot.config.Option.Scale.CONTINUOUS_TRANSFORM
import jetbrains.datalore.plot.config.Option.Scale.EXPAND
import jetbrains.datalore.plot.config.Option.Scale.GUIDE
import jetbrains.datalore.plot.config.Option.Scale.LABELS
import jetbrains.datalore.plot.config.Option.Scale.LIMITS
import jetbrains.datalore.plot.config.Option.Scale.NAME
import jetbrains.datalore.plot.config.Option.Scale.NA_VALUE
import jetbrains.letsPlot.MappingMeta
import jetbrains.letsPlot.intern.SeriesStandardizing.toList
import jetbrains.letsPlot.intern.layer.WithSpatialParameters
import jetbrains.letsPlot.spatial.GeometryFormat
import jetbrains.letsPlot.spatial.SpatialDataset

fun Plot.toSpec(): MutableMap<String, Any> {
    val spec = HashMap<String, Any>()
    val plot = this

    spec[KIND] = PLOT

    plot.data?.let {
        require(data !is SpatialDataset) {
            "SpatialDataset is not allowed in 'ggplot(data=..)' or 'lets_plot(data=..)'"
        }

        spec[Option.PlotBase.DATA] = asPlotData(plot.data)
        val dataMeta = createDataMeta(plot.data, plot.mapping.map)
        if (dataMeta.isNotEmpty()) {
            spec[DATA_META] = dataMeta
        }
    }

    spec[Option.PlotBase.MAPPING] = asMappingData(plot.mapping.map)
    spec[Option.Plot.LAYERS] = plot.layers().map { it.toSpec() }
    spec[Option.Plot.SCALES] = plot.scales().map { it.toSpec() }

    // Width of plot in percents of the available in frontend width.
    plot.widthScale?.let { spec["widthScale"] = it }

// TODO:
//    const val COORD = "coord"

    for (plotFeature in plot.otherFeatures()) {
        if (plotFeature.kind == Option.Plot.THEME && spec.containsKey(Option.Plot.THEME)) {
            // merge themes
            spec[Option.Plot.THEME] = spec[Option.Plot.THEME] as Map<*, *> + plotFeature.toSpec()
        } else {
            spec[plotFeature.kind] = plotFeature.toSpec()
        }
    }

    return spec
}

fun Layer.toSpec(): MutableMap<String, Any> {
    val spec = HashMap<String, Any>()

    data?.let {
        spec[Option.PlotBase.DATA] = asPlotData(data)
    }

    val allMappings = (mapping + geom.mapping + stat.mapping).map
    spec[Option.PlotBase.MAPPING] = asMappingData(allMappings)

    val dataMeta = createDataMeta(data, allMappings)
    if (dataMeta.isNotEmpty()) {
        spec[DATA_META] = dataMeta
    }

    spec[Option.Layer.GEOM] = geom.kind.optionName()
    spec[Option.Layer.STAT] = stat.kind.optionName()

    val posOptions = position
    spec[Option.Layer.POS] = if (posOptions.parameters.isEmpty()) {
        posOptions.kind.optionName()
    } else {
        OptionsMap(
            Option.Meta.Kind.POS, posOptions.kind.optionName(), posOptions.parameters.map
        ).toSpec(true)
    }

    sampling?.let {
        spec[Option.Layer.SAMPLING] =
            if (it.isNone) "none"
            else it.mapping.map
    }

    tooltips?.let {
        spec[Option.Layer.TOOLTIPS] = it.options
    }

    // parameters 'map', 'mapJoin'
    if (this is WithSpatialParameters) {
        map?.run {
            require(geometryFormat == GeometryFormat.GEOJSON) { "Only GEOJSON geometry format is supported." }

            spec[Option.Geom.Choropleth.GEO_POSITIONS] = this
            spec[Option.Meta.MAP_DATA_META] = createGeoDataframeAnnotation(geometryKey)

            mapJoin?.run {
                val v = mapJoin!!
                val (first, second) = v
                when (first) {
                    is String -> require(second is String) { "'mapJoin': both members must be either Strings or Lists." }
                    is List<*> -> {
                        require(second is List<*>) { "'mapJoin': both members must be either Strings or Lists." }
                        require(first.isNotEmpty()) { "'mapJoin': 'first' should not be empty." }
                        require(first.size == second.size) { "'mapJoin': members must have equal size." }
                    }
                }
                spec[Option.Layer.MAP_JOIN] = v.toList()
            }
        }
    }

    val allParameters = parameters + geom.parameters + stat.parameters
    spec.putAll(allParameters.map)
    if (!showLegend) {
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
    breaks?.let { spec[BREAKS] = toList(BREAKS, breaks) }
    labels?.let { spec[LABELS] = labels }
    limits?.let { spec[LIMITS] = toList(LIMITS, limits) }
    expand?.let { spec[EXPAND] = expand }
    naValue?.let { spec[NA_VALUE] = naValue }
    guide?.let { spec[GUIDE] = guide }
    trans?.let { spec[CONTINUOUS_TRANSFORM] = trans }

    spec.putAll(otherOptions.map)
    return spec
}

fun OptionsMap.toSpec(includeKind: Boolean = false): MutableMap<String, Any> {
    return if (includeKind) {
        HashMap(mapOf(KIND to kind) + options)
    } else {
        HashMap(options)
    }
}

internal fun asPlotData(rawData: Map<*, *>): Map<String, List<Any?>> {
    val standardisedData = HashMap<String, List<Any?>>()
    for ((rawKey, rawValue) in rawData) {
        val key = rawKey.toString()
        standardisedData[key] = toList(key, rawValue!!)
    }
    return standardisedData
}

private fun asMappingData(rawMapping: Map<String, Any>): Map<String, Any> {
    val mapping = rawMapping.toMutableMap()
    mapping.replaceAll { _, value ->
        when (value) {
            is MappingMeta -> value.variable
            else -> value
        }
    }
    return mapping
}

private fun createDataMeta(data: Map<*, *>?, mappings: Map<String, Any>): Map<String, Any> {
    val spatialDataMeta: Map<String, Any> = if (data is SpatialDataset) {
        createGeoDataframeAnnotation(data.geometryKey)
    } else {
        emptyMap()
    }

    val mappingAnnotations = createMappingAnnotations(mappings)
    val mappingDataMeta: Map<String, Any> = if (mappingAnnotations.isNotEmpty()) {
        mapOf(
            Option.Meta.MappingAnnotation.TAG to mappingAnnotations
        )
    } else {
        emptyMap()
    }

    return spatialDataMeta + mappingDataMeta
}

private fun createGeoDataframeAnnotation(geometryKey: String): Map<String, Any> {
    return mapOf(
        "geodataframe" to mapOf(
            "geometry" to geometryKey
        )
    )
}

private fun createMappingAnnotations(mappings: Map<String, Any>): List<Map<String, Any>> {
    return mappings
        .filter { it.value is MappingMeta }
        .map { (it.value as MappingMeta).getAnnotatedData(it.key) }
}
