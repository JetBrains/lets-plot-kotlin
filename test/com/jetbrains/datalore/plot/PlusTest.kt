package com.jetbrains.datalore.plot

import com.jetbrains.datalore.plot.PlotAssert.Companion.assertThat
import org.junit.Test

class PlusTest {

    @Test
    fun plus() {
        assertThat(Plot() + SomeFeature())
            .features().length(1)
        assertThat(Plot() + SomeFeature() + SomeFeature())
            .features().length(2)
        assertThat(Plot() + (SomeFeature() + SomeFeature()))
            .features().length(2)

        FeatureAssert.assertThat(SomeFeature() + SomeFeature())
            .isList().length(2)
        FeatureAssert.assertThat((SomeFeature() + SomeFeature()))
            .isList().length(2)
        FeatureAssert.assertThat((SomeFeature() + SomeFeature()) + SomeFeature())
            .isList().length(3)
        FeatureAssert.assertThat(SomeFeature() + (SomeFeature() + SomeFeature()))
            .isList().length(3)
        FeatureAssert.assertThat((SomeFeature() + SomeFeature()) + (SomeFeature() + SomeFeature()))
            .isList().length(4)
    }
}