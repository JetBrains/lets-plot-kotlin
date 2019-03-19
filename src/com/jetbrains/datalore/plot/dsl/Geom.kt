package com.jetbrains.datalore.plot.dsl

import com.jetbrains.datalore.plot.GeomKind
import com.jetbrains.datalore.plot.layer.GeomOptions
import com.jetbrains.datalore.plot.layer.geom.AreaAesthetics
import com.jetbrains.datalore.plot.layer.geom.AreaMapping
import com.jetbrains.datalore.plot.layer.geom.PointAesthetics
import com.jetbrains.datalore.plot.layer.geom.PointMapping

object Geom {
    val blank = GeomOptions(
        GeomKind.BLANK
    )

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
        GeomOptions(
            GeomKind.POINT,
            PointMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }

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
        GeomOptions(
            GeomKind.AREA,
            AreaMapping().apply(mapping).seal()
        ) {
        override val parameters = this.seal()
    }
}