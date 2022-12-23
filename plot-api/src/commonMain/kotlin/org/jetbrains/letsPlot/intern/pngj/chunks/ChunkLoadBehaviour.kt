package org.jetbrains.letsPlot.intern.pngj.chunks

/**
 * What to do with ancillary (non-critical) chunks when reading.
 *
 *
 *
 */
enum class ChunkLoadBehaviour {
    /**
     * All non-critical chunks are skipped
     */
    LOAD_CHUNK_NEVER,

    /**
     * Load chunk if "safe to copy"
     */
    LOAD_CHUNK_IF_SAFE,

    /**
     * Load only most important chunk: TRNS
     */
    LOAD_CHUNK_MOST_IMPORTANT,

    /**
     * Load all chunks. <br></br>
     * Notice that other restrictions might apply, see
     * PngReader.skipChunkMaxSize PngReader.skipChunkIds
     */
    LOAD_CHUNK_ALWAYS
}