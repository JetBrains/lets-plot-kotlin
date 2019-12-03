package jetbrains.letsPlot.intern

enum class StatKind {
    IDENTITY,
    COUNT,
    DENSITY,
    BIN,
    BOXPLOT;

    fun optionName() = name.toLowerCase()
}