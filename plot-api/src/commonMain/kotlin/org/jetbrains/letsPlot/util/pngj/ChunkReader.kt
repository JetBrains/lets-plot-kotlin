/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("KDocUnresolvedReference")

package org.jetbrains.letsPlot.util.pngj

import org.jetbrains.letsPlot.util.pngj.ChunkReader.ChunkReaderMode
import org.jetbrains.letsPlot.util.pngj.chunks.ChunkRaw

/**
 * Parses a PNG chunk, consuming bytes in one of three modes:
 * [ChunkReaderMode.BUFFER], [ChunkReaderMode.PROCESS],
 * [ChunkReaderMode.SKIP].
 *
 *
 * It calls [.chunkDone] when done. Also calls
 * [.processData] if `PROCESS` mode. Apart
 * from thas, it's totally agnostic (it doesn't know about IDAT chunks, or PNG
 * general structure)
 *
 *
 * The object wraps a <tt>ChunkRaw</tt> instance (content allocated and filled
 * only if BUFFER mode). It should be short lived (one instance created for each
 * chunk, and discarded after reading), but the wrapped <tt>chunkRaw</tt> can be
 * (usually is) long lived.
 */
internal abstract class ChunkReader(clen: Int, id: String, offsetInPng: Long, mode: ChunkReaderMode?) : IBytesConsumer {
    /**
     * see [ChunkReaderMode]
     */
    val mode: ChunkReaderMode

    /**
     * Returns raw chunk (data can be empty or not, depending on
     * ChunkReaderMode)
     *
     * @return Raw chunk - never null
     */
    val chunkRaw: ChunkRaw

    /**
     * How many bytes have been read for this chunk, data only
     */
    protected var read = 0
    private var crcn = 0 // how many bytes have been read from crc
    private var crcCheck // by default, this is false for SKIP, true elsewhere
            : Boolean
    protected var errorBehav: ErrorBehaviour = ErrorBehaviour.STRICT

    /**
     * Modes of ChunkReader chunk processing.
     */
    enum class ChunkReaderMode {
        /**
         * Stores full chunk data in buffer
         */
        BUFFER,

        /**
         * Does not store content, processes on the fly, calling processData()
         * for each partial read
         */
        PROCESS,

        /**
         * Does not store nor process - implies crcCheck=false (by default).
         */
        SKIP
    }

    /**
     * The constructor creates also a chunkRaw, preallocated if mode =
     * ChunkReaderMode.BUFFER
     *
     * @param clen
     * @param id
     * @param offsetInPng
     * Informational, is stored in chunkRaw
     * @param mode
     */
    init {
        if (mode == null || id.length != 4 || clen < 0) throw PngjInputException("Bad chunk paramenters: $mode")
        this.mode = mode
        chunkRaw = ChunkRaw(clen, id, mode == ChunkReaderMode.BUFFER)
        chunkRaw.offset = offsetInPng
        crcCheck = mode != ChunkReaderMode.SKIP // can be changed with setter
        // PngHelperInternal.debug("ChunkReader " + this.getClass() + " id="+id + " mode:"+mode);
    }

