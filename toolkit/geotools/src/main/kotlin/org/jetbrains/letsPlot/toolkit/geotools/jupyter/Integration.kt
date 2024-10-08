package org.jetbrains.letsPlot.toolkit.geotools.jupyter

import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration

@Suppress("unused")
class Integration: JupyterIntegration() {
    override fun Builder.onLoaded() {
        import("org.jetbrains.letsPlot.toolkit.geotools.toSpatialDataset")
    }
}
