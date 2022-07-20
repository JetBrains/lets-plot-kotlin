/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption
import org.jetbrains.letsPlot.intern.layer.stat.CountStatAesthetics

class BarMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var width: Any? = null,
    override var size: Any? = null,
    override var group: Any? = null,
    override var weight: Any? = null
) : BarAesthetics, CountStatAesthetics, WithGroupOption {
    override fun seal() = super<BarAesthetics>.seal() +
            super<CountStatAesthetics>.seal() + groupOption()
}


