/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import demoData.AutoMpg
import frontendContextDemo.ScriptInJfxContext
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.geom.geomBar
import org.jetbrains.letsPlot.geom.geomBoxplot
import org.jetbrains.letsPlot.geom.geomErrorBar
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.pos.positionDodge
import org.jetbrains.letsPlot.themes.theme
import org.jetbrains.letsPlot.tooltips.layerTooltips
import org.jetbrains.letsPlot.tooltips.tooltipsNone

object Tooltips {
    @JvmStatic
    @Suppress("DuplicatedCode")
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("Tooltip Customization", maxCol = 4) {
            val data = mapOf<String, Any>(
                "supp" to listOf("OJ", "OJ", "OJ", "VC", "VC", "VC"),
                "dose" to listOf(0.5, 1.0, 1.5, 0.5, 1.0, 1.5),
                "len" to listOf(13.23, 21.7, 27.06, 7.00, 17.77, 25.14),
                "min" to listOf(10.00, 20.8, 24.0, 5.24, 15.26, 24.35),
                "max" to listOf(15.00, 24.6, 28.11, 10.72, 18.28, 27.93)
            )

            // Configure the "general" multiline tooltip
            run {
                val plot = ggplot(data) { x = "dose"; y = "len"; color = "supp" } +
                        geomErrorBar(
                            position = positionDodge(width = 0.9),
                            color = "black",
                            width = 0.1
                        ) {
                            ymin = "min"; ymax = "max"; group = "supp"
                        } + theme().legendPositionNone()

                val tooltipsOpts = layerTooltips()
                    .format("@len", ".2f")
                    .format("@min", ".1f")
                    .format("@max", ".1f")
                    .format("@supp", "supplement is {}")
                    .title("@supp")
                    .line("@|@len")
                    .line("min/max|@min/@max")

                (plot + geomBar(
                    position = positionDodge(),
                    color = "black",
                    stat = Stat.identity,
                    tooltips = tooltipsOpts
                ) { fill = "supp" }
                        ).show()

                // Move tooltip to the corner.
                (plot + geomBar(
                    position = positionDodge(),
                    color = "black",
                    stat = Stat.identity,
                    tooltips = tooltipsOpts.anchor("top_left")
                ) { fill = "supp" }
                        ).show()

                // Set the minimum width of the tooltip.
                (plot + geomBar(
                    position = positionDodge(),
                    color = "black",
                    stat = Stat.identity,
                    tooltips = tooltipsOpts.anchor("top_left").minWidth(150)
                ) { fill = "supp" }
                        ).show()

                // Hide tooltips.
                (plot + geomBar(
                    position = positionDodge(),
                    color = "black",
                    stat = Stat.identity,
                    tooltips = tooltipsNone
                ) { fill = "supp" }
                        ).show()
            }

            // "Outlier" tooltips
            run {
                val plot = ggplot(data) { x = "supp"; y = "len"; fill = "supp" }

                // Default tooltips
                (plot + geomBoxplot()).show()

                // Configure text in outlier tooltips using the 'format()' function.
                (plot + geomBoxplot(
                    tooltips = layerTooltips()
                        .format("^Y", "{.0f}")
                        .format("^middle", ".2f")
                        .format("^ymin", "min: {}")
                        .format("^ymax", "max: {}")
                )).show()

                // Replace "outlier" tooltips with the "general" tooltip.
                (plot + geomBoxplot(
                    tooltips = layerTooltips()
                        .format("^Y", ".0f")
                        .format("^middle", ".2f")
                        .line("min/max|^ymin/^ymax")
                        .line("lower/upper|^lower/^upper")
                        .line("@|^middle")
                )).show()

                // Use '\n' in tooltip lines
                (plot + geomBoxplot(
                    tooltips = layerTooltips()
                        .format("^Y", ".0f")
                        .format("^middle", ".2f")
                        .line("min\nmax|^ymin\n^ymax")
                        .line("@|^middle")
                        .line("lower\nupper|^lower\n^upper")

                )).show()
            }

            // Anchor + multi-line title
            run {
                val mpgData = AutoMpg.map()
                val plot = ggplot(mpgData) +
                        theme().legendPositionNone() +
                        geomPoint(
                            tooltips = layerTooltips()
                                .line("@|^color (mpg)")
                                .line("@|@{origin of car}")
                                .title("@{vehicle name}\n(@{model year})")
                                .format(field = "model year", format = "19{d}")
                                .minWidth(240)
                                .anchor("top_left")
                        ) {
                            x = "engine displacement (cu. inches)"
                            y = "engine horsepower"
                            color = "miles per gallon"
                        }

                plot.show()
            }

            // Quick select tooltip variables
            run {
                val mpgData = AutoMpg.map()
                val plot = ggplot(mpgData) +
                        theme().legendPositionNone() +
                        geomPoint(
                            tooltips = layerTooltips("vehicle name", "model year", "origin of car")
                        ) {
                            x = "engine displacement (cu. inches)"
                            y = "engine horsepower"
                            color = "miles per gallon"
                        }

                plot.show()
            }
        }
    }
}