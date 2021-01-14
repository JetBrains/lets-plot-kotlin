/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import jetbrains.letsPlot.*
import jetbrains.letsPlot.geom.geom_bar
import jetbrains.letsPlot.geom.geom_boxplot
import jetbrains.letsPlot.geom.geom_errorbar
import jetbrains.letsPlot.tooltips.layer_tooltips
import jetbrains.letsPlot.tooltips.tooltips_none

object Tooltips {
    @JvmStatic
    @Suppress("DuplicatedCode")
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("Tooltip Customization") {
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
                        geom_errorbar(
                            position = position_dodge(width = 0.9),
                            color = "black",
                            width = 0.1
                        ) {
                            ymin = "min"; ymax = "max"; group = "supp"
                        } + theme().legendPosition_none()

                val tooltipsOpts = layer_tooltips()
                    .format("@len", ".2f")
                    .format("@min", ".1f")
                    .format("@max", ".1f")
                    .format("@supp", "supplement is {}")
                    .line("@supp")
                    .line("@|@len")
                    .line("min/max|@min/@max")

                (plot + geom_bar(
                    position = Pos.dodge,
                    color = "black",
                    stat = Stat.identity,
                    tooltips = tooltipsOpts
                ) { fill = "supp" }
                        ).show()

                // Move tooltip to the corner.
                (plot + geom_bar(
                    position = Pos.dodge,
                    color = "black",
                    stat = Stat.identity,
                    tooltips = tooltipsOpts.anchor("top_left")
                ) { fill = "supp" }
                        ).show()

                // Set the minimum width of the tooltip.
                (plot + geom_bar(
                    position = Pos.dodge,
                    color = "black",
                    stat = Stat.identity,
                    tooltips = tooltipsOpts.anchor("top_left").minWidth(150)
                ) { fill = "supp" }
                        ).show()

                // Hide tooltips.
                (plot + geom_bar(
                    position = Pos.dodge,
                    color = "black",
                    stat = Stat.identity,
                    tooltips = tooltips_none
                ) { fill = "supp" }
                        ).show()
            }

            // "Outlier" tooltips
            run {
                val plot = ggplot(data) { x = "supp"; y = "len"; fill = "supp" }

                // Default tooltips
                (plot + geom_boxplot()).show()

                // Configure text in outlier tooltips using the 'format()' function.
                (plot + geom_boxplot(
                    tooltips = layer_tooltips()
                        .format("^Y", "{.0f}")
                        .format("^middle", ".2f")
                        .format("^ymin", "min: {}")
                        .format("^ymax", "max: {}")
                )).show()

                // Replace "outlier" tooltips with the "general" tooltip.
                (plot + geom_boxplot(
                    tooltips = layer_tooltips()
                        .format("^Y", ".0f")
                        .format("^middle", ".2f")
                        .line("min/max|^ymin/^ymax")
                        .line("lower/upper|^lower/^upper")
                        .line("@|^middle")
                )).show()
            }
        }
    }
}