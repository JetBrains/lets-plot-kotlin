/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.layer.WithGroupOption
import jetbrains.letsPlot.intern.layer.stat.Density2dStatAesthetics

class Density2dfMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var size: Any? = null,
    override var linetype: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var alpha: Any? = null,
    override var weight: Any? = null,
    override var group: Any? = null
) : PolygonAesthetics, Density2dStatAesthetics, WithGroupOption {
    override fun seal() = super<PolygonAesthetics>.seal() +
            super<Density2dStatAesthetics>.seal() +
            group()
}
