/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import jetbrains.datalore.plot.config.Option
import org.jetbrains.letsPlot.Geom
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.LayerBase
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.StatOptions
import org.jetbrains.letsPlot.intern.layer.geom.ImageAesthetics
import org.jetbrains.letsPlot.intern.layer.geom.ImageMapping
import org.jetbrains.letsPlot.pos.positionIdentity

@Suppress("ClassName")
class geomImage(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = positionIdentity,
    showLegend: Boolean = true,
    sampling: SamplingOptions? = null,
    val href: String? = null,
    override val xmin: Any? = null,
    override val xmax: Any? = null,
    override val ymin: Any? = null,
    override val ymax: Any? = null,
    mapping: ImageMapping.() -> Unit = {}
) : ImageAesthetics,
    LayerBase(
        mapping = ImageMapping().apply(mapping).seal(),
        data = data,
        geom = Geom.image(),
        stat = stat,
        position = position,
        showLegend = showLegend,
        sampling = sampling
    ) {
    override fun seal(): Options {
        return super.seal() + Options.of(Option.Geom.Image.HREF to href)
    }
}