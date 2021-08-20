/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.export

import org.junit.Test

/**
 * Doesn't actually check versions - just shouldn't fail to load resource.
 */
internal class VersionCheckerTest {

    @Test
    fun getLetsPlotVersion() {
        println(VersionChecker.letsPlotVersion)
    }

    @Test
    fun getLetsPlotKotlinAPIVersion() {
        println(VersionChecker.letsPlotKotlinAPIVersion)
    }
}