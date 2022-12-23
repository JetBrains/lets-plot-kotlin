package org.jetbrains.letsPlot.intern.pngj

/**
 * Exception thrown by bad signature (not a PNG file)
 */
class PngjBadSignature(message: String) : PngjInputException(message)