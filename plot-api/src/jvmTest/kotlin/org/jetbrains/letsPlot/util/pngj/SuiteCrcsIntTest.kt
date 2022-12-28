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
class SuiteCrcsIntTest : PngjTest() {
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
        val png = PngReader(istream(f))
        val crc = CRC32()
        for (i in 0 until png.imgInfo!!.rows) {
            val line = png.readRow(i) as ImageLineInt
            for (j in 0 until line.size) {
                var x = line.getElem(j)
                crc.update(x)
                x = x shr 8
                crc.update(x)
            }
        }
        png.end()
        return crc.value
    }

    private fun init() {
        crcs = LinkedHashMap()
        // these were computed with old PNJG
        crcs!!["basi0g01.png"] = java.lang.Long.valueOf(385240647L)
        crcs!!["basi0g02.png"] = java.lang.Long.valueOf(117051011L)
        crcs!!["basi0g04.png"] = java.lang.Long.valueOf(3411899179L)
        crcs!!["basi0g08.png"] = java.lang.Long.valueOf(1558158563L)
        crcs!!["basi0g16.png"] = java.lang.Long.valueOf(716263731L)
        crcs!!["basi2c08.png"] = java.lang.Long.valueOf(2378745398L)
        crcs!!["basi2c16.png"] = java.lang.Long.valueOf(2395397753L)
        crcs!!["basi3p01.png"] = java.lang.Long.valueOf(685967487L)
        crcs!!["basi3p02.png"] = java.lang.Long.valueOf(2980053571L)
        crcs!!["basi3p04.png"] = java.lang.Long.valueOf(42091252L)
        crcs!!["basi3p08.png"] = java.lang.Long.valueOf(4147469058L)
        crcs!!["basi4a08.png"] = java.lang.Long.valueOf(3749889517L)
        crcs!!["basi4a16.png"] = java.lang.Long.valueOf(1310642363L)
        crcs!!["basi6a08.png"] = java.lang.Long.valueOf(2530735596L)
        crcs!!["basi6a16.png"] = java.lang.Long.valueOf(142337329L)
        crcs!!["basn0g01.png"] = java.lang.Long.valueOf(385240647L)
        crcs!!["basn0g02.png"] = java.lang.Long.valueOf(117051011L)
        crcs!!["basn0g04.png"] = java.lang.Long.valueOf(3411899179L)
        crcs!!["basn0g08.png"] = java.lang.Long.valueOf(1558158563L)
        crcs!!["basn0g16.png"] = java.lang.Long.valueOf(716263731L)
        crcs!!["basn2c08.png"] = java.lang.Long.valueOf(2378745398L)
        crcs!!["basn2c16.png"] = java.lang.Long.valueOf(2395397753L)
        crcs!!["basn3p01.png"] = java.lang.Long.valueOf(685967487L)
        crcs!!["basn3p02.png"] = java.lang.Long.valueOf(2980053571L)
        crcs!!["basn3p04.png"] = java.lang.Long.valueOf(42091252L)
        crcs!!["basn3p08.png"] = java.lang.Long.valueOf(4147469058L)
        crcs!!["basn4a08.png"] = java.lang.Long.valueOf(3749889517L)
        crcs!!["basn4a16.png"] = java.lang.Long.valueOf(1310642363L)
        crcs!!["basn6a08.png"] = java.lang.Long.valueOf(2530735596L)
        crcs!!["basn6a16.png"] = java.lang.Long.valueOf(142337329L)
        crcs!!["bgai4a08.png"] = java.lang.Long.valueOf(3749889517L)
        crcs!!["bgai4a16.png"] = java.lang.Long.valueOf(1310642363L)
        crcs!!["bgan6a08.png"] = java.lang.Long.valueOf(2530735596L)
        crcs!!["bgan6a16.png"] = java.lang.Long.valueOf(142337329L)
        crcs!!["bgbn4a08.png"] = java.lang.Long.valueOf(3749889517L)
        crcs!!["bggn4a16.png"] = java.lang.Long.valueOf(1310642363L)
        crcs!!["bgwn6a08.png"] = java.lang.Long.valueOf(2530735596L)
        crcs!!["bgyn6a16.png"] = java.lang.Long.valueOf(142337329L)
        crcs!!["ccwn2c08.png"] = java.lang.Long.valueOf(2749952221L)
        crcs!!["ccwn3p08.png"] = java.lang.Long.valueOf(3232422439L)
        crcs!!["cdfn2c08.png"] = java.lang.Long.valueOf(1004784475L)
        crcs!!["cdhn2c08.png"] = java.lang.Long.valueOf(3767596225L)
        crcs!!["cdsn2c08.png"] = java.lang.Long.valueOf(1165166485L)
        crcs!!["cdun2c08.png"] = java.lang.Long.valueOf(1358040891L)
        crcs!!["ch1n3p04.png"] = java.lang.Long.valueOf(42091252L)
        crcs!!["ch2n3p08.png"] = java.lang.Long.valueOf(4147469058L)
        crcs!!["cm0n0g04.png"] = java.lang.Long.valueOf(239744072L)
        crcs!!["cm7n0g04.png"] = java.lang.Long.valueOf(239744072L)
        crcs!!["cm9n0g04.png"] = java.lang.Long.valueOf(239744072L)
        crcs!!["cs3n2c16.png"] = java.lang.Long.valueOf(2031730372L)
        crcs!!["cs3n3p08.png"] = java.lang.Long.valueOf(2603498434L)
        crcs!!["cs5n2c08.png"] = java.lang.Long.valueOf(3591461903L)
        crcs!!["cs5n3p08.png"] = java.lang.Long.valueOf(4185835007L)
        crcs!!["cs8n2c08.png"] = java.lang.Long.valueOf(127361955L)
        crcs!!["cs8n3p08.png"] = java.lang.Long.valueOf(1899206989L)
        crcs!!["ct0n0g04.png"] = java.lang.Long.valueOf(239744072L)
        crcs!!["ct1n0g04.png"] = java.lang.Long.valueOf(239744072L)
        crcs!!["cten0g04.png"] = java.lang.Long.valueOf(2485959390L)
        crcs!!["ctfn0g04.png"] = java.lang.Long.valueOf(2144806951L)
        crcs!!["ctgn0g04.png"] = java.lang.Long.valueOf(4145660843L)
        crcs!!["cthn0g04.png"] = java.lang.Long.valueOf(735065130L)
        crcs!!["ctjn0g04.png"] = java.lang.Long.valueOf(4127411432L)
        crcs!!["ctzn0g04.png"] = java.lang.Long.valueOf(239744072L)
        crcs!!["f00n0g08.png"] = java.lang.Long.valueOf(2120354897L)
        crcs!!["f00n2c08.png"] = java.lang.Long.valueOf(388769213L)
        crcs!!["f01n0g08.png"] = java.lang.Long.valueOf(4095017386L)
        crcs!!["f01n2c08.png"] = java.lang.Long.valueOf(3908657445L)
        crcs!!["f02n0g08.png"] = java.lang.Long.valueOf(3465606629L)
        crcs!!["f02n2c08.png"] = java.lang.Long.valueOf(2103042764L)
        crcs!!["f03n0g08.png"] = java.lang.Long.valueOf(2044373410L)
        crcs!!["f03n2c08.png"] = java.lang.Long.valueOf(2127122668L)
        crcs!!["f04n0g08.png"] = java.lang.Long.valueOf(1878881775L)
        crcs!!["f04n2c08.png"] = java.lang.Long.valueOf(789320001L)
        crcs!!["f99n0g04.png"] = java.lang.Long.valueOf(149645259L)
        crcs!!["g03n0g16.png"] = java.lang.Long.valueOf(473707466L)
        crcs!!["g03n2c08.png"] = java.lang.Long.valueOf(4133284128L)
        crcs!!["g03n3p04.png"] = java.lang.Long.valueOf(1158497212L)
        crcs!!["g04n0g16.png"] = java.lang.Long.valueOf(1061496161L)
        crcs!!["g04n2c08.png"] = java.lang.Long.valueOf(3887021429L)
        crcs!!["g04n3p04.png"] = java.lang.Long.valueOf(1576095991L)
        crcs!!["g05n0g16.png"] = java.lang.Long.valueOf(3735522154L)
        crcs!!["g05n2c08.png"] = java.lang.Long.valueOf(3152630461L)
        crcs!!["g05n3p04.png"] = java.lang.Long.valueOf(907804064L)
        crcs!!["g07n0g16.png"] = java.lang.Long.valueOf(528485053L)
        crcs!!["g07n2c08.png"] = java.lang.Long.valueOf(1176251103L)
        crcs!!["g07n3p04.png"] = java.lang.Long.valueOf(800464339L)
        crcs!!["g10n0g16.png"] = java.lang.Long.valueOf(3347570312L)
        crcs!!["g10n2c08.png"] = java.lang.Long.valueOf(115288574L)
        crcs!!["g10n3p04.png"] = java.lang.Long.valueOf(3822507246L)
        crcs!!["g25n0g16.png"] = java.lang.Long.valueOf(1442058880L)
        crcs!!["g25n2c08.png"] = java.lang.Long.valueOf(3404091835L)
        crcs!!["g25n3p04.png"] = java.lang.Long.valueOf(3766988542L)
        crcs!!["oi1n0g16.png"] = java.lang.Long.valueOf(716263731L)
        crcs!!["oi1n2c16.png"] = java.lang.Long.valueOf(2395397753L)
        crcs!!["oi2n0g16.png"] = java.lang.Long.valueOf(716263731L)
        crcs!!["oi2n2c16.png"] = java.lang.Long.valueOf(2395397753L)
        crcs!!["oi4n0g16.png"] = java.lang.Long.valueOf(716263731L)
        crcs!!["oi4n2c16.png"] = java.lang.Long.valueOf(2395397753L)
        crcs!!["oi9n0g16.png"] = java.lang.Long.valueOf(716263731L)
        crcs!!["oi9n2c16.png"] = java.lang.Long.valueOf(2395397753L)
        crcs!!["PngSuite.png"] = java.lang.Long.valueOf(3046406988L)
        crcs!!["pp0n2c16.png"] = java.lang.Long.valueOf(2395397753L)
        crcs!!["pp0n6a08.png"] = java.lang.Long.valueOf(29814635L)
        crcs!!["ps1n0g08.png"] = java.lang.Long.valueOf(1558158563L)
        crcs!!["ps1n2c16.png"] = java.lang.Long.valueOf(2395397753L)
        crcs!!["ps2n0g08.png"] = java.lang.Long.valueOf(1558158563L)
        crcs!!["ps2n2c16.png"] = java.lang.Long.valueOf(2395397753L)
        crcs!!["s01i3p01.png"] = java.lang.Long.valueOf(1104745215L)
        crcs!!["s01n3p01.png"] = java.lang.Long.valueOf(1104745215L)
        crcs!!["s02i3p01.png"] = java.lang.Long.valueOf(1696784233L)
        crcs!!["s02n3p01.png"] = java.lang.Long.valueOf(1696784233L)
        crcs!!["s03i3p01.png"] = java.lang.Long.valueOf(2295964787L)
        crcs!!["s03n3p01.png"] = java.lang.Long.valueOf(2295964787L)
        crcs!!["s04i3p01.png"] = java.lang.Long.valueOf(3147056371L)
        crcs!!["s04n3p01.png"] = java.lang.Long.valueOf(3147056371L)
        crcs!!["s05i3p02.png"] = java.lang.Long.valueOf(3147295260L)
        crcs!!["s05n3p02.png"] = java.lang.Long.valueOf(3147295260L)
        crcs!!["s06i3p02.png"] = java.lang.Long.valueOf(1832758033L)
        crcs!!["s06n3p02.png"] = java.lang.Long.valueOf(1832758033L)
        crcs!!["s07i3p02.png"] = java.lang.Long.valueOf(2391917463L)
        crcs!!["s07n3p02.png"] = java.lang.Long.valueOf(2391917463L)
        crcs!!["s08i3p02.png"] = java.lang.Long.valueOf(1940116363L)
        crcs!!["s08n3p02.png"] = java.lang.Long.valueOf(1940116363L)
        crcs!!["s09i3p02.png"] = java.lang.Long.valueOf(641071288L)
        crcs!!["s09n3p02.png"] = java.lang.Long.valueOf(641071288L)
        crcs!!["s32i3p04.png"] = java.lang.Long.valueOf(1178170563L)
        crcs!!["s32n3p04.png"] = java.lang.Long.valueOf(1178170563L)
        crcs!!["s33i3p04.png"] = java.lang.Long.valueOf(2797147378L)
        crcs!!["s33n3p04.png"] = java.lang.Long.valueOf(2797147378L)
        crcs!!["s34i3p04.png"] = java.lang.Long.valueOf(549270401L)
        crcs!!["s34n3p04.png"] = java.lang.Long.valueOf(549270401L)
        crcs!!["s35i3p04.png"] = java.lang.Long.valueOf(2708301864L)
        crcs!!["s35n3p04.png"] = java.lang.Long.valueOf(2708301864L)
        crcs!!["s36i3p04.png"] = java.lang.Long.valueOf(3803983580L)
        crcs!!["s36n3p04.png"] = java.lang.Long.valueOf(3803983580L)
        crcs!!["s37i3p04.png"] = java.lang.Long.valueOf(2384076453L)
        crcs!!["s37n3p04.png"] = java.lang.Long.valueOf(2384076453L)
        crcs!!["s38i3p04.png"] = java.lang.Long.valueOf(2104405411L)
        crcs!!["s38n3p04.png"] = java.lang.Long.valueOf(2104405411L)
        crcs!!["s39i3p04.png"] = java.lang.Long.valueOf(3397979578L)
        crcs!!["s39n3p04.png"] = java.lang.Long.valueOf(3397979578L)
        crcs!!["s40i3p04.png"] = java.lang.Long.valueOf(1512187790L)
        crcs!!["s40n3p04.png"] = java.lang.Long.valueOf(1512187790L)
        crcs!!["tbbn0g04.png"] = java.lang.Long.valueOf(2380849062L)
        crcs!!["tbbn2c16.png"] = java.lang.Long.valueOf(675079740L)
        crcs!!["tbbn3p08.png"] = java.lang.Long.valueOf(3558460409L)
        crcs!!["tbgn2c16.png"] = java.lang.Long.valueOf(675079740L)
        crcs!!["tbgn3p08.png"] = java.lang.Long.valueOf(3558460409L)
        crcs!!["tbrn2c08.png"] = java.lang.Long.valueOf(762241843L)
        crcs!!["tbwn0g16.png"] = java.lang.Long.valueOf(1577244699L)
        crcs!!["tbwn3p08.png"] = java.lang.Long.valueOf(3558460409L)
        crcs!!["tbyn3p08.png"] = java.lang.Long.valueOf(3558460409L)
        crcs!!["tp0n0g08.png"] = java.lang.Long.valueOf(3617951865L)
        crcs!!["tp0n2c08.png"] = java.lang.Long.valueOf(1385374186L)
        crcs!!["tp0n3p08.png"] = java.lang.Long.valueOf(3791776703L)
        crcs!!["tp1n3p08.png"] = java.lang.Long.valueOf(3558460409L)
        crcs!!["z00n2c08.png"] = java.lang.Long.valueOf(2166536248L)
        crcs!!["z03n2c08.png"] = java.lang.Long.valueOf(2166536248L)
        crcs!!["z06n2c08.png"] = java.lang.Long.valueOf(2166536248L)
        crcs!!["z09n2c08.png"] = java.lang.Long.valueOf(2166536248L)
    }

    @Before
    fun setUp() {
        init()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val filename = "basi0g01.png"
            val tc = SuiteCrcsIntTest()
            val res = tc.calcCrc(filename)
            val crc0 = tc.crcs!![filename]!!
            TestCase.assertEquals("bad crc for $filename", res, crc0)
            println("ok")
        }
    }
}