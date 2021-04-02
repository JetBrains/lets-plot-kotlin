/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.sampling

import jetbrains.letsPlot.intern.SamplingKind
import jetbrains.letsPlot.intern.layer.SamplingOptions

val samplingNone = SamplingOptions(SamplingKind.NONE, emptyMap())

fun samplingRandom(n: Int, seed: Int? = null): SamplingOptions {
    return SamplingOptions(SamplingKind.RANDOM, n, seed)
}

fun samplingRandomStratified(n: Int, seed: Int? = null, minSubsample: Int? = null): SamplingOptions {
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
