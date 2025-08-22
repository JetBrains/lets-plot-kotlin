/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

/**
 * Aesthetic mappings supported by [statCount2D()][org.jetbrains.letsPlot.stat.statCount2D].
 *
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param weight Used by the stat to compute weighted sum instead of simple count.
 */
class Count2dStatMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var weight: Any? = null
) : Count2dStatAesthetics