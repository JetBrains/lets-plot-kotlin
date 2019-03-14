package com.jetbrains.datalore.plot.layer

import com.jetbrains.datalore.plot.Options

interface FreezableOptions {
    fun toFrozen(): Options
}

