/*
 * Copyright (c) 2022. JetBrains s.r.o. 
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("unused", "KDocUnresolvedReference")
package org.jetbrains.letsPlot.util.pngj

import jetbrains.datalore.base.numberFormat.NumberFormat
import org.jetbrains.letsPlot.util.pngj.Zip.adler32
import org.jetbrains.letsPlot.util.pngj.Zip.crc32
import org.jetbrains.letsPlot.util.pngj.chunks.ChunkLoadBehaviour
import org.jetbrains.letsPlot.util.pngj.chunks.ChunksList
import org.jetbrains.letsPlot.util.pngj.chunks.PngChunk.Companion.ID_FCTL
import org.jetbrains.letsPlot.util.pngj.chunks.PngChunk.Companion.ID_FDAT
import org.jetbrains.letsPlot.util.pngj.chunks.PngChunkIDAT
import org.jetbrains.letsPlot.util.pngj.chunks.PngMetadata
import kotlin.jvm.JvmOverloads

/**
 * Reads a PNG image (pixels and/or metadata) from a file or stream.
 *
 *
 * Each row is read as an [ImageLineInt] object (one int per sample), but
 * this can be changed by setting a different ImageLineFactory
 *
 *
 * Internally, this wraps a [ChunkSeqReaderPng] with a
 * [BufferedStreamFeeder]
 *
 *
 * The reading sequence is as follows: <br></br>
 * 1. At construction time, the header and IHDR chunk are read (basic image
 * info) <br></br>
 * 2. Afterwards you can set some additional global options. Eg.
 * [.setCrcCheckDisabled].<br></br>
 * 3. Optional: If you call getMetadata() or getChunksLisk() before start
 * reading the rows, all the chunks before IDAT are then loaded and available
 * <br></br>
 * 4a. The rows are read in order by calling [.readRow]. You can also
 * call [.readRow] to skip rows -but you can't go backwards, at least
 * not with this implementation. This method returns a [IImageLine] object
 * which can be casted to the concrete class. This class returns by default a
 * [ImageLineInt], but this can be changed.<br></br>
 * 4b. Alternatively, you can read all rows, or a subset, in a single call:
 * [.readRows], [.readRows] ,etc. In general this
 * consumes more memory, but for interlaced images this is equally efficient,
 * and more so if reading a small subset of rows.<br></br>
 * 5. Reading of the last row automatically loads the trailing chunks, and ends
 * the reader.<br></br>
 * 6. end() also loads the trailing chunks, if not done, and finishes cleanly
 * the reading and closes the stream.
 *
 *
 * See also [PngReaderInt] (esentially the same as this, and slightly
 * preferred) and [PngReaderByte] (uses byte instead of int to store the
 * samples).
 */
internal open class PngReader @JvmOverloads constructor(inputStream: InputPngStream, shouldCloseStream: Boolean = true) {
    // checked)
    /**
     * Basic image info - final and inmutable.
     */
    var imgInfo // People always told me: be careful what you do, and don't go around declaring
            : ImageInfo? = null
    // public
    // fields...
    /**
     * Interlaced PNG is accepted -though not welcomed- now...
     */
    /**
     * flag: image was in interlaced format
     */
    var isInterlaced = false

    /**
     * This object has most of the intelligence to parse the chunks and
     * decompress the IDAT stream
     */
    val chunkseq: ChunkSeqReaderPng?

    /**
     * Takes bytes from the InputStream and passes it to the ChunkSeqReaderPng.
     * Never null.
     */
    private val streamFeeder: BufferedStreamFeeder?

    /**
     * @see .getMetadata
     */
    private var metadata // this a wrapper over chunks
            : PngMetadata? = null

    /**
     * Current row number (reading or read), numbered from 0
     */
    private var rowNum = -1

