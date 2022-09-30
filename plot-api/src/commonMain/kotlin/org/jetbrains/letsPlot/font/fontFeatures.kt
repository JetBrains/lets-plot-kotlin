/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.font

import jetbrains.datalore.plot.config.Option
import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.intern.filterNonNullValues

/**
 * Adjust estimated width of text labels on plot.
 *
 * Allows for manual correction in a rare cases when plot layout looks broken
 * due to either overestimation or underestimation of size of text labels on plot.
 *
 * Can be mixed with other plot features in a plot-expression:
 * ```
 * p + ggsize(300, 500) + fontMetricsAdjustment(1.3)
 * ```
 *
 * @param widthCorrection Correcting coefficient applied to default width estimate of a text label.
 *
 */
fun fontMetricsAdjustment(widthCorrection: Number?) = OptionsMap(
    kind = Option.Plot.METAINFO,
    name = Option.PlotMetainfo.FONT_METRICS_ADJUSTMENT,
    options = mapOf(
        Option.FontMetainfo.WIDTH_CORRECTION to widthCorrection
    )
        .filterNonNullValues()
        .toMutableMap()
)

/**
 * Specify properies of a particular font-family to adjust estimated width of text labels on plot.
 *
 * Might be useful when some exotic font-family is used that causes issues with the plot layout.
 *
 * Allows for manual correction in a rare cases when plot layout looks broken
 * due to either overestimation or underestimation of size of text labels on plot.
 *
 * Can be mixed with other plot features in a plot-expression:
 * ```
 * p + ggsize(300, 500) + fontFamilyInfo("HyperFont", mono = true)
 * ```
 *
 * @param family Font family.
 * @param widthCorrection Correcting coefficient applied to default width estimate of a text label.
 * @param mono When True - the font is marked as `monospaced`.
 *
 */
fun fontFamilyInfo(
    family: String,
    widthCorrection: Number? = null,
    mono: Boolean? = null
) = OptionsMap(
    kind = Option.Plot.METAINFO,
    name = Option.PlotMetainfo.FONT_FAMILY_INFO,
    options = mapOf(
        Option.FontMetainfo.FAMILY to family,
        Option.FontMetainfo.WIDTH_CORRECTION to widthCorrection,
        Option.FontMetainfo.MONOSPACED to mono
    )
        .filterNonNullValues()
        .toMutableMap()
)
