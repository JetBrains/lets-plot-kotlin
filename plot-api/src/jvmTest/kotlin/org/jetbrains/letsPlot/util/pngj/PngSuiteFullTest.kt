/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 *
 * This file has been modified by JetBrains : Java code has been converted to Kotlin code.
 * */

package org.jetbrains.letsPlot.util.pngj

import junit.framework.TestCase
import org.jetbrains.letsPlot.util.pngj.TestSupport.istream
import org.jetbrains.letsPlot.util.pngj.TestSupport.ostream
import org.jetbrains.letsPlot.util.pngj.chunks.ChunkCopyBehaviour
import org.jetbrains.letsPlot.util.pngj.chunks.PngChunkPLTE
import org.jetbrains.letsPlot.util.pngj.chunks.PngChunkTRNS
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

/**
 * To test all images in PNG test suite doing a horizontal mirror on all them
 *
 * Instructions: Original images from PNG test suite is supposed to be in local
 * dir resources/testsuite1/ (images supposed to fail, because are erroneous,
 * must start with 'x') Output dir is hardcoded in static "outdir" field - it
 * should be empty After running main, no error should be thrown Errors: 0/141
 * Result images are mirrored, with a 'z' appended to their names, and the
 * originals are laso copied. Suggestion: sort by name, and watch them in
 * sequence
 *
 */
class PngSuiteFullTest {
    private var rand = Random()
    private val clearTempFiles = false // change to false so that the images are not removed, and

    // you can check them visually
    private val imagesToCheck = -1 // -1 to check all

    /**
     * Takes a image, mirrors it using row-per-row int reading, mirror it again
     * using byte (if possible) and compares
     *
     * IF the original was interlaced, compares with origni
     */
    private fun testmirror(orig: File, origni: File, truecolor: File) {
        val mirror = TestSupport.addSuffixToName(orig, "_mirror")
        val recov = TestSupport.addSuffixToName(orig, "_recov")
        var crc0: Long = 0
        var interlaced: Boolean
        var palete: Boolean
        run {
            val pngr = PngReader(istream(orig))
            var pngw: PngWriter? = null
            try {
                if ( /* pngr.imgInfo.bitDepth < 16 && */rand.nextBoolean()) pngr.setLineSetFactory(ImageLineSetDefault.factoryByte)
                palete = pngr.imgInfo!!.indexed
                pngr.prepareSimpleDigestComputation()
                interlaced = pngr.isInterlaced
                pngw = PngWriter(ostream(mirror), pngr.imgInfo!!)
                pngw.setFilterType(FilterType.FILTER_CYCLIC) // just to test all filters
                pngw.copyChunksFrom(pngr.chunksList)
                val lines = pngr.readRows()
                for (row in 0 until pngr.imgInfo!!.rows) {
                    mirrorLine(lines.getImageLine(row), pngr.imgInfo)
                    pngw.writeRow(lines.getImageLine(row))
                }
                pngr.end()
                crc0 = PngHelperInternal.getDigest(pngr)
                pngw.end()
            } finally {
                pngr.close()
                pngw?.close()
            }
        }
        // mirror again (now line by line)
        run {
            val pngr2 = PngReader(istream(mirror))
            var pngw: PngWriter? = null
            try {
                if (pngr2.imgInfo!!.bitDepth < 16 && rand.nextBoolean()) pngr2.setLineSetFactory(ImageLineSetDefault.factoryByte)
                pngw = PngWriter(ostream(recov), pngr2.imgInfo!!)
                pngw!!.setFilterType(FilterType.FILTER_DEFAULT)
                pngw!!.copyChunksFrom(pngr2.chunksList)
                for (row in 0 until pngr2.imgInfo!!.rows) {
                    val line = pngr2.readRow()
                    mirrorLine(line, pngr2.imgInfo)
                    pngw!!.writeRow(line)
                }
                pngr2.end()
                pngw!!.end()
            } finally {
                pngr2.close()
                if (pngw != null) pngw!!.close()
            }
        }
        // now check
        if (!interlaced) TestSupport.testCrcEquals(recov, crc0) else TestSupport.testSameCrc(recov, origni)
        if (interlaced) additionalTestInterlaced(orig, origni)
        if (palete && truecolor.exists()) additionalTestPalette(orig, truecolor)
    }

    private fun additionalTestInterlaced(orig: File, origni: File) {
        // tests also read/write in packed format
        val copy = TestSupport.addSuffixToName(orig, "_icopy")
        val pngr = PngReader(istream(orig))
        val pngw = PngWriter(ostream(copy), pngr.imgInfo!!)
        try {
            pngw.copyChunksFrom(pngr.chunksList)
            val useByte = rand.nextBoolean() && pngr.imgInfo!!.bitDepth < 16
            if (useByte) pngr.setLineSetFactory(ImageLineSetDefault.factoryByte)
            for (row in 0 until pngr.imgInfo!!.rows) {
                val line = pngr.readRow()
                pngw.writeRow(line)
            }
            pngr.end()
            pngw.end()
            TestSupport.testSameCrc(copy, origni)
        } finally {
            pngr.close()
            pngw.close()
        }
        copy.delete()
    }

