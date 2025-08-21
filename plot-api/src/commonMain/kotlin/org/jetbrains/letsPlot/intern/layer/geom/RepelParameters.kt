package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Parameters supported by [geomTextRepel()][org.jetbrains.letsPlot.geom.geomTextRepel] and [geomLabelRepel()][org.jetbrains.letsPlot.geom.geomLabelRepel].
 *
 * @param seed Random seed for reproducibility.
 * @param maxIter Maximum number of iterations used to resolve collisions.
 * @param maxTime Maximum allowed time in seconds for resolving label collisions.
 * @param direction Direction in which text labels can be moved.
 * @param pointPadding Padding around data points to prevent overlap with text labels.
 * @param boxPadding Padding around text labels to prevent overlap between labels.
 * @param maxOverlaps The maximum number of overlapping text labels allowed. Additional text labels will be hidden.
 * @param minSegmentLength Minimum length of the line connecting the label to the point. Shorter segments will be omitted.
 * @param arrow Specification for arrow head, as created by `arrow()` function.
 */
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