package com.jetbrains.datalore.plot

import junit.framework.AssertionFailedError
import junit.framework.TestCase.assertEquals

internal class PlotAssert(private val plotSpec: Plot) {
    companion object {
        internal fun assertThat(plotSpec: Plot) = PlotAssert(plotSpec)
    }

    fun features() = FeatureSpecListAssert(plotSpec.features)
}


internal class FeatureSpecAssert(private val featureSpec: Feature) {
    companion object {
        internal fun assertThat(featureSpec: Feature) = FeatureSpecAssert(featureSpec)
    }

    fun isList(): FeatureSpecListAssert {
        if (featureSpec is FeatureList) {
            return FeatureSpecListAssert(featureSpec.elements)
        }
        throw AssertionFailedError("expected ${FeatureList::class.simpleName} but was ${featureSpec::class.simpleName}")
    }
}

internal class FeatureSpecListAssert(private val featureSpecs: List<Feature>) {
    companion object {
        internal fun assertThat(specList: FeatureList) = FeatureSpecListAssert(specList.elements)
    }

    fun length(length: Int): FeatureSpecListAssert {
        assertEquals(length, featureSpecs.size)
        return this
    }
}
