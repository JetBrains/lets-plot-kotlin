package com.jetbrains.datalore.plot.layer

import com.jetbrains.datalore.plot.Options

interface MappingOptions {
    val group: Any?

    fun withGroup(options: Options) = options.union(
        Options.of(
            "group" to group
        )
    )
}