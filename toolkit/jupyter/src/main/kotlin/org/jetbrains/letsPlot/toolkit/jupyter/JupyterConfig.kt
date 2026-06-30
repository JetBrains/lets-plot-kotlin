package org.jetbrains.letsPlot.toolkit.jupyter

/**
 * Lets-Plot config for Jupyter.
 */
class JupyterConfig {
    /**
     * Is IDEA theme applied to plots in the notebook outputs rendered with Swing.
     *
     * Enabled by default.
     */
    var themeApplied: Boolean = true

    /**
     * Is Java Swing applied to plots in the notebook outputs.
     * Otherwise, web (HTML + JS) rendering is used.
     *
     * Enabled by default.
     */
    var swingEnabled: Boolean = true

    /**
     * The maximum number of rows of a `SpatialDataset` shown in notebook table output.
     * Extra rows are truncated with a "Showing N of M rows" note.
     *
     * Defaults to 5.
     */
    var spatialDatasetRowLimit: Int = SpatialDatasetHtmlRenderer.ROW_LIMIT
}
