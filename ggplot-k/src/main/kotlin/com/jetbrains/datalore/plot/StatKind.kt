package com.jetbrains.datalore.plot

enum class StatKind {
    IDENTITY,
    COUNT,
    DENSITY;

    fun optionName() = name.toLowerCase()
}