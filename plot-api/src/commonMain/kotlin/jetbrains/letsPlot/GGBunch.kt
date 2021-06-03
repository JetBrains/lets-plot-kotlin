/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot

import jetbrains.datalore.plot.config.Option
import jetbrains.datalore.plot.config.Option.GGBunch.ITEMS
import jetbrains.datalore.plot.config.Option.GGBunch.Item
import jetbrains.datalore.plot.config.Option.Meta.KIND
import jetbrains.letsPlot.frontend.CurrentFrontendContext
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.intern.toSpec

class GGBunch : Figure {
    private val items: MutableList<PlotItem> = ArrayList()

    fun addPlot(plot: Plot, x: Int, y: Int, width: Int? = null, height: Int? = null): GGBunch {
        items.add(PlotItem(plot, x, y, width, height))
        return this;
    }

    fun toSpec(): MutableMap<String, Any> {
        val spec = HashMap<String, Any>()
        spec[KIND] = Option.Meta.Kind.GG_BUNCH

        val itemSpecs = ArrayList<Map<String, Any?>>()
        for (item in items) {
            itemSpecs.add(
                mapOf(
                    Item.FEATURE_SPEC to item.plot.toSpec(),
                    Item.X to item.x,
                    Item.Y to item.y,
                    Item.WIDTH to item.width,
                    Item.HEIGHT to item.height
                )
            )
        }

        spec[ITEMS] = itemSpecs
        return spec
    }

    override fun show() {
        CurrentFrontendContext.display(this.toSpec())
    }

    private class PlotItem(
        val plot: Plot,
        val x: Int,
        val y: Int,
        val width: Int?,
        val height: Int?
    )
}