    /**
     * Represents the set of lines (rows) being read. Normally this works as a
     * cursor, storing only one (the current) row. This stores several (perhaps
     * all) rows only if calling [.readRows] or for interlaced images
     * (this later is transparent to the user)
     */
    private var imlinesSet: IImageLineSet<out IImageLine>? = null

    /**
     * This factory decides the concrete type of the ImageLine that will be
     * used. See [ImageLineSetDefault] for examples
     */
    private var imageLineSetFactory: IImageLineSetFactory<out IImageLine>? = null
    var idatCrca // for internal testing
            : Checksum? = null // java.util.zip.CRC32? = null
    var idatCrcb // for internal testing
            : Checksum? = null //java.util.zip.Adler32? = null
    private var errorBehaviour: ErrorBehaviour = ErrorBehaviour.STRICT
    /**
     * Same as [.PngReader] but allows to specify early if
     * the stream must be closed
     *
     * @param inputStream
     * @param shouldCloseStream
     * The stream will be closed in case of exception (constructor
     * included) or normal termination.
     */
    /**
     * Constructs a PngReader object from a stream, with default options. This
     * reads the signature and the first IHDR chunk only.
     *
     *
     * Warning: In case of exception the stream is NOT closed.
     *
     *
     * Warning: By default the stream will be closed when this object is
     * [.close]d. See [.PngReader] or
     * [.setShouldCloseStream]
     *
     *
     *
     * @param inputStream
     * PNG stream
     */
    init {
        streamFeeder = BufferedStreamFeeder(inputStream)
        streamFeeder.setCloseStream(shouldCloseStream)
        chunkseq = createChunkSeqReader()
        try {
            if (streamFeeder.feedFixed(
                    chunkseq,
                    36
                ) != 36
            ) throw PngjInputException("Could not read first 36 bytes (PNG signature+IHDR chunk)")
            imgInfo = chunkseq.imageInfo
            isInterlaced = chunkseq.deinterlacer != null
            setMaxBytesMetadata(MAX_BYTES_METADATA_DEFAULT)
            setMaxTotalBytesRead(MAX_TOTAL_BYTES_READ_DEFAULT)
            setSkipChunkMaxSize(MAX_CHUNK_SIZE_SKIP)
            chunkseq.addChunkToSkip(ID_FDAT) // default: skip fdAT chunks!
            chunkseq.addChunkToSkip(ID_FCTL) // default: skip fctl chunks!
            metadata = PngMetadata(chunkseq.chunksList!!)
            // sets a default factory (with ImageLineInt),
            // this can be overwriten by a extended constructor, or by a setter
            setLineSetFactory(ImageLineSetDefault.factoryInt)
            rowNum = -1
        } catch (e: Exception) {
            streamFeeder.close()
            chunkseq.close()
            throw e
        }
    }

    /**
     * Constructs a PngReader opening a file. Sets
     * <tt>shouldCloseStream=true</tt>, so that the stream will be closed with
     * this object.
     *
     * @param file
     * PNG image file
     */
    //constructor(file: java.io.File?) : this(PngHelperInternal.istreamFromFile(file), true) {}

    /**
     * Reads chunks before first IDAT. Normally this is called automatically
     *
     *
     * Position before: after IDHR (crc included) Position after: just after the
     * first IDAT chunk id
     * <P>
     * This can be called several times (tentatively), it does nothing if
     * already run
    </P> *
     *
     * (Note: when should this be called? in the constructor? hardly, because we
     * loose the opportunity to call setChunkLoadBehaviour() and perhaps other
     * settings before reading the first row? but sometimes we want to access
     * some metadata (plte, phys) before. Because of this, this method can be
     * called explicitly but is also called implicititly in some methods
     * (getMetatada(), getChunksList())
     */
    private fun readFirstChunks() {
        while (chunkseq!!.currentChunkGroup < ChunksList.CHUNK_GROUP_4_IDAT) if (streamFeeder!!.feed(chunkseq) <= 0) throw PngjInputException(
            "Premature ending reading first chunks"
        )
    }

