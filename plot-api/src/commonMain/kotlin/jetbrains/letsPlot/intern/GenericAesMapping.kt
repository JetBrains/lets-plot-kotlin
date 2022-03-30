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
    var z: Any? = null,
    var alpha: Any? = null,
    var color: Any? = null,
    var fill: Any? = null,
    var shape: Any? = null,
    var linetype: Any? = null,

    var size: Any? = null,
    var width: Any? = null,
    var height: Any? = null,

    var intercept: Any? = null,
    var slope: Any? = null,
    var xintercept: Any? = null,
    var yintercept: Any? = null,

    var xmin: Any? = null,
    var xmax: Any? = null,
    var ymin: Any? = null,
    var ymax: Any? = null,
    var xend: Any? = null,
    var yend: Any? = null,

    var label: Any? = null,
    var weight: Any? = null,

    override var group: Any? = null

) : OptionsCapsule, WithGroupOption {
    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "z" to z,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "shape" to shape,
        "linetype" to linetype,

        "size" to size,
        "width" to width,
        "height" to height,

        "intercept" to intercept,
        "slope" to slope,
        "xintercept" to xintercept,
        "yintercept" to yintercept,

        "xmin" to xmin,
        "xmax" to xmax,
        "ymin" to ymin,
        "ymax" to ymax,
        "xend" to xend,
        "yend" to yend,

        "label" to label,
        "weight" to weight,

        ) + groupOption()
}