/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.layer.WithGroupOption
import jetbrains.letsPlot.intern.layer.stat.ContourStatAesthetics

class ContourMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var z: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var linetype: Any? = null,
    override var size: Any? = null,
    override var speed: Any? = null,
    override var flow: Any? = null,
    override var group: Any? = null
) : PathAesthetics, ContourStatAesthetics, WithGroupOption {
    override fun seal() = super<PathAesthetics>.seal() +
            super<ContourStatAesthetics>.seal() +
            group()
}