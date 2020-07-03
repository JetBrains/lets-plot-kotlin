/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern

fun checkScaleExpand(expand: List<Any>?) {
    expand?.let { require(expand.size in (1..2)) { "'expand' must be a list of size 1 or 1: $expand" } }
    expand?.let { require(expand.all { it is Number }) { "'expand' must contain only numbers: $expand" } }
}

fun checkGreyScaleStartEnd(start: Number? = null, end: Number? = null) {
    start?.let { require(start.toDouble() in (0.0..1.0)) { "'start' must be in range: [0,1]: $start" } }
    end?.let { require(end.toDouble() in (0.0..1.0)) { "'end' must be in range: [0,1]: $end" } }
}

