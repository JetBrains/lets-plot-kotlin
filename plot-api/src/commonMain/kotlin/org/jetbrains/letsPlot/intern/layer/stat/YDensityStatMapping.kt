/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

/**
 * Aesthetic mappings supported by [statYDensity()][org.jetbrains.letsPlot.stat.statYDensity].
 *
 * @param x X-axis coordinates.
 * @param y Y-axis coordinates.
 * @param weight Used by the stat to compute weighted density.
 */
class YDensityStatMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var weight: Any? = null
) : YDensityStatAesthetics