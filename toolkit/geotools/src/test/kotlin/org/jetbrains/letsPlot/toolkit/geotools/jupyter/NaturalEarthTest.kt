package org.jetbrains.letsPlot.toolkit.geotools.jupyter

import org.jetbrains.kotlinx.jupyter.testkit.JupyterReplTestCase
import org.jetbrains.kotlinx.jupyter.testkit.ReplProvider
import kotlin.test.Test

class NaturalEarthTest : JupyterReplTestCase(
    ReplProvider.forLibrariesTesting(listOf("lets-plot-kotlin-geotools"))
) {
    private val geotoolsDependencies = """
        USE {
            repositories {
                maven("https://repo.osgeo.org/repository/release")
            }

            dependencies {
                implementation("org.geotools:gt-main:32.0")
                implementation("org.geotools:gt-shapefile:32.0")
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
    fun `Plot output in jupyter`() {
        execRendered(geotoolsDependencies)
        execRendered(naturalEarthMap)
    }
}