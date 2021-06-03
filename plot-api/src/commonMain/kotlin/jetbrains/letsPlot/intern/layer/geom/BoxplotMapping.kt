/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.layer.WithGroupOption
import jetbrains.letsPlot.intern.layer.stat.BoxplotStatAesthetics

class BoxplotMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var lower: Any? = null,
    override var middle: Any? = null,
    override var upper: Any? = null,
    override var ymin: Any? = null,
    override var ymax: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var size: Any? = null,
    override var linetype: Any? = null,
    override var shape: Any? = null,
    override var width: Any? = null,
    override var group: Any? = null,
    override var weight: Any? = null
) : BoxplotAesthetics, BoxplotStatAesthetics, WithGroupOption {

    override fun seal() = super<BoxplotAesthetics>.seal() +
            super<BoxplotStatAesthetics>.seal() + groupOption()
}
