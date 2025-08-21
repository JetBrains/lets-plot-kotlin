/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

/**
 * Parameters supported by [geomYDotplot()][org.jetbrains.letsPlot.geom.geomYDotplot].
 *
 * @param stackDir Which direction to stack the dots.
 *  Values: "up", "down", "center", "centerwhole" (default = "up").
 * @param stackRatio default = 1.0.
 *  How close to stack the dots.
 *  Use smaller values for closer, overlapping dots.
 * @param dotSize default = 1.0.
 *  The diameter of the dots relative to binWidth.
 * @param stackGroups Stack dots across groups when method = "histodot".
 */
interface YDotplotParameters : DotplotParameters
