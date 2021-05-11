/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern

enum class SamplingKind {
    NONE,
    RANDOM,
    RANDOM_STRATIFIED,
    PICK,
    SYSTEMATIC,
    GROUP_SYSTEMATIC,
    VERTEX_VW,
    VERTEX_DP,
    GROUP_RANDOM;

    fun optionName() = name.toLowerCase()
}