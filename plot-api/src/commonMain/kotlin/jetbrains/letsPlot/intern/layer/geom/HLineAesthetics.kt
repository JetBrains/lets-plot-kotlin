/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern.layer.geom

import jetbrains.letsPlot.intern.Options

interface HLineAesthetics : jetbrains.letsPlot.intern.layer.OptionsCapsule {
    @Suppress("SpellCheckingInspection")
    val yintercept: Any?
    val alpha: Any?
    val color: Any?
    val linetype: Any?
    val size: Any?

    @Suppress("SpellCheckingInspection")
    override fun seal() = Options.of(
        "yintercept" to yintercept,
        "alpha" to alpha,
        "color" to color,
        "linetype" to linetype,
        "size" to size
    )
}