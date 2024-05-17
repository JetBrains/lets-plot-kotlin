# Inset Map of Kotlin Island

Kotlin island is situated in Gulf of Finland and is one of districts of the city of Saint Petersburg in Russia.

This example shows how Lets-Plot-Kotlin `SpatialDataset` class can help to build an inset map of Kotlin island.

[The geodata is provided by © OpenStreetMap contributors and is made available here under the Open Database License (ODbL)](https://www.openstreetmap.org/copyright).

```kotlin
import java.net.URL
import org.geotools.geojson.feature.FeatureJSON

fun getData(url: String): SpatialDataset {
    return FeatureJSON()
        .readFeatureCollection(URL(url).readText()) // Read GeoJSON file.
        .toSpatialDataset() // Transform GeoTools FeatureCollection to Lets-Plot SpatialDataset.
}

data class BBox(val xmin: Double, val ymin: Double, val xmax: Double, val ymax: Double)

val kotlinBBox = BBox(29.63, 59.965, 29.815, 60.035)

// Load boundaries of St.-Petersburg districts.
val spbDistrictsData = getData("https://raw.githubusercontent.com/JetBrains/lets-plot-docs/master/data/spb_districts.geojson")

// Create a map showing all districts of St.-Petersburg.
// This map will become the inset map. The red rectangle indicates the bounds of the future main map.
val spbPlot = letsPlot() +
        geomPolygon(map = spbDistrictsData, color = "#a1d99b", fill = "#f7fcf5") +
        geomRect(xmin = kotlinBBox.xmin, ymin = kotlinBBox.ymin, xmax = kotlinBBox.xmax, ymax = kotlinBBox.ymax, color = "red", alpha = 0) +
        geomText(label = "Saint Petersburg", x = 30.334445, y = 59.934294, color = "black", size = 6) +
        scaleXContinuous(expand = listOf(0.0, 0.0)) + scaleYContinuous(expand = listOf(0.0, 0.0)) +
        themeVoid() + theme(panelBackground = elementRect(color = "black", fill = "white"))

// Create the main map with only Kotlin island on it.
// We use xlim and ylim parameters of the coordinate system to crop the entire map containing all districts of St.-Petersburg.
// SpatialDataset containing names and coordinates of some tourist attractions to show on the main map
val kotlinPlacesData = getData("https://raw.githubusercontent.com/JetBrains/lets-plot-docs/master/data/kotlin_places.geojson")

// Cut-out the Kotlin area and add layes with text and points of interest.
val kotlinPlot = letsPlot() +
        geomRect(xmin = kotlinBBox.xmin, ymin = kotlinBBox.ymin, xmax = kotlinBBox.xmax, ymax = kotlinBBox.ymax, fill = "#aadaff", alpha = 0.2) +
        geomPolygon(map = spbDistrictsData, color = "#31a354", fill = "#e5f5e0") +
        geomPoint(map = kotlinPlacesData, size = 5) { color = "type"; shape = "type" } +
        geomText(map = kotlinPlacesData, hjust = "right", position = positionNudge(x = -.002)) { label = "name" } +
        geomText(label = "Kotlin Isl.", x = 29.725, y = 60.011, color = "#31a354", size = 13, fontface = "italic") +
        geomText(label = "Gulf of Finland", x = 29.665, y = 60.002, color = "#578bcc", size = 11, fontface = "italic") +
        coordCartesian(xlim = Pair(kotlinBBox.xmin, kotlinBBox.xmax), ylim = Pair(kotlinBBox.ymin, kotlinBBox.ymax)) +
        ggtitle("Tourist attractions on Kotlin island") +
        themeVoid() + theme(legendTitle = "blank").legendPosition(.15, .2)

// Finally, use GGBunch to show these two maps together.
val bunch = GGBunch()
bunch.addPlot(kotlinPlot, 0, 0, 800, 600)
bunch.addPlot(spbPlot, 600, 25, 200, 150)
bunch.show()
```

![Inset Map of Kotlin Island](spatialdataset_kotlin_isl.png)

<seealso style="cards">
    <category ref="example">
        <a href="%nb-spatialdataset_kotlin_isl%" summary="View the notebook in NBViewer">
            Jupyer Notebook
        </a>
    </category>
</seealso>