/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 * 
 * This file has been modified by JetBrains : Java code has been converted to Kotlin code.
 * */

@file:Suppress("unused")
package org.jetbrains.letsPlot.util.pngj

import org.jetbrains.letsPlot.util.pngj.chunks.*

/**
 * Adds to ChunkSeqReader the storing of PngChunk, with a PngFactory, and
 * imageInfo + deinterlacer.
 *
 *
 * Most usual PNG reading should use this class, or a [PngReader], which
 * is a thin wrapper over this.
 */
internal open class ChunkSeqReaderPng(private val isCallbackMode: Boolean) : ChunkSeqReader() {
    lateinit var imageInfo // initialized at parsing the IHDR
            : ImageInfo
        protected set
    var curImgInfo // can vary, for apng
            : ImageInfo? = null
        protected set
    var deinterlacer: Deinterlacer? = null
        protected set

    /**
     * From 0 to 6, see ChunksList CHUNK_GROUP_*
     */
    var currentChunkGroup = -1
        protected set

    /**
     * All chunks, but some of them can have the buffer empty (IDAT and skipped)
     */
    var chunksList: ChunksList? = null
    private var bytesChunksLoaded: Long = 0 // bytes loaded from buffered chunks non-critical chunks (data only)
    var isCheckCrc = true

    // --- parameters to be set prior to reading ---
    private var includeNonBufferedChunks = false
    private val chunksToSkip = mutableSetOf<String>()
    var maxTotalBytesRead: Long = 0
    var skipChunkMaxSize: Long = 0
    var maxBytesMetadata: Long = 0
    private var chunkFactory: IChunkFactory
    private var chunkLoadBehaviour: ChunkLoadBehaviour = ChunkLoadBehaviour.LOAD_CHUNK_ALWAYS

    init {
        chunkFactory = ChunkFactory() // default factory
    }

    // call just after beginging new chunk parse
    private fun updateAndCheckChunkGroup(id: String) {
        currentChunkGroup = if (id == PngChunkIHDR.ID) { // IDHR
            if (currentChunkGroup < 0) ChunksList.CHUNK_GROUP_0_IDHR else throw PngjInputException("unexpected chunk $id")
        } else if (id == PngChunkPLTE.ID) { // PLTE
            if (currentChunkGroup == ChunksList.CHUNK_GROUP_0_IDHR
                || currentChunkGroup == ChunksList.CHUNK_GROUP_1_AFTERIDHR
            ) ChunksList.CHUNK_GROUP_2_PLTE else throw PngjInputException(
                "unexpected chunk here $id"
            )
        } else if (id == PngChunkIDAT.ID) { // IDAT (no necessarily the first)
            if (currentChunkGroup >= ChunksList.CHUNK_GROUP_0_IDHR
                && currentChunkGroup <= ChunksList.CHUNK_GROUP_4_IDAT
            ) ChunksList.CHUNK_GROUP_4_IDAT else throw PngjInputException(
                "unexpected chunk $id"
            )
        } else if (id == PngChunkIEND.ID) { // END
            if (currentChunkGroup >= ChunksList.CHUNK_GROUP_4_IDAT) ChunksList.CHUNK_GROUP_6_END else throw PngjInputException(
                "unexpected chunk $id"
            )
        } else { // ancillary
            if (currentChunkGroup <= ChunksList.CHUNK_GROUP_1_AFTERIDHR) ChunksList.CHUNK_GROUP_1_AFTERIDHR else if (currentChunkGroup <= ChunksList.CHUNK_GROUP_3_AFTERPLTE) ChunksList.CHUNK_GROUP_3_AFTERPLTE else ChunksList.CHUNK_GROUP_5_AFTERIDAT
        }
    }

    override fun shouldSkipContent(len: Int, id: String): Boolean {
        if (super.shouldSkipContent(len, id)) return true
        if (maxTotalBytesRead > 0 && len + bytesCount > maxTotalBytesRead) throw PngjInputException(
            "Maximum total bytes to read exceeeded: " + maxTotalBytesRead + " offset:"
                    + bytesCount + " len=" + len
        )
        if (chunksToSkip.contains(id)) return true // specific skip
        if (ChunkHelper.isCritical(id)) return false // critical chunks are never skipped
        if (skipChunkMaxSize in 1 until len) return true // too big chunk
        if (maxBytesMetadata > 0 && len > maxBytesMetadata - bytesChunksLoaded) return true // too much ancillary chunks loaded
        when (chunkLoadBehaviour) {
            ChunkLoadBehaviour.LOAD_CHUNK_IF_SAFE -> if (!ChunkHelper.isSafeToCopy(id)) return true
            ChunkLoadBehaviour.LOAD_CHUNK_NEVER -> return true
            else -> {}
        }
        return false
    }

