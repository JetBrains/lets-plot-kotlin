/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.annotations.layerLabels
import org.jetbrains.letsPlot.asDiscrete
import org.jetbrains.letsPlot.commons.values.Color
import org.jetbrains.letsPlot.coord.coordFixed
import org.jetbrains.letsPlot.geom.geomPie
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.scaleFillGradient
import org.jetbrains.letsPlot.scale.xlim
import org.jetbrains.letsPlot.scale.ylim
import org.jetbrains.letsPlot.themes.themeMinimal
import org.jetbrains.letsPlot.themes.themeVoid
import org.jetbrains.letsPlot.tooltips.layerTooltips
import org.jetbrains.letsPlot.tooltips.tooltipsNone

object Pie {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Pie chart", maxCol = 2) {

            val data = mapOf(
                "name" to ('A'..'H').toList() + 'B',
                "value" to listOf(160, 90, 34, 44, 21, 86, 15, 100, 20)
            )

            val p1 = letsPlot(data) + themeVoid()

            (p1 + ggtitle("stat = Identity") +
                    geomPie(stat = Stat.identity, size = 20) { fill = "name"; slice = "value" }).show()

            (p1 + ggtitle("stat = count2d + sectors ordering") +
                    geomPie(
                        size = 20, hole = 0.2, stroke = 1.0,
                        tooltips = layerTooltips().line("@|^fill")
                            .line("count|@{..count..} (@{..prop..})")
                            .line("total|@{..sum..}")
                            .format(field = "..prop..", format = ".0%")
                    ) {
                        fill = asDiscrete("name", orderBy = "..count..")
                        weight = "value"
                    }).show()

            (p1 + ggtitle("size_unit=x") +
                    geomPie(sizeUnit = "x", stat = Stat.identity, hole = 0.2) {
                        fill = "name"
                        slice = "value"
                    } +
                    coordFixed() +
                    xlim(-5 to 5) + ylim(-5 to 5) +
                    themeMinimal()
                    ).show()


            val length = mapOf(
                "name" to listOf(
                    "2-3 km",
                    "3-5 km",
                    "5-7 km",
                    "7-10 km",
                    "10-20 km",
                    "20-50 km",
                    "50-75 km",
                    "75-100 km",
                    ">100 km"
                ),
                "count" to listOf(1109, 696, 353, 192, 168, 86, 74, 65, 53),
                "explode" to listOf(0, 0, 0, 0.1, 0.1, 0.2, 0.3, 0.4, 0.6),
            )
            val p2 = letsPlot(length) + themeVoid()

            (p2 + ggtitle("Explode slices away from the center") +
                    geomPie(
                        stat = Stat.identity,
                        size = 20,
                        hole = 0.2,
                        stroke = 1,
                        color = Color.BLACK,
                        strokeSide = "both",
                        spacerWidth = 1,
                        spacerColor = "black"
                    ) {
                        slice = "count"
                        fill = "name"
                        explode = "explode"
                    }).show()

            // add annotation
            (p2 + ggtitle("With annotations") +
                    geomPie(
                        stat = Stat.identity, size = 20, stroke = 1, color = Color.BLACK, hole = 0.6,
                        labels = layerLabels("count").format("count", "d").size(14),
                        tooltips = tooltipsNone
                    ) {
                        slice = "count"
                        fill = "name"
                    } +
                    scaleFillGradient(low = "light_green", high = "dark_blue")).show()
        }
    }
}