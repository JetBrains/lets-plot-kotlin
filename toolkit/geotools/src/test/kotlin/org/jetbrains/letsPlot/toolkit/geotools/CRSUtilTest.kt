/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.toolkit.geotools

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.geotools.referencing.crs.DefaultGeographicCRS
import org.geotools.referencing.cs.DefaultCartesianCS
import org.junit.Test


internal class CRSUtilTest {
    @Test
    fun crsCode() {
        assertTrue(CRSUtil.isWGS84Code("GCS_WGS_1984"))
        assertTrue(CRSUtil.isWGS84Code("WGS84(DD)"))
        assertTrue(CRSUtil.isWGS84Code("WGS84"))
        assertTrue(CRSUtil.isWGS84Code("WGS 84"))
    }

    @Test
    fun crs() {
        assertTrue(CRSUtil.isWGS84_2D(DefaultGeographicCRS.WGS84))
        assertFalse(CRSUtil.isWGS84_2D(DefaultGeographicCRS.WGS84_3D))
        assertFalse(CRSUtil.isWGS84_2D(DefaultCartesianCS.PROJECTED))
    }
}