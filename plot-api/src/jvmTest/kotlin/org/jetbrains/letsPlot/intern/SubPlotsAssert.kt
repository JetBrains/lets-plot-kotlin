/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern

import jetbrains.datalore.plot.config.Option.SubPlots
import junit.framework.TestCase.*
import org.jetbrains.letsPlot.intern.figure.SubPlotsFigure

internal class SubPlotsAssert(private val figure: SubPlotsFigure) {
    companion object {
        internal fun assertThat(figure: SubPlotsFigure) = SubPlotsAssert(figure)
    }

    @Suppress("UNCHECKED_CAST")
    fun layout() = SubPlotsLayoutAssert(figure.toSpec().getValue(SubPlots.LAYOUT) as Map<String, Any>)
}

internal class SubPlotsLayoutAssert(private val layoutOptions: Map<String, Any>) {
    fun isGrid(): SubPlotsLayoutAssert {
        assertEquals(
            SubPlots.Layout.SUBPLOTS_GRID,
            layoutOptions.getValue(SubPlots.Layout.NAME)
        )
        return this
    }

    fun dim(ncol: Int, nrow: Int): SubPlotsLayoutAssert {
        assertEquals(
            ncol,
            layoutOptions.getValue(SubPlots.Grid.NCOLS)
        )
        assertEquals(
            nrow,
            layoutOptions.getValue(SubPlots.Grid.NROWS)
        )
        return this
    }

    fun spacing(hspace: Number, vspase: Number): SubPlotsLayoutAssert {
        assertEquals(
            hspace,
            layoutOptions.getValue(SubPlots.Grid.HSPACE)
        )
        assertEquals(
            vspase,
            layoutOptions.getValue(SubPlots.Grid.VSPACE)
        )
        return this
    }

    fun widths(list: List<Number>): SubPlotsLayoutAssert {
        assertEquals(
            list,
            layoutOptions.getValue(SubPlots.Grid.COL_WIDTHS)
        )
        return this
    }

    fun heights(list: List<Number>): SubPlotsLayoutAssert {
        assertEquals(
            list,
            layoutOptions.getValue(SubPlots.Grid.ROW_HEIGHTS)
        )
        return this
    }
}