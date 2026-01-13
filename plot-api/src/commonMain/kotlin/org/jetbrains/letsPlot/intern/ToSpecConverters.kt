/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern

import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.MappingMeta
import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.core.spec.Option.Meta.DATA_META
import org.jetbrains.letsPlot.core.spec.Option.Meta.KIND
import org.jetbrains.letsPlot.core.spec.Option.Meta.Kind.PLOT
import org.jetbrains.letsPlot.core.spec.Option.Meta.MappingAnnotation
import org.jetbrains.letsPlot.core.spec.Option.Meta.SeriesAnnotation
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
import org.jetbrains.letsPlot.core.spec.provideMap
import org.jetbrains.letsPlot.intern.figure.SubPlotsFigure
import org.jetbrains.letsPlot.intern.layer.WithSpatialParameters
import org.jetbrains.letsPlot.intern.standardizing.JvmStandardizing
import org.jetbrains.letsPlot.intern.standardizing.MapStandardizing
import org.jetbrains.letsPlot.intern.standardizing.SeriesStandardizing.asList
import org.jetbrains.letsPlot.intern.standardizing.SeriesStandardizing.isListy
import org.jetbrains.letsPlot.intern.standardizing.SeriesStandardizing.toList
import org.jetbrains.letsPlot.spatial.CRSCode.isWGS84Code
import org.jetbrains.letsPlot.spatial.GeometryFormat
import org.jetbrains.letsPlot.spatial.SpatialDataset
import kotlin.reflect.KClass

