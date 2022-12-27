package org.jetbrains.letsPlot.util.pngj

/**
 * Packs information of current row. Only used internally
 */
internal class RowInfo(imgInfo: ImageInfo, deinterlacer: Deinterlacer?) {
    val imgInfo: ImageInfo
    val deinterlacer: Deinterlacer?
    val imode // Interlaced
            : Boolean
    var dY = 0
    var dX = 0
    var oY = 0
    var oX // current step and offset (in pixels)
            = 0
    var rowNseq // row number (from 0) in sequential read order
            = 0
    var rowNreal // row number in the real image
            = 0
    var rowNsubImg // current row in the virtual subsampled image; this increments (by 1) from 0 to rows/dy 7 times
            = 0
    var rowsSubImg = 0
    var colsSubImg // size of current subimage , in pixels
            = 0
    var bytesRow = 0
    var pass // 1-7
            = 0
    lateinit var buf // non-deep copy
            : ByteArray
    var buflen // valid bytes in buffer (include filter byte)
            = 0

    init {
        this.imgInfo = imgInfo
        this.deinterlacer = deinterlacer
        imode = deinterlacer != null
    }

    fun update(rowseq: Int) {
        rowNseq = rowseq
        if (imode) {
            pass = deinterlacer!!.pass
            dX = deinterlacer.dX
            dY = deinterlacer.dY
            oX = deinterlacer.oX
            oY = deinterlacer.oY
            rowNreal = deinterlacer.currRowReal
            rowNsubImg = deinterlacer.currRowSubimg
            rowsSubImg = deinterlacer.rows
            colsSubImg = deinterlacer.cols
            bytesRow = (imgInfo.bitspPixel * colsSubImg + 7) / 8
        } else {
            pass = 1
            dY = 1
            dX = dY
            oY = 0
            oX = oY
            rowNsubImg = rowseq
            rowNreal = rowNsubImg
            rowsSubImg = imgInfo.rows
            colsSubImg = imgInfo.cols
            bytesRow = imgInfo.bytesPerRow
        }
    }

    fun updateBuf(buf: ByteArray, buflen: Int) {
        this.buf = buf
        this.buflen = buflen
    }
}