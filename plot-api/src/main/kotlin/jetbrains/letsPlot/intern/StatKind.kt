package jetbrains.letsPlot.intern

enum class StatKind {
    IDENTITY,
    COUNT,
    DENSITY,
    BIN;

    fun optionName() = name.toLowerCase()
}