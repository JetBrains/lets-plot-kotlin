package com.jetbrains.datalore.plot

import com.jetbrains.datalore.plot.layer.GeomOptions
import com.jetbrains.datalore.plot.layer.StatOptions
import junit.framework.AssertionFailedError
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue

internal class PlotAssert(private val plot: Plot) : AesMappingAssert<PlotAssert> {
    companion object {
        internal fun assertThat(plot: Plot) = PlotAssert(plot)
    }

    override val mappingOptions: Options
        get() = plot.mapping

    fun features() = FeatureListAssert(plot.features)
    fun layers() = LayerListAssert(plot.layers())
}


internal open class FeatureAssert(private val feature: Feature) {
    companion object {
        internal fun assertThat(featureSpec: Feature) = FeatureAssert(featureSpec)
    }

    fun isList(): FeatureListAssert {
        if (feature is FeatureList) {
            return FeatureListAssert(feature.elements)
        }
        throw AssertionFailedError("expected ${FeatureList::class.simpleName} but was ${feature::class.simpleName}")
    }
}

internal open class FeatureListAssert(private val featureList: List<Feature>) {
    open fun length(length: Int): FeatureListAssert {
        assertEquals(length, featureList.size)
        return this
    }

    open fun get(index: Int) = FeatureAssert(featureList[index])
}

internal class LayerListAssert(private val layerList: List<Layer>) : FeatureListAssert(layerList) {
    override fun length(length: Int): LayerListAssert {
        return super.length(length) as LayerListAssert
    }

    override fun get(index: Int) = LayerAssert(layerList[index])
}

internal class LayerAssert(private val layer: Layer) : AesMappingAssert<LayerAssert>, FeatureAssert(layer) {
    companion object {
        internal fun assertThat(layer: Layer) = LayerAssert(layer)
    }

    override val mappingOptions: Options
        get() = layer.mapping

    fun geom() = GeomOptionsAssert(layer.geom)
    fun stat() = StatOptionsAssert(layer.stat)
}

internal interface AesMappingAssert<T> {
    val mappingOptions: Options

    @Suppress("UNCHECKED_CAST")
    fun aes(name: String, value: String): T {
        assertTrue(mappingOptions.has(name))
        assertEquals(value, mappingOptions.get(name))
        return this as T
    }

    @Suppress("UNCHECKED_CAST")
    fun noMapping(): T {
        assertTrue(mappingOptions.isEmpty())
        return this as T
    }
}

internal class GeomOptionsAssert(private val geom: GeomOptions) : AesMappingAssert<GeomOptionsAssert> {
    override val mappingOptions: Options
        get() = geom.mapping

    fun kind(kind: GeomKind): GeomOptionsAssert {
        assertTrue(geom.kind === kind)
        return this
    }

    fun constant(aes: String, value: Any): GeomOptionsAssert {
        assertTrue(geom.constants.has(aes))
        assertEquals(value, geom.constants.get(aes))
        return this
    }
}

internal class StatOptionsAssert(private val stat: StatOptions) : AesMappingAssert<StatOptionsAssert> {
    override val mappingOptions: Options
        get() = stat.mapping

    fun kind(kind: StatKind): StatOptionsAssert {
        assertTrue(stat.kind === kind)
        return this
    }

    fun parameter(name: String, value: Any): StatOptionsAssert {
        assertTrue(stat.parameters.has(name))
        assertEquals(value, stat.parameters.get(name))
        return this
    }
}
