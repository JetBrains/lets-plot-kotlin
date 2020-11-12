/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_text
import jetbrains.letsPlot.lets_plot

@Suppress("ClassName")
object Text_LabelFormat {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Label Format") {

            val df = mapOf<String, Any?>(
                "y" to (0 until 5),
                "z" to listOf(1.0 / 3, 12.5 / 7, -22.5 / 11, 2.5 / 7, 31.67 / 1.77),
                "s" to listOf("one", "two", "three", "four", "five")
            )

            // Floating point numbers without formatting.
            (lets_plot(df) + geom_text { y = "y"; label = "z" }).show()
            // Floating point numbers with formatting.
            (lets_plot(df) + geom_text(labelFormat = ".3f") { y = "y"; label = "z" }).show()
            // Number format as a part of a string pattern.
            (lets_plot(df) + geom_text(labelFormat = "Ttl: \${.2f} (B)") { y = "y"; label = "z" }).show()
            // String pattern without value formatting.
            (lets_plot(df) + geom_text(labelFormat = "- {} -") { y = "y"; label = "z" }).show()
        }
    }
}
