package org.jetbrains.letsPlot.intern.pngj

import org.jetbrains.letsPlot.intern.pngj.chunks.ChunkRaw
import org.jetbrains.letsPlot.intern.pngj.chunks.PngChunk


/**
 * Factory to create a [PngChunk] from a [ChunkRaw].
 *
 *
 * Used by [PngReader]
 */
interface IChunkFactory {
    /**
     * @param chunkRaw
     * Chunk in raw form. Data can be null if it was skipped or
     * processed directly (eg IDAT)
     * @param imgInfo
     * Not normally necessary, but some chunks want this info
     * @return should never return null.
     */
    fun createChunk(chunkRaw: ChunkRaw, imgInfo: ImageInfo?): PngChunk
}