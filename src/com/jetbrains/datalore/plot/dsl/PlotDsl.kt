package com.jetbrains.datalore.plot.dsl

import com.jetbrains.datalore.plot.MutableDefaultAesMapping
import com.jetbrains.datalore.plot.Plot

fun ggplot(data: Any? = null, mapping: MutableDefaultAesMapping.() -> Unit = {}): Plot {
    return Plot(
        data,
        MutableDefaultAesMapping().apply(mapping).toFrozen(),
        emptyList()
    )
}

