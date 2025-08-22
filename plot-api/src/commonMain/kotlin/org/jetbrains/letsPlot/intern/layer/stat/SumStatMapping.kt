package org.jetbrains.letsPlot.intern.layer.stat

/**
 * Aesthetic mappings supported by [statSum()][org.jetbrains.letsPlot.stat.statSum].
 *
 * @param x X-axis coordinates.
 * @param y Y-axis coordinates.
 */
class SumStatMapping(
    override var x: Any? = null,
    override var y: Any? = null,
) : SumStatAesthetics