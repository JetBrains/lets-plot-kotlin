package com.jetbrains.datalore.plot.dsl

import com.jetbrains.datalore.plot.GenericAesMapping
import com.jetbrains.datalore.plot.Plot

fun ggplot(data: Any? = null, mapping: GenericAesMapping.() -> Unit = {}): Plot {
    return Plot(
        data,
        GenericAesMapping().apply(mapping).seal(),
        emptyList()
    )
}

