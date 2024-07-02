/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.label

import org.jetbrains.letsPlot.core.spec.Option
import org.jetbrains.letsPlot.intern.Feature
import org.jetbrains.letsPlot.intern.FeatureList
import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.scale.titleGuides

/**
 * Adds label to the x-axis.
 *
 * @param label The text for the x-axis label.
 */
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
 * - [title_subtitle_caption.ipynb](https://nbviewer.org/github/JetBrains/lets-plot-docs/blob/master/source/kotlin_examples/cookbook/title_subtitle_caption.ipynb)

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
 * @param manual The custom legend title text.
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
    linetype: String? = null,
    manual: String? = null
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

    // use guides to set titles
    titleGuides(x, y, alpha, color, fill, shape, size, width, height, linetype, manual)
        .let(list::add)

    return FeatureList(list)
}


/**
 * Changes axis labels and legend titles.

 * @param titles Name-value pairs where name should be an aesthetic name or group name used in the `layerKey()` function
 *  and value should be a string, e.g. `"color"="New Color label"`.
 *
 */
fun labsAlt(vararg titles: Pair<String, String>): FeatureList {
    val list = listOf(titleGuides(titles = titles))
    return FeatureList(list)
}