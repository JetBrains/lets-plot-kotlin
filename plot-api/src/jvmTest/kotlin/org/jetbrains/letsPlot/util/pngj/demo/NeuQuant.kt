/*
 * Copyright (c) 2022. JetBrains s.r.o. 
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("unused")
package org.jetbrains.letsPlot.util.pngj.demo

import org.jetbrains.letsPlot.util.pngj.ImageInfo
import org.jetbrains.letsPlot.util.pngj.ImageLineByte
import org.jetbrains.letsPlot.util.pngj.PngReader
import kotlin.math.sqrt

/*
 * NeuQuant Neural-Net Quantization Algorithm ------------------------------------------
 * 
 * Copyright (c) 1994 Anthony Dekker
 * 
 * NEUQUANT Neural-Net quantization algorithm by Anthony Dekker, 1994. See
 * "Kohonen neural networks for optimal colour quantization" in "Network: Computation in Neural Systems" Vol. 5 (1994)
 * pp 351-367. for a discussion of the algorithm. See also http://www.acm.org/~dekker/NEUQUANT.HTML
 * 
 * Any party obtaining a copy of these files from the author, directly or indirectly, is granted, free of charge, a full
 * and unrestricted irrevocable, world-wide, paid up, royalty-free, nonexclusive right and license to deal in this
 * software and documentation files (the "Software"), including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons who receive copies
 * from any such party to do so, with the only requirement being that this copyright notice remain intact.
 */ /**
 * Modified for PngReader - no special colours - sequential read
 *
 * @author Hernan J Gonzalez
 */
class NeuQuant(private val width: Int, private val height: Int, private val pixelGetter: PixelGetter) {
    interface PixelGetter {
        // 3 ints if not alpha, 4 if alpha
        fun getPixel(row: Int, col: Int): IntArray
    }

    // parameters - do not change during running - naming convention:
    // parNcolors ... parameters setteable with set()
    // _parMmaxnetpos ... derived parameter, not seteable, computed at init()
    private var parNcolors = 256 // number of colours used
    private var parNcycles = 330 // no. of learning cycles
    private var _parCutnetsize = 0 // = ncolors;//
    private var _parMaxnetpos = 0 // = ncolors - 1;
    private var _parInitrad = 0 // = ncolors / 8; // for 256 cols, radius starts at 32

    private var parRadiusbiasshift = 6
    private var _parRadiusbias = 0 // = 1 << radiusbiasshift;
    private var _parInitBiasRadius = 0 // = initrad * radiusbias;
    private var parRadiusdec = 30 // factor of 1/30 each cycle
    private var parTransparencyThreshold = 127
    private var parAlphabiasshift = 10 // alpha starts at 1
    private var _parIinitalpha = 0 // = 1 << alphabiasshift; // biased by 10 bits
    private var parGamma = 1024.0
    private var parBeta = 1.0 / 1024.0
    private var _parGammaBetta = 0.0 // = beta * gamma;

    var isParReserveAlphaColor = false
    private var parMaxPixelsToSample = 30000
    private var _parSamplefac = 0 // 1-30
    private lateinit var network: Array<DoubleArray> // the network itself //WARNING: BGR
    private lateinit var colormap: Array<IntArray> // the network itself //WARNING: BGR
    private val netindex = IntArray(256) // for network lookup - really 256
    private lateinit var bias: DoubleArray // = new double[parNcolors]; // bias and freq arrays for learning
    private lateinit var freq: DoubleArray // = new double[parNcolors];
    private var done = false

    // includes transparent color if applicable
    val colorCount: Int
        get() { // includes transparent color if applicable
            if (!done) run()
            return if (isParReserveAlphaColor) parNcolors + 1 else parNcolors
        }

    // -1 if not exists
    val transparentIndex: Int
        get() { // -1 if not exists
            if (!done) run()
            return if (isParReserveAlphaColor) 0 else -1
        }

    fun lookup(r: Int, g: Int, b: Int, a: Int): Int {
        if (!done) run()
        if (isParReserveAlphaColor && a < parTransparencyThreshold) return 0 // extra entry: transparent
        val i = inxsearch(b, g, r)
        return if (isParReserveAlphaColor) i + 1 else i
    }