    /** return number of unexpected errors  */
    private fun testAllSuite(dirsrc: File, dirdest: File, maxfiles: Int): Int {
        if (!dirdest.isDirectory) throw RuntimeException("$dirdest not a directory")
        var cont = 0
        var conterr = 0
        for (im1 in dirsrc.listFiles()!!) {
            val name = im1.name
            if (maxfiles in 1..cont) break
            if (!im1.isFile) continue
            if (!name.endsWith(".png")) continue
            if (name.contains("_ni.png") || name.contains("_tc.png")) continue  // non-interlaced version of interlaced or true color version
            try {
                val orig = File(dirdest, name)
                copyFile(im1, orig)
                cont++
                testmirror(orig, TestSupport.addSuffixToName(im1, "_ni"), TestSupport.addSuffixToName(im1, "_tc"))
                if (name.startsWith("x")) {
                    System.err.println("this should have failed! $name")
                    conterr++
                }
            } catch (e: Exception) {
                if (name.startsWith("x")) { // suppposed to fail
                    println("ok error with " + name + " " + e.message)
                } else { // real error
                    System.err.println("error with " + name + " " + e.message)
                    throw if (e is RuntimeException) e else RuntimeException(e)
                }
            } finally {
            }
        }
        println("Errors: $conterr/$cont images")
        if (conterr == 0) println("=========== SUCCESS ! ================") else println("---- THERE WERE ERRORS!  :-((( ")
        return conterr
    }

    @Test
    fun testAll() {
        val t0 = System.currentTimeMillis()
        val dir = TestSupport.pngTestSuiteDir
        val outdir = TestSupport.tempDir
        println("Lines starting with 'ok error' are expected errors, they are ok.")
        if (clearTempFiles) print("Output files removed, to see them set clearTempFiles=false. ")
        println("Output dir: $outdir")
        val err = testAllSuite(dir, outdir, imagesToCheck)
        TestCase.assertEquals("The suite returner $err unexpected errors", 0, err)
        val t1 = System.currentTimeMillis()
        println("Time: " + (t1 - t0) + " msecs. Java version: " + System.getProperty("java.version"))
    }

    @Before
    fun setUp() {
    }

    /**
     * Tears down the test fixture. (Called after every test case method.)
     */
    @After
    fun tearDown() {
        if (clearTempFiles) {
            TestSupport.cleanAll()
        }
    }

    companion object {
        private fun additionalTestPalette(orig: File, truecolor: File) {
            // covnert to true color 8 bits and check equality
            val pngr = PngReader(istream(orig))
            var pngw: PngWriter? = null
            val copy = TestSupport.addSuffixToName(orig, "_tccopy")
            try {
                val plte: PngChunkPLTE = pngr.getMetadata()!!.pLTE
                val trns: PngChunkTRNS? = pngr.getMetadata()!!.tRNS
                val alpha = trns != null
                val im2 = ImageInfo(pngr.imgInfo!!.cols, pngr.imgInfo!!.rows, 8, alpha)
                pngw = PngWriter(ostream(copy), im2)
                pngw.copyChunksFrom(pngr.chunksList, ChunkCopyBehaviour.COPY_ALL_SAFE)
                var buf: IntArray? = null
                for (row in 0 until pngr.imgInfo!!.rows) {
                    val line = pngr.readRow() as ImageLineInt
                    buf = ImageLineHelper.palette2rgb(line, plte, trns, buf)
                    pngw.writeRowInt(buf)
                }
                pngr.end()
                pngw.end()
                TestSupport.testSameCrc(copy, truecolor)
            } finally {
                pngr.close()
                pngw?.close()
            }
            copy.delete()
        }

        internal fun mirrorLine(imline: IImageLine?, iminfo: ImageInfo?) { // unpacked line
            val channels = iminfo!!.channels
            var imlinei: IntArray? = null
            var imlineb: ByteArray? = null
            var imlineb2: ByteArray? = null
            if (imline is ImageLineInt) { // INT
                imlinei = imline.scanline
            } else if (imline is ImageLineByte) { // BYTE
                imlineb = imline.scanline
                imlineb2 = imline.scanline2 // only for 16bpp
            }
            var c1 = 0
            var c2 = iminfo.cols - 1
            while (c1 < c2) {
                for (i in 0 until channels) {
                    val s1 = c1 * channels + i // sample left
                    val s2 = c2 * channels + i // sample right
                    if (imlinei != null) { // INT
                        val aux = imlinei[s1]
                        imlinei[s1] = imlinei[s2]
                        imlinei[s2] = aux
                    } else {
                        var aux = imlineb!![s1]
                        imlineb[s1] = imlineb[s2]
                        imlineb[s2] = aux
                        if (imlineb2 != null) {
                            aux = imlineb2[s1]
                            imlineb2[s1] = imlineb2[s2]
                            imlineb2[s2] = aux
                        }
                    }
                }
                c1++
                c2--
            }
        }

        private fun copyFile(sourceFile: File, destFile: File) {
            try {
                if (!destFile.exists()) {
                    destFile.createNewFile()
                }
                FileInputStream(sourceFile).use { sx ->
                    sx.channel.use { source ->
                        FileOutputStream(destFile).use { sox ->
                            sox.channel.use { destination ->
                                destination.transferFrom(
                                    source,
                                    0,
                                    source.size()
                                )
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
        }
    }
}