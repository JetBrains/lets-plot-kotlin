/*
 * Copyright (c) 2026. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInSwingContext
import org.jetbrains.letsPlot.geom.geomTile
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.ggsize
import org.jetbrains.letsPlot.intern.ColorScale
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.scale.*

object ScaleColorPalette {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInSwingContext.eval(
            "Scale palette() Demo", maxCol = 2
        ) {
            fun showPalette(title: String, scale: ColorScale, n: Int = 9) {
                val colors = scale.palette(n)
                val data = mapOf(
                    "x" to (0 until n),
                    "c" to colors
                )
                val p = ggplot(data) + ggsize(400, 80) +
                        geomTile(width = 1.0, height = 1.0) {
                            x = "x"
                            fill = "c"
                        } +
                        scaleFillIdentity() +
                        ggtitle(title)
                p.show()
            }

            showPalette("Brewer: Set1", scaleColorBrewer(palette = "Set1"))
            showPalette("Brewer: Blues", scaleColorBrewer(type = "seq", palette = "Blues"))
            showPalette("Brewer: Spectral", scaleColorBrewer(type = "div", palette = "Spectral"))
            showPalette("Viridis", scaleColorViridis())
            showPalette("Viridis: Plasma", scaleColorViridis(option = "plasma"))
            showPalette("Gradient: red-blue", scaleColorGradient(low = "red", high = "blue"))
            showPalette("Grey", scaleColorGrey())
            showPalette("Hue", scaleColorHue())
        }
    }
}
