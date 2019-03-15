package com.jetbrains.datalore.plot.dsl.geom

import com.jetbrains.datalore.plot.GeomKind
import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.layer.GeomOptions
import com.jetbrains.datalore.plot.layer.geom.GeomSupplier
import com.jetbrains.datalore.plot.layer.geom.Geoms
import com.jetbrains.datalore.plot.layer.geom.PointAesthetics
import com.jetbrains.datalore.plot.layer.geom.PointMapping

@Suppress("ClassName")
class point(
    mapping: PointMapping.() -> Unit = {},
    override val x: Any? = null,
    override val y: Any? = null,
    override val alpha: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val shape: Any? = null,
    override val size: Any? = null,
    override val stroke: Any? = null
) : PointAesthetics,
    GeomOptions() {
    override val kind = GeomKind.POINT
    override val mapping = PointMapping().apply(mapping).toFrozen()
    override val parameters = this.toFrozen()

//    override fun invoke(): GeomOptions {
//        return Geoms.Point(
//            mapping = PointMapping().apply(mapping).toFrozen(),
//            constants = this.toFrozen()
//        )
//    }
}