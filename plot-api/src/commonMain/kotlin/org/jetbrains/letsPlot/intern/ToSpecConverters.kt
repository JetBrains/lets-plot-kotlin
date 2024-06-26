/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern

import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.GGBunch
import org.jetbrains.letsPlot.MappingMeta
import org.jetbrains.letsPlot.commons.intern.datetime.Instant
import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.core.spec.Option.Meta.DATA_META
import org.jetbrains.letsPlot.core.spec.Option.Meta.KIND
import org.jetbrains.letsPlot.core.spec.Option.Meta.Kind.PLOT
import org.jetbrains.letsPlot.core.spec.Option.Scale.AES
import org.jetbrains.letsPlot.core.spec.Option.Scale.BREAKS
import org.jetbrains.letsPlot.core.spec.Option.Scale.CONTINUOUS_TRANSFORM
import org.jetbrains.letsPlot.core.spec.Option.Scale.EXPAND
import org.jetbrains.letsPlot.core.spec.Option.Scale.FORMAT
import org.jetbrains.letsPlot.core.spec.Option.Scale.GUIDE
import org.jetbrains.letsPlot.core.spec.Option.Scale.LABELS
import org.jetbrains.letsPlot.core.spec.Option.Scale.LABLIM
import org.jetbrains.letsPlot.core.spec.Option.Scale.LIMITS
import org.jetbrains.letsPlot.core.spec.Option.Scale.NAME
import org.jetbrains.letsPlot.core.spec.Option.Scale.NA_VALUE
import org.jetbrains.letsPlot.core.spec.Option.Scale.POSITION
import org.jetbrains.letsPlot.intern.figure.SubPlotsFigure
import org.jetbrains.letsPlot.intern.layer.WithSpatialParameters
import org.jetbrains.letsPlot.intern.standardizing.JvmStandardizing
import org.jetbrains.letsPlot.intern.standardizing.MapStandardizing
import org.jetbrains.letsPlot.intern.standardizing.SeriesStandardizing
import org.jetbrains.letsPlot.intern.standardizing.SeriesStandardizing.toList
import org.jetbrains.letsPlot.spatial.CRSCode.isWGS84Code
import org.jetbrains.letsPlot.spatial.GeometryFormat
import org.jetbrains.letsPlot.spatial.SpatialDataset

fun Figure.toSpec(): MutableMap<String, Any> {
    return when (this) {
        is Plot -> this.toSpec()
        is SubPlotsFigure -> this.toSpec()
        is GGBunch -> this.toSpec()
        else -> throw IllegalArgumentException("Unsupported figure type ${this::class.simpleName}")
    }
}

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
    spec[Option.Plot.SCALES] = plot.scales().flatMap(Scale::toSpec)

    // Width of plot in percents of the available in frontend width.
    plot.widthScale?.let { spec["widthScale"] = it }

    val features = plot.otherFeatures()
    val themeOptionList = features.filter { it.kind == Option.Plot.THEME }
    val metaInfoOptionList = features.filter { it.kind == Option.Plot.METAINFO }

    // Merge themes
    ThemeOptionsUtil.toSpec(themeOptionList)?.let {
        spec[Option.Plot.THEME] = it
    }

    metaInfoOptionList.forEach { metaInfoOptions ->
        val l = spec.getOrPut(Option.Plot.METAINFO_LIST) { ArrayList<Map<String, Any>>() }
        @Suppress("UNCHECKED_CAST")
        (l as MutableList<Map<String, Any>>).add(metaInfoOptions.toSpec())
    }

    @Suppress("ConvertArgumentToSet")
    (features - themeOptionList - metaInfoOptionList).forEach {
        spec[it.kind] = it.toSpec()
    }

    return spec
}

