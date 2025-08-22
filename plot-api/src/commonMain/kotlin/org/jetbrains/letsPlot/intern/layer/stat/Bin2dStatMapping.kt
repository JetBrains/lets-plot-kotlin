/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

/**
 * Aesthetic mappings supported by [statBin2D()][org.jetbrains.letsPlot.stat.statBin2D].
 *
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param weight Used by `Stat.bin2D()`stat to compute weighted sum instead of simple count.
 */
class Bin2dStatMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var weight: Any? = null
) : Bin2dStatAesthetics