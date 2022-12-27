/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.util.pngj

/**
 * This object process the concatenation of IDAT chunks.
 *
 *
 * It extends [DeflatedChunksSet], adding the intelligence to unfilter
 * rows, and to understand row lenghts in terms of ImageInfo and (eventually)
 * Deinterlacer
 */
internal class IdatSet(
    id: String,
    private val callbackMode: Boolean,
    iminfo: ImageInfo,
    deinterlacer: Deinterlacer?,
    inf: Inflater?,
    buffer: ByteArray?
) : DeflatedChunksSet(
    id, callbackMode, if (deinterlacer != null) deinterlacer.bytesToRead + 1 else iminfo.bytesPerRow + 1,
    iminfo.bytesPerRow + 1, inf, buffer
) {
    /**
     * Unfiltered row.
     *
     *
     * This should be called only if [.isRowReady] returns true.
     *
     *
     * To get real length, use [.getRowLen]
     *
     *
     *
     * @return Unfiltered row, includes filter byte
     */
    var unfilteredRow: ByteArray? = null
        private set
    private var rowUnfilteredPrev: ByteArray? = null
    private val imgInfo // in the case of APNG this is the frame image
            : ImageInfo
    private val deinterlacer: Deinterlacer?
    internal val rowinfo // info for the last processed row, for debug
            : RowInfo

    /**
     * Only for debug/stats
     *
     * @return Array of 5 integers (sum equal numbers of rows) counting each
     * filter use
     */
    private var filterUseStat = IntArray(5) // for stats

    /**
     * @param id
     * Chunk id (first chunk), should be shared by all concatenated
     * chunks
     * @param iminfo
     * Image info
     * @param deinterlacer
     * Not null if interlaced
     */
    constructor(id: String, callbackMode: Boolean, iminfo: ImageInfo, deinterlacer: Deinterlacer?) : this(
        id,
        callbackMode,
        iminfo,
        deinterlacer,
        null,
        null
    )

    /**
     * Special constructor with preallocated buffer.
     *
     *
     *
     *
     * Same as [.IdatSet], but you can
     * pass a Inflater (will be reset internally), and a buffer (will be used
     * only if size is enough)
     */
    init {
        imgInfo = iminfo
        this.deinterlacer = deinterlacer
        rowinfo = RowInfo(iminfo, deinterlacer)
        println("Creating IDAT set ")
    }

    /**
     * Applies PNG un-filter to inflated raw line. Result in
     * [.getUnfilteredRow] [.getRowLen]
     */
    private fun unfilterRow() {
        unfilterRow(rowinfo.bytesRow)
    }

    // nbytes: NOT including the filter byte. leaves result in rowUnfiltered
    private fun unfilterRow(nbytes: Int) {
        if (unfilteredRow == null || unfilteredRow!!.size < row!!.size) {
            unfilteredRow = ByteArray(row!!.size)
            rowUnfilteredPrev = ByteArray(row!!.size)
        }
        if (rowinfo.rowNsubImg == 0) fill(unfilteredRow!!, 0.toByte()) // see swap that follows
        // swap
        val tmp = unfilteredRow!!
        unfilteredRow = rowUnfilteredPrev
        rowUnfilteredPrev = tmp
        val ftn: Int = row!![0].toInt()
        if (!FilterType.isValidStandard(ftn)) throw PngjInputException("Filter type $ftn invalid")
        val ft: FilterType = FilterType.getByVal(ftn)
        filterUseStat[ftn]++
        unfilteredRow!![0] = row!![0] // we copy the filter type, can be useful
        when (ft) {
            FilterType.FILTER_NONE -> unfilterRowNone(nbytes)
            FilterType.FILTER_SUB -> unfilterRowSub(nbytes)
            FilterType.FILTER_UP -> unfilterRowUp(nbytes)
            FilterType.FILTER_AVERAGE -> unfilterRowAverage(nbytes)
            FilterType.FILTER_PAETH -> unfilterRowPaeth(nbytes)
            else -> throw PngjInputException("Filter type $ftn not implemented")
        }
    }

    private fun unfilterRowAverage(nbytes: Int) {
        var x: Int
        var j: Int = 1 - imgInfo.bytesPixel
        var i = 1
        while (i <= nbytes) {
            x = if (j > 0) unfilteredRow!![j].toInt() and 0xff else 0
            unfilteredRow!![i] = (row!![i] + (x + (rowUnfilteredPrev!![i].toInt() and 0xFF)) / 2).toByte()
            i++
            j++
        }
    }

    private fun unfilterRowNone(nbytes: Int) {
        for (i in 1..nbytes) {
            unfilteredRow!![i] = row!![i]
        }
    }

    private fun unfilterRowPaeth(nbytes: Int) {
        var x: Int
        var y: Int
        var j: Int = 1 - imgInfo.bytesPixel
        var i = 1
        while (i <= nbytes) {
            x = if (j > 0) unfilteredRow!![j].toInt() and 0xFF else 0
            y = if (j > 0) rowUnfilteredPrev!![j].toInt() and 0xFF else 0
            unfilteredRow!![i] = (row!![i]
                    + PngHelperInternal.filterPaethPredictor(x, rowUnfilteredPrev!![i].toInt() and 0xFF, y)).toByte()
            i++
            j++
        }
    }

    private fun unfilterRowSub(nbytes: Int) {
        var i = 1
        while (i <= imgInfo.bytesPixel) {
            unfilteredRow!![i] = row!![i]
            i++
        }
        var j = 1
        i = imgInfo.bytesPixel + 1
        while (i <= nbytes) {
            unfilteredRow!![i] = (row!![i] + unfilteredRow!![j]).toByte()
            i++
            j++
        }
    }

    private fun unfilterRowUp(nbytes: Int) {
        for (i in 1..nbytes) {
            unfilteredRow!![i] = (row!![i] + rowUnfilteredPrev!![i]).toByte()
        }
    }

    /**
     * does the unfiltering of the inflated row, and updates row info
     */
    override fun preProcessRow() {
        super.preProcessRow()
        rowinfo.update(rown)
        unfilterRow()
        rowinfo.updateBuf(unfilteredRow!!, rowinfo.bytesRow + 1)
    }

    /**
     * Method for async/callback mode .
     *
     *
     * In callback mode will be called as soon as each row is retrieved
     * (inflated and unfiltered), after [.preProcessRow]
     *
     *
     * This is a dummy implementation (this normally should be overriden) that
     * does nothing more than compute the length of next row.
     *
     *
     * The return value is essential
     *
     *
     *
     * @return Length of next row, in bytes (including filter byte),
     * non-positive if done
     */
    override fun processRowCallback(): Int {
        return advanceToNextRow()
    }

    /**
     * Signals that we are done with the previous row, begin reading the next
     * one.
     *
     *
     * In polled mode, calls setNextRowLen()
     *
     *
     * Warning: after calling this, the unfilterRow is invalid!
     *
     * @return Returns nextRowLen
     */
    fun advanceToNextRow(): Int {
        // PngHelperInternal.LOGGER.info("advanceToNextRow");
        val bytesNextRow: Int = if (deinterlacer == null) {
            if (rown >= imgInfo.rows - 1) 0 else imgInfo.bytesPerRow + 1
        } else {
            val more: Boolean = deinterlacer.nextRow()
            if (more) deinterlacer.bytesToRead + 1 else 0
        }
        if (!callbackMode) { // in callback mode, setNextRowLen() is called internally
            prepareForNextRow(bytesNextRow)
        }
        return bytesNextRow
    }

    override val isRowReady: Boolean
        get() = !isWaitingForMoreInput

    fun updateCrcs(vararg idatCrcs: Checksum?) {
        for (idatCrca in idatCrcs)
            idatCrca?.update(unfilteredRow!!, 1, rowFilled - 1)
    }

    override fun close() {
        super.close()
        unfilteredRow = null // not really necessary...
        rowUnfilteredPrev = null
    }
}