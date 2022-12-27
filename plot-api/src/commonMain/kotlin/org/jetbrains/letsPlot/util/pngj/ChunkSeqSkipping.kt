/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("KDocUnresolvedReference")

package org.jetbrains.letsPlot.util.pngj

import org.jetbrains.letsPlot.util.pngj.chunks.ChunkRaw
import kotlin.jvm.JvmOverloads

/**
 * This simple reader skips all chunks contents and stores the chunkRaw in a
 * list. Useful to read chunks structure.
 *
 * Optionally the contents might be processed. This doesn't distinguish IDAT
 * chunks
 */
internal class ChunkSeqSkipping @JvmOverloads constructor(skipAll: Boolean = true) : ChunkSeqReader() {
    private val chunks = mutableListOf<ChunkRaw>()
    private var skip = true

    /**
     * @param skipAll
     * if true, contents will be truly skipped, and CRC will not be
     * computed
     */
    init {
        skip = skipAll
    }

    override fun createChunkReaderForNewChunk(id: String, len: Int, offset: Long, skip: Boolean): ChunkReader {
        return object : ChunkReader(len, id, offset, if (skip) ChunkReaderMode.SKIP else ChunkReaderMode.PROCESS) {
            override fun chunkDone() {
                postProcessChunk(this)
            }

            override fun processData(offsetInchunk: Int, buf: ByteArray, off: Int, len: Int) {
                processChunkContent(chunkRaw, offsetInchunk, buf, off, len)
            }
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun processChunkContent(chunkRaw: ChunkRaw?, offsetinChhunk: Int, buf: ByteArray?, off: Int, len: Int) {
        // does nothing
    }

    override fun postProcessChunk(chunkR: ChunkReader) {
        super.postProcessChunk(chunkR)
        chunks.add(chunkR.chunkRaw)
    }

    override fun shouldSkipContent(len: Int, id: String): Boolean {
        return skip
    }

    override fun isIdatKind(id: String?): Boolean {
        return false
    }

    fun getChunks(): List<ChunkRaw> {
        return chunks
    }

    override fun createIdatSet(id: String): DeflatedChunksSet? {
        error("Should not get here")
    }
}