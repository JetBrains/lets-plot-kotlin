package org.jetbrains.letsPlot.intern.layer.stat

/**
 * Aesthetic mappings supported by [statBinHex()][org.jetbrains.letsPlot.stat.statBinHex].
 *
 * @param x X-axis value.
 * @param y Y-axis value.
 * @param weight Used to compute weighted sum instead of simple count.
 */
class BinHexStatMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var weight: Any? = null
) : BinHexStatAesthetics