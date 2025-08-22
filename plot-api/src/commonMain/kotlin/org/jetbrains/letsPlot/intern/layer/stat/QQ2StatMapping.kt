/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

/**
 * Aesthetic mappings supported by [statQQ2()][org.jetbrains.letsPlot.stat.statQQ2].
 *
 * @param x X-axis value.
 * @param y Y-axis value.
 */
class QQ2StatMapping(
    override var x: Any? = null,
    override var y: Any? = null
) : QQ2StatAesthetics