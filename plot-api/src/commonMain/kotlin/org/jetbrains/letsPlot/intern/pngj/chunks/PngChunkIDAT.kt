package org.jetbrains.letsPlot.intern.pngj.chunks

import org.jetbrains.letsPlot.intern.pngj.ImageInfo

/**
 * IDAT chunk.
 *
 *
 * see http://www.w3.org/TR/PNG/#11IDAT
 *
 *
 * This is dummy placeholder - we write/read this chunk (actually several) by
 * special code.
 */
class PngChunkIDAT  // http://www.w3.org/TR/PNG/#11IDAT
    (i: ImageInfo?) : PngChunkMultiple(ID, i) {
    override val orderingConstraint: ChunkOrderingConstraint
        get() = ChunkOrderingConstraint.NA

    override fun createRawChunk(): ChunkRaw { // does nothing
        return createEmptyChunk(0, false)
    }

    override fun parseFromRaw(chunk: ChunkRaw) { // does nothing
    }

    companion object {
        const val ID: String = ChunkHelper.IDAT
    }
}