package jetbrains.datalorePlot.intern

enum class StatKind {
    IDENTITY,
    COUNT,
    DENSITY;

    fun optionName() = name.toLowerCase()
}