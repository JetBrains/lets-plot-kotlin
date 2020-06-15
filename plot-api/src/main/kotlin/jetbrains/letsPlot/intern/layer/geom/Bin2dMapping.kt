/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.layer.WithGroupOption
import jetbrains.letsPlot.intern.layer.stat.Bin2dAesthetics

class Bin2dMapping(
    override var x: Double? = null,
    override var y: Double? = null,
    override var width: Double? = null,
    override var height: Double? = null,
    override var alpha: Double? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var linetype: Any? = null,
    override var size: Double? = null,
    override var weight: Any? = null,
    override var group: Any? = null
) : TileAesthetics, Bin2dAesthetics, WithGroupOption {
    override fun seal() = super<TileAesthetics>.seal() +
            super<Bin2dAesthetics>.seal() +
            group()
}