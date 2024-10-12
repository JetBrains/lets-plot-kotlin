package org.jetbrains.letsPlot.toolkit.geotools.jupyter

import org.jetbrains.kotlinx.jupyter.api.Notebook
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration
import org.jetbrains.kotlinx.jupyter.api.libraries.repositories
import org.jetbrains.letsPlot.export.VersionChecker

@Suppress("unused")
class Integration(private val notebook: Notebook, private val options: MutableMap<String, String?>) :
    JupyterIntegration() {
    private val api = options["api"] ?: VersionChecker.letsPlotKotlinAPIVersion
    override fun Builder.onLoaded() {
        repositories {
            maven("https://repo.osgeo.org/repository/release")
        }
        import("org.jetbrains.letsPlot.toolkit.geotools.toSpatialDataset")
    }
}
