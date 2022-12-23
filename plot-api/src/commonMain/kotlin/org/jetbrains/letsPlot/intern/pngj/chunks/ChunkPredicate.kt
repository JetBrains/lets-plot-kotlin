package org.jetbrains.letsPlot.intern.pngj.chunks

/**
 * Decides if another chunk "matches", according to some criterion
 */
interface ChunkPredicate {
    /**
     * The other chunk matches with this one
     *
     * @param chunk
     * @return true if match
     */
    fun match(chunk: PngChunk): Boolean
}