    fun setChunksToSkip(vararg chunksToSkip: String) {
        this.chunksToSkip.clear()
        for (c in chunksToSkip) this.chunksToSkip.add(c)
    }

    fun addChunkToSkip(chunkToSkip: String) {
        chunksToSkip.add(chunkToSkip)
    }

    fun dontSkipChunk(chunkToSkip: String) {
        chunksToSkip.remove(chunkToSkip)
    }

    fun firstChunksNotYetRead(): Boolean {
        return currentChunkGroup < ChunksList.CHUNK_GROUP_4_IDAT
    }

    override fun postProcessChunk(chunkR: ChunkReader) {
        // PngHelperInternal.debug("postProcessChunk " + chunkR.getChunkRaw().id);
        super.postProcessChunk(chunkR)
        if (chunkR.chunkRaw.id == PngChunkIHDR.ID) {
            val ch = PngChunkIHDR(null)
            ch.parseFromRaw(chunkR.chunkRaw)
            imageInfo = ch.createImageInfo()
            curImgInfo = imageInfo
            if (ch.isInterlaced()) deinterlacer = Deinterlacer(curImgInfo!!)
            chunksList = ChunksList(imageInfo)
        }
        if (chunkR.mode == ChunkReader.ChunkReaderMode.BUFFER && countChunkTypeAsAncillary(chunkR.chunkRaw.id)) {
            bytesChunksLoaded += chunkR.chunkRaw.len.toLong()
        }
        if (chunkR.mode == ChunkReader.ChunkReaderMode.BUFFER || includeNonBufferedChunks) {
            try {
                val chunk: PngChunk = chunkFactory.createChunk(chunkR.chunkRaw, imageInfo)
                chunksList!!.appendReadChunk(chunk, currentChunkGroup)
            } catch (e: PngjException) {
                throw e
            }
        }
        if (isDone) {
            processEndPng()
        }
    }

    private fun countChunkTypeAsAncillary(id: String): Boolean {
        return !ChunkHelper.isCritical(id)
    }

    override fun createIdatSet(id: String): DeflatedChunksSet {
        return IdatSet(id, isCallbackMode, curImgInfo!!, deinterlacer)
    }

    val idatSet: IdatSet?
        get() {
            val c: DeflatedChunksSet? = getCurDeflatedSet()
            return if (c is IdatSet) c else null
        }

    override fun isIdatKind(id: String?): Boolean {
        return id == PngChunkIDAT.ID
    }

    /**
     * sets a custom chunk factory. This is typically called with a custom class
     * extends ChunkFactory, to adds custom chunks to the default well-know ones
     *
     * @param chunkFactory
     */
    fun setChunkFactory(chunkFactory: IChunkFactory) {
        this.chunkFactory = chunkFactory
    }

    /**
     * Things to be done after closed.
     */
    private fun processEndPng() {
        // nothing to do
    }

    val isInterlaced: Boolean
        get() = deinterlacer != null

    override fun startNewChunk(len: Int, id: String, offset: Long) {
        updateAndCheckChunkGroup(id)
        super.startNewChunk(len, id, offset)
    }

    override fun close() {
        if (currentChunkGroup != ChunksList.CHUNK_GROUP_6_END) // this could only happen if forced close
            currentChunkGroup = ChunksList.CHUNK_GROUP_6_END
        super.close()
    }

    val chunks: List<PngChunk>
        get() = chunksList!!.chunks

    override fun shouldCheckCrc(len: Int, id: String?): Boolean {
        return isCheckCrc
    }

    fun getChunksToSkip(): Set<String?> {
        return chunksToSkip
    }

    fun setChunkLoadBehaviour(chunkLoadBehaviour: ChunkLoadBehaviour) {
        this.chunkLoadBehaviour = chunkLoadBehaviour
    }

    fun updateCurImgInfo(iminfo: ImageInfo) {
        if (iminfo != curImgInfo) {
            curImgInfo = iminfo
        }
        if (deinterlacer != null) deinterlacer = Deinterlacer(curImgInfo!!) // we could reset it, but...
    }

    /**
     * If true, the chunks with no data (because skipped or because processed
     * like IDAT-type) are still stored in the PngChunks list, which might be
     * more informative.
     *
     * Setting this to false saves a few bytes
     *
     * Default: false
     *
     * @param includeNonBufferedChunks
     */
    fun setIncludeNonBufferedChunks(includeNonBufferedChunks: Boolean) {
        this.includeNonBufferedChunks = includeNonBufferedChunks
    }
}