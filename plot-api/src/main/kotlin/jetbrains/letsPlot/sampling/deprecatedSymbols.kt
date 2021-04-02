/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */
@file:Suppress("FunctionName")

package jetbrains.letsPlot.sampling

@Deprecated("", ReplaceWith("samplingNone"))
val sampling_none = samplingNone

@Deprecated("", ReplaceWith("samplingRandom(n, seed)"))
fun sampling_random(n: Int, seed: Int? = null) = samplingRandom(n, seed)

@Deprecated("", ReplaceWith("samplingRandomStratified(n, seed, minSubsample)"))
fun sampling_random_stratified(n: Int, seed: Int? = null, minSubsample: Int? = null) =
    samplingRandomStratified(n, seed, minSubsample)

@Deprecated("", ReplaceWith("samplingPick(n)"))
fun sampling_pick(n: Int) = samplingPick(n)

@Deprecated("", ReplaceWith("samplingSystematic(n)"))
fun sampling_systematic(n: Int) = samplingSystematic(n)

@Deprecated("", ReplaceWith("samplingGroupSystematic(n)"))
fun sampling_group_systematic(n: Int) = samplingGroupSystematic(n)

@Deprecated("", ReplaceWith("samplingGroupRandom(n, seed)"))
fun sampling_group_random(n: Int, seed: Int? = null) = samplingGroupRandom(n, seed)

@Deprecated("", ReplaceWith("samplingVertexVW(n)"))
fun sampling_vertex_vw(n: Int) = samplingVertexVW(n)

@Deprecated("", ReplaceWith("samplingVertexDP(n)"))
fun sampling_vertex_dp(n: Int) = samplingVertexDP(n)