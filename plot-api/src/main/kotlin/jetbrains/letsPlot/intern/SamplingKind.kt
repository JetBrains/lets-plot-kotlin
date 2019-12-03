package jetbrains.letsPlot.intern

enum class SamplingKind {
    RANDOM,
    RANDOM_STRATIFIED,
    PICK,
    SYSTEMATIC,
    GROUP_SYSTEMATIC,
    VERTEX_VW,
    VERTEX_DP,
    GROUP_RANDOM;

    fun optionName() = name.toLowerCase()
}