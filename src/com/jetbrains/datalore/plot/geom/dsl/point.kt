package com.jetbrains.datalore.plot.geom.dsl

import com.jetbrains.datalore.plot.geom.*
import com.jetbrains.datalore.plot.geom.aes.PointAesthetics
import com.jetbrains.datalore.plot.geom.aes.PointMapping

@Suppress("ClassName")
class point(
    private val mapping: PointMapping.() -> Unit = {},
    override val x: Any? = null,
    override val y: Any? = null,
    override val alpha: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val shape: Any? = null,
    override val size: Any? = null,
    override val stroke: Any? = null
) : PointAesthetics, GeomSupplier {

    override fun invoke(): GeomOptions {
        return GeomSuppliers.point(
            mapping = PointMapping().apply(mapping).toFrozen(),
            constants = this.toFrozen()
        ).invoke()
    }
}