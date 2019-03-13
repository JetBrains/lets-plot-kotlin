package com.jetbrains.datalore.plot.geom

import com.jetbrains.datalore.plot.Options

interface AestheticsOptions {
    fun toFrozen(): Options
}

interface MappingOptions {
    val group: Any?

    fun withGroup(options: Options) = options.union(Options(mapOf("group" to group)))
}