    /**
     * Determines which ancillary chunks (metadata) are to be loaded and which
     * skipped.
     *
     *
     * Additional restrictions may apply. See also
     * [.setChunksToSkip], [.addChunkToSkip],
     * [.setMaxBytesMetadata], [.setSkipChunkMaxSize]
     *
     * @param chunkLoadBehaviour
     * [ChunkLoadBehaviour]
     */
    fun setChunkLoadBehaviour(chunkLoadBehaviour: ChunkLoadBehaviour) {
        chunkseq!!.setChunkLoadBehaviour(chunkLoadBehaviour)
    }

    /**
     * All loaded chunks (metada). If we have not yet end reading the image,
     * this will include only the chunks before the pixels data (IDAT)
     *
     *
     * Critical chunks are included, except that all IDAT chunks appearance are
     * replaced by a single dummy-marker IDAT chunk. These might be copied to
     * the PngWriter
     *
     *
     *
     * @see .getMetadata
     */
    val chunksList: ChunksList
        get() = getChunksList(true)

    /**
     * @param forceLoadingOfFirstChunks
     * If true, chunks before IDAT will be load if needed
     * @return list of currently read chunks
     */
    fun getChunksList(forceLoadingOfFirstChunks: Boolean): ChunksList {
        if (forceLoadingOfFirstChunks && chunkseq!!.firstChunksNotYetRead()) readFirstChunks()
        return chunkseq!!.chunksList!!
    }

    /**
     * From 0 to 6, see ChunksList CHUNK_GROUP_*
     */
    val currentChunkGroup: Int
        get() = chunkseq!!.currentChunkGroup

    /**
     * High level wrapper over chunksList
     *
     * @see .getChunksList
     */
    fun getMetadata(): PngMetadata? {
        if (chunkseq!!.firstChunksNotYetRead()) readFirstChunks()
        return metadata
    }

    /**
     * Reads next row.
     *
     * The caller must know that there are more rows to read.
     *
     * @return Never null. Throws PngInputException if no more
     */
    fun readRow(): IImageLine {
        return readRow(rowNum + 1)
    }

    /**
     * True if last row has not yet been read
     */
    fun hasMoreRows(): Boolean {
        return rowNum < curImgInfo.rows - 1
    }

    /**
     * The row number is mostly meant as a check, the rows must be called in
     * ascending order (not necessarily consecutive)
     */
    fun readRow(nrow: Int): IImageLine {
        if (chunkseq!!.firstChunksNotYetRead()) readFirstChunks()
        return if (!isInterlaced) {
            if (imlinesSet == null) imlinesSet = createLineSet(true, -1, 0, 1)
            val line: IImageLine = imlinesSet!!.getImageLine(nrow)
            if (nrow == rowNum) return line // already read??
            else if (nrow < rowNum) throw PngjInputException("rows must be read in increasing order: $nrow")
            while (rowNum < nrow) {
                while (!chunkseq.idatSet!!.isRowReady) {
                    if (streamFeeder!!.feed(chunkseq) < 1) throw PngjInputException("premature ending")
                }
                rowNum++
                chunkseq.idatSet!!.updateCrcs(idatCrca, idatCrcb)
                if (rowNum == nrow) {
                    line.readFromPngRaw(
                        chunkseq.idatSet!!.unfilteredRow!!, curImgInfo.bytesPerRow + 1, 0,
                        1
                    )
                    line.endReadFromPngRaw()
                }
                chunkseq.idatSet!!.advanceToNextRow()
            }
            line
        } else { // and now, for something completely different (interlaced!)
            if (imlinesSet == null) {
                imlinesSet = createLineSet(false, curImgInfo.rows, 0, 1)
                loadAllInterlaced(curImgInfo.rows, 0, 1)
            }
            rowNum = nrow
            imlinesSet!!.getImageLine(nrow)
        }
    }