    /**
     * Consumes data for the chunk (data and CRC). This only consumes bytes
     * owned by this chunk (data + crc , not id+len prefix)
     *
     * In ChunkReaderMode.PROCESS can call processData() (not more than once)
     *
     * If this ends the chunk (included CRC) it checks CRC (if checking) and
     * calls chunkDone()
     *
     * @param buf
     * @param offset
     * @param len
     * @return How many bytes have been consumed
     */
    override fun consume(buf: ByteArray, offset: Int, len: Int): Int {
        var off = offset
        @Suppress("NAME_SHADOWING")
        var len = len
        if (len == 0) return 0
        if (len < 0) throw PngjException("negative length??")
        if (read == 0 && crcn == 0 && crcCheck) chunkRaw.updateCrc(
            chunkRaw.idbytes,
            0,
            4
        ) // initializes crc calculation with the Chunk ID
        var bytesForData = chunkRaw.len - read // bytesForData : bytes to be actually read from chunk data
        if (bytesForData > len) bytesForData = len
        // we want to call processData even for empty chunks (IEND:bytesForData=0) at
        // least once
        if (bytesForData > 0 || crcn == 0) {
            // in buffer mode we compute the CRC at the end
            if (crcCheck && mode != ChunkReaderMode.BUFFER && bytesForData > 0) chunkRaw.updateCrc(
                buf,
                off,
                bytesForData
            )
            if (mode == ChunkReaderMode.BUFFER) {
                // just copy the contents to the internal buffer
                if (!chunkRaw.data.contentEquals(buf) && bytesForData > 0) {
                    // If the buffer passed if the same as this one, we don't copy.
                    // The caller should know what he's doing
                    arraycopy(buf, off, chunkRaw.data!!, read, bytesForData)
                }
            } else if (mode == ChunkReaderMode.PROCESS) {
                processData(read, buf, off, bytesForData)
            } else {
                // mode == ChunkReaderMode.SKIP; nothing to do
            }
            read += bytesForData
            off += bytesForData
            len -= bytesForData
        }
        var crcRead = 0
        if (read == chunkRaw.len) { // data done - read crc?
            crcRead = 4 - crcn
            if (crcRead > len) crcRead = len
            if (crcRead > 0) {
                if (!buf.contentEquals(chunkRaw.crcval)) arraycopy(buf, off, chunkRaw.crcval, crcn, crcRead)
                crcn += crcRead
                if (crcn == 4) {
                    if (crcCheck) {
                        if (mode == ChunkReaderMode.BUFFER) { // in buffer mode we compute the CRC on one single call
                            chunkRaw.updateCrc(chunkRaw.data!!, 0, chunkRaw.len)
                        }
                        chunkRaw.checkCrc(errorBehav === ErrorBehaviour.STRICT)
                    }
                    println("Chunk done")
                    chunkDone()
                }
            }
        }
        return if (bytesForData > 0 || crcRead > 0) bytesForData + crcRead else -1 /* should not happen */
    }// has read all 4 bytes from the crc

    /**
     * Chunks has been read
     *
     * @return true if we have read all chunk, including trailing CRC
     */
    override val isDone: Boolean
        get() = crcn == 4 // has read all 4 bytes from the crc

    /**
     * Determines if CRC should be checked. This should be called before
     * starting reading.
     *
     * @see also .setErrorBehav
     * @param crcCheck
     */
    fun setCrcCheck(crcCheck: Boolean) {
        if (read != 0 && crcCheck && !this.crcCheck) throw PngjException("too late!")
        this.crcCheck = crcCheck
    }

    /**
     * This method will only be called in PROCESS mode, probably several times,
     * each time with a new fragment of data read from inside the chunk. For
     * chunks with zero-length data, this will still be called once.
     *
     * It's guaranteed that the data corresponds exclusively to this chunk data
     * (no crc, no data from no other chunks, )
     *
     * @param offsetInchunk
     * data bytes that had already been read/processed for this chunk
     * @param buf
     * @param off
     * @param len
     */
    protected abstract fun processData(offsetInchunk: Int, buf: ByteArray, off: Int, len: Int)

    /**
     * This method will be called (in all modes) when the full chunk -including
     * crc- has been read
     */
    protected abstract fun chunkDone()
    open val isFromDeflatedSet: Boolean
        get() = false

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + chunkRaw.hashCode()
        return result
    }

    /**
     * Equality (and hash) is basically delegated to the ChunkRaw
     */
    override fun equals(other: Any?): Boolean { // delegates to chunkraw
        if (this === other) return true
        if (other == null) return false
        if (this::class != other::class) return false
        other as ChunkReader
        if (chunkRaw != other.chunkRaw) return false
        return true
    }

    override fun toString(): String {
        return chunkRaw.toString()
    }
}