fun Layer.toSpec(): MutableMap<String, Any> {
    val spec = HashMap<String, Any>()

    data?.let {
        val data = beforeAsPlotData(data)
        spec[Option.PlotBase.DATA] = asPlotData(data)
    }

//    val allMappings = (mapping + geom.mapping + stat.mapping).map
    val allMappings = mapping.map
    spec[Option.PlotBase.MAPPING] = asMappingData(allMappings)

    val dataMeta = createDataMeta(data, allMappings)
    if (dataMeta.isNotEmpty()) {
        spec[DATA_META] = dataMeta
    }

    spec[Option.Layer.GEOM] = geom.kind.optionName()
    spec[Option.Layer.STAT] = stat.kind.optionName()

    position?.let {
        spec[Option.Layer.POS] =
            if (it.parameters.isEmpty()) {
                it.kind.optionName()
            } else {
                OptionsMap(
                    "pos",
                    it.kind.optionName(),
                    it.parameters.map
                ).toSpec()
            }
    }

    sampling?.let {
        spec[Option.Layer.SAMPLING] =
            if (it.isNone) "none"
            else it.mapping.map
    }

    tooltips?.let {
        spec[Option.Layer.TOOLTIPS] = it.options
    }
    labels?.let {
        spec[Option.Layer.ANNOTATIONS] = it.options
    }

    orientation?.let {
        spec[Option.Layer.ORIENTATION] = it
    }

    // parameters 'map', 'mapJoin'
    if (this is WithSpatialParameters) {
        map?.run {
            if (useCRS != "provided") {
                this.crs?.let {
                    require(isWGS84Code(it)) {
                        "Geometry must use WGS84 coordinate reference system but was: $it."
                    }
                }
            }
            useCRS?.let { spec[Option.Layer.USE_CRS] = it }

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

//    val allParameters = parameters + geom.parameters + stat.parameters
//    spec.putAll(allParameters.map)
    val allParameters = parameters.map
    spec.putAll(allParameters)
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


fun Scale.toSpec(): List<Map<String, Any>> {
    val spec = HashMap<String, Any>()

    name?.let { spec[NAME] = name }
    breaks?.let { spec[BREAKS] = toList(breaks, BREAKS) }
    labels?.let { spec[LABELS] = labels }
    lablim?.let { spec[LABLIM] = lablim }
    limits?.let { spec[LIMITS] = toList(limits, LIMITS) }
    expand?.let { spec[EXPAND] = expand }
    naValue?.let { spec[NA_VALUE] = naValue }
    guide?.let { spec[GUIDE] = guide }
    trans?.let { spec[CONTINUOUS_TRANSFORM] = trans }
    format?.let { spec[FORMAT] = format }
    position?.let { spec[POSITION] = position }

    spec.putAll(otherOptions.map)

    return aesthetic.map {
        mapOf(AES to it.name) + spec
    }
}

fun OptionsMap.toSpec(): MutableMap<String, Any> {
    return HashMap(
        MapStandardizing.standardize(options)
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

    data class VariableMeta(
        val levels: List<Any>,
        val aesthetics: List<String>,
        val order: Int?,
    )
    val variablesMeta = mutableMapOf<String, VariableMeta>()
    mappings
        .filterValues { it is MappingMeta }
        .forEach { (aes, mappingMeta) ->
            mappingMeta as MappingMeta

            val variableMeta = variablesMeta[mappingMeta.variable]
            val levels = mappingMeta.levels ?: variableMeta?.levels ?: emptyList()
            val order = mappingMeta.order ?: variableMeta?.order
            val aesthetics = variableMeta?.aesthetics?.let { it + aes } ?: listOf(aes)
            variablesMeta[mappingMeta.variable] = VariableMeta(levels, aesthetics, order)
        }

    // mapping annotations
    val aesListForAnnotations = variablesMeta.values.filter { it.levels.isEmpty() }.flatMap(VariableMeta::aesthetics)
    val mappingAnnotations = createMappingAnnotations(
        mappings.filterKeys { aes -> aes in aesListForAnnotations }
    )
    val mappingDataMeta = if (mappingAnnotations.isNotEmpty()) {
        mapOf(
            Option.Meta.MappingAnnotation.TAG to mappingAnnotations
        )
    } else {
        emptyMap()
    }

    // series annotations
    val seriesAnnotations =
        // with factor levels
        variablesMeta
            .filterValues { it.levels.isNotEmpty() }
            .map { (variable, variableMeta) ->
                createSeriesAnnotationWithLevels(variable, variableMeta.levels, variableMeta.order)
            } +
                // date-time series
                createDateTimeSeriesAnnotations(data)
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

private fun createDateTimeSeriesAnnotations(data: Map<*, *>?): List<Map<String, Any>> {
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

private fun createSeriesAnnotationWithLevels(varName: String, levels: List<Any>, order: Int?): Map<String, Any?> {
    return mapOf(
        Option.Meta.SeriesAnnotation.COLUMN to varName,
        Option.Meta.SeriesAnnotation.FACTOR_LEVELS to levels,
        Option.Meta.SeriesAnnotation.ORDER to order
    )
}

//private fun mergeThemeOptions(m0: Map<String, Any>, m1: Map<String, Any>): Map<String, Any> {
//    val overlappingKeys = m0.keys.intersect(m1.keys)
//    val keysToMerge = overlappingKeys.filter {
//        m0[it] is Map<*, *> && m1[it] is Map<*, *>
//    }
//    val m2 = keysToMerge.map {
//        it to (m0[it] as Map<*, *> + m1[it] as Map<*, *>)
//    }.toMap()
//    return m0 + m1 + m2
//}
