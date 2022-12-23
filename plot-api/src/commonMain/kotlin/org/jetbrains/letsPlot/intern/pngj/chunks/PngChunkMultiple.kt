package org.jetbrains.letsPlot.intern.pngj.chunks

import org.jetbrains.letsPlot.intern.pngj.ImageInfo


/**
 * PNG chunk type (abstract) that allows multiple instances in same image.
 */
abstract class PngChunkMultiple protected constructor(id: String, imgInfo: ImageInfo?) : PngChunk(id, imgInfo) {
    override fun allowsMultiple(): Boolean {
        return true
    }
    /**
     * NOTE: this chunk uses the default Object's equals() hashCode()
     * implementation.
     *
     * This is the right thing to do, normally.
     *
     * This is important, eg see ChunkList.removeFromList()
     */
}