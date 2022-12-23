package org.jetbrains.letsPlot.intern.pngj

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
        @BeforeClass
        fun setup() {
        }
    }
}