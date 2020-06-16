/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.stat

import jetbrains.letsPlot.intern.layer.WithGroupOption

class Density2dMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var weight: Any? = null,
    override var group: Any? = null
) : Density2dAesthetics, WithGroupOption {
    override fun seal() = super.seal() + group()
}