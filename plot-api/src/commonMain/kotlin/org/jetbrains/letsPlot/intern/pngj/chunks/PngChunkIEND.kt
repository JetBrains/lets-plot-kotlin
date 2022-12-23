package org.jetbrains.letsPlot.intern.pngj.chunks

import org.jetbrains.letsPlot.intern.pngj.ImageInfo

/**
 * IEND chunk.
 *
 *
 * see http://www.w3.org/TR/PNG/#11IEND
 */
class PngChunkIEND  // http://www.w3.org/TR/PNG/#11IEND
// this is a dummy placeholder
    (info: ImageInfo?) : PngChunkSingle(ID, info) {
    override val orderingConstraint: ChunkOrderingConstraint
        get() = ChunkOrderingConstraint.NA

    override fun createRawChunk(): ChunkRaw {
        return ChunkRaw(0, ChunkHelper.b_IEND, false)
    }

    override fun parseFromRaw(chunk: ChunkRaw) {
        // this is not used
    }

    companion object {
        const val ID: String = ChunkHelper.IEND
    }
}