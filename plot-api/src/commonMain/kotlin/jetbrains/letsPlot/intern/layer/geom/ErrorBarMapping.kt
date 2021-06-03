/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.layer.WithGroupOption

class ErrorBarMapping(
    override var x: Any? = null,
    override var ymin: Any? = null,
    override var ymax: Any? = null,
    override var width: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var linetype: Any? = null,
    override var size: Any? = null,
    override var group: Any? = null
) : ErrorBarAesthetics, WithGroupOption {
    override fun seal() = super.seal() + groupOption()
}