/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 *
 * This file has been modified by JetBrains : Java code has been converted to Kotlin code.
 * */

package org.jetbrains.letsPlot.util.pngj

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import java.util.*
import java.util.zip.DataFormatException
import java.util.zip.Inflater

/**
 */
class DeflatedChunk2Test : PngjTest() {
    @Test
    @Throws(DataFormatException::class)
    fun inflate() { // just to check that our arrays are ok
        val raw = ByteArray(inflated.size)
        val inf = Inflater()
        inf.setInput(compressed)
        val n: Int = inf.inflate(raw)
        TestCase.assertEquals(inflated.size, n)
        TestCase.assertEquals(raw.contentToString(), inflated.contentToString())
    }

    @Test
    @Throws(Exception::class)
    fun testLowLevel() {
        val rowsize = 3
        val nrows = 2
        var row = -1
        val c = DeflatedChunksSet("XXXX", false, rowsize, rowsize)
        c.processBytes(compressed, 0, 4)
        val sb = StringBuilder()
        while (c.isRowReady) {
            row++
            sb.append(
                (c.state.toString() + " " + c.bytesIn + " "
                        + TestSupport.showRow(c.row!!, c.rowFilled, row)) + ", "
            )
            c.prepareForNextRow(if (row == nrows) 0 else rowsize)
        }
        c.processBytes(compressed, 4, 2)
        while (c.isRowReady) {
            row++
            sb.append(
                (c.state.toString() + " " + c.bytesIn + " "
                        + TestSupport.showRow(c.row!!, c.rowFilled, row)) + ", "
            )
            c.prepareForNextRow(if (row == nrows) 0 else rowsize)
        }
        c.processBytes(compressed, 6, 2)
        while (c.isRowReady) {
            row++
            sb.append(
                (c.state.toString() + " " + c.bytesIn + " "
                        + TestSupport.showRow(c.row!!, c.rowFilled, row)) + ", "
            )
            c.prepareForNextRow(if (row == nrows) 0 else rowsize)
        }
        c.processBytes(compressed, 8, 2)
        while (c.isRowReady) {
            row++
            sb.append(
                (c.state.toString() + " " + c.bytesIn + " "
                        + TestSupport.showRow(c.row!!, c.rowFilled, row)) + ", "
            )
            c.prepareForNextRow(if (row == nrows - 1) 0 else rowsize)
        }
        c.processBytes(compressed, 10, 2)
        while (c.isRowReady) {
            row++
            sb.append(
                (c.state.toString() + " " + c.bytesIn + " "
                        + TestSupport.showRow(c.row!!, c.rowFilled, row)) + ", "
            )
            c.prepareForNextRow(if (row == nrows) 0 else rowsize)
        }
        TestCase.assertEquals("ROW_READY 6 r=0[  0| 42  43], ROW_READY 10 r=1[  0| 44  41], ", sb.toString())
    }

    @Before
    fun setUp() {
    }

    companion object {
        val inflated = byteArrayOf(0, 42, 43, 0, 44, 41)
        val compressed = byteArrayOf(120, -100, 99, -48, -46, 102, -48, -47, 4, 0, 2, 5, 0, -85)
    }
}