    /**
     * Reads all rows in a ImageLineSet This is handy, but less memory-efficient
     * (except for interlaced)
     */
    fun readRows(): IImageLineSet<out IImageLine> {
        return readRows(curImgInfo.rows, 0, 1)
    }

    /**
     * Reads a subset of rows.
     *
     *
     * This method should called once, and not be mixed with [.readRow]
     *
     * @param nRows
     * how many rows to read (default: imageInfo.rows; negative:
     * autocompute)
     * @param rowOffset
     * rows to skip (default:0)
     * @param rowStep
     * step between rows to load( default:1)
     */
    fun readRows(nRows: Int, rowOffset: Int, rowStep: Int): IImageLineSet<out IImageLine> {
        @Suppress("NAME_SHADOWING")
        var nRows = nRows
        if (chunkseq!!.firstChunksNotYetRead()) readFirstChunks()
        if (nRows < 0) nRows = (curImgInfo.rows - rowOffset) / rowStep
        if (rowStep < 1 || rowOffset < 0 || nRows == 0 || nRows * rowStep + rowOffset > curImgInfo.rows) throw PngjInputException(
            "bad args"
        )
        if (rowNum >= rowOffset) throw PngjInputException("readRows cannot be mixed with readRow")
        imlinesSet = createLineSet(false, nRows, rowOffset, rowStep)
        if (!isInterlaced) {
            var m = -1 // last row already read in
            while (m < nRows - 1) {
                while (!chunkseq.idatSet!!.isRowReady) {
                    if (streamFeeder!!.feed(chunkseq) < 1) throw PngjInputException("Premature ending")
                }
                rowNum++
                chunkseq.idatSet!!.updateCrcs(idatCrca, idatCrcb)
                m = (rowNum - rowOffset) / rowStep
                if (rowNum >= rowOffset && rowStep * m + rowOffset == rowNum) {
                    val line: IImageLine = imlinesSet!!.getImageLine(rowNum)
                    line.readFromPngRaw(
                        chunkseq.idatSet!!.unfilteredRow!!, curImgInfo.bytesPerRow + 1, 0,
                        1
                    )
                    line.endReadFromPngRaw()
                }
                chunkseq.idatSet!!.advanceToNextRow()
            }
        } else { // and now, for something completely different (interlaced)
            loadAllInterlaced(nRows, rowOffset, rowStep)
        }
        chunkseq.idatSet!!.markAsDone()
        return imlinesSet!!
    }

    /**
     * Sets the factory that creates the ImageLine. By default, this
     * implementation uses ImageLineInt but this can be changed (at construction
     * time or later) by calling this method.
     *
     *
     * See also [.createLineSet]
     *
     * @param factory
     */
    fun setLineSetFactory(factory: IImageLineSetFactory<out IImageLine>) {
        imageLineSetFactory = factory
    }

    /**
     * By default this uses the factory (which, by default creates
     * ImageLineInt). You should rarely override this.
     *
     *
     * See doc in
     * [IImageLineSetFactory.create]
     */
    protected fun createLineSet(
        singleCursor: Boolean, nlines: Int, noffset: Int, step: Int
    ): IImageLineSet<out IImageLine> {
        return imageLineSetFactory!!.create(curImgInfo, singleCursor, nlines, noffset, step)
    }

    protected fun loadAllInterlaced(nRows: Int, rowOffset: Int, rowStep: Int) {
        require(chunkseq != null)
        val idat: IdatSet = chunkseq.idatSet!!
        var nread = 0
        do {
            while (!chunkseq.idatSet!!.isRowReady) if (streamFeeder!!.feed(chunkseq) <= 0) break
            if (!chunkseq.idatSet!!.isRowReady) throw PngjInputException("Premature ending?")
            chunkseq.idatSet!!.updateCrcs(idatCrca, idatCrcb)
            val rowNumreal: Int = idat.rowinfo.rowNreal
            val inset: Boolean = imlinesSet!!.hasImageLine(rowNumreal)
            if (inset) {
                imlinesSet!!.getImageLine(rowNumreal).readFromPngRaw(
                    idat.unfilteredRow!!, idat.rowinfo.buflen,
                    idat.rowinfo.oX, idat.rowinfo.dX
                )
                nread++
            }
            idat.advanceToNextRow()
        } while (nread < nRows || !idat.isDone)
        idat.markAsDone()
        var i = 0
        var j = rowOffset
        while (i < nRows) {
            imlinesSet!!.getImageLine(j).endReadFromPngRaw()
            i++
            j += rowStep
        }
    }

