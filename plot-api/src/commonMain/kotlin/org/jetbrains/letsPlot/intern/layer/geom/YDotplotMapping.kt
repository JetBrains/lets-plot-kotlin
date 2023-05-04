/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

class YDotplotMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var binWidth: Any? = null,
    override var stackSize: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : YDotplotAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<YDotplotAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}