package com.jetbrains.datalore.plot

import com.jetbrains.datalore.plot.GeomKind.POINT

class GeomOptions(
    val kind: GeomKind,
    val mapping: Options = Options.empty(),
    val constants: Options = Options.empty()
)


private typealias GeomSupplier = () -> GeomOptions

val blank: GeomSupplier = { GeomOptions(GeomKind.BLANK) }

@Suppress("ClassName")
class point(
    private val mapping: PointMapping.() -> Unit = {},
    override var x: Any? = null,
    override var y: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var group: Any? = null,
    override var shape: Any? = null,
    override var size: Any? = null,
    override var stroke: Any? = null
) : PointAesthetics, GeomSupplier {

    override fun invoke(): GeomOptions {
        return GeomOptions(
            POINT,
            mapping = PointMapping().apply(mapping).toFrozen(),
            constants = toFrozen()
        )
    }
}