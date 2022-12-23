package org.jetbrains.letsPlot.intern.pngj

/**
 * Exception thrown by writing process
 */
class PngjOutputException : PngjException {
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
}