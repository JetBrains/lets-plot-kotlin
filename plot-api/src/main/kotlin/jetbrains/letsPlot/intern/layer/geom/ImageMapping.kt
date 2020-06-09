/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.layer.WithGroupOption

class ImageMapping(
    override var xmin: Any? = null,
    override var xmax: Any? = null,
    override var ymin: Any? = null,
    override var ymax: Any? = null,
    override var group: Any? = null
) : ImageAesthetics, WithGroupOption{
    override fun seal() = super.seal() + group()
}