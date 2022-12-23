package org.jetbrains.letsPlot.intern.pngj

/**
 * Image Line factory.
 */
interface IImageLineFactory<T : IImageLine> {
    fun createImageLine(iminfo: ImageInfo): T
}