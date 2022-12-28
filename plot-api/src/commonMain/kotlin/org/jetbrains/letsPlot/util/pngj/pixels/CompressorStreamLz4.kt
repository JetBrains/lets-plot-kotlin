/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 * 
 * This file has been modified by JetBrains : Java code has been converted to Kotlin code.
 * */

@file:Suppress("unused", "UNUSED_PARAMETER")
package org.jetbrains.letsPlot.util.pngj.pixels

import org.jetbrains.letsPlot.util.pngj.Deflater
import org.jetbrains.letsPlot.util.pngj.IdatChunkWriter
import org.jetbrains.letsPlot.util.pngj.PngjOutputException
import org.jetbrains.letsPlot.util.pngj.arraycopy

/**
 * This class uses a quick compressor to get a rough estimate of deflate
 * compression ratio.
 *
 * This just ignores the outputStream, and the deflater related parameters
 */
internal class CompressorStreamLz4(os: IdatChunkWriter?, maxBlockLen: Int, totalLen: Long) :
    CompressorStream(os, maxBlockLen, totalLen) {
    private val lz4: DeflaterEstimatorLz4 = DeflaterEstimatorLz4()
    private var buf // lazily allocated, only if needed
            : ByteArray? = null
    private val buffer_size: Int

    // bufpos=bytes in buffer yet not compressed (bytesIn include this)
    private var inbuf = 0

    init {
        buffer_size = (if (totalLen > MAX_BUFFER_SIZE) MAX_BUFFER_SIZE else totalLen).toInt()
    }

    constructor(os: IdatChunkWriter?, maxBlockLen: Int, totalLen: Long, def: Deflater) : this(
        os,
        maxBlockLen,
        totalLen
    ) // edlfater ignored

    constructor(
        os: IdatChunkWriter?, maxBlockLen: Int, totalLen: Long, deflaterCompLevel: Int,
        deflaterStrategy: Int
    ) : this(os, maxBlockLen, totalLen) // paramters ignored

    override fun mywrite(data: ByteArray, off: Int, len: Int) {
        @Suppress("NAME_SHADOWING")
        var off = off
        @Suppress("NAME_SHADOWING")
        var len = len
        if (len == 0) return
        if (isDone || isClosed) throw PngjOutputException("write beyond end of stream")
        bytesIn += len.toLong()
        while (len > 0) {
            if (inbuf == 0 && (len >= MAX_BUFFER_SIZE || bytesIn == totalbytes)) {
                // direct copy (buffer might be null or empty)
                bytesOut += lz4.compressEstim(data, off, len)
                len = 0
            } else {
                if (buf == null) buf = ByteArray(buffer_size)
                val len1 = if (inbuf + len <= buffer_size) len else buffer_size - inbuf // to copy
                if (len1 > 0) arraycopy(data, off, buf!!, inbuf, len1)
                inbuf += len1
                len -= len1
                off += len1
                if (inbuf == buffer_size) compressFromBuffer()
            }
        }
    }

    private fun compressFromBuffer() {
        if (inbuf > 0) {
            bytesOut += lz4.compressEstim(buf!!, 0, inbuf)
            inbuf = 0
        }
    }

    override fun done() {
        if (!isDone) {
            compressFromBuffer()
            isDone = true
        }
    }

    override fun close() {
        done()
        if (!isClosed) {
            super.close()
            buf = null
        }
    }

    companion object {
        private const val MAX_BUFFER_SIZE = 16000
    }
}