    fun lookup(r: Int, g: Int, b: Int): Int {
        if (!done) run()
        val i = inxsearch(b, g, r)
        return if (isParReserveAlphaColor) i + 1 else i
    }

    fun getColor(i: Int): IntArray? {
        @Suppress("NAME_SHADOWING")
        var i = i
        if (!done) run()
        if (isParReserveAlphaColor) {
            i--
            if (i < 0) return intArrayOf(0, 0, 0, 0)
        }
        if (i < 0 || i >= parNcolors) return null
        val bb = colormap[i][0]
        val gg = colormap[i][1]
        val rr = colormap[i][2]
        return intArrayOf(rr, gg, bb, 255)
    }

    fun convert(r: Int, g: Int, b: Int, a: Int): IntArray {
        if (!done) run()
        if (isParReserveAlphaColor && a < parTransparencyThreshold) return intArrayOf(0, 0, 0, 0)
        val i = inxsearch(b, g, r)
        val bb = colormap[i][0]
        val gg = colormap[i][1]
        val rr = colormap[i][2]
        return intArrayOf(rr, gg, bb)
    }

    fun convert(r: Int, g: Int, b: Int): IntArray {
        if (!done) run()
        val i = inxsearch(b, g, r)
        val bb = colormap[i][0]
        val gg = colormap[i][1]
        val rr = colormap[i][2]
        return intArrayOf(rr, gg, bb)
    }

    fun run() {
        if (done) return
        initParams()
        setUpArrays()
        learn()
        fix()
        inxbuild()
        done = true
    }

    private fun initParams() {
        if (isParReserveAlphaColor && parNcolors % 2 == 0) parNcolors--
        _parGammaBetta = parBeta * parGamma
        _parCutnetsize = parNcolors //
        _parMaxnetpos = parNcolors - 1
        _parInitrad = (parNcolors + 7) / 8 // for 256 cols, radius starts at 32
        _parRadiusbias = 1 shl parRadiusbiasshift
        _parInitBiasRadius = _parInitrad * _parRadiusbias
        _parIinitalpha = 1 shl parAlphabiasshift // biased by 10 bits
        _parSamplefac = width * height / parMaxPixelsToSample
        if (_parSamplefac < 1) _parSamplefac = 1 else if (_parSamplefac > 30) _parSamplefac = 30
    }

    private fun setUpArrays() {
        network = Array(parNcolors) { DoubleArray(3) } // the network itself //WARNING: BGR
        colormap = Array(parNcolors) { IntArray(4) } // the network itself //WARNING: BGR
        bias = DoubleArray(parNcolors)
        freq = DoubleArray(parNcolors)
        network[0][0] = 0.0 // black
        network[0][1] = 0.0
        network[0][2] = 0.0
        network[1][0] = 255.0 // white
        network[1][1] = 255.0
        network[1][2] = 255.0
        for (i in 0 until parNcolors) {
            val p = network[i]
            p[0] = 255.0 * i / _parCutnetsize
            p[1] = 255.0 * i / _parCutnetsize
            p[2] = 255.0 * i / _parCutnetsize
            freq[i] = 1.0 / parNcolors
            bias[i] = 0.0
        }
    }

    private fun altersingle(alpha: Double, i: Int, b: Double, g: Double, r: Double) {
        // Move neuron i towards biased (b,g,r) by factor alpha
        val n = network[i] // alter hit neuron
        n[0] -= alpha * (n[0] - b)
        n[1] -= alpha * (n[1] - g)
        n[2] -= alpha * (n[2] - r)
    }

    private fun alterneigh(alpha: Double, rad: Int, i: Int, b: Double, g: Double, r: Double) {
        var lo = i - rad
        if (lo < -1) lo = -1
        var hi = i + rad
        if (hi > parNcolors) hi = parNcolors
        var j = i + 1
        var k = i - 1
        var q = 0
        while (j < hi || k > lo) {
            val a = alpha * (rad * rad - q * q) / (rad * rad)
            q++
            if (j < hi) {
                val p = network[j]
                p[0] -= a * (p[0] - b)
                p[1] -= a * (p[1] - g)
                p[2] -= a * (p[2] - r)
                j++
            }
            if (k > lo) {
                val p = network[k]
                p[0] -= a * (p[0] - b)
                p[1] -= a * (p[1] - g)
                p[2] -= a * (p[2] - r)
                k--
            }
        }
    }