fun Figure.toSpec(): MutableMap<String, Any> {
    return when (this) {
        is Plot -> this.toSpec()
        is SubPlotsFigure -> this.toSpec()
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

    // Width of plot in percentages of the available in frontend width.
    plot.widthScale?.let { spec["widthScale"] = it }

    val features = plot.otherFeatures()
    val themeOptionList = features.filter { it.kind == Option.Plot.THEME }
    val metaInfoOptionList = features.filter { it.kind == Option.Plot.METAINFO }
    val guidesOptionList = features.filter { it.kind == Option.Plot.GUIDES }

    // Merge themes
    ThemeOptionsUtil.toSpec(themeOptionList)?.let {
        spec[Option.Plot.THEME] = it
    }

    metaInfoOptionList.forEach { metaInfoOptions ->
        val l = spec.getOrPut(Option.Plot.METAINFO_LIST) { ArrayList<Map<String, Any>>() }
        @Suppress("UNCHECKED_CAST")
        (l as MutableList<Map<String, Any>>).add(metaInfoOptions.toSpec())
    }

    // Merge guides
    OptionsUtil.toSpec(guidesOptionList)?.let {
        spec[Option.Plot.GUIDES] = it
    }

    @Suppress("ConvertArgumentToSet")
    (features - themeOptionList - metaInfoOptionList - guidesOptionList).forEach {
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

    val allParameters = parameters.map
    spec.putAll(allParameters)
    if (!showLegend) {
        spec[Option.Layer.SHOW_LEGEND] = false
    }

    inheritAes?.let {
        spec[Option.Layer.INHERIT_AES] = it
    }

    manualKey?.let {
        spec[Option.Layer.MANUAL_KEY] = it
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

private fun createDataMeta(data: Map<*, *>?, mappingSpec: Map<String, Any>): Map<String, Any> {
    val spatialDataMeta: Map<String, Any> = if (data is SpatialDataset) {
        createGeoDataframeAnnotation(data)
    } else {
        emptyMap()
    }

    // VarName to Type
    val dataTypeByVar: MutableMap<String, String> = mutableMapOf()

    // VarName to Dict[Aes, MappingMeta]
    val mappingMetaByVar: MutableMap<String, MutableMap<String, MappingMeta>> = mutableMapOf()

    // Aes to VarName
    val regularMapping: MutableMap<String, String> = mutableMapOf()

    if (mappingSpec.isNotEmpty()) {
        mappingSpec.forEach { (aes, spec) ->
            when (spec) {
                is String -> regularMapping[aes] = spec
                is MappingMeta -> {
                    regularMapping[aes] = spec.variable
                    mappingMetaByVar.provideMap(spec.variable)[aes] = spec
                    dataTypeByVar[spec.variable] = SeriesAnnotation.Types.UNKNOWN
                }

                is Collection<*> -> {} // no variable name, can't use inferred type

                else -> throw IllegalArgumentException("Unsupported mapping spec: $spec")
            }
        }
    }

    dataTypeByVar += inferDTypes(data)
    val timeZoneByVar = detectTimeZones(data)

    // fill series annotations
    val seriesAnnotations = mutableMapOf<String, MutableMap<String, Any>>()
    dataTypeByVar.forEach { (varName, dataType) ->
        val seriesAnnotation = mutableMapOf<String, Any>()

        if (dataType != SeriesAnnotation.Types.UNKNOWN) {
            seriesAnnotation[SeriesAnnotation.TYPE] = dataType
        }

        if (varName in timeZoneByVar) {
            seriesAnnotation[SeriesAnnotation.TIME_ZONE] = timeZoneByVar.getValue(varName)
        }

        if (varName in mappingMetaByVar) {
            val levels = mappingMetaByVar[varName]?.values?.mapNotNull(MappingMeta::levels)?.lastOrNull()
            if (levels != null) {
                seriesAnnotation[SeriesAnnotation.FACTOR_LEVELS] = levels
            }
        }

        if (SeriesAnnotation.FACTOR_LEVELS in seriesAnnotation && varName in mappingMetaByVar) {
            val order = mappingMetaByVar[varName]!!.values.mapNotNull(MappingMeta::order).lastOrNull()
            if (order != null) {
                seriesAnnotation[SeriesAnnotation.ORDER] = order
            }
        }

        if (seriesAnnotation.isNotEmpty()) {
            seriesAnnotation[SeriesAnnotation.COLUMN] = varName
            seriesAnnotations[varName] = seriesAnnotation
        }
    }

    // fill mapping annotations
    val mappingAnnotations = mappingMetaByVar.flatMap { (varName, varMeta) ->
        varMeta.mapNotNull { (aes, mappingMeta) ->
            if (mappingMeta.annotation != "as_discrete") {
                return@mapNotNull null
            }

            if (seriesAnnotations[varName]?.contains(SeriesAnnotation.FACTOR_LEVELS) == true) {
                // Don't duplicate ordering options - store them in mappingAnnotation only if they are not in seriesAnnotations
                return@mapNotNull null
            }

            val mappingAnnotation = mutableMapOf(
                MappingAnnotation.AES to aes,
                MappingAnnotation.ANNOTATION to "as_discrete",
                MappingAnnotation.PARAMETERS to mutableMapOf(
                    MappingAnnotation.LABEL to mappingMeta.label
                )
            )

            mappingMeta.levels?.let {
                mappingAnnotation[SeriesAnnotation.FACTOR_LEVELS] = it
            }

            mappingMeta.orderBy?.let {
                mappingAnnotation.provideMap(MappingAnnotation.PARAMETERS)[MappingAnnotation.ORDER_BY] = it
            }

            mappingMeta.order?.let {
                mappingAnnotation.provideMap(MappingAnnotation.PARAMETERS)[MappingAnnotation.ORDER] = it
            }

            mappingAnnotation
        }
    }

    val dataMeta = mutableMapOf<String, Any>()
    if (seriesAnnotations.isNotEmpty()) {
        dataMeta[SeriesAnnotation.TAG] = seriesAnnotations.values.toList()
    }

    if (mappingAnnotations.isNotEmpty()) {
        dataMeta[MappingAnnotation.TAG] = mappingAnnotations
    }

    return spatialDataMeta + dataMeta
}

private fun inferDTypes(data: Any?): Map<String, String> {
    return if (data is Map<*, *>) {
        data.entries
            .associate { (key, values) -> key.toString() to inferSeriesDType(values) }
    } else {
        emptyMap()
    }
}

private fun inferSeriesDType(data: Any?): String {
    if (data == null) {
        return SeriesAnnotation.Types.UNKNOWN
    }

    if (!isListy(data)) {
        return SeriesAnnotation.Types.UNKNOWN
    }

    val l = asList(data).filterNotNull()
    if (l.isEmpty()) {
        return SeriesAnnotation.Types.UNKNOWN
    }

    val types = l.fold(mutableSetOf<KClass<*>>()) { acc, value -> acc.apply { acc + value::class } }

    if (types.size > 1) {
        return SeriesAnnotation.Types.UNKNOWN
    }

    // types.size == 1 means all elements are of the same type, so we can take any (let's take the first one)
    val value = l.first()

    return if (JvmStandardizing.isJvm(value)) {
        JvmStandardizing.getTypeAnnotation(value)
    } else {
        when {
            value is String -> SeriesAnnotation.Types.STRING
            value is Boolean -> SeriesAnnotation.Types.BOOLEAN

            // Primitive numeric types: using `::class` comparisons instead of `is` checks
            // due to Kotlin/JS type coercion issues
            value::class == Byte::class -> SeriesAnnotation.Types.INTEGER
            value::class == Short::class -> SeriesAnnotation.Types.INTEGER
            value::class == Int::class -> SeriesAnnotation.Types.INTEGER
            value::class == Long::class -> SeriesAnnotation.Types.INTEGER
            value::class == Double::class -> SeriesAnnotation.Types.FLOATING
            value::class == Float::class -> SeriesAnnotation.Types.FLOATING

            value is kotlinx.datetime.Instant -> SeriesAnnotation.Types.DATE_TIME
            value is kotlinx.datetime.LocalDate -> SeriesAnnotation.Types.DATE
            value is kotlinx.datetime.LocalTime -> SeriesAnnotation.Types.TIME
            value is kotlinx.datetime.LocalDateTime -> SeriesAnnotation.Types.DATE_TIME

            value is kotlin.time.Duration -> SeriesAnnotation.Types.INTEGER

            else -> SeriesAnnotation.Types.UNKNOWN
        }
    }
}

/**
 * Not private to allow the access to in tests.
 */
internal fun detectTimeZones(data: Any?): Map<String, String> {
    return if (data is Map<*, *>) {
        @Suppress("UNCHECKED_CAST")
        data.entries
            .associate { (key, values) ->
                key.toString() to detectSeriesTimeZoneID(values)
            }.filterNonNullValues() as Map<String, String>
    } else {
        emptyMap()
    }
}

private fun detectSeriesTimeZoneID(data: Any?): String? {
    @Suppress("NAME_SHADOWING")
    val data = if (isListy(data)) {
        asList(data!!).filterNotNull()
    } else {
        null
    }

    if (data == null || data.isEmpty()) return null

    val value = data.first()

    return if (JvmStandardizing.isJvm(value)) {
        JvmStandardizing.getTimeZoneAnnotation(value)
    } else {
        //
        null
    }
}


private fun createGeoDataframeAnnotation(data: SpatialDataset): Map<String, Any> {
    require(data.geometryFormat == GeometryFormat.GEOJSON) { "Only GEOJSON geometry format is supported." }
    return mapOf(
        "geodataframe" to mapOf(
            "geometry" to data.geometryKey
        )
    )
}
