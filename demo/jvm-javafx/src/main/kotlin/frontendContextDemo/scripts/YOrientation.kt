/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts;

import frontendContextDemo.ScriptInJfxContext
import jetbrains.letsPlot.coordFlip
import jetbrains.letsPlot.geom.geomBar
import jetbrains.letsPlot.ggplot


object YOrientation {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("Y-orientation", maxCol = 3) {
            val varName = "varCategory"
            val data = mapOf(
                varName to List(30) { "a" } + List(40) { "b" } + List(20) { "c" }
            )

            val p = ggplot(data)
            (p + geomBar { x = varName } ).show()
            (p + geomBar { x = varName } + coordFlip()).show()
            (p + geomBar(orientation = "y") { y = varName }).show()
        }
    }
}

