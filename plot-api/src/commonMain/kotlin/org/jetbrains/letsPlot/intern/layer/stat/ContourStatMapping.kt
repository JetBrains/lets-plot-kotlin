/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

/**
 * Aesthetic mappings supported by [statContour()][org.jetbrains.letsPlot.stat.statContour].
 *
 * @param x X-axis coordinates of the center of rectangles, forming a tessellation.
 * @param y Y-axis coordinates of the center of rectangles, forming a tessellation.
 * @param z Value at point (x, y).
 */
class ContourStatMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var z: Any? = null
) : ContourStatAesthetics
