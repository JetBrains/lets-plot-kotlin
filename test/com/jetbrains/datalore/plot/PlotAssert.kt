package com.jetbrains.datalore.plot

import junit.framework.AssertionFailedError
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue

internal class PlotAssert(private val plot: Plot) {
    companion object {
        internal fun assertThat(plotSpec: Plot) = PlotAssert(plotSpec)
    }

    fun features() = FeatureListAssert(plot.features)
    fun mapping() = AesMappingAssert(plot.mapping)
}


internal class FeatureAssert(private val featureSpec: Feature) {
    companion object {
        internal fun assertThat(featureSpec: Feature) = FeatureAssert(featureSpec)
    }

    fun isList(): FeatureListAssert {
        if (featureSpec is FeatureList) {
            return FeatureListAssert(featureSpec.elements)
        }
        throw AssertionFailedError("expected ${FeatureList::class.simpleName} but was ${featureSpec::class.simpleName}")
    }
}

internal class FeatureListAssert(private val featureSpecs: List<Feature>) {
    companion object {
        internal fun assertThat(specList: FeatureList) = FeatureListAssert(specList.elements)
    }

    fun length(length: Int): FeatureListAssert {
        assertEquals(length, featureSpecs.size)
        return this
    }
}

internal class AesMappingAssert(private val options: Options) {
    fun contains(aes: String, variableName: String): AesMappingAssert {
        assertTrue(options.has(aes))
        assertEquals(variableName, options.get(aes))
        return this
    }
}
