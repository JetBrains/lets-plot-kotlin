/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption
import org.jetbrains.letsPlot.intern.layer.stat.ContourStatAesthetics
import org.jetbrains.letsPlot.intern.layer.stat.Density2dStatAesthetics

class Density2dMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var z: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var linetype: Any? = null,
    override var size: Any? = null,
    override var weight: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : PathAesthetics, ContourStatAesthetics, Density2dStatAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<PathAesthetics>.seal() +
            super<ContourStatAesthetics>.seal() +
            super<Density2dStatAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}

