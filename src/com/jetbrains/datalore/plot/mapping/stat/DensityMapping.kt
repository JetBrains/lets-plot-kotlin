package com.jetbrains.datalore.plot.mapping.stat

import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.mapping.AestheticsOptions
import com.jetbrains.datalore.plot.mapping.MappingOptions

class DensityMapping(
    var x: Any? = null,
    var y: Any? = null,
    var alpha: Any? = null,
    var color: Any? = null,
    var fill: Any? = null,
    var linetype: Any? = null,
    var size: Any? = null,
    var weight: Any? = null,
    override var group: Any? = null
) : AestheticsOptions, MappingOptions {
    override fun toFrozen(): Options {
        return withGroup(
            Options(
                mapOf(
                    "x" to x,
                    "y" to y,
                    "alpha" to alpha,
                    "color" to color,
                    "fill" to fill,
                    "linetype" to linetype,
                    "size" to size,
                    "weight" to weight
                )
            )
        )
    }
}
