package org.jetbrains.letsPlot.toolkit.geotools.jupyter

import org.jetbrains.kotlinx.jupyter.testkit.JupyterReplTestCase
import kotlin.test.Test

class NaturalEarthTest : JupyterReplTestCase() {
    private val geotoolsDependencies = """
        USE {
            dependencies {
                implementation("org.geotools:gt-shapefile:30.0")
            }
        }
    """.trimIndent()

    private val naturalEarthMap = """
    import org.geotools.data.shapefile.ShapefileDataStoreFactory
    import org.geotools.data.simple.SimpleFeatureCollection
    import java.net.URL   
        
    val factory = ShapefileDataStoreFactory()
    val worldFeatures : SimpleFeatureCollection = with("naturalearth_lowres") {
        val url = "https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/shp/${'$'}this/${'$'}this.shp"
        factory.createDataStore(URL(url)).featureSource.features
    }

    val world = worldFeatures.toSpatialDataset(10)
    letsPlot() +
    geomMap(map = world) +
    ggsize(700, 400) +
    themeVoid()
    """.trimIndent()

    @Test
    fun `natural earth example compilation in jupyter`() {
        execRendered(geotoolsDependencies)
        execRendered(naturalEarthMap)
    }
}