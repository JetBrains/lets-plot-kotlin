package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

/**
 * Parameters supported by [geomCurve()][org.jetbrains.letsPlot.geom.geomCurve].
 *
 * @param curvature The amount of curvature.
 *  Negative values produce left-hand curves, positive values produce right-hand curves.
 * @param angle Angle in degrees, giving an amount to skew the control points of the curve.
 *  Values less than 90 skew the curve towards the start point
 *  and values greater than 90 skew the curve towards the end point.
 * @param ncp The number of control points used to draw the curve. More control points creates a smoother curve.
 * @param spacer Space to shorten a curve by moving the start/end.
 * @param arrow Specification for arrow head, as created by `arrow()` function.
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