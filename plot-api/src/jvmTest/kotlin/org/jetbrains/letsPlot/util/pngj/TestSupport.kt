/*
 * Copyright (c) 2022. JetBrains s.r.o. 
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("unused")

package org.jetbrains.letsPlot.util.pngj

import junit.framework.TestCase
import org.jetbrains.letsPlot.util.pngj.chunks.ChunkRaw
import org.jetbrains.letsPlot.util.pngj.chunks.ChunksList
import org.jetbrains.letsPlot.util.pngj.chunks.PngChunk
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.net.URISyntaxException
import java.util.*

/**
 * Methods of this class are designed for debug and testing PNGJ library, they
 * are not optimized
 */
object TestSupport {
    // WARNING: showXXXX methods are also for machine consumption
    private var rand = Random()

    init {
        Locale.setDefault(Locale.US)
    }

    /**
     * The resources dir for the tests should include PNG_TEST_STRIPES (and
     * testsuite1) Typically target/tests-classes
     */
    private var resourcesDir: File? = null
        get() {
            if (field == null) {
                val tokenfile = PNG_TEST_STRIPES
                val u = TestSupport::class.java.classLoader.getResource(tokenfile)
                    ?: throw PngjException(
                        PNG_TEST_STRIPES
                                + " not found in classpath, this is required in order to locate the resources dir for the PNGJ tests to run"
                    )
                val f: File = try {
                    File(u.toURI()).parentFile.parentFile
                } catch (e: URISyntaxException) {
                    throw PngjException(e)
                }
                field = f
            }
            return field
        }

    // this files should be accessible from the classpath
    private const val PNG_TEST_STRIPES = "test/stripes.png"
    const val PNG_TEST_STRIPES2 = "test/stripes2.png"
    const val PNG_TEST_TESTG2 = "test/testg2.png"
    const val PNG_TEST_TESTG2I = "test/testg2i.png" // interlaced

    // Correct but tricky: several 0 length idats
    const val PNG_TEST_IDATTRICKY = "test/stripes0idat.png"
    const val PNG_TEST_BADCRC = "test/bad_testg1crc.png"
    const val PNG_TEST_BAD_MISSINGIDAT = "test/bad_missingidat.png"
    const val PNG_TEST_BADTRUNCATED = "test/bad_truncated.png"
    const val PNG_TEST_BADNOEND = "test/bad_testrgb3_noend.png"
    const val PNG_TEST_BAD_TRUNCATEDIDAT = "test/bad_stripes_rgb8_truncatedidat.png"
    const val PNG_TEST_TRAILINGBYES = "bad_stripes_extratrailing.png"
    internal fun showChunks(chunks: List<PngChunk?>): String {
        val sb = StringBuilder()
        for (chunk in chunks) {
            sb.append(showChunk(chunk)).append(" ")
        }
        return sb.toString()
    }

    private fun randBufSize(): Int {
        return if (rand.nextBoolean()) rand.nextInt(8) + 1 else rand.nextInt(30) + 80000 // very small or
        // very big
    }

    @JvmOverloads
    internal fun feedFromStreamTest(`as`: ChunkSeqReader, fs: String, bufferSize: Int = -1) {
        @Suppress("NAME_SHADOWING")
        var bufferSize = bufferSize
        if (bufferSize < 1) bufferSize = randBufSize()
        val bf = BufferedStreamFeeder(fs.toInputPngStream(), bufferSize)
        while (!`as`.isDone) {
            if (!bf.hasPendingBytes()) throw PngjInputException("premature ending")
            if (bf.feed(`as`) <= 0) break
        }
        bf.close()
    }

    private fun showChunk(chunk: PngChunk?): String {
        return if (chunk == null) "null" else (chunk.id + "[" + chunk.len) + "]"
    }

    private fun showChunksRaw(chunks: List<ChunkRaw?>): String {
        val sb = StringBuilder()
        for (chunk in chunks) {
            sb.append(showChunkRaw(chunk)).append(" ")
        }
        return sb.toString()
    }

    private fun showChunkRaw(chunk: ChunkRaw?): String {
        return if (chunk == null) "null" else (chunk.id + "[" + chunk.len) + "]"
    }

    internal fun chunkListToChunksRaw(chunks: List<PngChunk?>): List<ChunkRaw> {
        val cr: MutableList<ChunkRaw> = ArrayList<ChunkRaw>()
        for (c in chunks) cr.add(c!!.raw!!)
        return cr
    }

