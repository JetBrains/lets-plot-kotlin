/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

/**
 * Aesthetic mappings supported by [statECDF()][org.jetbrains.letsPlot.stat.statECDF].
 *
 * @param x X-axis coordinates.
 * @param y Y-axis coordinates.
 */
class ECDFStatMapping(
    override var x: Any? = null,
    override var y: Any? = null,
) : ECDFStatAesthetics