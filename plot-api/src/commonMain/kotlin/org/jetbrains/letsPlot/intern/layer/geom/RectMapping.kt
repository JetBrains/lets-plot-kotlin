/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

class RectMapping(
    override var xmin: Any? = null,
    override var xmax: Any? = null,
    override var ymin: Any? = null,
    override var ymax: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var linetype: Any? = null,
    override var size: Any? = null,
    override var fill: Any? = null,
    override var group: Any? = null
) : RectAesthetics, WithGroupOption {
    override fun seal() = super.seal() + groupOption()
}


