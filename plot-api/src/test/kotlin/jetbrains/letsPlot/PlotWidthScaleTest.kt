/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.intern.toSpec
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import org.junit.Test

class PlotWidthScaleTest {

    @Test
    fun `plot without widthScale`() {
        val spec = Plot().toSpec()
        assertFalse(spec.containsKey("widthScale"))
    }

    @Test
    fun `plot with widthScale`() {
        val spec = Plot(widthScale = 67).toSpec()
        assertEquals(67, spec["widthScale"])
    }
}