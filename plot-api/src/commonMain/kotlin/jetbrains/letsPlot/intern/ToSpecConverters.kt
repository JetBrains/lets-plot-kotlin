/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern

import jetbrains.datalore.base.datetime.Instant
import jetbrains.datalore.plot.config.Option
import jetbrains.datalore.plot.config.Option.Meta.DATA_META
import jetbrains.datalore.plot.config.Option.Meta.KIND
import jetbrains.datalore.plot.config.Option.Meta.Kind.PLOT
import jetbrains.datalore.plot.config.Option.Scale.AES
import jetbrains.datalore.plot.config.Option.Scale.BREAKS
import jetbrains.datalore.plot.config.Option.Scale.CONTINUOUS_TRANSFORM
import jetbrains.datalore.plot.config.Option.Scale.EXPAND
import jetbrains.datalore.plot.config.Option.Scale.FORMAT
import jetbrains.datalore.plot.config.Option.Scale.GUIDE
import jetbrains.datalore.plot.config.Option.Scale.LABELS
import jetbrains.datalore.plot.config.Option.Scale.LIMITS
import jetbrains.datalore.plot.config.Option.Scale.NAME
import jetbrains.datalore.plot.config.Option.Scale.NA_VALUE
import jetbrains.letsPlot.MappingMeta
import jetbrains.letsPlot.intern.layer.WithSpatialParameters
import jetbrains.letsPlot.intern.standardizing.JvmStandardizing
import jetbrains.letsPlot.intern.standardizing.MapStandardizing
import jetbrains.letsPlot.intern.standardizing.SeriesStandardizing
import jetbrains.letsPlot.intern.standardizing.SeriesStandardizing.toList
import jetbrains.letsPlot.spatial.GeometryFormat
import jetbrains.letsPlot.spatial.SpatialDataset

fun Plot.toSpec(): MutableMap<String, Any> {
    val spec = HashMap<String, Any>()
    val plot = this

    spec[KIND] = PLOT

    plot.data?.let {
        // SpatialDataset is not allowed in 'ggplot(data=..)' or 'lets_plot(data=..)'
        val data = if (plot.data is SpatialDataset) {
            HashMap<Any?, Any?>(plot.data) // convert to a regular Map.
        } else {
            plot.data
        }
        spec[Option.PlotBase.DATA] = asPlotData(data)
        val dataMeta = createDataMeta(data, plot.mapping.map)
        if (dataMeta.isNotEmpty()) {
            spec[DATA_META] = dataMeta
        }
    }

    spec[Option.PlotBase.MAPPING] = asMappingData(plot.mapping.map)
    spec[Option.Plot.LAYERS] = plot.layers().map(Layer::toSpec)
    spec[Option.Plot.SCALES] = plot.scales().map(Scale::toSpec)

    // Width of plot in percents of the available in frontend width.
    plot.widthScale?.let { spec["widthScale"] = it }

    for (plotFeature in plot.otherFeatures()) {
        if (plotFeature.kind == Option.Plot.THEME && spec.containsKey(Option.Plot.THEME)) {
            val otherThemeOpts = plotFeature.toSpec()
            val newThemeOptions = otherThemeOpts[Option.Meta.NAME]?.let {
                // 'named' theme overrides all prev theme options.
                otherThemeOpts
            } ?: let {
                // Merge themes.
                @Suppress("UNCHECKED_CAST")
                mergeThemeOptions(spec.getValue(Option.Plot.THEME) as Map<String, Any>, otherThemeOpts)
            }

            spec[Option.Plot.THEME] = newThemeOptions
        } else {
            spec[plotFeature.kind] = plotFeature.toSpec()
        }
    }

    return spec
}

