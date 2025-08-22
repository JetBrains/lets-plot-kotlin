/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

/**
 * Aesthetic mappings supported by [statBoxplot()][org.jetbrains.letsPlot.stat.statBoxplot].
 *
 * @param x X-axis value for vertical boxplot.
 * @param y Y-axis value for horizontal boxplot.
 * @param weight Used to compute weighted statistics.
 */
class BoxplotStatMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var weight: Any? = null
) : BoxplotStatAesthetics
