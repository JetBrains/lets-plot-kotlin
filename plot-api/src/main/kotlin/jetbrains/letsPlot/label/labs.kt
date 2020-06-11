/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.label

import jetbrains.datalore.plot.base.Aes
import jetbrains.letsPlot.intern.Feature
import jetbrains.letsPlot.intern.FeatureList
import jetbrains.letsPlot.intern.Scale

/**
 * Add label to the x axis
 *
 * @param label string
 *      The text for the x axis label
 */
@Suppress("SpellCheckingInspection")
fun xlab(label: String): Feature {
    return labs(x = label)
}

/**
 * Add label to the y axis
 *
 * @param label string
 *      The text for the y axis label
 */
@Suppress("SpellCheckingInspection")
fun ylab(label: String): Feature {
    return labs(y = label)
}

/**
 * Change plot title, axis labels and legend titles.

 * @param title string.
 *     The plot title text.
 * @param x string.
 *     The x-axis text.
 * @param y string.
 *     The y-axis text.
 * @param alpha string.
 *     The legend title text.
 * @param color string.
 *     The legend title text.
 * @param fill string.
 *     The legend title text.
 * @param shape string.
 *     The legend title text.
 * @param size string.
 *     The legend title text.
 * @param width string.
 *     The legend title text.
 * @param height string.
 *     The legend title text.
 * @param linetype string.
 *     The legend title text.
 */
fun labs(
    title: String? = null,
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
    title?.let { list.add(ggtitle(it)) }

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