/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package kotlinIsland

import org.jetbrains.letsPlot.awt.plot.util.SimplePlotViewerWindow
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.intern.toSpec
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.toolkit.geotools.toSpatialDataset
import java.awt.Dimension

fun main() {
    // GeoTools
    val places = KotlinIslandGeojson.loadPlaces()

    // Lets-Plot
    val placesSD = places.toSpatialDataset()
    val p = letsPlot() +
            geomPoint(map = placesSD, color = "red") +
            ggtitle("Kotlin Island Tourists Attractions")

    SimplePlotViewerWindow(
        "Spatial plot demo",
        rawSpec = p.toSpec(),
        windowSize = Dimension(800, 500),
        preserveAspectRatio = false,
        repaintDelay = 300
    ).open()

}
