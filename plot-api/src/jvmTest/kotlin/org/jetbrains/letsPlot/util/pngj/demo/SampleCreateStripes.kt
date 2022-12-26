/*
 * Copyright (c) 2022. JetBrains s.r.o. 
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.util.pngj.demo

import jetbrains.datalore.base.datetime.Date
import jetbrains.datalore.base.datetime.DateTime
import jetbrains.datalore.base.datetime.Month
import jetbrains.datalore.base.datetime.Time
import org.jetbrains.letsPlot.util.pngj.ImageInfo
import org.jetbrains.letsPlot.util.pngj.ImageLineHelper
import org.jetbrains.letsPlot.util.pngj.ImageLineInt
import org.jetbrains.letsPlot.util.pngj.PngWriter
import org.jetbrains.letsPlot.util.pngj.TestSupport.ostream
import org.jetbrains.letsPlot.util.pngj.chunks.PngChunkTextVar
import java.io.File
import java.time.LocalDateTime
import kotlin.math.sin
import kotlin.system.exitProcess

/**
 * grayscale image - distorted diagonal stripes
 */
object SampleCreateStripes {
    private fun makeTestImage(png: PngWriter) {
        val cols = png.imgInfo.cols
        val rows = png.imgInfo.rows
        png.getMetadata().setDpi(123.0)
        png.getMetadata().setTimeNow(
            LocalDateTime.now().let {
                DateTime(
                    Date(it.dayOfMonth, Month.values()[it.month.ordinal], it.year),
                    Time(it.hour, it.minute, it.second)
                )
            }
        )
        png.getMetadata().setText(PngChunkTextVar.KEY_Software, "pngj test")
        val t1 = (cols + rows) / 16.0 // typical period
        val iline = ImageLineInt(png.imgInfo)
        png.setIdatMaxSize(2000)
        for (i in 0 until rows) {
            val fase = sin(1.3 * i / t1)
            for (j in 0 until cols) {
                val sin = sin((i + j) * Math.PI / t1 + fase)
                iline.scanline[j] = ImageLineHelper.clampTo_0_255(((sin + 1) * 127 + 0.5).toInt())
            }
            png.writeRow(iline, i)
        }
        png.end()
    }

    private fun createTest(name: String, cols: Int, rows: Int) {
        val i2 = PngWriter(ostream(File(name)), ImageInfo(cols, rows, 8,
            alpha = false,
            greyscale = true,
            indexed = false
        ))
        makeTestImage(i2)
        println("Done: $name")
    }

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        if (args.size < 3) {
            System.err.println("Arguments: [pngdest] [cols] [rows]")
            exitProcess(1)
        }
        val cols = args[1].toInt()
        val rows = args[2].toInt()
        createTest(args[0], cols, rows)
    }
}
