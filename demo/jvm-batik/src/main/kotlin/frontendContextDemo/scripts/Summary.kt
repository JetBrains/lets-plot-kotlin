/*
 * Copyright (c) 2023. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.stat.statSummary
import org.jetbrains.letsPlot.geom.geomBoxplot
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.tooltips.layerTooltips

object Summary {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Summary") {
            val data = getData()

            run {
                val statLayer = statSummary {
                    x = "cat"; y = "val"
                }
                val p = ggplot(data) + statLayer + ggtitle("Basic demo")
                p.show()
            }

            run {
                val geomLayer = geomBoxplot()
                val statLayer = statSummary(fn = "median", fnMin = "lq", fnMax = "uq", color = "red")
                val p = ggplot(data) {
                    x = "cat"; y = "val"
                } + geomLayer + statLayer + ggtitle("Summary vs. Boxplot")
                p.show()
            }

            run {
                val statLayer = statSummary(
                    geom = Geom.crossbar(),
                    fn = "median", fnMin = "lq",
                    quantiles = listOf(0.45, 0.5, 0.55),
                    tooltips = layerTooltips()
                        .line("max|^ymax")
                        .line("median|^y")
                        .line("min|^ymin")
                ) {
                    fill = "..y.."
                }
                val p = ggplot(data) {
                    x = "cat"; y = "val"
                } + statLayer + ggtitle("Crossbar geom")
                p.show()
            }

            run {
                val geom = Geom.boxplot() {
                    middle = "..mq.."; lower = "..lq.."; upper = "..uq.."
                }
                val statLayer = statSummary(geom = geom) {
                    x = "cat"; y = "val"
                }
                val p = ggplot(data) + statLayer + ggtitle("Boxplot geom")
                p.show()
            }
        }
    }

    private fun getData(n: Int = 100): Map<String, Any> {
        val categories = listOf("A", "B", "C")
        val rand = java.util.Random(42)
        return mapOf<String, Any>(
            "val" to List(n) { rand.nextGaussian() },
            "cat" to List(n) { categories[rand.nextInt(categories.size)] }
        )
    }
}