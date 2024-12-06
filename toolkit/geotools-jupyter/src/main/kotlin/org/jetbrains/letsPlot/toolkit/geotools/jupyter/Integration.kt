package org.jetbrains.letsPlot.toolkit.geotools.jupyter

import org.jetbrains.kotlinx.jupyter.api.Notebook
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration
import org.jetbrains.kotlinx.jupyter.api.libraries.repositories

@Suppress("unused")
class Integration(private val notebook: Notebook, options: MutableMap<String, String?>) :
    JupyterIntegration() {
    override fun Builder.onLoaded() {
        repositories {
            maven("https://repo.osgeo.org/repository/release")
        }
        import("org.jetbrains.letsPlot.toolkit.geotools.toSpatialDataset")
    }
}
