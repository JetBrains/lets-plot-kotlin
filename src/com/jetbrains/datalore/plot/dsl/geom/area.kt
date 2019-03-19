package com.jetbrains.datalore.plot.dsl.geom

import com.jetbrains.datalore.plot.GeomKind
import com.jetbrains.datalore.plot.layer.GeomOptions
import com.jetbrains.datalore.plot.layer.geom.AreaAesthetics
import com.jetbrains.datalore.plot.layer.geom.AreaMapping

@Suppress("ClassName")
class area(
    mapping: AreaMapping.() -> Unit = {},
    override val x: Any? = null,
    override val y: Any? = null,
    override val alpha: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val linetype: Any? = null,
    override val size: Any? = null
) : AreaAesthetics,
    GeomOptions() {
    override val kind = GeomKind.AREA
    override val mapping = AreaMapping().apply(mapping).seal()
    override val parameters = this.seal()
}