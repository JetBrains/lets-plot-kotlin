/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

class TextRepelMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var label: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var size: Any? = null,
    override var family: Any? = null,
    override var fontface: Any? = null,
    override var hjust: Any? = null,
    override var vjust: Any? = null,
    override var angle: Any? = null,
    override var lineheight: Any? = null,
    override var shape: Any? = null,
    override var pointSize: Any? = null,
    override var pointStroke: Any? = null,
    override var segmentColor: Any? = null,
    override var segmentSize: Any? = null,
    override var segmentAlpha: Any? = null,
    override var linetype: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : TextRepelAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<TextRepelAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}


