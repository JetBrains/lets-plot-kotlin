/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.layer.WithGroupOption
import jetbrains.letsPlot.intern.layer.stat.QQStatAesthetics

class QQLineMapping(
    override var sample: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var linetype: Any? = null,
    override var size: Any? = null,
    override var group: Any? = null,
) : QQLineAesthetics, QQStatAesthetics, WithGroupOption {
    override fun seal() = super<QQLineAesthetics>.seal() +
            super<QQStatAesthetics>.seal() + groupOption()
}