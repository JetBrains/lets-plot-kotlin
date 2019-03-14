package com.jetbrains.datalore.plot.mapping

import com.jetbrains.datalore.plot.Options

interface FreezableOptions {
    fun toFrozen(): Options
}

