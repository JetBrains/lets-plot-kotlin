/*
 * Copyright (c) 2025. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.stat

/**
 * Aesthetic mappings supported by the default stat of [geomSina()][org.jetbrains.letsPlot.geom.geomSina].
 *
 * @param x X-axis coordinates.
 * @param y Y-axis coordinates.
 * @param weight Used by stat to compute weighted density.
 */
class SinaStatMapping(
    override var x: Any? = null,
    override var y: Any? = null,
    override var weight: Any? = null
) : SinaStatAesthetics