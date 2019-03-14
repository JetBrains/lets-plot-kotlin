package com.jetbrains.datalore.plot.layer

import com.jetbrains.datalore.plot.Options

internal typealias GeomFactory = (mapping: Options, constants: Options) -> GeomOptions