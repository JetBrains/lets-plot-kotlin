/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.util.pngj

internal actual object Zip {
    actual fun compressBytes(
        ori: ByteArray,
        offset: Int,
        len: Int,
        compress: Boolean
    ): ByteArray {
        TODO("Not yet implemented")
    }

    actual fun newDeflater(deflaterCompLevel: Int): Deflater {
        TODO("Not yet implemented")
    }

    actual fun newInflater(): Inflater {
        TODO("Not yet implemented")
    }

    actual fun crc32(): Checksum {
        TODO("Not yet implemented")
    }

    actual fun adler32(): Checksum {
        TODO("Not yet implemented")
    }

    actual val IS_BYTE_ORDER_BIG_ENDIAN: Boolean
        get() = TODO("Not yet implemented")
}