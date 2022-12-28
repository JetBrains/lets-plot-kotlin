/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 *
 * This file has been modified by JetBrains : Java code has been converted to Kotlin code.
 * */

@file:Suppress("unused")
package org.jetbrains.letsPlot.util.pngj

import org.jetbrains.letsPlot.util.pngj.chunks.PngChunk

/**
 * This class allows to use a simple PNG reader as an input filter, wrapping a
 * ChunkSeqReaderPng in callback mode.
 *
 * In this sample implementation, all IDAT chunks are skipped and the rest are
 * stored. An example of use, that lets us grab the Metadata and let the pixels
 * go towards a BufferedImage:
 *
 *
 * <pre class="code">
 * PngReaderFilter reader = new PngReaderFilter(new FileInputStream(&quot;image.png&quot;));
 * BufferedImage image1 = ImageIO.read(reader);
 * reader.readUntilEndAndClose(); // in case ImageIO.read() does not read the traling chunks (it happens)
</pre> *
 *
 */
internal class PngReaderFilter(arg0: InputPngStream) : AbstractInputPngStream(arg0) {
    val chunkseq: ChunkSeqReaderPng

    init {
        chunkseq = createChunkSequenceReader()
    }

    protected fun createChunkSequenceReader(): ChunkSeqReaderPng {
        return object : ChunkSeqReaderPng(true) {
            public override fun shouldSkipContent(len: Int, id: String): Boolean {
                return super.shouldSkipContent(len, id) || id == "IDAT"
            }

            override fun shouldCheckCrc(len: Int, id: String?): Boolean {
                return false
            }

        }
    }

    override fun close() {
        super.close()
        chunkseq.close()
    }

    override fun read(): Int {
        val r: Int = super.read()
        if (r > 0) chunkseq.feedAll(byteArrayOf(r.toByte()), 0, 1)
        return r
    }

    override fun read(b: ByteArray, off: Int, len: Int): Int {
        val res: Int = super.read(b, off, len)
        if (res > 0) chunkseq.feedAll(b, off, res)
        return res
    }

    override fun read(outBuffer: ByteArray): Int {
        val res: Int = super.read(outBuffer)
        if (res > 0) chunkseq.feedAll(outBuffer, 0, res)
        return res
    }

    fun readUntilEndAndClose() {
        val br = BufferedStreamFeeder(this.stream)
        while (!chunkseq.isDone && br.hasPendingBytes()) {
            if (br.feed(chunkseq) < 1) break
        }
        br.close()
        close()
    }

    val chunksList: List<PngChunk>
        get() = chunkseq.chunks
}