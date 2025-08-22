/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

/**
 * Aesthetic mappings supported by [geomDotplot()][org.jetbrains.letsPlot.geom.geomDotplot].
 *
 * @param x X-axis coordinates.
 */
class DotplotStatMapping(
    override var x: Any? = null,
) : DotplotStatAesthetics