    /**
     * Reads all the (remaining) file, skipping the pixels data. This is much
     * more efficient that calling [.readRow], specially for big files
     * (about 10 times faster!), because it doesn't even decompress the IDAT
     * stream and disables CRC check Use this if you are not interested in
     * reading pixels,only metadata.
     */
    fun readSkippingAllRows() {
        setCrcCheckDisabled()
        chunkseq!!.addChunkToSkip(PngChunkIDAT.ID)
        chunkseq.addChunkToSkip(ID_FDAT)
        if (chunkseq.firstChunksNotYetRead()) readFirstChunks()
        end()
    }

    /**
     * Set total maximum bytes to read (0: unlimited; default: 200MB). <br></br>
     * These are the bytes read (not loaded) in the input stream. If exceeded,
     * an exception will be thrown.
     */
    fun setMaxTotalBytesRead(maxTotalBytesToRead: Long) {
        chunkseq!!.maxTotalBytesRead = maxTotalBytesToRead
    }

    /**
     * Set total maximum bytes to load from ancillary chunks (0: unlimited;
     * default: 5Mb).<br></br>
     * If exceeded, some chunks will be skipped
     */
    fun setMaxBytesMetadata(maxBytesMetadata: Long) {
        chunkseq!!.maxBytesMetadata = maxBytesMetadata
    }

    /**
     * Set maximum size in bytes for individual ancillary chunks (0: unlimited;
     * default: 2MB). <br></br>
     * Chunks exceeding this length will be skipped (the CRC will not be
     * checked) and the chunk will be saved as a PngChunkSkipped object. See
     * also setSkipChunkIds
     */
    fun setSkipChunkMaxSize(skipChunkMaxSize: Long) {
        chunkseq!!.skipChunkMaxSize = skipChunkMaxSize
    }

    /**
     * Chunks ids to be skipped. <br></br>
     * These chunks will be skipped (the CRC will not be checked) and the chunk
     * will be saved as a PngChunkSkipped object. See also setSkipChunkMaxSize
     */
    fun setChunksToSkip(vararg chunksToSkip: String) {
        chunkseq!!.setChunksToSkip(*chunksToSkip)
    }

    fun addChunkToSkip(chunkToSkip: String) {
        chunkseq!!.addChunkToSkip(chunkToSkip)
    }

    fun dontSkipChunk(chunkToSkip: String) {
        chunkseq!!.dontSkipChunk(chunkToSkip)
    }

    /**
     * if true, input stream will be closed after ending read
     *
     *
     * default=true
     */
    fun setShouldCloseStream(shouldCloseStream: Boolean) {
        streamFeeder!!.setCloseStream(shouldCloseStream)
    }

    /**
     * Reads till end of PNG stream and calls <tt>close()</tt>
     *
     * This should normally be called after reading the pixel data, to read the
     * trailing chunks and close the stream. But it can be called at anytime.
     * This will also read the first chunks if not still read, and skip pixels
     * (IDAT) if still pending.
     *
     * If you want to read all metadata skipping pixels,
     * <tt>readSkippingAllRows()</tt> is a little more efficient.
     *
     * If you want to abort immediately, call instead <tt>close()</tt>
     */
    fun end() {
        try {
            if (chunkseq!!.firstChunksNotYetRead()) {
                readFirstChunks()
            }

            if (chunkseq.idatSet != null && !chunkseq.idatSet!!.isDone) {
                chunkseq.idatSet!!.markAsDone() // it will ignore data
            }

            while (!chunkseq.isDone) if (streamFeeder!!.feed(chunkseq) <= 0) break
        } finally {
            close()
        }
    }

