package org.jetbrains.letsPlot.intern.pngj

/**
 * Generic exception for this library. It's a RuntimeException (unchecked)
 */
open class PngjException : Exception {
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
}