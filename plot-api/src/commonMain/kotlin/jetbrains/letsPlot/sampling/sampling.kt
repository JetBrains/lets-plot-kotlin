/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.sampling

import jetbrains.letsPlot.intern.SamplingKind

val samplingNone = jetbrains.letsPlot.intern.layer.SamplingOptions(SamplingKind.NONE, emptyMap())

fun samplingRandom(n: Int, seed: Int? = null): jetbrains.letsPlot.intern.layer.SamplingOptions {
    return jetbrains.letsPlot.intern.layer.SamplingOptions(SamplingKind.RANDOM, n, seed)
}

fun samplingRandomStratified(
    n: Int,
    seed: Int? = null,
    minSubsample: Int? = null
): jetbrains.letsPlot.intern.layer.SamplingOptions {
    return jetbrains.letsPlot.intern.layer.SamplingOptions(SamplingKind.RANDOM_STRATIFIED, n, seed, minSubsample)
}

fun samplingPick(n: Int): jetbrains.letsPlot.intern.layer.SamplingOptions {
    return jetbrains.letsPlot.intern.layer.SamplingOptions(SamplingKind.PICK, n)
}

fun samplingSystematic(n: Int): jetbrains.letsPlot.intern.layer.SamplingOptions {
    return jetbrains.letsPlot.intern.layer.SamplingOptions(SamplingKind.SYSTEMATIC, n)
}

fun samplingGroupSystematic(n: Int): jetbrains.letsPlot.intern.layer.SamplingOptions {
    return jetbrains.letsPlot.intern.layer.SamplingOptions(SamplingKind.GROUP_SYSTEMATIC, n)
}

fun samplingGroupRandom(n: Int, seed: Int? = null): jetbrains.letsPlot.intern.layer.SamplingOptions {
    return jetbrains.letsPlot.intern.layer.SamplingOptions(SamplingKind.GROUP_RANDOM, n, seed)
}

fun samplingVertexVW(n: Int): jetbrains.letsPlot.intern.layer.SamplingOptions {
    return jetbrains.letsPlot.intern.layer.SamplingOptions(SamplingKind.VERTEX_VW, n)
}

fun samplingVertexDP(n: Int): jetbrains.letsPlot.intern.layer.SamplingOptions {
    return jetbrains.letsPlot.intern.layer.SamplingOptions(SamplingKind.VERTEX_DP, n)
}
