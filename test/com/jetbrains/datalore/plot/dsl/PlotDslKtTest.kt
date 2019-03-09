package com.jetbrains.datalore.plot.dsl

import com.jetbrains.datalore.plot.PlotAssert.Companion.assertThat
import org.junit.Test

class PlotDslTest {

    @Test
    fun `empty plot`() {
        var p = ggplot()
        assertThat(p).features().length(0)

        p = ggplot {}
        assertThat(p).features().length(0)
    }

    @Test
    fun `empty plot with mapping`() {
        var p = ggplot(data = "data", mapping = { x = "X"; y = "Y" })
        assertThat(p).features().length(0)

        p = ggplot { x = "X"; y = "Y" }
        assertThat(p).features().length(0)

        p = ggplot(data = "data") { x = "X"; y = "Y" }
        assertThat(p).features().length(0)

        // ToDo: check mappings
    }
}