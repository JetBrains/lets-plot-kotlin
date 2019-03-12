package com.jetbrains.datalore.plot

import junit.framework.AssertionFailedError
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue

internal class PlotAssert(private val plot: Plot) {
    companion object {
        internal fun assertThat(plotSpec: Plot) = PlotAssert(plotSpec)
    }

    fun features() = FeatureListAssert(plot.features)
    fun layers() = LayerListAssert(plot.layers())
    fun mapping() = AesMappingAssert(plot.mapping)
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

internal class LayerAssert(private val layer: Layer) : FeatureAssert(layer) {
    fun mapping() = AesMappingAssert(layer.mapping)
    fun geom() = GeomOptionsAssert(layer.geom)
}

internal class AesMappingAssert(private val options: Options) {
    fun contains(aes: String, variableName: String): AesMappingAssert {
        assertTrue(options.has(aes))
        assertEquals(variableName, options.get(aes))
        return this
    }
}

internal class GeomOptionsAssert(private val geom: GeomOptions) {
    fun kind(kind: GeomKind): GeomOptionsAssert {
        assertTrue(geom.kind === kind)
        return this
    }

    fun mapping() = AesMappingAssert(options = geom.mapping)
    fun constant(aes: String, value: Any): GeomOptionsAssert {
        assertTrue(geom.constants.has(aes))
        assertEquals(value, geom.constants.get(aes))
        return this
    }
}
