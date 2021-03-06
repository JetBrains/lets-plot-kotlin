/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern

import jetbrains.letsPlot.intern.layer.OptionsCapsule
import jetbrains.letsPlot.intern.layer.WithGroupOption

class GenericAesMapping(
    var x: Any? = null,
    var y: Any? = null,
    var alpha: Any? = null,
    var color: Any? = null,
    var fill: Any? = null,
    override var group: Any? = null
) : OptionsCapsule, WithGroupOption {
    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill
    ) + groupOption()
}