@file:Suppress("unused")
package org.jetbrains.letsPlot.intern.pngj

import org.jetbrains.letsPlot.intern.pngj.PngHelperInternal.readInt4fromBytes
import org.jetbrains.letsPlot.intern.pngj.chunks.PngChunk.Companion.ID_FDAT
import org.jetbrains.letsPlot.intern.pngj.utils.arraycopy

/**
 *
 * Specialization of ChunkReader, for IDAT-like chunks. These chunks are part of
 * a set of similar chunks (contiguos normally, not necessariyl) which conforms
 * a zlib stream
 */
abstract class DeflatedChunkReader(
    clen: Int,
    chunkid: String,
    @Suppress("UNUSED_PARAMETER")
    checkCrc: Boolean,
    offsetInPng: Long,
    iDatSet: DeflatedChunksSet
) : ChunkReader(clen, chunkid, offsetInPng, ChunkReaderMode.PROCESS) {
    private val deflatedChunksSet: DeflatedChunksSet
    private var alsoBuffer = false
    private var skipBytes = false // fDAT (APNG) skips 4 bytes)
    private lateinit var skippedBytes // only for fDAT
            : ByteArray
    private var seqNumExpected = -1 // only for fDAT

    init {
        deflatedChunksSet = iDatSet
        if (chunkid == ID_FDAT) {
            skipBytes = true
            skippedBytes = ByteArray(4)
        }
        iDatSet.appendNewChunk(this)
    }

    /**
     * Delegates to ChunkReaderDeflatedSet.processData()
     */
    override fun processData(offsetInchunk: Int, buf: ByteArray, off: Int, len: Int) {
        @Suppress("NAME_SHADOWING")
        var off = off
        @Suppress("NAME_SHADOWING")
        var len = len

        if (skipBytes && offsetInchunk < 4) { // only for APNG (sigh)
            var oc = offsetInchunk
            while (oc < 4 && len > 0) {
                skippedBytes[oc] = buf[off]
                oc++
                off++
                len--
            }
        }
        if (len > 0) { // delegate to idatSet
            deflatedChunksSet.processBytes(buf, off, len)
            if (alsoBuffer) { // very rare!
                arraycopy(buf, off, chunkRaw.data!!, read, len)
            }
        }
    }

    /**
     * only a stupid check for fDAT (I wonder how many APGN readers do this)
     */
    override fun chunkDone() {
        if (skipBytes && chunkRaw.id == ID_FDAT) {
            if (seqNumExpected >= 0) {
                val seqNum = readInt4fromBytes(skippedBytes, 0)
                if (seqNum != seqNumExpected) throw PngjInputException(
                    "bad chunk sequence for fDAT chunk $seqNum expected $seqNumExpected"
                )
            }
        }
    }

    override val isFromDeflatedSet: Boolean
        get() = true

    /**
     * In some rare cases you might want to also buffer the data?
     */
    fun setAlsoBuffer() {
        require(read > 0) { "too late" }
        alsoBuffer = true
        chunkRaw.allocData()
    }

    /** only relevant for fDAT  */
    fun setSeqNumExpected(seqNumExpected: Int) {
        this.seqNumExpected = seqNumExpected
    }
}