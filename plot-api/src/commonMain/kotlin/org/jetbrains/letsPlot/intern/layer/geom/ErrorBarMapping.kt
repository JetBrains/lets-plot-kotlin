/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

class ErrorBarMapping(
    override var x: Any? = null,
    override var ymin: Any? = null,
    override var ymax: Any? = null,
    override var width: Any? = null,
    override var y: Any? = null,
    override var xmin: Any? = null,
    override var xmax: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var linetype: Any? = null,
    override var size: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : ErrorBarAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<ErrorBarAesthetics>.seal() + groupOption() +
            super<PaintAesthetics>.seal()
}