package org.jetbrains.letsPlot.intern.pngj

/**
 * Exception thrown when reading a PNG.
 */
open class PngjInputException : PngjException {
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
}