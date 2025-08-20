/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Parameters for [geomCurve()][org.jetbrains.letsPlot.geom.geomCurve].
 *
 * @param curvature Amount of curvature. Negative values produce left-hand curves, positive values produce right-hand curves, and zero produces a straight line.
 * @param angle Angle at which the curve bends. Used to control the shape of the curve.
 * @param ncp Number of control points used to draw the curve. More points create smoother curves.
 * @param spacer Amount of space between the curve endpoints and the target points.
 * @param arrow Arrow specification (created by [arrow()][org.jetbrains.letsPlot.arrow]) for curve endpoints.
 */
interface CurveParameters : OptionsCapsule {
    val curvature: Number?
    val angle: Number?
    val ncp: Int?
    val spacer: Number?
    val arrow: Map<String, Any>?

    override fun seal() = Options.of(
        Option.Geom.Curve.CURVATURE to curvature,
        Option.Geom.Curve.ANGLE to angle,
        Option.Geom.Curve.NCP to ncp,
        Option.Geom.Curve.SPACER to spacer,
        Option.Geom.Curve.ARROW to arrow
    )
}