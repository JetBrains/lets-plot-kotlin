package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.coord.coordFixed
import org.jetbrains.letsPlot.geom.extras.arrow
import org.jetbrains.letsPlot.geom.geomSpoke
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.scaleColorGradient
import org.jetbrains.letsPlot.themes.elementText
import org.jetbrains.letsPlot.themes.theme
import org.jetbrains.letsPlot.themes.themeMinimal
import org.jetbrains.letsPlot.tooltips.layerTooltips
import kotlin.math.atan2
import kotlin.math.sqrt

object Spoke {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("geomSpoke") {
            run {
                val data = mapOf(
                    "x" to listOf(0, 1, 1, 0),
                    "y" to listOf(0, 0, 1, 1),
                    "angle" to listOf(4.7124, 0, 1.5708, 3.1416),
                    "radius" to listOf(0.2, 0.4, 0.6, 0.8)
                )

                val p =
                    letsPlot(data) + geomSpoke(size = 5, color = "blue", alpha = 0.5, tooltips = layerTooltips("x")) {
                        x = "x"
                        y = "y"
                        angle = "angle"
                        radius = "radius"
                    } + coordFixed()
                p.show()
            }

            run {
                fun linspace(start: Double, stop: Double, num: Int): List<Double> {
                    if (num <= 0) return emptyList()
                    if (num == 1) return listOf(start)
                    val step = (stop - start) / (num - 1)
                    return List(num) { start + it * step }
                }

                fun simpleMeshgrid(xs: List<Double>, ys: List<Double>): Pair<List<List<Double>>, List<List<Double>>> {
                    return Pair(
                        List(ys.size) { xs },
                        ys.map { y -> List(xs.size) { y } }
                    )
                }

                fun cartesianToPolar(xArray: List<Double>, yArray: List<Double>): Pair<List<Double>, List<Double>> {
                    val rArray = xArray.zip(yArray).map { (x, y) -> sqrt(x * x + y * y) }
                    val maxR = rArray.maxOrNull() ?: 0.0
                    val normalizedR = rArray.map { it / maxR }
                    val theta = xArray.zip(yArray).map { (x, y) -> atan2(y, x) }
                    return Pair(normalizedR, theta)
                }

                fun f(x: List<Double>, y: List<Double>) = y to x.map { -it }

                val n = 11
                val ab = -5.0 to 5.0
                val space = linspace(ab.first, ab.second, n)
                val (X, Y) = simpleMeshgrid(space, space)
                val (R, A) = f(X.flatten(), Y.flatten()).let { cartesianToPolar(it.first, it.second) }
                val data = mapOf(
                    "x" to X.flatten(),
                    "y" to Y.flatten(),
                    "r" to R,
                    "a" to A
                )
                val p = letsPlot(data) { x = "x"; y = "y"; color = "r" } +
                        geomSpoke(arrow = arrow(type = "closed", angle = 12, length = 15)) {
                            angle = "a"; radius = "r"
                        } +
                        scaleColorGradient(low = "#3288bd", high = "#d53e4f", guide = "none") +
                        coordFixed(xlim = ab, ylim = ab) +
                        themeMinimal() +
                        theme(axisText = elementText(margin = 10), axisTitle = "blank")

                p.show()
            }
        }
    }
}