    /**
     * show detailed filter information (grouped by consecutive rows) eg:
     * PAETH:337(103) means 103 rows of type PAETH starting from row 337
     */
    @JvmOverloads
    fun showFilters(
        pngr: File, maxgroups: Int, usenewlines: Boolean, showSumm: Boolean = false,
        showSummPercent: Boolean = false
    ): String {
        @Suppress("NAME_SHADOWING")
        var maxgroups = maxgroups
        @Suppress("NAME_SHADOWING")
        var usenewlines = usenewlines
        if (maxgroups == 0) usenewlines = false
        if (maxgroups < 0) maxgroups = Int.MAX_VALUE
        val types = IntArray(5)
        val png = PngReaderByte(pngr.toInputPngStream())
        val sb = StringBuilder()
        val ft = IntArray(png.imgInfo!!.rows + 1)
        for (r in 0 until png.imgInfo!!.rows) {
            ft[r] = (png.readRow() as IImageLineArray).filterType.value
            types[ft[r]]++
        }
        png.end()
        ft[png.imgInfo!!.rows] = -1 // dummy end value to ease computation
        if (showSummPercent) {
            for (i in 0..4) {
                types[i] = ((types[i] * 100 + png.imgInfo!!.rows / 2) / png.imgInfo!!.rows)
            }
        }
        if (showSumm || showSummPercent) sb.append(types.contentToString() + if (usenewlines) "\n" else "\t")
        // groups
        if (maxgroups != 0) {
            var contgroups = 0
            var r0 = 0
            for (r in 1 until ft.size) {
                if (ft[r] != ft[r - 1]) { // found group
                    sb.append(java.lang.String.format("%s:%d(%d)", FilterType.getByVal(ft[r - 1]), r0, r - r0))
                        .append(if (usenewlines) "\n" else " ")
                    contgroups++
                    r0 = r
                    if (contgroups >= maxgroups && r < ft.size - 1) {
                        sb.append("...").append(if (usenewlines) "\n" else " ")
                        break
                    }
                }
            }
        }
        return sb.toString().trim { it <= ' ' }.replace("FILTER_".toRegex(), "")
    }

    /**
     * First byte is the filter type, nbytes is the valid content (including filter
     * byte) This shows at most 9 bytes
     */
    @JvmOverloads
    fun showRow(row: ByteArray, nbytes: Int, rown: Int, dx: Int = 1, ox: Int = 0): String {
        val sb = StringBuilder()
        sb.append(String.format("r=%d", rown))
        if (dx != 1 || ox != 0) sb.append(String.format("(dx:%d,ox:%d)", dx, ox))
        var n = nbytes - 1
        if (n > 9) n = 9
        sb.append(if (n == nbytes - 1) String.format("[") else String.format(" b(%d/%d)=[", n, nbytes - 1))
        for (i in 0..n) {
            sb.append(String.format("%3d", row[i].toInt() and 0xff))
            sb.append(if (i == 0) "|" else if (i < n) " " else "")
        }
        return sb.append("]").toString()
    }

    @JvmOverloads
    internal fun showLine(linex: IImageLine, maxtoshow: Int = 9): String {
        val line = linex as IImageLineArray
        val sb = StringBuilder()
        var n = line.size
        if (n > maxtoshow) n = maxtoshow
        sb.append(if (n == line.size) String.format("[") else String.format(" b(%d/%d)=[", n, line.size))
        for (i in 0 until n) {
            sb.append(String.format("%3d", line.getElem(i)))
            sb.append(if (i < n - 1) " " else "")
        }
        return sb.append("]").toString()
    }

    /**
     * the location (if relative) is realtive to the resources dir
     *
     * @param f
     * @return
     */
    internal fun istream(f: String): InputPngStream {
        return File(f).toInputPngStream()
    }

    /**
     * the location (if relative) is realtive to the resources dir
     *
     * @param f
     * @return
     */
    internal fun ostream(f: String): OutputPngStream {
        return ostream(File(f))
    }

    /**
     * the location (if relative) is realtive to the resources dir
     *
     * @param f
     * @return
     */
    internal fun istream(f: File): InputPngStream {
        @Suppress("NAME_SHADOWING")
        var f = f
        return try {
            if (!f.isAbsolute) f = File(resourcesDir, f.path)
            f.toInputPngStream()
        } catch (e: FileNotFoundException) {
            throw RuntimeException(e)
        }
    }

    // if relative, it's assumed to be realtive to resources dir
    private fun absFile(f: File): File {
        @Suppress("NAME_SHADOWING")
        var f = f
        if (!f.isAbsolute) f = File(resourcesDir, f.path)
        return f
    }

    fun absFile(x: String): File {
        var f = File(x)
        if (!f.isAbsolute) f = File(resourcesDir, f.path)
        return f
    }

    /**
     * the location (if relative) is realtive to the resources dir
     *
     * @param f
     * @return
     */
    internal fun ostream(f: File): OutputPngStream {
        @Suppress("NAME_SHADOWING")
        var f = f
        return try {
            if (!f.isAbsolute) f = File(resourcesDir, f.path)
            FileOutputStream(f)
        } catch (e: FileNotFoundException) {
            throw RuntimeException(e)
        }
    }

