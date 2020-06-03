/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import jetbrains.letsPlot.geom.geom_polygon
import jetbrains.letsPlot.ggplot

object Polygons {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("Polygons") {
            val data = samplePolygons()
            val p = ggplot(data) { x = "x"; y = "y" } + geom_polygon { fill = "value"; group = "id" }
            p.show()
        }
    }

    private fun samplePolygons(): Map<String, List<*>> {
        val ids = listOf("1.1", "2.1", "1.2", "2.2", "1.3", "2.3")
        val values = listOf(3.0, 3.1, 3.1, 3.2, 3.15, 3.5)

        val x = listOf(
            2.0, 1.0, 1.1, 2.2, 1.0, 0.0, 0.3, 1.1, 2.2, 1.1, 1.2, 2.5, 1.1, 0.3,
            0.5, 1.2, 2.5, 1.2, 1.3, 2.7, 1.2, 0.5, 0.6, 1.3
        )
        val y = listOf(
            -0.5, 0.0, 1.0, 0.5, 0.0, 0.5, 1.5, 1.0, 0.5, 1.0, 2.1, 1.7, 1.0, 1.5,
            2.2, 2.1, 1.7, 2.1, 3.2, 2.8, 2.1, 2.2, 3.3, 3.2
        )

        val idsRep = ArrayList<String>()
        val valueRep = ArrayList<Double>()
        for (i in ids.indices) {
            val id = ids[i]
            val v = values[i]
            for (j in 0..3) {
                idsRep.add(id)
                valueRep.add(v)
            }
        }

        val map = HashMap<String, List<*>>()
        map["id"] = idsRep
        map["x"] = x
        map["y"] = y
        map["value"] = valueRep
        return map
    }
}