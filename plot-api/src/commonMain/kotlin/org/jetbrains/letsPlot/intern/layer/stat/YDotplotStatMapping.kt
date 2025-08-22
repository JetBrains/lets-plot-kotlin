/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

/**
 * Aesthetic mappings supported by the default stat of [geomYDotplot()][org.jetbrains.letsPlot.geom.geomYDotplot].
 *
 * @param x X-axis coordinates.
 */
class YDotplotStatMapping(
    override var x: Any? = null,
) : YDotplotStatAesthetics
