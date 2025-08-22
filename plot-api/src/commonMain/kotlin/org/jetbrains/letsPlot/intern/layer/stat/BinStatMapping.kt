/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

/**
 * Aesthetic mappings supported by [statBin()][org.jetbrains.letsPlot.stat.statBin].
 *
 * @param x X-axis coordinates.
 * @param weight Used to compute weighted sum instead of simple count.
 */
class BinStatMapping(
    override var x: Any? = null,
    override var weight: Any? = null
) : BinStatAesthetics