    fun listPngFromDir(dir: File): List<File> {
        @Suppress("NAME_SHADOWING")
        var dir = dir
        dir = absFile(dir)
        val pngs = ArrayList<File>()
        for (f in dir.list()!!) {
            if (f.lowercase(Locale.getDefault()).endsWith(".png")) pngs.add(File(dir, f))
        }
        return pngs
    }

    val pngTestSuiteDir: File
        get() = File(resourcesDir, "testsuite1")
    val tempDir: File
        get() {
            val t = File(resourcesDir, "test/temp")
            if (!t.isDirectory) throw RuntimeException("missing test resource dir: $t")
            return t
        }

    fun cleanAll() {
        val t = tempDir
        for (x in t.listFiles()!!) {
            if (x.name.endsWith("png")) {
                val ok = x.delete()
                if (!ok) System.err.println("could not remove $x")
            }
        }
    }

    fun testSameCrc(ori: File, dest: File) {
        @Suppress("NAME_SHADOWING")
        var ori = ori
        val oriname = ori.name
        if ("testsuite1" == ori.parentFile.name && oriname.matches(Regex(".*i.....png"))) { // interlaced? change
            // it
            ori = addSuffixToName(ori, "_ni")
        }
        val png1 = PngReader(ori.toInputPngStream())
        PngHelperInternal.initCrcForTests(png1)
        val png2 = PngReader(dest.toInputPngStream())
        PngHelperInternal.initCrcForTests(png2)
        TestCase.assertEquals(
            "Cannot compare, one is interlaced, the other not:", png1.isInterlaced,
            png2.isInterlaced
        )
        TestCase.assertEquals("Image are of different type", png1.imgInfo, png2.imgInfo)
        png1.readRow(png1.imgInfo!!.rows - 1)
        png2.readRow(png2.imgInfo!!.rows - 1)
        png1.end()
        png2.end()
        val crc1 = PngHelperInternal.getDigest(png1)
        val crc2 = PngHelperInternal.getDigest(png2)
        TestCase.assertEquals("different crcs $ori=$crc1 $dest=$crc2", crc1, crc2)
    }

    @JvmOverloads
    fun testSameValues(ori: File, dest: File, tolerance: Int = 0) {
        val png1 = PngReaderInt(ori.toInputPngStream())
        val png2 = PngReaderInt(dest.toInputPngStream())
        TestCase.assertEquals("Image are of different size", png1.imgInfo!!.rows, png2.imgInfo!!.rows)
        TestCase.assertEquals("Image are of different size", png1.imgInfo!!.cols, png2.imgInfo!!.cols)
        var r1 = IntArray(png1.imgInfo!!.cols * 4)
        var r2 = IntArray(png1.imgInfo!!.cols * 4)
        var firstDif = ""
        var difAv = 0.0
        for (r in 0 until png1.imgInfo!!.rows) {
            val line1 = png1.readRowInt()
            val line2 = png2.readRowInt()
            r1 = ImageLineHelper.convert2rgba(line1, png1.getMetadata()!!.pLTE, png1.getMetadata()!!.tRNS, r1)
            r2 = ImageLineHelper.convert2rgba(line2, png2.getMetadata()!!.pLTE, png2.getMetadata()!!.tRNS, r2)
            fixAlpha(r1)
            fixAlpha(r2)
            for (c in r1.indices) {
                var dif1 = r1[c] - r2[c]
                if (dif1 < 0) dif1 = -dif1
                difAv += dif1.toDouble()
                if (dif1 > tolerance) {
                    if (firstDif == "") firstDif = String.format("1st dif at: (%d,%d)", c / 4, r)
                }
            }
        }
        difAv /= png1.imgInfo!!.rows * png1.imgInfo!!.samplesPerRow.toDouble()
        png1.end()
        png2.end()
        TestCase.assertEquals(
            firstDif + " avdif=" + difAv + " f=" + ori.name + " " + " -> " + dest.name, "",
            firstDif
        )
        if (difAv > 0.001) PngHelperInternal.debug(String.format("%s errorav=%f\n", ori.name, difAv))
    }

    private fun fixAlpha(line: IntArray) {
        var c = 3
        while (c <= line.size) {
            if (line[c] == 0) { // transparent
                line[c - 1] = 0
                line[c - 2] = 0
                line[c - 3] = 0
            }
            c += 4
        }
    }

    fun testCrcEquals(image1: File, crc: Long) {
        val png1 = PngReader(image1.toInputPngStream())
        PngHelperInternal.initCrcForTests(png1)
        png1.readRow(png1.imgInfo!!.rows - 1)
        png1.end()
        val crc1 = PngHelperInternal.getDigest(png1)
        TestCase.assertEquals(crc1, crc)
    }

