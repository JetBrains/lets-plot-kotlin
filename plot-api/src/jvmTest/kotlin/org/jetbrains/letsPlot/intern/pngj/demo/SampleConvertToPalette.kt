package org.jetbrains.letsPlot.intern.pngj.demo

import org.jetbrains.letsPlot.intern.pngj.ImageInfo
import org.jetbrains.letsPlot.intern.pngj.ImageLineInt
import org.jetbrains.letsPlot.intern.pngj.PngReader
import org.jetbrains.letsPlot.intern.pngj.PngWriter
import org.jetbrains.letsPlot.intern.pngj.TestSupport.istream
import org.jetbrains.letsPlot.intern.pngj.TestSupport.ostream
import org.jetbrains.letsPlot.intern.pngj.chunks.ChunkCopyBehaviour
import org.jetbrains.letsPlot.intern.pngj.chunks.PngChunkTRNS
import org.jetbrains.letsPlot.intern.pngj.demo.NeuQuant.Companion.createPixelGetterFromPngReader
import java.io.File
import kotlin.system.exitProcess

/**
 * Example: convert a RGB8/RGBA8 image to palette using Kohonen quantizer
 * Supports Alpha Does not support dithering yet
 *
 * THIS IS JUST AN EXAMPLE, IT'S NOT OPTIMIZED NOR TESTED
 */
object SampleConvertToPalette {
    private fun convertPal(origFilename: File, destFilename: File) {
        if (origFilename == destFilename) throw RuntimeException("source same as target!")
        // first pass
        var pngr = PngReader(istream(origFilename))
        val channels = pngr.imgInfo!!.channels
        if (channels < 3 || pngr.imgInfo!!.bitDepth != 8) throw RuntimeException("This method is for RGB8/RGBA8 images")
        val cuant = NeuQuant(
            pngr.imgInfo!!.cols, pngr.imgInfo!!.rows,
            createPixelGetterFromPngReader(pngr)
        )
        val useTransparency = pngr.imgInfo!!.alpha
        cuant.isParReserveAlphaColor = useTransparency
        cuant.run()
        pngr.end()
        pngr = PngReader(istream(origFilename))
        val imiw = ImageInfo(pngr.imgInfo!!.cols, pngr.imgInfo!!.rows, 8,
            alpha = false,
            greyscale = false,
            indexed = true
        )
        // second pass
        val pngw = PngWriter(ostream(destFilename), imiw)
        val palette = pngw.getMetadata().createPLTEChunk()
        val ncolors = cuant.colorCount
        palette.setNentries(ncolors)
        for (i in 0 until ncolors) {
            val col = cuant.getColor(i)
            palette.setEntry(i, col!![0], col[1], col[2])
        }
        val transparentIndex = cuant.transparentIndex
        if (transparentIndex >= 0) {
            val transparent = PngChunkTRNS(imiw)
            transparent.setIndexEntryAsTransparent(transparentIndex)
            pngw.getChunksList().queue(transparent)
        }
        pngw.copyChunksFrom(pngr.chunksList, ChunkCopyBehaviour.COPY_ALL_SAFE)
        val line = ImageLineInt(imiw)
        for (row in 0 until pngr.imgInfo!!.rows) {
            val l1 = pngr.readRow(row) as ImageLineInt
            var index: Int
            val scanline = l1.scanline
            var j = 0
            var k = 0
            while (j < pngr.imgInfo!!.cols) {
                index =
                    if (!useTransparency) cuant.lookup(scanline[k], scanline[k + 1], scanline[k + 2]) else cuant.lookup(
                        scanline[k],
                        scanline[k + 1],
                        scanline[k + 2],
                        scanline[k + 3]
                    )
                line.scanline[j] = index
                j++
                k += channels
            }
            pngw.writeRow(line, row)
        }
        pngw.end()
        pngr.end()
        println("colours: $ncolors")
    }

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        if (args.size != 2 || args[0] == args[1]) {
            System.err.println("Arguments: [pngsrc] [pngdest]")
            exitProcess(1)
        }
        convertPal(File(args[0]), File(args[1]))
        println("Done: " + args[1])
    }
}