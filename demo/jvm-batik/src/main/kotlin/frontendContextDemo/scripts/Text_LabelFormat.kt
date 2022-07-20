/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.geomText
import org.jetbrains.letsPlot.letsPlot

@Suppress("ClassName")
object Text_LabelFormat {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Label Format") {

            val df = mapOf<String, Any?>(
                "y" to (0 until 5),
                "z" to listOf(1.0 / 3, 12.5 / 7, -22.5 / 11, 2.5 / 7, 31.67 / 1.77),
                "s" to listOf("one", "two", "three", "four", "five"),
                "nans" to listOf(null, Double.NaN, Float.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)
            )

            // Floating point numbers without formatting.
            (letsPlot(df) + geomText { y = "y"; label = "z" }).show()
            // Floating point numbers with formatting.
            (letsPlot(df) + geomText(labelFormat = ".3f") { y = "y"; label = "z" }).show()
            // Number format as a part of a string pattern.
            (letsPlot(df) + geomText(labelFormat = "Ttl: \${.2f} (B)") { y = "y"; label = "z" }).show()
            // String pattern without value formatting.
            (letsPlot(df) + geomText(labelFormat = "- {} -") { y = "y"; label = "z" }).show()

            // na-value
            (letsPlot(df) + geomText(naText = "Nothing") { y = "y"; label = "nans" }).show()
        }
    }
}
