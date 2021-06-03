/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.layer.WithGroupOption

class RasterMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var alpha: Any? = null,
    override var fill: Any? = null,
    override var group: Any? = null
) : RasterAesthetics, WithGroupOption {
    override fun seal() = super.seal() + groupOption()
}


