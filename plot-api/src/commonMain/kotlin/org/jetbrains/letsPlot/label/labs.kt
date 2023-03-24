/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.label

import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.config.Option
import org.jetbrains.letsPlot.intern.Feature
import org.jetbrains.letsPlot.intern.FeatureList
import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.intern.Scale

/**
 * Adds label to the x-axis.
 *
 * @param label The text for the x-axis label.
 */
@Suppress("SpellCheckingInspection")
fun xlab(label: String): Feature {
    return labs(x = label)
}

/**
 * Adds label to the y-axis.
 *
 * @param label The text for the y-axis label.
 */
@Suppress("SpellCheckingInspection")
fun ylab(label: String): Feature {
    return labs(y = label)
}

/**
 * Changes plot title, axis labels and legend titles.
 *
 * ## Examples
 *
 * - [title_subtitle_caption.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/title_subtitle_caption.ipynb)

 * @param title The plot title text.
 * @param subtitle The plot subtitle text.
 * @param caption The plot caption text.
 * @param x The x-axis text.
 * @param y The y-axis text.
 * @param alpha The legend title text.
 * @param color The legend title text.
 * @param fill The legend title text.
 * @param shape The legend title text.
 * @param size The legend title text.
 * @param width The legend title text.
 * @param height The legend title text.
 * @param linetype The legend title text.
 */
fun labs(
    title: String? = null,
    subtitle: String? = null,
    caption: String? = null,
    // aesthetics
    x: String? = null,
    y: String? = null,
    alpha: String? = null,
    color: String? = null,
    fill: String? = null,
    shape: String? = null,
    size: String? = null,
    width: String? = null,
    height: String? = null,
    linetype: String? = null
): FeatureList {
    val list = ArrayList<Feature>()
    title?.let { list.add(ggtitle(it, subtitle)) }

    caption?.let {
        list.add(
            OptionsMap(
                Option.Plot.CAPTION,
                mapOf(Option.Plot.CAPTION_TEXT to it)
            )
        )
    }

    x?.let { list.add(Scale(aesthetic = Aes.X, name = it)) }
    y?.let { list.add(Scale(aesthetic = Aes.Y, name = it)) }
    alpha?.let { list.add(Scale(aesthetic = Aes.ALPHA, name = it)) }
    color?.let { list.add(Scale(aesthetic = Aes.COLOR, name = it)) }
    fill?.let { list.add(Scale(aesthetic = Aes.FILL, name = it)) }
    shape?.let { list.add(Scale(aesthetic = Aes.SHAPE, name = it)) }
    size?.let { list.add(Scale(aesthetic = Aes.SIZE, name = it)) }
    width?.let { list.add(Scale(aesthetic = Aes.WIDTH, name = it)) }
    height?.let { list.add(Scale(aesthetic = Aes.HEIGHT, name = it)) }
    linetype?.let { list.add(Scale(aesthetic = Aes.LINETYPE, name = it)) }

    return FeatureList(list)
}