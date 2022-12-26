/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("unused")
package org.jetbrains.letsPlot.util.pngj

import org.jetbrains.letsPlot.util.pngj.Zip.newInflater
import kotlin.jvm.JvmOverloads

/**
 * A set of IDAT-like chunks which, concatenated, form a zlib stream.
 *
 *
 * The inflated stream is intented to be read as a sequence of "rows", of which
 * the caller knows the lengths (not necessary equal!) and number.
 *
 *
 * Eg: For IDAT non-interlaced images, a row has bytesPerRow + 1 filter byte<br></br>
 * For interlaced images, the lengths are variable.
 *
 *
 * This class can work in sync (polled) mode or async (callback) mode. But for
 * callback mode the method processRowCallback() must be overriden
 *
 *
 * See [IdatSet], which is mostly used and has a slightly simpler use.<br></br>
 * See `DeflatedChunkSetTest` for example of use.
 */
internal open class DeflatedChunksSet @JvmOverloads constructor(
    /**
     * All IDAT-like chunks that form a same DeflatedChunksSet should have the
     * same id
     */
    val chunkid: String,
    /**
     * Callback mode = async processing
     */
    val isCallbackMode // true: CALLBACK (non-blocking) false: POLL (blocking)
    : Boolean,
    /**
     * Target size of the current row, including filter byte. <br></br>
     * should coincide (or be less than) with row.length
     */
    var rowLen // what amount of bytes is to be interpreted as a complete "row". can change
    : Int,
    maxRowLen: Int,
    inflater: Inflater? = null,
    buffer: ByteArray? = null
) {
     var row // a "row" here means a raw (uncopressed filtered) part of the IDAT stream,
            : ByteArray? = null
         protected set

    // normally a image row (or subimage row for interlaced) plus a filter byte
    private val rowfilled // effective/valid length of row
            = 0

    /** This the amount of valid bytes in the buffer  */
    // normally a image row (or subimage row for interlaced) plus a filter byte
    var rowFilled = 0 // effective/valid length of row
        private set
    private val rowlen = 0 // what amount of bytes is to be interpreted as a complete "row". can change

    /**
     * Get current (last) row number.
     *
     *
     * This corresponds to the raw numeration of rows as seen by the deflater.
     * Not the same as the real image row, if interlaced.
     *
     */
    // (for interlaced)
    var rown: Int // only coincide with image row if non-interlaced - incremented by
        private set

    // setNextRowSize()
    /*
	 * States WAITING_FOR_INPUT ROW_READY WORK_DONE TERMINATED
	 * 
	 * processBytes() is externally called, prohibited in READY (in DONE it's
	 * ignored)
	 * 
	 * WARNING: inflater.finished() != DONE (not enough, not neccesary) DONE means
	 * that we have already uncompressed all the data of interest.
	 * 
	 * In non-callback mode, prepareForNextRow() is also externally called, in
	 * 
	 * Flow: - processBytes() calls inflateData()
	 * 
	 * inflateData() : if buffer is filled goes to READY
	 * 
	 * else if ! inf.finished goes to WAITING
	 * 
	 * else if any data goes to READY (incomplete data to be read)
	 * 
	 * else goes to DONE
	 * 
	 * in Callback mode, after going to READY, n=processCallback() is called and
	 * then prepareForNextRow(n) is called.
	 * 
	 * in Polled mode, prepareForNextRow(n) must be called from outside (after
	 * checking state=READY)
	 * 
	 * prepareForNextRow(n) goes to DONE if n==0 calls inflateData() again - end()
	 * goes to DONE
	 */
    enum class State {
        WAITING_FOR_INPUT,  // waiting for more bytes to be fed
        ROW_READY,  // ready for consumption (might be less than fully filled), ephemeral state for

        // CALLBACK mode
        DONE,  // all data of interest has been read, but we might accept still more trailing

        // chunks (we'll ignore them)
        CLOSED;

        // we are done, and also won't accept more IDAT chunks
        // the caller has already uncompressed all the data of interest or EOF
        val isDone: Boolean
            get() = this == DONE || this == CLOSED

        // we dont accept more chunks
        val isClosed: Boolean
            get() = this == CLOSED
    }

    var state = State.WAITING_FOR_INPUT // never null
    private var inf: Inflater
    private var infOwn // true if we own the inflater (we created it)
            = false
    private var curChunk: DeflatedChunkReader? = null
    /** total number of bytes that have been fed to this object  */
    var bytesIn: Long = 0 // count the total compressed bytes that have been fed
        private set
    /** total number of bytes that have been uncompressed  */
    var bytesOut: Long = 0 // count the total uncompressed bytes
        private set
    var chunkNum = -1 // incremented at each new chunk start
    var firstChunqSeqNum = -1 // expected seq num for first chunk. used only for fDAT (APNG)

    init {
        if (rowLen < 1 || maxRowLen < rowLen) throw PngjException("bad inital row len $rowLen")
        if (inflater != null) {
            inf = inflater
            infOwn = false
        } else {
            inf = newInflater()
            infOwn = true // inflater is own, we will release on close()
        }
        row = if (buffer != null && buffer.size >= rowLen) buffer else ByteArray(maxRowLen)
        rown = -1
        state = State.WAITING_FOR_INPUT
        try {
            prepareForNextRow(rowLen)
        } catch (e: Exception) {
            close()
            throw e
        }
    }

    fun appendNewChunk(cr: DeflatedChunkReader) {
        // all chunks must have same id
        if (chunkid != cr.chunkRaw.id) throw PngjInputException(
            "Bad chunk inside IdatSet, id:" + cr.chunkRaw.id + ", expected:" + chunkid
        )
        curChunk = cr
        chunkNum++
        if (firstChunqSeqNum >= 0) cr.setSeqNumExpected(chunkNum + firstChunqSeqNum)
    }

    /**
     * Feeds the inflater with the compressed bytes
     *
     * In poll mode, the caller should not call repeatedly this, without
     * consuming first, checking isDataReadyForConsumer()
     *
     * @param buf
     * @param off
     * @param len
     */
    fun processBytes(buf: ByteArray, off: Int, len: Int) {
        bytesIn += len.toLong()
        // PngHelperInternal.LOGGER.info("processing compressed bytes in chunkreader : "
        // + len);
        if (len < 1 || state.isDone) return
        if (state == State.ROW_READY) throw PngjInputException("this should only be called if waitingForMoreInput")
        if (inf.needsDictionary || !inf.needsInput) error("should not happen")
        inf.setInput(buf, off, len)
        // PngHelperInternal.debug("entering processs bytes, state=" + state +
        // " callback="+callbackMode);
        if (isCallbackMode) {
            while (inflateData()) {
                val nextRowLen = processRowCallback()
                prepareForNextRow(nextRowLen)
                if (isDone) processDoneCallback()
            }
        } else inflateData()
    }

    /*
	 * This never inflates more than one row This returns true if this has resulted
	 * in a row being ready and preprocessed with preProcessRow (in callback mode,
	 * we should call immediately processRowCallback() and
	 * prepareForNextRow(nextRowLen)
	 */
    private fun inflateData(): Boolean {
        try {
            // PngHelperInternal.debug("entering inflateData bytes, state=" + state +
            // " callback="+callbackMode);
            if (state == State.ROW_READY) throw PngjException("invalid state") // assert
            if (state.isDone) return false
            val ninflated: Int
            if (row == null || row!!.size < rowLen) row = ByteArray(rowLen) // should not happen
            if (rowFilled < rowLen && !inf.finished) {
                ninflated = inf.inflate(row!!, rowFilled, rowLen - rowFilled)
                rowFilled += ninflated
                bytesOut += ninflated.toLong()
            }
            val nextstate: State = if (rowFilled == rowLen) State.ROW_READY // complete row, process it
            else if (!inf.finished) State.WAITING_FOR_INPUT else if (rowFilled > 0) State.ROW_READY // complete row, process it
            else {
                State.DONE // eof, no more data
            }
            state = nextstate
            if (state == State.ROW_READY) {
                preProcessRow()
                return true
            }
        } catch (e: Exception) {
            close()
            throw e
        }
        return false
    }

    /**
     * Called automatically in all modes when a full row has been fully
     * inflated.
     */
    protected open fun preProcessRow() {}

    /**
     * Callback, must be implemented in callbackMode
     *
     *
     * This should use [.getRowFilled] and [.getInflatedRow] to
     * access the row.
     *
     *
     * Must return byes of next row, for next callback.
     */
    protected open fun processRowCallback(): Int {
        throw PngjInputException("not implemented")
    }

    /**
     * Callback, to be implemented in callbackMode
     *
     *
     * This will be called once to notify state done
     */
    protected open fun processDoneCallback() {}

    /**
     * Should be called after the previous row was processed
     *
     *
     * Pass 0 or negative to signal that we are done (not expecting more bytes)
     *
     *
     * This resets [.rowfilled]
     *
     *
     * The
     */
    fun prepareForNextRow(len: Int) {
        rowFilled = 0
        rown++
        if (len < 1) {
            rowLen = 0
            markAsDone()
        } else if (inf.finished) {
            rowLen = 0
            markAsDone()
        } else {
            state = State.WAITING_FOR_INPUT
            rowLen = len
            if (!isCallbackMode) inflateData()
        }
    }

    /**
     * In this state, the object is waiting for more input to deflate.
     *
     *
     * Only in this state it's legal to feed this
     */
    val isWaitingForMoreInput: Boolean
        get() = state == State.WAITING_FOR_INPUT

    /**
     * In this state, the object is waiting the caller to retrieve inflated data
     *
     *
     * Effective length: see [.getRowFilled]
     */
    open val isRowReady: Boolean
        get() = state == State.ROW_READY

    /**
     * In this state, all relevant data has been uncompressed and retrieved
     * (exceptionally, the reading has ended prematurely).
     *
     *
     * We can still feed this object, but the bytes will be swallowed/ignored.
     */
    val isDone: Boolean
        get() = state.isDone
    val isClosed: Boolean
        get() = state.isClosed

    /**
     * This will be called by the owner to report us the next chunk to come. We
     * can make our own internal changes and checks. This returns true if we
     * acknowledge the next chunk as part of this set
     */
    fun ackNextChunkId(id: String): Boolean {
        return if (state.isClosed) false else if (id == chunkid) {
            true
        } else {
            if (!allowOtherChunksInBetween(id)) {
                if (state.isDone) {
                    if (!state.isClosed) close()
                    false
                } else {
                    throw PngjInputException("Unexpected chunk $id while $chunkid set is not done")
                }
            } else true
        }
    }

    /**
     * This should be called when discarding this object, or for aborting.
     * Secure, idempotent Don't use this just to notify this object that it has
     * no more work to do, see [.markAsDone]
     */
    open fun close() {
        try {
            if (!state.isClosed) {
                state = State.CLOSED
            }
            if (infOwn) {
                inf.end() // we end the Inflater only if we created it
            }
        } catch (_: Exception) {
        }
    }

    /**
     * Forces the DONE state (except if it was CLOSED), this object won't uncompress more data. It's still
     * not terminated, it will accept more IDAT chunks, but will ignore them.
     */
    fun markAsDone() {
        if (!isDone) state = State.DONE
    }

    /**
     * Some IDAT-like set can allow other chunks in between (APGN?).
     *
     *
     * Normally false.
     *
     * @param id
     * Id of the other chunk that appeared in middel of this set.
     * @return true if allowed
     */
    @Suppress("UNUSED_PARAMETER")
    fun allowOtherChunksInBetween(id: String?): Boolean {
        return false
    }

    override fun toString(): String {
        val sb = StringBuilder(
            "idatSet : " + curChunk!!.chunkRaw.id + " state=" + state + " rows="
                    + rown + " bytes=" + bytesIn + "/" + bytesOut
        )
        return sb.toString()
    }
}