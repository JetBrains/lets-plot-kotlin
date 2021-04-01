/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("FunctionName", "SpellCheckingInspection")

package jetbrains.letsPlot

import jetbrains.letsPlot.facet.facetGrid
import jetbrains.letsPlot.facet.facetWrap
import jetbrains.letsPlot.geom.*
import jetbrains.letsPlot.intern.GenericAesMapping
import jetbrains.letsPlot.intern.OptionsMap
import jetbrains.letsPlot.intern.Plot

@Deprecated("", replaceWith = ReplaceWith("letsPlot(data, mapping)"))
fun lets_plot(data: Map<*, *>? = null, mapping: GenericAesMapping.() -> Unit = {}): Plot = letsPlot(data, mapping)

// Geometries

@Deprecated("", ReplaceWith("geomAbline"))
typealias geom_abline = geomAbline

@Deprecated("", ReplaceWith("geomArea"))
typealias geom_area = geomArea

@Deprecated("", ReplaceWith("geomBar"))
typealias geom_bar = geomBar

@Deprecated("", ReplaceWith("geomBin2d"))
typealias geom_bin2d = geomBin2d

@Deprecated("", ReplaceWith("geomBoxplot"))
typealias geom_boxplot = geomBoxplot

@Deprecated("", ReplaceWith("geomContour"))
typealias geom_contour = geomContour

@Deprecated("", ReplaceWith("geomContourf"))
typealias geom_contourf = geomContourf

@Deprecated("", ReplaceWith("geomCrossbar"))
typealias geom_crossbar = geomCrossbar

@Deprecated("", ReplaceWith("geomDensity"))
typealias geom_density = geomDensity

@Deprecated("", ReplaceWith("geomDensity2d"))
typealias geom_density2d = geomDensity2d

@Deprecated("", ReplaceWith("geomDensity2df"))
typealias geom_density2df = geomDensity2df

@Deprecated("", ReplaceWith("geomErrorbar"))
typealias geom_errorbar = geomErrorbar

@Deprecated("", ReplaceWith("geomFreqpoly"))
typealias geom_freqpoly = geomFreqpoly

@Deprecated("", ReplaceWith("geomHistogram"))
typealias geom_histogram = geomHistogram

@Deprecated("", ReplaceWith("geomHline"))
typealias geom_hline = geomHline

@Deprecated("", ReplaceWith("geomImage"))
typealias geom_image = geomImage

@Deprecated("", ReplaceWith("geomJitter"))
typealias geom_jitter = geomJitter

@Deprecated("", ReplaceWith("geomLine"))
typealias geom_line = geomLine

@Deprecated("", ReplaceWith("geomLinerange"))
typealias geom_linerange = geomLinerange

@Deprecated("", ReplaceWith("geomMap"))
typealias geom_map = geomMap

@Deprecated("", ReplaceWith("geomPath"))
typealias geom_path = geomPath

@Deprecated("", ReplaceWith("geomPoint"))
typealias geom_point = geomPoint

@Deprecated("", ReplaceWith("geomPointrange"))
typealias geom_pointrange = geomPointrange

@Deprecated("", ReplaceWith("geomPolygon"))
typealias geom_polygon = geomPolygon

@Deprecated("", ReplaceWith("geomRaster"))
typealias geom_raster = geomRaster

@Deprecated("", ReplaceWith("geomRect"))
typealias geom_rect = geomRect

@Deprecated("", ReplaceWith("geomRibbon"))
typealias geom_ribbon = geomRibbon

@Deprecated("", ReplaceWith("geomSegment"))
typealias geom_segment = geomSegment

@Deprecated("", ReplaceWith("geomSmooth"))
typealias geom_smooth = geomSmooth

@Deprecated("", ReplaceWith("geomStep"))
typealias geom_step = geomStep

@Deprecated("", ReplaceWith("geomText"))
typealias geom_text = geomText

@Deprecated("", ReplaceWith("geomTile"))
typealias geom_tile = geomTile

@Deprecated("", ReplaceWith("geomVline"))
typealias geom_vline = geomVline

// Facets

@Deprecated("", ReplaceWith("facetGrid(x, y, xOrder, yOrder, xFormat, yFormat)"))
fun facet_grid(
    x: String? = null,
    y: String? = null,
    xOrder: Int = 1,
    yOrder: Int = 1,
    xFormat: String? = null,
    yFormat: String? = null
): OptionsMap = facetGrid(x, y, xOrder, yOrder, xFormat, yFormat)

@Deprecated("", ReplaceWith("facetWrap(facets, ncol, nrow, order, format, dir)"))
fun facet_wrap(
    facets: Any,
    ncol: Any? = null,
    nrow: Any? = null,
    order: Any? = null,
    format: Any? = null,
    dir: String = "h"
): OptionsMap = facetWrap(facets, ncol, nrow, order, format, dir)

// Scales
// Positions
// Coordinates
// Theme functions
// Sampling