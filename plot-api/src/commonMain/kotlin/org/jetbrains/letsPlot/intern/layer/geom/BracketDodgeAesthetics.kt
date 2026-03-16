package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.plot.base.Aes
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * TODO
 */
interface BracketDodgeAesthetics : OptionsCapsule {
    val x: Any?
    val y: Any?
    val iStart: Any?
    val iEnd: Any?
    val lenStart: Any?
    val lenEnd: Any?
    val label: Any?
    val size: Any?
    val linetype: Any?
    val color: Any?
    val alpha: Any?
    val family: Any?
    val fontface: Any?
    val hjust: Any?
    val vjust: Any?
    val angle: Any?
    val lineheight: Any?
    val segmentColor: Any?
    val segmentSize: Any?
    val segmentAlpha: Any?

    override fun seal() = Options.of(
        Aes.X.name to x,
        Aes.Y.name to y,
        Aes.ISTART.name to iStart,
        Aes.IEND.name to iEnd,
        Aes.LENSTART.name to lenStart,
        Aes.LENEND.name to lenEnd,
        Aes.LABEL.name to label,
        Aes.SIZE.name to size,
        Aes.LINETYPE.name to linetype,
        Aes.COLOR.name to color,
        Aes.ALPHA.name to alpha,
        Aes.FAMILY.name to family,
        Aes.FONTFACE.name to fontface,
        Aes.HJUST.name to hjust,
        Aes.VJUST.name to vjust,
        Aes.ANGLE.name to angle,
        Aes.LINEHEIGHT.name to lineheight,
        Aes.SEGMENT_COLOR.name to segmentColor,
        Aes.SEGMENT_SIZE.name to segmentSize,
        Aes.SEGMENT_ALPHA.name to segmentAlpha,
    )
}