/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern

import org.junit.Assert.assertEquals
import org.junit.Test


class OptionsTest {
    @Test
    fun `left hand is overridden by right hand`() {
        val left = Options.of("a" to "A", "b" to "B-L")
        val right = Options.of("b" to "B-R", "c" to "C")

        val result = left + right
        assertEquals("A", result.get("a"))
        assertEquals("B-R", result.get("b"))
        assertEquals("C", result.get("c"))
    }
}