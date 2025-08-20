/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

/**
 * Aesthetic mappings supported by [geomLabel()][org.jetbrains.letsPlot.geom.geomLabel].
 *
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param label Text to add to plot.
 * @param alpha Opacity; a number in [0, 1]. Lower values are more transparent (0 - transparent, 1 - opaque).
 * @param color Label text color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param fill Label background color.
 *  For more info see: [aesthetics.html#color-and-fill](https://lets-plot.org/kotlin/aesthetics.html#color-and-fill).
 * @param size Label font size.
 * @param family Label font family.
 *  For more info see: [aesthetics.html#font-family](https://lets-plot.org/kotlin/aesthetics.html#font-family).
 * @param fontface Label font face.
 *  For more info see: [aesthetics.html#font-face](https://lets-plot.org/kotlin/aesthetics.html#font-face).
 * @param hjust Horizontal text alignment relative to the x-coordinate.
 * @param vjust Vertical text alignment relative to the y-coordinate.
 * @param angle Label rotation angle in degrees.
 * @param lineheight Line height multiplier applied to the font size in the case of multi-line text.
 * @param group Grouping key. Observations with the same value form one group.
 *  If not set, grouping may be inferred from other aesthetics (e.g., color, shape).
 * @param paint_a Auxiliary paint channel A that can be used as either `color` or `fill` as needed.
 *  Map a variable here for composite/multi-channel color with a matching scale.
 * @param paint_b Auxiliary paint channel B. See `paint_a`.
 * @param paint_c Auxiliary paint channel C. See `paint_a`.
 */
class LabelMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var label: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var size: Any? = null,
    override var family: Any? = null,
    override var fontface: Any? = null,
    override var hjust: Any? = null,
    override var vjust: Any? = null,
    override var angle: Any? = null,
    override var lineheight: Any? = null,
    override var group: Any? = null,
    override var paint_a: Any? = null,
    override var paint_b: Any? = null,
    override var paint_c: Any? = null
) : LabelAesthetics, WithGroupOption, PaintAesthetics {
    override fun seal() = super<LabelAesthetics>.seal() +
            groupOption() +
            super<PaintAesthetics>.seal()
}