fun Layer.toSpec(): MutableMap<String, Any> {
    val spec = HashMap<String, Any>()

    data?.let {
        val data = beforeAsPlotData(data)
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
            spec[Option.Geom.Choropleth.GEO_POSITIONS] = this
            spec[Option.Meta.MAP_DATA_META] = createGeoDataframeAnnotation(this)

            mapJoin?.let {
                val (first, second) = it
                when (first) {
                    is String -> require(second is String) { "'mapJoin': both members must be either Strings or Lists." }
                    is List<*> -> {
                        require(second is List<*>) { "'mapJoin': both members must be either Strings or Lists." }
                        require(first.isNotEmpty()) { "'mapJoin': 'first' should not be empty." }
                        require(first.size == second.size) { "'mapJoin': members must have equal size." }
                    }
                }
                spec[Option.Layer.MAP_JOIN] = listOf(
                    when (first) {
                        is String -> listOf(first)
                        else -> first
                    },
                    when (second) {
                        is String -> listOf(second)
                        else -> second
                    }
                )
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

private fun Layer.beforeAsPlotData(rawData: Map<*, *>): Map<*, *> {
    if (rawData is SpatialDataset) {
        return when (this) {
            is WithSpatialParameters -> if (this.map == null) {
                // No "map" parameter -> keep the Spatial dataset.
                rawData
            } else {
                // Has "map" parameter -> convert "data" to a regular Map.
                HashMap<Any?, Any?>(rawData)
            }
            else -> HashMap<Any?, Any?>(rawData) // convert "data" to a regular Map.
        }
    }
    return rawData
}


@Suppress("UNCHECKED_CAST")
fun Map<String, Any?>.filterNonNullValues(): Map<String, Any> {
    return filter { it.value != null } as Map<String, Any>
}


fun Scale.toSpec(): MutableMap<String, Any> {
    val spec = HashMap<String, Any>()

    spec[AES] = aesthetic.name
    name?.let { spec[NAME] = name }
    breaks?.let { spec[BREAKS] = toList(breaks, BREAKS) }
    labels?.let { spec[LABELS] = labels }
    limits?.let { spec[LIMITS] = toList(limits, LIMITS) }
    expand?.let { spec[EXPAND] = expand }
    naValue?.let { spec[NA_VALUE] = naValue }
    guide?.let { spec[GUIDE] = guide }
    trans?.let { spec[CONTINUOUS_TRANSFORM] = trans }
    format?.let { spec[FORMAT] = format }

    spec.putAll(otherOptions.map)
    return spec
}

fun OptionsMap.toSpec(includeKind: Boolean = false): MutableMap<String, Any> {
    return HashMap(
        MapStandardizing.standardize(
            if (includeKind) {
                mapOf(KIND to kind) + options
            } else {
                options
            }
        )
    )
}

internal fun asPlotData(rawData: Map<*, *>): Map<String, List<Any?>> {
    val standardisedData = HashMap<String, List<Any?>>()
    for ((rawKey, rawValue) in rawData) {
        val key = rawKey.toString()
        standardisedData[key] = toList(rawValue!!, key)
    }
    return standardisedData
}

private fun asMappingData(rawMapping: Map<String, Any>): Map<String, Any> {
    val mapping = rawMapping.toMutableMap()
    return mapping.mapValues { (_, value) ->
        when (value) {
            is MappingMeta -> value.variable
            else -> value
        }
    }
}

private fun createDataMeta(data: Map<*, *>?, mappings: Map<String, Any>): Map<String, Any> {
    val spatialDataMeta: Map<String, Any> = if (data is SpatialDataset) {
        createGeoDataframeAnnotation(data)
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

    val seriesAnnotations = createSeriesAnnotations(data)
    val seriesDataMeta: Map<String, Any> = if (seriesAnnotations.isNotEmpty()) {
        mapOf(
            Option.Meta.SeriesAnnotation.TAG to seriesAnnotations
        )
    } else {
        emptyMap()
    }

    return spatialDataMeta + mappingDataMeta + seriesDataMeta
}

private fun createGeoDataframeAnnotation(data: SpatialDataset): Map<String, Any> {
    require(data.geometryFormat == GeometryFormat.GEOJSON) { "Only GEOJSON geometry format is supported." }
    return mapOf(
        "geodataframe" to mapOf(
            "geometry" to data.geometryKey
        )
    )
}

private fun createMappingAnnotations(mappings: Map<String, Any>): List<Map<String, Any>> {
    return mappings
        .filter { it.value is MappingMeta }
        .map { (it.value as MappingMeta).getAnnotatedData(it.key) }
}

private fun createDateTimeAnnotation(varName: String): Map<String, Any> {
    return mapOf(
        Option.Meta.SeriesAnnotation.COLUMN to varName,
        Option.Meta.SeriesAnnotation.TYPE to Option.Meta.SeriesAnnotation.DateTime.DATE_TIME
    )
}

private fun createSeriesAnnotations(data: Map<*, *>?): List<Map<String, Any>> {
    fun isDateTime(value: Any?): Boolean {
        return value is Instant ||
                (value?.let(JvmStandardizing::isDateTimeJvm) ?: false)
    }

    return data?.mapNotNull { (varName, values) ->
        if (SeriesStandardizing.isListy(values)) {
            val l = values?.let(SeriesStandardizing::asList)
            if (!l.isNullOrEmpty() && l.all(::isDateTime)) {
                return@mapNotNull createDateTimeAnnotation(varName as String)
            }
        }
        return@mapNotNull null
    } ?: emptyList()
}

private fun mergeThemeOptions(m0: Map<String, Any>, m1: Map<String, Any>): Map<String, Any> {
    val overlappingKeys = m0.keys.intersect(m1.keys)
    val keysToMerge = overlappingKeys.filter {
        m0[it] is Map<*, *> && m1[it] is Map<*, *>
    }
    val m2 = keysToMerge.map {
        it to (m0[it] as Map<*, *> + m1[it] as Map<*, *>)
    }.toMap()
    return m0 + m1 + m2
}