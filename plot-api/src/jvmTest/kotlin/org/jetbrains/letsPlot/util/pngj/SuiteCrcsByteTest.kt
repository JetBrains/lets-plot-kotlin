/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 *
 * This file has been modified by JetBrains : Java code has been converted to Kotlin code.
 * */

package org.jetbrains.letsPlot.util.pngj

import junit.framework.TestCase
import org.jetbrains.letsPlot.util.pngj.TestSupport.istream
import org.jetbrains.letsPlot.util.pngj.TestSupport.pngTestSuiteDir
import org.junit.Before
import org.junit.Test
import java.io.File
import java.util.zip.CRC32

/**
 * Reads all (valid) PNG images from the test suite, loads as INT (unpacked) and
 * computes a CRC of all lines (bytes 0 and 1), comparing with precomputed
 */
class SuiteCrcsByteTest : PngjTest() {
    var crcs // these were computed with old PNJG
            : LinkedHashMap<String, Long>? = null

    init {
        init()
    }

    @Test
    fun testcrcs() {
        val bad = LinkedHashMap<String, Long>()
        var errs = 0
        var ok = 0
        for (file in crcs!!.keys) {
            try {
                val res = calcCrc(file)
                val correct = crcs!![file]!!
                if (res != correct) {
                    errs++
                    bad[file] = res
                    if (errs > 10) break
                } else {
                    ok++
                }
            } catch (e: Exception) {
                println("exception with " + file + ": " + e.message)
            }
        }
        TestCase.assertEquals("bad crcs:$bad", 0, errs)
    }

    private fun calcCrc(file: String): Long {
        val f = File(pngTestSuiteDir, file)
        val png = PngReaderByte(istream(f))
        val crc = CRC32()
        for (i in 0 until png.imgInfo!!.rows) {
            val line = png.readRow(i) as ImageLineByte
            for (j in 0 until line.size) {
                val x = line.scanline[j].toInt()
                crc.update(x)
            }
        }
        png.end()
        return crc.value
    }

