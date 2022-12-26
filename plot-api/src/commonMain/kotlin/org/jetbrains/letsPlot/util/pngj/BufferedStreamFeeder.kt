/*
 * Copyright (c) 2022. JetBrains s.r.o. 
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.util.pngj

import kotlin.jvm.JvmOverloads

/**
 * Reads bytes from an input stream, and feeds a IBytesConsumer.
 */
internal class BufferedStreamFeeder @JvmOverloads constructor(
    private val stream: InputPngStream,
    bufSize: Int = DEFAULTSIZE
) {
    private val buf = ByteArray(if (bufSize < 1) DEFAULTSIZE else bufSize)
    private var pendinglen // bytes read+stored in buf, not yet still sent to IBytesConsumer
            = 0
    private var offset = 0

    /**
     * @return EOF on stream, or close() was called
     */
    private var isEof = false // EOF on inputStream
    private var closeStream = true
    private var bytesRead: Long = 0

    /**
     * @see BufferedStreamFeeder.feed
     */
    fun feed(consumer: IBytesConsumer): Int {
        return feed(consumer, Int.MAX_VALUE)
    }

    /**
     * Tries to feed the consumer with bytes read from the stream, at most
     * maxbytes
     *
     *
     * It can return less than maxbytes (that doesn't mean that the consumer or
     * the input stream is done)
     *
     *
     * Returns 0 if premature ending (no more to read, consumer not done) <br></br>
     * Returns -1 if nothing fed, but consumer is done
     */
    private fun feed(consumer: IBytesConsumer, maxBytes: Int): Int {
        refillBufferIfAppropiate()
        val consumed: Int
        val toFeed = if (maxBytes in 1 until pendinglen) maxBytes else pendinglen
        if (toFeed > 0) {
            consumed = consumer.consume(buf, offset, toFeed) // never returns 0
            if (consumed > 0) {
                offset += consumed
                pendinglen -= consumed
                require(pendinglen >= 0)
            }
        } else {
            // nothing to fed ? premature ending ?
            if (!isEof) throw PngjInputException("This should not happen")
            return if (consumer.isDone) -1 else 0 /* premature ending */
        }
        return if (consumed > 0) {
            consumed
        } else { // read bytes, but consumer refused to eat them ? (rare)
            if (!consumer.isDone) throw PngjInputException("This should not happen!")
            -1
        }
    }

    /**
     * Feeds as much bytes as it can to the consumer, in a loop. <br></br>
     * Returns bytes actually consumed <br></br>
     * This will stop when either the input stream is eof, or when the consumer
     * refuses to eat more bytes. The caller can distinguish both cases by
     * calling [.hasPendingBytes]
     */
    fun feedAll(consumer: IBytesConsumer): Long {
        var n: Long = 0
        while (hasPendingBytes()) {
            val n1 = feed(consumer)
            if (n1 <= 0) break
            n += n1.toLong()
        }
        return n
    }

    /**
     * Feeds exactly nbytes, retrying if necessary
     *
     * @param consumer
     * Consumer
     * @param nbytes
     * Number of bytes
     * @return nbytes if success, 0 if premature input ending, -1 if consumer
     * done
     */
    fun feedFixed(consumer: IBytesConsumer, nbytes: Int): Int {
        var remain = nbytes
        while (remain > 0) {
            val n = feed(consumer, remain)
            if (n <= 0) return n
            remain -= n
        }
        require(remain == 0)
        return nbytes
    }

    /**
     * If there are not pending bytes to be consumed, tries to fill the buffer
     * reading bytes from the stream.
     *
     * If EOF is reached, sets eof=TRUE and calls close()
     *
     * Find in <tt>pendinglen</tt> the amounts of bytes read.
     *
     * If IOException, throws a PngjInputException
     */
    private fun refillBufferIfAppropiate() {
        if (pendinglen > 0 || isEof) return  // only if not pending data
        // try to read
        offset = 0
        pendinglen = stream.read(buf)
        if (pendinglen == 0) throw PngjInputException("This should not happen: stream.read(buf) returned 0") else if (pendinglen < 0) close() // this sets EOF and pendinglen=0
        else bytesRead += pendinglen.toLong()
        // on return, either pendinglen > 0 or eof == true
    }

    /**
     * Returuns true if we have more data to fed the consumer. This might try to
     * grab more bytes from the stream if necessary
     */
    fun hasPendingBytes(): Boolean {
        refillBufferIfAppropiate()
        return pendinglen > 0
    }

    /**
     * @param closeStream
     * If true, the underlying stream will be closed on when close()
     * is called
     */
    fun setCloseStream(closeStream: Boolean) {
        this.closeStream = closeStream
    }

    /**
     * Closes this object.
     *
     * Sets EOF=true, and closes the stream if <tt>closeStream</tt> is true
     *
     * This can be called internally, or from outside.
     *
     * Idempotent, secure, never throws exception.
     */
    fun close() {
        isEof = true
        pendinglen = 0
        offset = 0
        if (closeStream) {
            stream.close()
        }
    }
    companion object {
        private const val DEFAULTSIZE = 16384
    }
}