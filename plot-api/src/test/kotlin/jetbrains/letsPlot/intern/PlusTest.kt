/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern

import jetbrains.letsPlot.intern.PlotAssert.Companion.assertThat
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