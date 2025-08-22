/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

/**
 * Aesthetic mappings supported by [statDensity2D()][org.jetbrains.letsPlot.stat.statDensity2D].
 *
 * @param x X-axis coordinates of the center of rectangles, forming a tessellation.
 * @param y Y-axis coordinates of the center of rectangles, forming a tessellation.
 * @param weight Used by the stat to compute weighted density.
 */
class Density2dStatMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var weight: Any? = null
) : Density2dStatAesthetics