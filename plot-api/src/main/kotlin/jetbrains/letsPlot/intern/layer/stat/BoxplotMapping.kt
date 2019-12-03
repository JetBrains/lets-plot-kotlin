/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.layer.WithGroupOption
import jetbrains.letsPlot.intern.Options

class BoxplotMapping(
    var alpha: Any? = null,
    var color: Any? = null,
    var fill: Any? = null,
    var size: Any? = null,
    var linetype: Any? = null,
    var shape: Any? = null,
    override var weight: Any? = null,
    override var group: Any? = null
) : BoxplotAesthetics, WithGroupOption {
    override fun seal() = super.seal() + Options.of(
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "size" to size,
        "linetype" to linetype,
        "shape" to shape
    ) + group()
}