    private fun contest(b: Double, g: Double, r: Double): Int { // Search for biased BGR values
        // finds closest neuron (min dist) and updates freq
        // finds best neuron (min dist-bias) and returns position
        // for frequently chosen neurons, freq[i] is high and bias[i] is negative
        // bias[i] = gamma*((1/netsize)-freq[i])
        var bestd = Float.MAX_VALUE.toDouble()
        var bestbiasd = bestd
        var bestpos = -1
        var bestbiaspos = bestpos
        for (i in 0 until parNcolors) {
            val n = network[i]
            var dist = n[0] - b
            if (dist < 0) dist = -dist
            var a = n[1] - g
            if (a < 0) a = -a
            dist += a
            a = n[2] - r
            if (a < 0) a = -a
            dist += a
            if (dist < bestd) {
                bestd = dist
                bestpos = i
            }
            val biasdist = dist - bias[i]
            if (biasdist < bestbiasd) {
                bestbiasd = biasdist
                bestbiaspos = i
            }
            freq[i] -= parBeta * freq[i]
            bias[i] += _parGammaBetta * freq[i]
        }
        freq[bestpos] += parBeta
        bias[bestpos] -= _parGammaBetta
        return bestbiaspos
    }

    private fun learn() {
        var biasRadius = _parInitBiasRadius
        val alphadec = 30 + (_parSamplefac - 1) / 3
        val lengthcount = width * height
        var samplepixels = lengthcount / _parSamplefac
        if (samplepixels < 1000) samplepixels = 1000
        if (samplepixels > lengthcount) samplepixels = lengthcount
        var delta = samplepixels / parNcycles
        if (delta < 1) delta = 1
        var alpha = _parIinitalpha
        val stepx = (sqrt((lengthcount / samplepixels).toDouble()) + 0.5).toInt()
        var stepy = (lengthcount / (samplepixels * stepx.toDouble()) + 0.5).toInt()
        if (stepy < 1) stepy = 1
        var rad = biasRadius shr parRadiusbiasshift
        if (rad <= 1) rad = 0

        // System.err.println("beginning 1D learning: samplepixels=" + samplepixels + "
        // rad=" + rad);
        var i = 1
        var row = 0
        while (row < height) {
            var col = 0
            while (col < width) {
                val rgb = pixelGetter.getPixel(row, (col + row) % width)
                val red = rgb[0]
                val green = rgb[1]
                val blue = rgb[2]
                val aaa = if (rgb.size == 3) 255 else rgb[3] // transparency
                if (aaa < parTransparencyThreshold) {
                    col += stepx
                    continue
                }
                val b = blue.toDouble()
                val g = green.toDouble()
                val r = red.toDouble()
                val j = contest(b, g, r)
                val a = 1.0 * alpha / _parIinitalpha
                altersingle(a, j, b, g, r)
                if (rad > 0) alterneigh(a, rad, j, b, g, r) // alter neighbours
                i++
                if (i % delta == 0) {
                    alpha -= alpha / alphadec
                    biasRadius -= biasRadius / parRadiusdec
                    rad = biasRadius shr parRadiusbiasshift
                    if (rad <= 1) rad = 0
                }
                col += stepx
            }
            row += stepy
        }
        // System.err.println("finished 1D learning: final alpha=" + (1.0 * alpha) /
        // initalpha + "!");
    }

    private fun fix() {
        for (i in 0 until parNcolors) {
            for (j in 0..2) {
                var x = (0.5 + network[i][j]).toInt()
                if (x < 0) x = 0
                if (x > 255) x = 255
                colormap[i][j] = x
            }
            colormap[i][3] = i
        }
    }

