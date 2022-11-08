/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.*
import org.jetbrains.letsPlot.geom.geomBar
import org.jetbrains.letsPlot.geom.geomLabel
import org.jetbrains.letsPlot.geom.geomText
import org.jetbrains.letsPlot.pos.positionFill
import org.jetbrains.letsPlot.pos.positionStack

object PositionStackFill {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("positionStack and positionFill") {

            val data = mapOf(
                "x" to listOf(1, 1, 2, 2, 2),
                "y" to listOf(1, 3, 2, 1, -3),
                "grp" to listOf("a", "b", "a", "b", "c")
            )

            val p = letsPlot(data) { x = "x"; y = "y"; group = "grp" }

            (p + geomBar(stat = Stat.identity, color = "black") { fill = "grp" } +
                    geomLabel(position = positionStack(0.5)) { label = "y" }).show()

            (p + geomBar(stat = Stat.identity, position = positionFill(), color = "black") { fill = "grp" } +
                    geomText(position = positionFill(0.5)) { label = "y" }).show()
        }
    }
}