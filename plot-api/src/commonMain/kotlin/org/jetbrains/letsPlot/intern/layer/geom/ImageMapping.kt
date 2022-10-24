/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

class ImageMapping(
    override var xmin: Any? = null,
    override var xmax: Any? = null,
    override var ymin: Any? = null,
    override var ymax: Any? = null,
    override var group: Any? = null
) : ImageAesthetics, WithGroupOption {
    override fun seal() = super.seal() + groupOption()
}