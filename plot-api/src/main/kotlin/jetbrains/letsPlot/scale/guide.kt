package jetbrains.letsPlot.scale

import jetbrains.letsPlot.intern.filterNonNullValues

/**
 * Legend guide.
 * Legend type guide shows key (i.e., geoms) mapped onto values.
 *
 * @param nrow int, optional
 * Number of rows in legend's guide
 * @param ncol int, optional
 * Number of columns in legend's guide
 * @param byrow boolean, optional
 * Type of output: by row (default), or by column
 */
@Suppress("FunctionName")
fun guide_legend(
    nrow: Int? = null,
    ncol: Int? = null,
    byrow: Boolean? = null
) : Map<String, Any> {
    return mapOf(
        "nrow" to nrow,
        "ncol" to ncol,
        "byrow" to byrow
    ).filterNonNullValues()
}
