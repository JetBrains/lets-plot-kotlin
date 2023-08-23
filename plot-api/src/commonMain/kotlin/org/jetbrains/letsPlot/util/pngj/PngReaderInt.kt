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

package org.jetbrains.letsPlot.util.pngj

/**
 * Trivial extension of [PngReader] that uses [ImageLineInt].
 *
 *
 * In the current implementation this is quite dummy/redundant, because (for
 * backward compatibility) PngReader already uses a [ImageLineInt].
 *
 *
 * The factory is set at construction time. Remember that this could still be
 * changed at runtime.
 */
internal class PngReaderInt(inputStream: InputPngStream) : PngReader(inputStream) {

    /**
     * Utility method that casts the IImageLine to a ImageLineInt
     *
     * This only make sense for this concrete class
     *
     */
    fun readRowInt(): ImageLineInt {
        val line: IImageLine = readRow()
        return if (line is ImageLineInt) line else throw PngjException("This is not a ImageLineInt : " + line::class)
    }
}