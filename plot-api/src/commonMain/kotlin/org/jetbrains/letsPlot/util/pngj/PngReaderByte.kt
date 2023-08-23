/*
 * Copyright (c) 2023 JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 *
 * This file has been modified by JetBrains : Java code has been converted to Kotlin code.
 *
 * THE FOLLOWING IS THE COPYRIGHT OF THE ORIGINAL DOCUMENT:
 *
 * Copyright (c) 2009-2012, Hernán J. González.
 * Licensed under the Apache License, Version 2.0.
 *
 * The original PNGJ library is written in Java and can be found here: [PNGJ](https://github.com/leonbloy/pngj).
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