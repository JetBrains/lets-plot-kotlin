/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption
import org.jetbrains.letsPlot.intern.layer.stat.Count2dStatAesthetics

class PieMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var slice: Any? = null,
    override var explode: Any? = null,
    override var size: Any? = null,
    override var fill: Any? = null,
    override var color: Any? = null,
    override var alpha: Any? = null,
    override var group: Any? = null,
    override var weight: Any? = null
) : PieAesthetics, Count2dStatAesthetics, WithGroupOption {
    override fun seal() = super<PieAesthetics>.seal() +
            super<Count2dStatAesthetics>.seal() + groupOption()
}