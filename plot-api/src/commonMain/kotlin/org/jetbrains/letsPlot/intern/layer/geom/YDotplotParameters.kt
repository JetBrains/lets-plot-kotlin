/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

/**
 * Parameters supported by [geomYDotplot()][org.jetbrains.letsPlot.geom.geomYDotplot].
 *
 * @property stackDir Which direction to stack the dots.
 *  Values: "up", "down", "center", "centerwhole" (default = "up").
 * @property stackRatio default = 1.0.
 *  How close to stack the dots.
 *  Use smaller values for closer, overlapping dots.
 * @property dotSize default = 1.0.
 *  The diameter of the dots relative to binWidth.
 * @property stackGroups Stack dots across groups when method = "histodot".
 */
interface YDotplotParameters : DotplotParameters
