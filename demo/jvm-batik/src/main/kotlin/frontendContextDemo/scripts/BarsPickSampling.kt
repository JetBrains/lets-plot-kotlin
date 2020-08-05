/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_bar
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.sampling.sampling_none

object BarsPickSampling {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Boxplot") {

            // Number of unique words exceeds threshold (50) of default 'pick' sampling on bar chart.
            val words = genWordSet(500)
            val dat = data(1000, words)
            val p = ggplot(dat) { x = "word" }

            // Disable sampling to see raw plot.
            (p + geom_bar(sampling = sampling_none)).show()

            // Draw plot with default sampling enabled
            (p + geom_bar()).show()

            // 'pick' sampling handles groups on bar chart correctly.
            (p + geom_bar { fill = "g" }).show()
        }
    }

    @Suppress("SameParameterValue")
    private fun genWord(length: Int): String {
        val letters = ('a'..'z')
        return List(length) { letters.random() }.joinToString("")
    }

    @Suppress("SameParameterValue")
    private fun genWordSet(n: Int): Set<String> {
        val words = HashSet<String>()
        while (words.size < n) {
            words.add(genWord(5))
        }
        return words
    }

    @Suppress("SameParameterValue")
    private fun data(n: Int, words: Set<String>): Map<String, *> {
        return mapOf(
            "word" to List(n) { words.random() },
            "g" to List(n) { listOf('a', 'b', 'c').random() }
        )
    }
}
