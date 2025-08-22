/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

/**
 * Aesthetic mappings supported by [statDensity()][org.jetbrains.letsPlot.stat.statDensity].
 *
 * @param x X-axis coordinates.
 * @param weight Used by the stat to compute weighted density.
 */
class DensityStatMapping(
    override var x: Any? = null,
    override var weight: Any? = null
) : DensityStatAesthetics
