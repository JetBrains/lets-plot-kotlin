package com.jetbrains.datalore.plot.layer.stat

import com.jetbrains.datalore.plot.Options
import com.jetbrains.datalore.plot.StatKind
import com.jetbrains.datalore.plot.layer.StatOptions

object Stat {
    val identity = object : StatOptions() {
        override val kind = StatKind.IDENTITY
        override val mapping = Options.empty()
        override val parameters = Options.empty()
    }
}
