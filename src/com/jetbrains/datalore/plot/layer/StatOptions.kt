package com.jetbrains.datalore.plot.layer

import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.StatKind

abstract class StatOptions {
    abstract val kind: StatKind
    abstract val mapping: Options
    abstract val parameters: Options
}