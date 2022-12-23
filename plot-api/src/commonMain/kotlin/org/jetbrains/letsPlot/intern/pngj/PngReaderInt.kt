package org.jetbrains.letsPlot.intern.pngj

import org.jetbrains.letsPlot.intern.pngj.utils.InputStream

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
class PngReaderInt(inputStream: InputStream) : PngReader(inputStream) {

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