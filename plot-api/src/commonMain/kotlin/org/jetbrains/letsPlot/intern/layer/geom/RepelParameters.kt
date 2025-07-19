package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

interface RepelParameters : OptionsCapsule {
    val seed: Int?
    val maxIter: Int?
    val maxTime: Double?
    val direction: String?
    val pointPadding: Number?
    val boxPadding: Number?
    val maxOverlaps: Int?
    val minSegmentLength: Number?
    val arrow: Map<String, Any>?


    override fun seal() = Options.of(
        Option.Geom.Repel.SEED to seed,
        Option.Geom.Repel.MAX_ITER to maxIter,
        Option.Geom.Repel.MAX_TIME to maxTime,
        Option.Geom.Repel.DIRECTION to direction,
        Option.Geom.Repel.POINT_PADDING to pointPadding,
        Option.Geom.Repel.BOX_PADDING to boxPadding,
        Option.Geom.Repel.MAX_OVERLAPS to maxOverlaps,
        Option.Geom.Repel.MIN_SEGMENT_LENGTH to minSegmentLength,
        Option.Geom.Repel.ARROW to arrow
    )
}