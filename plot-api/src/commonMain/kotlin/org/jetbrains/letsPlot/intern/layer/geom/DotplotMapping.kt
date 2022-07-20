/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer.geom

import org.jetbrains.letsPlot.intern.layer.WithGroupOption

class DotplotMapping(
    override var x: Any? = null,
    override var binWidth: Any? = null,
    override var stackSize: Any? = null,
    override var alpha: Any? = null,
    override var color: Any? = null,
    override var fill: Any? = null,
    override var size: Any? = null,
    override var group: Any? = null
) : DotplotAesthetics, WithGroupOption {
    override fun seal() = super.seal() + groupOption()
}