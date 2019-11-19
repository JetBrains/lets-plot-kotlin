package jetbrains.datalorePlot.intern.layer.stat

import jetbrains.datalorePlot.intern.Options
import jetbrains.datalorePlot.intern.layer.OptionsCapsule

interface BinParameters : OptionsCapsule {
    val binCount: Int
    val binWidth: Double?
    val center: Double?
    val boundary: Double?

    override fun seal() = Options.of(
        "binCount" to binCount,
        "binWidth" to binWidth,
        "center" to center,
        "boundary" to boundary
    )

    companion object {
        const val DEF_BIN_COUNT = 30
    }
}
