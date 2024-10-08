package org.jetbrains.letsPlot.jupyter

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
}
