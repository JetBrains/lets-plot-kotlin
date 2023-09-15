package frontendContextDemo.scripts

import demoData.AutoMpg
import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.stat.statSum

object Sum {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Summary") {
            val data = AutoMpg.map()

            run {
                val statLayer = statSum {
                    x =  "origin of car"; y = "number of cylinders"
                }
                val p = ggplot(data) + statLayer + ggtitle("sumStat { x=\"origin\"; y=\"cyl\" }")
                p.show()
            }

            run {
                val statLayer = statSum {
                    x =  "origin of car"; y = "number of cylinders"; size = "..prop.."
                }
                val p = ggplot(data) + statLayer + ggtitle("sumStat { ...; size=\"..prop..\" }")
                p.show()
            }

            run {
                val statLayer = statSum {
                    x = "origin of car"; y = "number of cylinders"; size = "..prop.."; group = "origin of car"
                }
                val p = ggplot(data) + statLayer + ggtitle("sumStat { ...; size=\"..prop..\"; group=\"origin\" }")
                p.show()
            }
        }

    }
}