    private fun init() {
        crcs = LinkedHashMap()
        crcs!!["basi0g01.png"] = java.lang.Long.valueOf(638439777L)
        crcs!!["basi0g02.png"] = java.lang.Long.valueOf(657548584L)
        crcs!!["basi0g04.png"] = java.lang.Long.valueOf(1464587703L)
        crcs!!["basi0g08.png"] = java.lang.Long.valueOf(2018200142L)
        crcs!!["basi0g16.png"] = java.lang.Long.valueOf(101827364L)
        crcs!!["basi2c08.png"] = java.lang.Long.valueOf(2018884031L)
        crcs!!["basi2c16.png"] = java.lang.Long.valueOf(4291567313L)
        crcs!!["basi3p01.png"] = java.lang.Long.valueOf(4221820031L)
        crcs!!["basi3p02.png"] = java.lang.Long.valueOf(2367188966L)
        crcs!!["basi3p04.png"] = java.lang.Long.valueOf(672045553L)
        crcs!!["basi3p08.png"] = java.lang.Long.valueOf(4142762662L)
        crcs!!["basi4a08.png"] = java.lang.Long.valueOf(2960547948L)
        crcs!!["basi4a16.png"] = java.lang.Long.valueOf(1352835972L)
        crcs!!["basi6a08.png"] = java.lang.Long.valueOf(2806903596L)
        crcs!!["basi6a16.png"] = java.lang.Long.valueOf(677111136L)
        crcs!!["basn0g01.png"] = java.lang.Long.valueOf(638439777L)
        crcs!!["basn0g02.png"] = java.lang.Long.valueOf(657548584L)
        crcs!!["basn0g04.png"] = java.lang.Long.valueOf(1464587703L)
        crcs!!["basn0g08.png"] = java.lang.Long.valueOf(2018200142L)
        crcs!!["basn0g16.png"] = java.lang.Long.valueOf(101827364L)
        crcs!!["basn2c08.png"] = java.lang.Long.valueOf(2018884031L)
        crcs!!["basn2c16.png"] = java.lang.Long.valueOf(4291567313L)
        crcs!!["basn3p01.png"] = java.lang.Long.valueOf(4221820031L)
        crcs!!["basn3p02.png"] = java.lang.Long.valueOf(2367188966L)
        crcs!!["basn3p04.png"] = java.lang.Long.valueOf(672045553L)
        crcs!!["basn3p08.png"] = java.lang.Long.valueOf(4142762662L)
        crcs!!["basn4a08.png"] = java.lang.Long.valueOf(2960547948L)
        crcs!!["basn4a16.png"] = java.lang.Long.valueOf(1352835972L)
        crcs!!["basn6a08.png"] = java.lang.Long.valueOf(2806903596L)
        crcs!!["basn6a16.png"] = java.lang.Long.valueOf(677111136L)
        crcs!!["bgai4a08.png"] = java.lang.Long.valueOf(2960547948L)
        crcs!!["bgai4a16.png"] = java.lang.Long.valueOf(1352835972L)
        crcs!!["bgan6a08.png"] = java.lang.Long.valueOf(2806903596L)
        crcs!!["bgan6a16.png"] = java.lang.Long.valueOf(677111136L)
        crcs!!["bgbn4a08.png"] = java.lang.Long.valueOf(2960547948L)
        crcs!!["bggn4a16.png"] = java.lang.Long.valueOf(1352835972L)
        crcs!!["bgwn6a08.png"] = java.lang.Long.valueOf(2806903596L)
        crcs!!["bgyn6a16.png"] = java.lang.Long.valueOf(677111136L)
        crcs!!["ccwn2c08.png"] = java.lang.Long.valueOf(1639358094L)
        crcs!!["ccwn3p08.png"] = java.lang.Long.valueOf(519007828L)
        crcs!!["cdfn2c08.png"] = java.lang.Long.valueOf(2578399395L)
        crcs!!["cdhn2c08.png"] = java.lang.Long.valueOf(2225401664L)
        crcs!!["cdsn2c08.png"] = java.lang.Long.valueOf(2192731567L)
        crcs!!["cdun2c08.png"] = java.lang.Long.valueOf(3998278602L)
        crcs!!["ch1n3p04.png"] = java.lang.Long.valueOf(672045553L)
        crcs!!["ch2n3p08.png"] = java.lang.Long.valueOf(4142762662L)
        crcs!!["cm0n0g04.png"] = java.lang.Long.valueOf(1865618197L)
        crcs!!["cm7n0g04.png"] = java.lang.Long.valueOf(1865618197L)
        crcs!!["cm9n0g04.png"] = java.lang.Long.valueOf(1865618197L)
        crcs!!["cs3n2c16.png"] = java.lang.Long.valueOf(1929786652L)
        crcs!!["cs3n3p08.png"] = java.lang.Long.valueOf(1361754892L)
        crcs!!["cs5n2c08.png"] = java.lang.Long.valueOf(454480233L)
        crcs!!["cs5n3p08.png"] = java.lang.Long.valueOf(2896231495L)
        crcs!!["cs8n2c08.png"] = java.lang.Long.valueOf(1929786652L)
        crcs!!["cs8n3p08.png"] = java.lang.Long.valueOf(18831189L)
        crcs!!["ct0n0g04.png"] = java.lang.Long.valueOf(1865618197L)
        crcs!!["ct1n0g04.png"] = java.lang.Long.valueOf(1865618197L)
        crcs!!["cten0g04.png"] = java.lang.Long.valueOf(47982921L)
        crcs!!["ctfn0g04.png"] = java.lang.Long.valueOf(263585808L)
        crcs!!["ctgn0g04.png"] = java.lang.Long.valueOf(404114289L)
        crcs!!["cthn0g04.png"] = java.lang.Long.valueOf(2657543270L)
        crcs!!["ctjn0g04.png"] = java.lang.Long.valueOf(2185308513L)
        crcs!!["ctzn0g04.png"] = java.lang.Long.valueOf(1865618197L)
        crcs!!["f00n0g08.png"] = java.lang.Long.valueOf(521676383L)
        crcs!!["f00n2c08.png"] = java.lang.Long.valueOf(1058891437L)
        crcs!!["f01n0g08.png"] = java.lang.Long.valueOf(409477503L)
        crcs!!["f01n2c08.png"] = java.lang.Long.valueOf(297906814L)
        crcs!!["f02n0g08.png"] = java.lang.Long.valueOf(2042218974L)
        crcs!!["f02n2c08.png"] = java.lang.Long.valueOf(2132584325L)
        crcs!!["f03n0g08.png"] = java.lang.Long.valueOf(2742273604L)
        crcs!!["f03n2c08.png"] = java.lang.Long.valueOf(828661129L)
        crcs!!["f04n0g08.png"] = java.lang.Long.valueOf(3087032872L)
        crcs!!["f04n2c08.png"] = java.lang.Long.valueOf(1996843631L)
        crcs!!["f99n0g04.png"] = java.lang.Long.valueOf(31400461L)
        crcs!!["g03n0g16.png"] = java.lang.Long.valueOf(786225710L)
        crcs!!["g03n2c08.png"] = java.lang.Long.valueOf(909312543L)
        crcs!!["g03n3p04.png"] = java.lang.Long.valueOf(1885837076L)
        crcs!!["g04n0g16.png"] = java.lang.Long.valueOf(1657700307L)
        crcs!!["g04n2c08.png"] = java.lang.Long.valueOf(2927775506L)
        crcs!!["g04n3p04.png"] = java.lang.Long.valueOf(3087039771L)
        crcs!!["g05n0g16.png"] = java.lang.Long.valueOf(4267405292L)
        crcs!!["g05n2c08.png"] = java.lang.Long.valueOf(1008110461L)
        crcs!!["g05n3p04.png"] = java.lang.Long.valueOf(971297531L)
        crcs!!["g07n0g16.png"] = java.lang.Long.valueOf(3805335016L)
        crcs!!["g07n2c08.png"] = java.lang.Long.valueOf(1838909555L)
        crcs!!["g07n3p04.png"] = java.lang.Long.valueOf(531282976L)
        crcs!!["g10n0g16.png"] = java.lang.Long.valueOf(1679108422L)
        crcs!!["g10n2c08.png"] = java.lang.Long.valueOf(4195624999L)
        crcs!!["g10n3p04.png"] = java.lang.Long.valueOf(2600520802L)
        crcs!!["g25n0g16.png"] = java.lang.Long.valueOf(925915624L)
        crcs!!["g25n2c08.png"] = java.lang.Long.valueOf(3851501873L)
        crcs!!["g25n3p04.png"] = java.lang.Long.valueOf(1079986293L)
        crcs!!["oi1n0g16.png"] = java.lang.Long.valueOf(101827364L)
        crcs!!["oi1n2c16.png"] = java.lang.Long.valueOf(4291567313L)
        crcs!!["oi2n0g16.png"] = java.lang.Long.valueOf(101827364L)
        crcs!!["oi2n2c16.png"] = java.lang.Long.valueOf(4291567313L)
        crcs!!["oi4n0g16.png"] = java.lang.Long.valueOf(101827364L)
        crcs!!["oi4n2c16.png"] = java.lang.Long.valueOf(4291567313L)
        crcs!!["oi9n0g16.png"] = java.lang.Long.valueOf(101827364L)
        crcs!!["oi9n2c16.png"] = java.lang.Long.valueOf(4291567313L)
        crcs!!["PngSuite.png"] = java.lang.Long.valueOf(4071145679L)
        crcs!!["pp0n2c16.png"] = java.lang.Long.valueOf(4291567313L)
        crcs!!["pp0n6a08.png"] = java.lang.Long.valueOf(249584737L)
        crcs!!["ps1n0g08.png"] = java.lang.Long.valueOf(2018200142L)
        crcs!!["ps1n2c16.png"] = java.lang.Long.valueOf(4291567313L)
        crcs!!["ps2n0g08.png"] = java.lang.Long.valueOf(2018200142L)
        crcs!!["ps2n2c16.png"] = java.lang.Long.valueOf(4291567313L)
        crcs!!["s01i3p01.png"] = java.lang.Long.valueOf(3523407757L)
        crcs!!["s01n3p01.png"] = java.lang.Long.valueOf(3523407757L)
        crcs!!["s02i3p01.png"] = java.lang.Long.valueOf(558161692L)
        crcs!!["s02n3p01.png"] = java.lang.Long.valueOf(558161692L)
        crcs!!["s03i3p01.png"] = java.lang.Long.valueOf(3681107230L)
        crcs!!["s03n3p01.png"] = java.lang.Long.valueOf(3681107230L)
        crcs!!["s04i3p01.png"] = java.lang.Long.valueOf(298271832L)
        crcs!!["s04n3p01.png"] = java.lang.Long.valueOf(298271832L)
        crcs!!["s05i3p02.png"] = java.lang.Long.valueOf(3091650273L)
        crcs!!["s05n3p02.png"] = java.lang.Long.valueOf(3091650273L)
        crcs!!["s06i3p02.png"] = java.lang.Long.valueOf(1027227604L)
        crcs!!["s06n3p02.png"] = java.lang.Long.valueOf(1027227604L)
        crcs!!["s07i3p02.png"] = java.lang.Long.valueOf(316580556L)
        crcs!!["s07n3p02.png"] = java.lang.Long.valueOf(316580556L)
        crcs!!["s08i3p02.png"] = java.lang.Long.valueOf(1723406241L)
        crcs!!["s08n3p02.png"] = java.lang.Long.valueOf(1723406241L)
        crcs!!["s09i3p02.png"] = java.lang.Long.valueOf(947415988L)
        crcs!!["s09n3p02.png"] = java.lang.Long.valueOf(947415988L)
        crcs!!["s32i3p04.png"] = java.lang.Long.valueOf(1290239135L)
        crcs!!["s32n3p04.png"] = java.lang.Long.valueOf(1290239135L)
        crcs!!["s33i3p04.png"] = java.lang.Long.valueOf(1896936080L)
        crcs!!["s33n3p04.png"] = java.lang.Long.valueOf(1896936080L)
        crcs!!["s34i3p04.png"] = java.lang.Long.valueOf(3517918178L)
        crcs!!["s34n3p04.png"] = java.lang.Long.valueOf(3517918178L)
        crcs!!["s35i3p04.png"] = java.lang.Long.valueOf(3585191218L)
        crcs!!["s35n3p04.png"] = java.lang.Long.valueOf(3585191218L)
        crcs!!["s36i3p04.png"] = java.lang.Long.valueOf(3482711170L)
        crcs!!["s36n3p04.png"] = java.lang.Long.valueOf(3482711170L)
        crcs!!["s37i3p04.png"] = java.lang.Long.valueOf(198056743L)
        crcs!!["s37n3p04.png"] = java.lang.Long.valueOf(198056743L)
        crcs!!["s38i3p04.png"] = java.lang.Long.valueOf(2729214834L)
        crcs!!["s38n3p04.png"] = java.lang.Long.valueOf(2729214834L)
        crcs!!["s39i3p04.png"] = java.lang.Long.valueOf(2270821299L)
        crcs!!["s39n3p04.png"] = java.lang.Long.valueOf(2270821299L)
        crcs!!["s40i3p04.png"] = java.lang.Long.valueOf(1834410701L)
        crcs!!["s40n3p04.png"] = java.lang.Long.valueOf(1834410701L)
        crcs!!["tbbn0g04.png"] = java.lang.Long.valueOf(3759719923L)
        crcs!!["tbbn2c16.png"] = java.lang.Long.valueOf(3843674096L)
        crcs!!["tbbn3p08.png"] = java.lang.Long.valueOf(554813364L)
        crcs!!["tbgn2c16.png"] = java.lang.Long.valueOf(3843674096L)
        crcs!!["tbgn3p08.png"] = java.lang.Long.valueOf(554813364L)
        crcs!!["tbrn2c08.png"] = java.lang.Long.valueOf(3843674096L)
        crcs!!["tbwn0g16.png"] = java.lang.Long.valueOf(1535967669L)
        crcs!!["tbwn3p08.png"] = java.lang.Long.valueOf(554813364L)
        crcs!!["tbyn3p08.png"] = java.lang.Long.valueOf(554813364L)
        crcs!!["tp0n0g08.png"] = java.lang.Long.valueOf(3286082606L)
        crcs!!["tp0n2c08.png"] = java.lang.Long.valueOf(3022435152L)
        crcs!!["tp0n3p08.png"] = java.lang.Long.valueOf(3796036490L)
        crcs!!["tp1n3p08.png"] = java.lang.Long.valueOf(554813364L)
        crcs!!["z00n2c08.png"] = java.lang.Long.valueOf(4176991825L)
        crcs!!["z03n2c08.png"] = java.lang.Long.valueOf(4176991825L)
        crcs!!["z06n2c08.png"] = java.lang.Long.valueOf(4176991825L)
        crcs!!["z09n2c08.png"] = java.lang.Long.valueOf(4176991825L)
    }

    @Before
    fun setUp() {
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "basi0g01.png"
            val tc = SuiteCrcsByteTest()
            val res = tc.calcCrc(filename)
            val crc0 = tc.crcs!![filename]!!
            TestCase.assertEquals("bad crc for $filename", res, crc0)
            println("ok")
        }
    }
}