/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.jetbrains.letsPlot.intern.SubPlotsAssert.Companion.assertThat
import org.junit.Test

class PlotGridTest {
    @Test
    fun gridLayoutParameters() {
        val list = listOf(ggplot(), ggplot())

        assertThat(gggrid(list))
            .layout()
            .isGrid()
            .dim(ncol = 2, nrow = 1)

        assertThat(gggrid(list, ncol = 1))
            .layout()
            .dim(ncol = 1, nrow = 2)

        assertThat(gggrid(list, ncol = 4))
            .layout()
            .dim(ncol = 4, nrow = 1)

        assertThat(gggrid(list, hspace = 10, vspace = 11.1))
            .layout()
            .spacing(hspace = 10, vspase = 11.1)

        assertThat(gggrid(list, widths = listOf(10, 11.1), heights = listOf(2, 1.1)))
            .layout()
            .widths(listOf(10, 11.1))
            .heights(listOf(2, 1.1))
    }
}