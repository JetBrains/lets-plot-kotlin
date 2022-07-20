/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.sampling

import org.jetbrains.letsPlot.intern.SamplingKind
import org.jetbrains.letsPlot.intern.layer.SamplingOptions

val samplingNone = SamplingOptions(SamplingKind.NONE, emptyMap())

/**
 * Return a subset of randomly selected items.
 *
 * ## Examples
 *
 * - [sampling_stratified.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/sampling_stratified.ipynb)
 *
 * @param n Number of items to return.
 * @param seed Number used to initialize a pseudo random number generator.
 */
fun samplingRandom(n: Int, seed: Int? = null): SamplingOptions {
    return SamplingOptions(SamplingKind.RANDOM, n, seed)
}

/**
 * Randomly sample from each stratum (subgroup).
 *
 * ## Examples
 *
 * - [sampling_stratified.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/sampling_stratified.ipynb)
 *
 * @param n Number of items to return.
 * @param seed Number used to initialize a pseudo random number generator.
 * @param minSubsample Minimal number of items in sub sample.
 */
fun samplingRandomStratified(
    n: Int,
    seed: Int? = null,
    minSubsample: Int? = null
): SamplingOptions {
    return SamplingOptions(SamplingKind.RANDOM_STRATIFIED, n, seed, minSubsample)
}

fun samplingPick(n: Int): SamplingOptions {
    return SamplingOptions(SamplingKind.PICK, n)
}

fun samplingSystematic(n: Int): SamplingOptions {
    return SamplingOptions(SamplingKind.SYSTEMATIC, n)
}

fun samplingGroupSystematic(n: Int): SamplingOptions {
    return SamplingOptions(SamplingKind.GROUP_SYSTEMATIC, n)
}

fun samplingGroupRandom(n: Int, seed: Int? = null): SamplingOptions {
    return SamplingOptions(SamplingKind.GROUP_RANDOM, n, seed)
}

fun samplingVertexVW(n: Int): SamplingOptions {
    return SamplingOptions(SamplingKind.VERTEX_VW, n)
}

fun samplingVertexDP(n: Int): SamplingOptions {
    return SamplingOptions(SamplingKind.VERTEX_DP, n)
}
