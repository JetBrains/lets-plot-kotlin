/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 *
 * This file has been modified by JetBrains : Java code has been converted to Kotlin code.
 * */

package org.jetbrains.letsPlot.util.pngj

import org.jetbrains.letsPlot.util.pngj.chunks.ChunkHelper
import kotlin.jvm.JvmOverloads

/**
 * Consumes a stream of bytes that consist of a series of PNG-like chunks.
 *
 *
 * This has little intelligence, it's quite low-level and general (it could even
 * be used for a MNG stream, for example). It supports signature recognition and
 * idat deflate
 */
internal abstract class ChunkSeqReader @JvmOverloads constructor(
    expectedSignature: ByteArray? = PngHelperInternal.pngIdSignature
) : IBytesConsumer {
    private val signatureLength: Int = expectedSignature?.size ?: 0
    private val buf0 = ByteArray(8) // for signature or chunk starts
    private var buf0len = 0

    /**
     * If false, we are still reading the signature
     *
     * @return true if signature has been read (or if we don't have signature)
     */
    private var isSignatureDone = signatureLength <= 0

    /**
     * If true, we have processe the IEND chunk
     */
    override var isDone = false
        protected set

    /**
     * Terminated, normally or not - If true, this will not accept more bytes
     */
    private var isClosed = false // ended, normally or not

    /**
     * @return Chunks already read, including partial reading (currently
     * reading)
     */
    private var chunkCount = 0

    /**
     * total of bytes read (buffered or not)
     */
    var bytesCount: Long = 0
        private set

    // one instance for each "idat-like set". Normally one.
    private var curDeflatedSet: DeflatedChunksSet? = null
    private var curChunkReader: ChunkReader? = null

    /**
     * Helper method, reports amount of bytes inside IDAT chunks.
     * this is only for the IDAT (not mrerely "idat-like")
     * @return Bytes in IDAT chunks
     */
    private var idatBytes: Long = 0

    private var errorBehaviour: ErrorBehaviour = ErrorBehaviour.STRICT

    /**
     * Consumes (in general, partially) a number of bytes. A single call never
     * involves more than one chunk.
     *
     * When the signature is read, it calls checkSignature()
     *
     * When the start of a chunk is detected, it calls
     * [.startNewChunk]
     *
     * When data from a chunk is being read, it delegates to ChunkReader.feedBytes
     *
     * The caller might want to call this method more than once in succesion
     *
     * This should rarely be overriden
     *
     * @param buf
     * @param offset
     * Offset in buffer
     * @param len
     * Valid bytes that can be consumed
     * @return processed bytes, in the 1-len range. -1 if done. Only returns 0
     * if len=0.
     */
    override fun consume(buf: ByteArray, offset: Int, len: Int): Int {
        if (isClosed) return -1
        if (len == 0) return 0 // nothing to do (should not happen)
        if (len < 0) throw PngjInputException("This should not happen. Bad length: $len")
        var processed = 0
        if (isSignatureDone) {
            if (curChunkReader == null || curChunkReader!!.isDone) { // new chunk: read first 8 bytes
                var read0 = 8 - buf0len
                if (read0 > len) read0 = len
                arraycopy(buf, offset, buf0, buf0len, read0)
                buf0len += read0
                processed += read0
                bytesCount += read0.toLong()
                // len -= read0;
                // offset += read0;
                if (buf0len == 8) { // end reading chunk length and id
                    chunkCount++
                    val clen: Int = PngHelperInternal.readInt4fromBytes(buf0, 0)
                    val cid: String = ChunkHelper.idFromBytes(buf0, 4)
                    startNewChunk(clen, cid, bytesCount - 8)
                    buf0len = 0
                }
            } else { // reading chunk, delegates to curChunkReader
                val read1: Int = curChunkReader!!.consume(buf, offset, len)
                if (read1 < 0) return -1 // should not happen
                processed += read1
                bytesCount += read1.toLong()
            }
        } else { // reading signature
            var read = signatureLength - buf0len
            if (read > len) read = len
            arraycopy(buf, offset, buf0, buf0len, read)
            buf0len += read
            if (buf0len == signatureLength) {
                checkSignature(buf0)
                buf0len = 0
                isSignatureDone = true
            }
            processed += read
            bytesCount += read.toLong()
        }
        return processed
    }

    /**
     * Trys to feeds exactly <tt>len</tt> bytes, calling
     * [.consume] retrying if necessary.
     *
     * This should only be used in callback mode
     *
     * @return Excess bytes (not feed). Normally should return 0
     */
    fun feedAll(buf: ByteArray, off: Int, len: Int): Int {
        @Suppress("NAME_SHADOWING")
        var off = off
        @Suppress("NAME_SHADOWING")
        var len = len
        while (len > 0) {
            val n = consume(buf, off, len)
            if (n < 1) return len
            len -= n
            off += n
        }
        require(len == 0)
        return 0
    }

    /**
     * Called for all chunks when a chunk start has been read (id and length),
     * before the chunk data itself is read. It creates a new ChunkReader (field
     * accesible via [.getCurChunkReader]) in the corresponding mode,
     * and eventually a curReaderDeflatedSet.(field accesible via
     * [.getCurDeflatedSet])
     *
     * To decide the mode and options, it calls
     * [.shouldCheckCrc],
     * [.shouldSkipContent], [.isIdatKind].
     * Those methods should be overriden in preference to this; if overriden,
     * this should be called first.
     *
     * The respective [ChunkReader.chunkDone] method is directed to this
     * [.postProcessChunk].
     *
     * Instead of overriding this, see also
     * [.createChunkReaderForNewChunk]
     */
    protected open fun startNewChunk(len: Int, id: String, offset: Long) {
        println("New chunk: $id $len off:$offset")

        // check id an length
        if (id.length != 4 || id.all { it !in 'a'..'z' && it !in 'A'..'Z' }) throw PngjInputException("Bad chunk id: $id")
        if (len < 0) throw PngjInputException("Bad chunk len: $len")
        if (id == ChunkHelper.IDAT) idatBytes += len.toLong()
        val checkCrc = shouldCheckCrc(len, id)
        val skip = shouldSkipContent(len, id)
        val isIdatType = isIdatKind(id)
        // PngHelperInternal.debug("start new chunk id=" + id + " off=" + offset + "
        // skip=" + skip + " idat=" +
        // isIdatType);
        // first see if we should terminate an active curReaderDeflatedSet
        var forCurrentIdatSet = false
        if (curDeflatedSet != null && !curDeflatedSet!!.isClosed) forCurrentIdatSet = curDeflatedSet!!.ackNextChunkId(id)
        if (isIdatType && !skip) { // IDAT non skipped: create a DeflatedChunkReader owned by a idatSet
            if (!forCurrentIdatSet) {
                if (curDeflatedSet != null && !curDeflatedSet!!.isDone) throw PngjInputException("new IDAT-like chunk when previous was not done")
                curDeflatedSet = createIdatSet(id)
            }
            curChunkReader = object : DeflatedChunkReader(len, id, checkCrc, offset, curDeflatedSet!!) {
                override fun chunkDone() {
                    super.chunkDone()
                    postProcessChunk(this)
                }
            }
        } else { // for non-idat chunks (or skipped idat like)
            curChunkReader = createChunkReaderForNewChunk(id, len, offset, skip)
        }
        if (curChunkReader != null && !checkCrc) curChunkReader!!.setCrcCheck(false)
    }

    /**
     * This will be called for all chunks (even skipped), except for IDAT-like
     * non-skiped chunks
     *
     * The default behaviour is to create a ChunkReader in BUFFER mode (or SKIP
     * if skip==true) that calls [.postProcessChunk] (always)
     * when done.
     *
     * @param id
     * Chunk id
     * @param len
     * Chunk length
     * @param offset
     * offset inside PNG stream , merely informative
     * @param skip
     * flag: is true, the content will not be buffered (nor
     * processed)
     * @return a newly created ChunkReader that will create the ChunkRaw and
     * then discarded
     */
    protected open fun createChunkReaderForNewChunk(id: String, len: Int, offset: Long, skip: Boolean): ChunkReader {
        return object : ChunkReader(len, id, offset, if (skip) ChunkReaderMode.SKIP else ChunkReaderMode.BUFFER) {
            override fun chunkDone() = postProcessChunk(this)

            override fun processData(offsetInchunk: Int, buf: ByteArray, off: Int, len: Int) {
                throw PngjExceptionInternal("should never happen")
            }
        }
    }

    /**
     * This is called after a chunk is read, in all modes
     *
     * This implementation only chenks the id of the first chunk, and process
     * the IEND chunk (sets done=true)
     *
     * Further processing should be overriden (call this first!)
     */
    protected open fun postProcessChunk(chunkR: ChunkReader) { // called after chunk is read
        if (chunkCount == 1) {
            // first chunk ?
            val cid = firstChunkId()
            if (cid != chunkR.chunkRaw.id) {
                val msg = "Bad first chunk: " + chunkR.chunkRaw.id + " expected: " + firstChunkId()
                if (errorBehaviour.c < ErrorBehaviour.SUPER_LENIENT.c) throw PngjInputException(msg) else println(msg)
            }
        }
        if (chunkR.chunkRaw.id == endChunkId()) {
            // last chunk ?
            isDone = true
            close()
        }
    }

    /**
     * DeflatedChunksSet factory. This implementation is quite dummy, it usually
     * should be overriden.
     */
    protected abstract fun createIdatSet(id: String): DeflatedChunksSet?

    /**
     * Decides if this Chunk is of "IDAT" kind (in concrete: if it is, and if
     * it's not to be skiped, a DeflatedChunksSet will be created to deflate it
     * and process+ the deflated data)
     *
     * This implementation always returns always false
     *
     * @param id
     */
    protected open fun isIdatKind(id: String?): Boolean {
        return false
    }

    /**
     * Chunks can be skipped depending on id and/or length. Skipped chunks are
     * still processed, but their data will be null, and CRC will never checked
     *
     * @param len
     * @param id
     */
    protected open fun shouldSkipContent(len: Int, id: String): Boolean {
        return false
    }

    protected open fun shouldCheckCrc(len: Int, id: String?): Boolean {
        return true
    }

    /**
     * Throws PngjInputException if bad signature
     *
     * @param buf
     * Signature. Should be of length 8
     */
    private fun checkSignature(buf: ByteArray) {
        if (!buf.contentEquals(PngHelperInternal.pngIdSignature)) throw PngjBadSignature("Bad signature:" + buf.joinToString())
    }

    /**
     * Currently reading chunk, or just ended reading
     *
     * @return null only if still reading signature
     */
    fun getCurChunkReader(): ChunkReader? {
        return curChunkReader
    }

    /**
     * The latest deflated set (typically IDAT chunks) reader. Notice that there
     * could be several idat sets (eg for APNG)
     */
    fun getCurDeflatedSet(): DeflatedChunksSet? {
        return curDeflatedSet
    }

    /**
     * Closes this object and release resources. For normal termination or
     * abort. Secure and idempotent.
     */
    open fun close() { // forced closing
        curDeflatedSet?.close()
        isClosed = true
    }

    /**
     * Returns true if we are not in middle of a chunk: we have just ended
     * reading past chunk , or we are at the start, or end of signature, or we
     * are done
     */
    val isAtChunkBoundary: Boolean
        get() = bytesCount == 0L || bytesCount == 8L || isClosed || curChunkReader == null || curChunkReader!!.isDone

    /**
     * Which should be the id of the first chunk. This will be checked
     *
     * @return null if you don't want to check it
     */
    private fun firstChunkId(): String {
        return "IHDR"
    }

    /**
     * Which should be the id of the last chunk
     */
    private fun endChunkId(): String {
        return "IEND"
    }

    /**
     * Reads all content from a file. Helper method, only for callback mode
     */
    //fun feedFromFile(f: java.io.File) {
    //    try {
    //        feedFromInputStream(java.io.FileInputStream(f), true)
    //    } catch (e: java.io.FileNotFoundException) {
    //        throw PngjInputException("Error reading from file '" + f + "' :" + e.message)
    //    }
    //}

    /**
     * Reads all content from an input stream. Helper method, only for callback
     * mode
     *
     * Caller should call isDone() to assert all expected chunks have been read
     *
     * Warning: this does not close this object, unless ended
     *
     * @param is
     * @param closeStream
     * Closes the input stream when done (or if error)
     */
    @JvmOverloads
    fun feedFromInputStream(`is`: InputPngStream, closeStream: Boolean = true) {
        val sf = BufferedStreamFeeder(`is`)
        sf.setCloseStream(closeStream)
        try {
            sf.feedAll(this)
        } finally {
            sf.close()
        }
    }

    fun setErrorBehaviour(errorBehaviour: ErrorBehaviour) {
        this.errorBehaviour = errorBehaviour
    }
}