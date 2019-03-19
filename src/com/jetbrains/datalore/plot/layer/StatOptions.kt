package com.jetbrains.datalore.plot.layer

import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.StatKind

open class StatOptions(
    val kind: StatKind,
    val mapping: Options = Options.empty()
    ) {
    open val parameters: Options = Options.empty()
}