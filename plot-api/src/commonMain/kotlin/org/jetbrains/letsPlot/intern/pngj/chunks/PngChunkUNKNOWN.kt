package org.jetbrains.letsPlot.intern.pngj.chunks

import org.jetbrains.letsPlot.intern.pngj.ImageInfo


/**
 * Placeholder for UNKNOWN (custom or not) chunks.
 *
 *
 * For PngReader, a chunk is unknown if it's not registered in the chunk factory
 */
class PngChunkUNKNOWN  // unkown, custom or not
    (id: String, info: ImageInfo?) : PngChunkMultiple(id, info) {
    override val orderingConstraint: ChunkOrderingConstraint
        get() = ChunkOrderingConstraint.NONE

    override fun createRawChunk(): ChunkRaw {
        error("Unsupported operation")
    }

    override fun parseFromRaw(chunk: ChunkRaw) {}
}
