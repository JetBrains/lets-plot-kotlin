/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.bistro.corr

import org.junit.Assert
import org.junit.Test

internal class LayerParamsTest {
    @Test
    fun copyTest() {
        val params = LayerParams()
        Assert.assertEquals(params.copy(), params)
        Assert.assertNotSame(params.copy(), params)
        Assert.assertFalse(params.added)
        Assert.assertFalse(params.copy().added)

        params.type = "full"
        Assert.assertEquals(params.copy(), params)
        Assert.assertNotSame(params.copy(), params)
        Assert.assertTrue(params.added)
        Assert.assertTrue(params.copy().added)

        val copy = params.copy()
        copy.type = "upper"
        params.type = "lower"
        Assert.assertEquals("lower", params.type)
        Assert.assertEquals("upper", copy.type)
    }
}