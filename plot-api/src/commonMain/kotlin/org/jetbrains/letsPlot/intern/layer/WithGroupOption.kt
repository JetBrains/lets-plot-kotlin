/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer

import org.jetbrains.letsPlot.intern.Options

/**
 * ## Data Grouping
 *
 * The `group` parameter is not a true aesthetic but controls how data is grouped for visualization:
 *
 * **Default Grouping Behavior:**
 *
 * Lets-Plot automatically groups data by discrete variables mapped to aesthetics like `color`, `shape`, `linetype`, etc.
 * This creates separate visual elements (lines, paths, polygons) for each unique combination of these variables.
 *
 * **Explicit Group Control:**
 *
 * - Use `group = "variableName"` to group only by that specific variable, overriding default grouping
 *
 * - Use `group = listOf("var1", "var2", ...)` to group by the interaction of multiple variables
 *
 * - Use `group = emptyList<Any>()` to disable all the grouping completely
 *
 * @property group Data grouping control (not a true aesthetic).
 *  Use a variable name to group by that variable,
 *  a list of variables to group by their interaction,
 *  or an empty list to disable all grouping.
 */
interface WithGroupOption {
    val group: Any?

    fun groupOption() = Options.of(
        "group" to group
    )
}