    /**
     * Releases resources, and closes stream if corresponds. Idempotent, secure,
     * no exceptions.
     *
     * This can be also called for abort. It is recommended to call this in case
     * of exceptions
     */
    fun close() {
        try {
            chunkseq?.close()
        } catch (e: Exception) {
            println("error closing chunk sequence:" + e.message)
        }
        streamFeeder?.close()
    }

    /**
     * Disables the CRC integrity check in IDAT chunks and ancillary chunks,
     * this gives a slight increase in reading speed for big files
     */
    fun setCrcCheckDisabled() {
        chunkseq!!.isCheckCrc = false
    }

    /**
     * called on construction time. Override if you want an alternative class
     */
    protected fun createChunkSeqReader(): ChunkSeqReaderPng {
        return ChunkSeqReaderPng(false)
    }

    /**
     * Enables and prepare the simple digest computation. Must be called before
     * reading the pixels. See [.getSimpleDigestHex]
     */
    fun prepareSimpleDigestComputation() {
        if (idatCrca == null) idatCrca = crc32() else idatCrca!!.reset()
        if (idatCrcb == null) idatCrcb = adler32() else idatCrcb!!.reset()
        imgInfo!!.updateCrc(idatCrca!!)
        idatCrcb!!.update(imgInfo!!.rows) // not important
    }

    val simpleDigest: Long
        get() = if (idatCrca == null) 0 else idatCrca!!.value xor (idatCrcb!!.value shl 31)

    /**
     * Pseudo 64-bits digest computed over the basic image properties and the
     * raw pixels data: it should coincide for equivalent images encoded with
     * different filters and compressors; but will not coincide for
     * interlaced/non-interlaced; also, this does not take into account the
     * palette info. This will be valid only if
     * [.prepareSimpleDigestComputation] has been called, and all rows
     * have been read. Not fool-proof, not cryptografically secure, only for
     * informal testing and duplicates detection.
     *
     * @return A 64-digest in hexadecimal
     */
    val simpleDigestHex: String
        get() = NumberFormat("016X").apply(simpleDigest)

    /**
     * Basic info, for debugging.
     */
    override fun toString(): String { // basic info
        return imgInfo.toString() + " interlaced=" + isInterlaced
    }

    /**
     * Basic info, in a compact format, apt for scripting
     * COLSxROWS[dBITDEPTH][a][p][g][i] ( the default dBITDEPTH='d8' is ommited)
     *
     */
    fun toStringCompact(): String {
        return imgInfo!!.toStringBrief() + if (isInterlaced) "i" else ""
    }

    val curImgInfo: ImageInfo
        get() = chunkseq!!.curImgInfo!!

    fun setErrorBehaviour(er: ErrorBehaviour) {
        errorBehaviour = er
        chunkseq!!.setErrorBehaviour(er)
    }

    val isDone: Boolean
        get() = chunkseq!!.isDone

    companion object {
        // some performance/defensive limits
        /**
         * Defensive limit: refuse to read more than 900MB, can be changed with
         * [.setMaxTotalBytesRead]
         */
        const val MAX_TOTAL_BYTES_READ_DEFAULT = 901001001L // ~ 900MB

        /**
         * Defensive limit: refuse to load more than 5MB of ancillary metadata, see
         * [.setMaxBytesMetadata] and also
         * [.addChunkToSkip]
         */
        const val MAX_BYTES_METADATA_DEFAULT: Long = 5024024 // for ancillary chunks

        /**
         * Skip ancillary chunks greater than 2MB, see
         * [.setSkipChunkMaxSize]
         */
        const val MAX_CHUNK_SIZE_SKIP: Long = 2024024 // chunks exceeding this size will be skipped (nor even CRC
    }
}