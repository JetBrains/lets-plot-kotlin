package jetbrains.letsPlot.sampling

import jetbrains.letsPlot.intern.SamplingKind
import jetbrains.letsPlot.intern.layer.SamplingOptions

@Suppress("FunctionName")
fun sampling_random(n: Int, seed: Int? = null): SamplingOptions {
    return SamplingOptions(SamplingKind.RANDOM, n, seed)
}

@Suppress("FunctionName")
fun sampling_random_stratified(n: Int, seed: Int? = null, minSubsample: Int? = null): SamplingOptions {
    return SamplingOptions(SamplingKind.RANDOM_STRATIFIED, n, seed, minSubsample)
}

@Suppress("FunctionName")
fun sampling_pick(n: Int): SamplingOptions {
    return SamplingOptions(SamplingKind.PICK, n)
}

@Suppress("FunctionName")
fun sampling_systematic(n: Int): SamplingOptions {
    return SamplingOptions(SamplingKind.SYSTEMATIC, n)
}

@Suppress("FunctionName")
fun sampling_group_systematic(n: Int): SamplingOptions {
    return SamplingOptions(SamplingKind.GROUP_SYSTEMATIC, n)
}

@Suppress("FunctionName")
fun sampling_group_random(n: Int, seed: Int? = null): SamplingOptions {
    return SamplingOptions(SamplingKind.GROUP_RANDOM, n, seed)
}

@Suppress("FunctionName")
fun sampling_vertex_vw(n: Int): SamplingOptions {
    return SamplingOptions(SamplingKind.VERTEX_VW, n)
}

@Suppress("FunctionName")
fun sampling_vertex_dp(n: Int): SamplingOptions {
    return SamplingOptions(SamplingKind.VERTEX_DP, n)
}
