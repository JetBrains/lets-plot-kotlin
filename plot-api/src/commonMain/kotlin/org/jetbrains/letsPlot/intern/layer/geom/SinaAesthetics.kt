package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Aesthetic properties supported by [geomSina()][org.jetbrains.letsPlot.geom.geomSina].
 *
 * @property x X-axis coordinates.
 * @property y Y-axis coordinates.
 * @property violinWidth Density scaled for the sina plot, according to area, counts or to a constant maximum width.
 * @property alpha Transparency level of a layer. Understands numbers between 0 and 1.
 * @property color Color of the geometry. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @property fill Fill color. For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @property shape Shape of the sina points. For more info see: [aesthetics.html#point-shapes](https://lets-plot.org/kotlin/aesthetics.html#point-shapes).
 * @property size Lines width. Defines line width.
 * @property stroke Width of the shape border. Applied only to the shapes having border.
 * @property width Width of sina bounding box.
 */
interface SinaAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val violinWidth: Any?
    val alpha: Any?
    val color: Any?
    val fill: Any?
    val shape: Any?
    val size: Any?
    val stroke: Any?
    val width: Any?

    override fun seal() = Options.of(
        "x" to x,
        "y" to y,
        "violinwidth" to violinWidth,
        "alpha" to alpha,
        "color" to color,
        "fill" to fill,
        "shape" to shape,
        "size" to size,
        "stroke" to stroke,
        "width" to width
    )
}