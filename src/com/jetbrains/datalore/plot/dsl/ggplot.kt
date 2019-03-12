package com.jetbrains.datalore.plot.dsl

import com.jetbrains.datalore.plot.DefaultAesMapping
import com.jetbrains.datalore.plot.Plot

fun ggplot(data: Any? = null, mapping: DefaultAesMapping.() -> Unit = {}): Plot {
    return Plot(
        data,
        DefaultAesMapping().apply(mapping).toFrozen(),
        emptyList()
    )
}

