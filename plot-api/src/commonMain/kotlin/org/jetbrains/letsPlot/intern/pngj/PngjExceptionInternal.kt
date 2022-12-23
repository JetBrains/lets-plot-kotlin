package org.jetbrains.letsPlot.intern.pngj

/**
 * Exception for anomalous internal problems (sort of asserts) that point to
 * some issue with the library
 *
 * @author Hernan J Gonzalez
 */
class PngjExceptionInternal(message: String?) : Exception(message)