    private fun inxbuild() {
        // Insertion sort of network and building of netindex[0..255]
        var previouscol = 0
        var startpos = 0
        for (i in 0 until parNcolors) {
            val p = colormap[i]
            var q: IntArray?
            var smallpos = i
            var smallval = p[1] // index on g
            // find smallest in i..netsize-1
            for (j in i + 1 until parNcolors) {
                q = colormap[j]
                if (q[1] < smallval) { // index on g
                    smallpos = j
                    smallval = q[1] // index on g
                }
            }
            q = colormap[smallpos]
            // swap p (i) and q (smallpos) entries
            if (i != smallpos) {
                var j = q[0]
                q[0] = p[0]
                p[0] = j
                j = q[1]
                q[1] = p[1]
                p[1] = j
                j = q[2]
                q[2] = p[2]
                p[2] = j
                j = q[3]
                q[3] = p[3]
                p[3] = j
            }
            // smallval entry is now in position i
            if (smallval != previouscol) {
                netindex[previouscol] = startpos + i shr 1
                for (j in previouscol + 1 until smallval) netindex[j] = i
                previouscol = smallval
                startpos = i
            }
        }
        netindex[previouscol] = startpos + _parMaxnetpos shr 1
        for (j in previouscol + 1..255) netindex[j] = _parMaxnetpos // really 256
    }

    private fun inxsearch(b: Int, g: Int, r: Int): Int {
        // Search for BGR values 0..255 and return colour index
        var bestd = 1000 // biggest possible dist is 256*3
        var best = -1
        var i = netindex[g] // index on g
        var j = i - 1 // start at netindex[g] and work outwards
        while (i < parNcolors || j >= 0) {
            if (i < parNcolors) {
                val p = colormap[i]
                var dist = p[1] - g // inx key
                if (dist >= bestd) i = parNcolors // stop iter
                else {
                    if (dist < 0) dist = -dist
                    var a = p[0] - b
                    if (a < 0) a = -a
                    dist += a
                    if (dist < bestd) {
                        a = p[2] - r
                        if (a < 0) a = -a
                        dist += a
                        if (dist < bestd) {
                            bestd = dist
                            best = i
                        }
                    }
                    i++
                }
            }
            if (j >= 0) {
                val p = colormap[j]
                var dist = g - p[1] // inx key - reverse dif
                if (dist >= bestd) j = -1 // stop iter
                else {
                    if (dist < 0) dist = -dist
                    var a = p[0] - b
                    if (a < 0) a = -a
                    dist += a
                    if (dist < bestd) {
                        a = p[2] - r
                        if (a < 0) a = -a
                        dist += a
                        if (dist < bestd) {
                            bestd = dist
                            best = j
                        }
                    }
                    j--
                }
            }
        }
        return best
    }

    fun setParNcolors(parNcolors: Int) {
        this.parNcolors = parNcolors
    }

    fun setParNcycles(parNcycles: Int) {
        this.parNcycles = parNcycles
    }

    fun setParRadiusbiasshift(parRadiusbiasshift: Int) {
        this.parRadiusbiasshift = parRadiusbiasshift
    }

    fun setParRadiusdec(parRadiusdec: Int) {
        this.parRadiusdec = parRadiusdec
    }

    fun setParTransparencyThreshold(parTransparencyThreshold: Int) {
        this.parTransparencyThreshold = parTransparencyThreshold
    }

    fun setParAlphabiasshift(parAlphabiasshift: Int) {
        this.parAlphabiasshift = parAlphabiasshift
    }

    fun setParGamma(parGamma: Double) {
        this.parGamma = parGamma
    }

    fun setParBeta(parBeta: Double) {
        this.parBeta = parBeta
    }

    fun setParMaxPixelsToSample(parMaxPixelsToSample: Int) {
        this.parMaxPixelsToSample = parMaxPixelsToSample
    }

    companion object {
        @JvmStatic
        internal fun createPixelGetterFromPngReader(png: PngReader): PixelGetter {
            if (png.imgInfo!!.indexed || png.imgInfo!!.greyscale || png.imgInfo!!.bitDepth < 8) throw RuntimeException("Bad image type " + png.imgInfo)
            return object : PixelGetter {
                private var imi: ImageInfo? = null

                init {
                    imi = png.imgInfo
                }

                override fun getPixel(row: Int, col: Int): IntArray {
                    val line = (png.readRow(row) as ImageLineByte).scanline
                    val off = col * imi!!.channels
                    return if (imi!!.alpha) intArrayOf(
                        line[off].toInt() and 0xFF, line[off + 1].toInt() and 0xFF, line[off + 2].toInt() and 0xFF,
                        line[off + 3].toInt() and 0xFF
                    ) else intArrayOf(
                        line[off].toInt() and 0xFF,
                        line[off + 1].toInt() and 0xFF,
                        line[off + 2].toInt() and 0xFF
                    )
                }
            }
        }
    }
}