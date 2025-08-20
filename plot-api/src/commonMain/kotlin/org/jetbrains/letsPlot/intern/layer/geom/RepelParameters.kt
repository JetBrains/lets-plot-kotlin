/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Parameters for text repel geometries ([geomTextRepel()][org.jetbrains.letsPlot.geom.geomTextRepel] and [geomLabelRepel()][org.jetbrains.letsPlot.geom.geomLabelRepel]).
 *
 * @param seed Random seed for text placement reproducibility.
 * @param maxIter Maximum number of iterations to attempt to resolve overlaps.
 * @param maxTime Maximum time (in seconds) to spend on resolving overlaps.
 * @param direction Direction in which text should be repelled ("both", "x", or "y").
 * @param pointPadding Amount of padding around each data point.
 * @param boxPadding Amount of padding around text boxes.
 * @param maxOverlaps Maximum number of overlaps tolerated before giving up.
 * @param minSegmentLength Minimum length for connecting segments. Shorter segments are not drawn.
 * @param arrow Arrow specification (created by [arrow()][org.jetbrains.letsPlot.arrow]) for segment endpoints.
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