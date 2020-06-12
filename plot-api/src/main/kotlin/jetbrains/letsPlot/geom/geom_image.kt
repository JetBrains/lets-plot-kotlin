/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.geom

import jetbrains.letsPlot.Geom
import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.StatOptions
import jetbrains.letsPlot.intern.layer.geom.ImageAesthetics
import jetbrains.letsPlot.intern.layer.geom.ImageMapping

@Suppress("ClassName")
class geom_image(
    data: Map<*, *>? = null,
    stat: StatOptions = Stat.identity,
    position: PosOptions = Pos.identity,
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
        return super.seal() + Options.of("href" to href)
    }
}