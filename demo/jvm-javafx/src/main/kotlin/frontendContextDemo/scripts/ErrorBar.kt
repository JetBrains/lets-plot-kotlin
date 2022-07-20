/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import org.jetbrains.letsPlot.Pos
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.geom.*
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.positionDodge
import org.jetbrains.letsPlot.scale.scaleColorManual
import org.jetbrains.letsPlot.geom.*

object ErrorBar {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("ErrorBar") {
            val data = mapOf<String, Any>(
                "supp" to listOf("OJ", "OJ", "OJ", "VC", "VC", "VC"),
                "dose" to listOf(0.5, 1.0, 2.0, 0.5, 1.0, 2.0),
                "len" to listOf(13.23, 22.7, 26.06, 7.98, 16.77, 26.14),
                "min" to listOf(10.83, 20.8, 24.0, 5.24, 15.26, 24.35),
                "max" to listOf(15.63, 24.6, 28.11, 10.72, 18.28, 27.93)
            )
            val plot = ggplot(data) { x = "dose"; y = "len"; color = "supp" }

            fun withLinesAndPoints() {
                val geom = geomErrorBar(
                    position = positionDodge(width = 0.1),
                    color = "black",
                    width = 0.1
                ) {
                    ymin = "min"; ymax = "max"; group = "supp"
                } +
                        geomLine(position = positionDodge(width = 0.1)) +
                        geomPoint(position = positionDodge(width = 0.1), size = 5.0)

                val p = plot + geom
                p.show()
            }

            @Suppress("SpellCheckingInspection")
            fun errorbar() {
                val geom = geomErrorBar(
                    position = positionDodge(width = 0.1),
                    width = 0.1
                ) {
                    ymin = "min"; ymax = "max"
                } +
                        geomLine(position = positionDodge(width = 0.1)) +
                        geomPoint(position = positionDodge(width = 0.1), size = 2.0)

                val p = plot + geom + ggtitle("errorbar")
                p.show()
            }

            @Suppress("SpellCheckingInspection")
            fun pointrange() {
                val geom = geomPointRange(
                    position = positionDodge(width = 0.1),
                    color = "black",
                    size = 1.0
                ) {
                    ymin = "min"; ymax = "max"; group = "supp"
                } + geomLine(position = positionDodge(width = 0.1))

                val p = plot + geom + ggtitle("pointrange")
                p.show()
            }

            @Suppress("SpellCheckingInspection")
            fun linerange() {
                val geom = geomLineRange(
                    position = positionDodge(width = 0.1),
                    size = 5.0
                ) {
                    ymin = "min"; ymax = "max"; group = "supp"
                } +
                        geomLine(position = positionDodge(width = 0.1)) +
                        geomPoint(position = positionDodge(width = 0.1), size = 2.0)
                val p = plot + geom + ggtitle("linerange") +
                        scaleColorManual(listOf("orange", "dark_green"), naValue = "gray")
                p.show()
            }

            fun withBars() {
                val geom = geomBar(
                    position = Pos.dodge,
                    color = "black",
                    stat = Stat.identity
                ) { fill = "supp" } +
                        geomErrorBar(
                            position = positionDodge(width = 0.9),
                            color = "black",
                            width = 0.1
                        ) {
                            ymin = "min"; ymax = "max"; group = "supp"
                        }
                val p = plot + geom + ggtitle("with bars")
                p.show()
            }

//            withLinesAndPoints()
//            errorbar()
//            pointrange()
            linerange()
//            withBars()
        }
    }
}