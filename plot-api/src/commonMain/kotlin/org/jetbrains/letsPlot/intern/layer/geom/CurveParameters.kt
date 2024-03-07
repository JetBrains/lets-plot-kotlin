package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.OptionsCapsule

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