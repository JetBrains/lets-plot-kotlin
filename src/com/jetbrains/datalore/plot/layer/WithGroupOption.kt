package com.jetbrains.datalore.plot.layer

import com.jetbrains.datalore.plot.Options

interface WithGroupOption {
    val group: Any?

    fun group() = Options.of(
        "group" to group
    )
}