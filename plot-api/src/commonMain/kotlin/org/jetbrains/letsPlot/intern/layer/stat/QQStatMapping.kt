/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

/**
 * Aesthetic mappings supported by [statQQ()][org.jetbrains.letsPlot.stat.statQQ].
 *
 * @param sample Y-axis value.
 */
class QQStatMapping(
    override var sample: Any? = null
) : QQStatAesthetics