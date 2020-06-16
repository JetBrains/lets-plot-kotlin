/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.OptionsCapsule

interface BoxplotAesthetics : OptionsCapsule {
    val x: Any?
    val lower: Any?
    val middle: Any?
    val upper: Any?
    val ymin: Any?
    val ymax: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val size: Any?
    val linetype: Any?
    val shape: Any?
    val width: Any?
    val weight: Any?
    override fun seal() = Options.empty()
}
