/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("unused")
package org.jetbrains.letsPlot.util.pngj

/**
 * Trivial extension of [PngReader] that uses [ImageLineByte]
 *
 *
 * The factory is set at construction time. Remember that this could still be
 * changed at runtime.
 */
internal class PngReaderByte(inputStream: InputPngStream) : PngReader(inputStream) {
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