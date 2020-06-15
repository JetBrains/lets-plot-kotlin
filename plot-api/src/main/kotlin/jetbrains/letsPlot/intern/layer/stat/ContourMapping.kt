/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.layer.WithGroupOption

class ContourMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var z: Any? = null,
    override var group: Any? = null
) : ContourAesthetics,
    WithGroupOption {
    override fun seal() = super.seal() + group()
}