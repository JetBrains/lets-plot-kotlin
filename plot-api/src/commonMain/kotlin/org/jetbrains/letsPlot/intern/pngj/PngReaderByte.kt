@file:Suppress("unused")
package org.jetbrains.letsPlot.intern.pngj

import org.jetbrains.letsPlot.intern.pngj.utils.InputStream

/**
 * Trivial extension of [PngReader] that uses [ImageLineByte]
 *
 *
 * The factory is set at construction time. Remember that this could still be
 * changed at runtime.
 */
class PngReaderByte(inputStream: InputStream) : PngReader(inputStream) {
    init {
        setLineSetFactory(ImageLineSetDefault.factoryByte)
    }

    /**
     * Utility method that casts [.readRow] return to
     * [ImageLineByte].
     */
    fun readRowByte(): ImageLineByte {
        return readRow() as ImageLineByte
    }
}