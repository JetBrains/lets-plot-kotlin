/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.layer.WithGroupOption
import jetbrains.letsPlot.intern.layer.stat.QQStatAesthetics

class QQMapping(
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var shape: Any? = null,
    override var size: Any? = null,
    override var group: Any? = null,
    override var sample: Any? = null
) : QQAesthetics, QQStatAesthetics, WithGroupOption {
    override fun seal() = super<QQAesthetics>.seal() +
            super<QQStatAesthetics>.seal() + groupOption()
}