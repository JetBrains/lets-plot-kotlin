/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.util.pngj

import java.util.zip.Adler32
import java.util.zip.CRC32

internal actual object Zip {
    actual fun compressBytes(ori: ByteArray, offset: Int, len: Int, compress: Boolean): ByteArray {
        return try {
            val tmpbuffer = ByteArray(4096)

            val inb: java.io.ByteArrayInputStream = java.io.ByteArrayInputStream(ori, offset, len)
            val `in`: java.io.InputStream = if (compress) inb else java.util.zip.InflaterInputStream(inb)
            val outb: java.io.ByteArrayOutputStream = java.io.ByteArrayOutputStream()
            val out: java.io.OutputStream = if (compress) java.util.zip.DeflaterOutputStream(outb) else outb
            //synchronized(tmpbuffer) {
            var len1: Int
            while (`in`.read(tmpbuffer).also<Int> { len1 = it } > 0) {
                out.write(tmpbuffer, 0, len1)
            }        //}
            `in`.close()
            out.close()
            outb.toByteArray()
        } catch (e: java.lang.Exception) {
            throw PngjException(e)
        }
    }

    actual val IS_BYTE_ORDER_BIG_ENDIAN: Boolean
        get() = java.nio.ByteOrder.nativeOrder() == java.nio.ByteOrder.BIG_ENDIAN

    actual fun newDeflater(deflaterCompLevel: Int): Deflater {
        return object : Deflater {
            private val deflater = java.util.zip.Deflater(deflaterCompLevel)
            override fun setStrategy(deflaterStrategy: Int) {
                deflater.setStrategy(deflaterStrategy)
            }

            override fun finished(): Boolean {
                return deflater.finished()
            }

            override fun setInput(data: ByteArray, off: Int, len: Int) {
                deflater.setInput(data, off, len)
            }

            override fun needsInput(): Boolean {
                return deflater.needsInput()
            }

            override fun deflate(buf: ByteArray, off: Int, n: Int): Int {
                return deflater.deflate(buf, off, n)
            }

            override fun finish() {
                deflater.finish()
            }

            override fun end() {
                deflater.end()
            }

            override fun reset() {
                deflater.reset()
            }
        }
    }

    actual fun newInflater(): Inflater {
        return object : Inflater {
            private val inflater = java.util.zip.Inflater()
            override fun inflate(output: ByteArray, off: Int, len: Int): Int {
                return inflater.inflate(output, off, len)
            }

            override fun setInput(input: ByteArray, off: Int, len: Int) {
                inflater.setInput(input, off, len)
            }

            override fun end() {
                inflater.end()
            }

            override val needsDictionary: Boolean
                get() = inflater.needsDictionary()
            override val needsInput: Boolean
                get() = inflater.needsInput()
            override val finished: Boolean
                get() = inflater.finished()
        }
    }

    actual fun crc32(): Checksum = Wrapper(CRC32())
    actual fun adler32(): Checksum = Wrapper(Adler32())

    private class Wrapper(
        private val checksum: java.util.zip.Checksum
    ) : Checksum {
        override fun update(b: Int) {
            checksum.update(b)
        }

        override fun update(b: ByteArray, off: Int, len: Int) {
            checksum.update(b, off, len)
        }

        override val value: Long
            get() = checksum.value

        override fun reset() {
            checksum.reset()
        }
    }
}