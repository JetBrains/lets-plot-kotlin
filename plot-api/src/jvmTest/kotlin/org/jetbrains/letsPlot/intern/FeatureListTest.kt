/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern

import org.jetbrains.letsPlot.intern.FeatureListAssert.Companion.assertThat
import org.junit.Test

class FeatureListTest {

    @Test
    fun create() {
        assertThat((SomeFeature() + SomeFeature()) as FeatureList)
            .length(2)

        assertThat(FeatureList(listOf(SomeFeature(), SomeFeature())))
            .length(2)

        // Feature list must be flat (can't contain other feature lists)
        val fl1 = SomeFeature() + SomeFeature()
        val fl2 = SomeFeature() + SomeFeature()

        assertThat(FeatureList(listOf(fl1, fl2)))
            .length(4)
    }

    @Test
    fun plus() {
        val fl1 = SomeFeature() + SomeFeature()

        assertThat((fl1 + SomeFeature()) as FeatureList)
            .length(3)

        assertThat((SomeFeature() + fl1) as FeatureList)
            .length(3)

        // Feature list must be flat (can't contain other feature lists)
        val fl2 = SomeFeature() + SomeFeature()
        assertThat((fl1 + fl2) as FeatureList)
            .length(4)
    }
}