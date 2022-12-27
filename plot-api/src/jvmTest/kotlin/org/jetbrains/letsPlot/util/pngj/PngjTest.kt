/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.util.pngj

import org.junit.After
import org.junit.BeforeClass

open class PngjTest {
    /** change to false if you want to inspect the temporary files  */
    private var clearTempFiles = true
    @After
    fun tearDown() {
        if (clearTempFiles) {
            TestSupport.cleanAll()
        }
    }

    companion object {
        @JvmStatic
        @BeforeClass
        fun setup(): Unit {
        }
    }
}