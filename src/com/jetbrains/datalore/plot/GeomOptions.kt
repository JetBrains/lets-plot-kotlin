package com.jetbrains.datalore.plot

import com.jetbrains.datalore.plot.GeomKind.POINT


private typealias GeomSupplier = () -> GeomOptions

class GeomOptions(
    val kind: GeomKind,
    val mapping: Options = Options.empty(),
    val constants: Options = Options.empty()
)


val blank: GeomSupplier = { GeomOptions(GeomKind.BLANK) }

internal object GeomSuppliers {
    internal fun point(
        mapping: PointMapping.() -> Unit = {},
        constants: Options
    ) = point(
        mapping = PointMapping().apply(mapping).toFrozen(),
        constants = constants
    )

    internal fun point(
        mapping: Options,
        constants: Options
    ) = {
        GeomOptions(
            POINT,
            mapping = mapping,
            constants = constants
        )
    }
}


@Suppress("ClassName")
class point(
    private val mapping: PointMapping.() -> Unit = {},
    override val x: Any? = null,
    override val y: Any? = null,
    override val alpha: Any? = null,
    override val color: Any? = null,
    override val fill: Any? = null,
    override val group: Any? = null,
    override val shape: Any? = null,
    override val size: Any? = null,
    override val stroke: Any? = null
) : PointAesthetics, GeomSupplier {

    override fun invoke(): GeomOptions {
        return GeomSuppliers.point(
            mapping = mapping,
            constants = this.toFrozen()
        ).invoke()
    }
}