    fun addSuffixToName(orig: File, suffix: String): File {
        var x = orig.path
        x = x.replace("\\.png$".toRegex(), "")
        return File("$x$suffix.png")
    }

    internal fun createNullOutputStream(): NullOutputPngStream {
        return NullOutputPngStream()
    }

    internal fun getChunkById(id: String?, chunks: Collection<PngChunk?>): List<PngChunk> {
        val list: ArrayList<PngChunk> = ArrayList<PngChunk>()
        for (c in chunks) if (c!!.id == id) list.add(c)
        return list
    }

    /** does not include IDAT  */
    internal fun readAllChunks(file: File, includeIdat: Boolean): ChunksList {
        val pngr = PngReader(InputPngStream(file.readBytes()))
        pngr.setChunksToSkip()
        pngr.chunkseq!!.setIncludeNonBufferedChunks(includeIdat)
        pngr.readSkippingAllRows()
        pngr.end()
        return pngr.chunksList
    }

    fun getTmpFile(suffix: String): File {
        return File(tempDir, "temp$suffix.png")
    }

    private fun prepareFileTmp(f: File, imi: ImageInfo): PngWriter {
        return PngWriter(FileOutputStream(f), imi)
    }

    /** creates a simple PNG image 32x32 RGB8 for test  */
    @JvmOverloads
    internal fun prepareFileTmp(f: File, palette: Boolean = false): PngWriter {
        return prepareFileTmp(f, ImageInfo(32, 32, 8, alpha = false, greyscale = false, indexed = palette))
    }

    internal fun generateNoiseLine(imi: ImageInfo?): IImageLine { // byte format!
        val line = ImageLineByte(imi!!)
        val r = Random()
        val scanline = line.scanline
        r.nextBytes(scanline)
        return line
    }

    internal fun endFileTmp(png: PngWriter) { // writes dummy data
        val imline = ImageLineInt(png.imgInfo)
        for (i in 0 until png.imgInfo.rows) png.writeRow(imline)
        png.end()
    }

    private fun getPngsFromDir(dirOrFile: File, @Suppress("SameParameterValue") recurse: Boolean): List<File> {
        val li: MutableList<File> = ArrayList()
        if (dirOrFile.isDirectory) {
            val files = dirOrFile.listFiles()!!
            Arrays.sort(files) // to guarantee predecible order
            for (f in files) {
                if (recurse && f.isDirectory) li.addAll(getPngsFromDir(f, true))
                if (f.name.lowercase(Locale.getDefault()).endsWith(".png")) li.add(f)
            }
        } else li.add(dirOrFile) // not a dir, but a file
        return li
    }

    fun getPngsFromDir(dir: File): List<File> {
        return getPngsFromDir(dir, true)
    }

    /** copies a PNG to another, taking a subset of lines  */
    fun copyPartial(ori: File, dest: File, nlines: Int, step: Int, offset: Int, filterPreserve: Boolean) {
        @Suppress("NAME_SHADOWING")
        var nlines = nlines
        val png2 = PngReaderByte(istream(ori))
        val nlinesmax = (png2.imgInfo!!.rows - offset) / step
        if (nlines < 1 || nlines > nlinesmax) nlines = nlinesmax
        val pngw = PngWriter(ostream(dest), png2.imgInfo!!.withSize(-1, nlines))
        if (filterPreserve) pngw.setFilterPreserve(true) else pngw.setFilterType(FilterType.FILTER_CYCLIC) // to test
        pngw.writeRows(png2.readRows(nlines, offset, step))
        png2.end()
        pngw.end()
    }

    fun getChunksSummary(f: String): String {
        return getChunksSummary(f, true)
    }

    // if fast=true does not check CRCs
    private fun getChunksSummary(file: String, @Suppress("SameParameterValue") fast: Boolean): String {
        val c = ChunkSeqSkipping(fast)
        c.feedFromInputStream(istream(file))
        return showChunksRaw(c.getChunks())
    }

    internal fun getImageInfo(f: File): ImageInfo? {
        val p = PngReaderByte(f.toInputPngStream())
        p.close()
        return p.imgInfo
    }

    internal class NullOutputPngStream : OutputPngStream() {
        private var cont = 0

        @Throws(IOException::class)
        override fun write(b: Int) {
            // nothing!
            cont++
        }

        @Throws(IOException::class)
        override fun write(data: ByteArray, off: Int, len: Int) {
            cont += len
        }

        @Throws(IOException::class)
        override fun write(data: ByteArray) {
            cont += data.size
        }
    }

    private fun File.toInputPngStream() = InputPngStream(readBytes())
    private fun String.toInputPngStream() = InputPngStream(this.toByteArray())

    private class FileOutputStream(private val file: File) : OutputPngStream() {
        override fun onClose() {
            file.writeBytes(byteArray)
        }
    }

}