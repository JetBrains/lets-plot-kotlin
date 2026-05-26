/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern.layer

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.SamplingKind
import org.jetbrains.letsPlot.intern.filterNonNullValues

/**
 * @property kind The sampling method kind.
 * @property isNone Whether the sampling is none (no sampling applied).
 * @property mapping The sampling options mapping.
 */
class SamplingOptions internal constructor(
    val kind: SamplingKind,
    private val options: Map<String, Any>,
    internal val samplings: List<SamplingOptions>?
) {
    internal constructor(kind: SamplingKind, options: Map<String, Any>) : this(kind, options, null)

    internal constructor(kind: SamplingKind, n: Int, seed: Int? = null, minSubsample: Int? = null) : this(
        kind,
        mapOf(
            "n" to n,
            "seed" to seed,
            "min_subsample" to minSubsample
        ).filterNonNullValues(),
        null
    )

    val isNone: Boolean = (kind == SamplingKind.NONE)

    val mapping: Options
        get() {
            check(!isNone) { "Not applicable to `none` sampling." }
            check(samplings == null) { "Not applicable to composite sampling." }
            return Options(options) +
                    Options(mapOf("name" to kind.optionName()))
        }

    /**
     * Combines two sampling options so that they are applied to a layer sequentially,
     * left to right (the result of this sampling becomes the input of `other`).
     *
     * ## Examples
     *
     * ```
     * geomPoint(sampling = samplingRandom(500, seed = 42) + samplingSystematic(100))
     * ```
     *
     * @param other The sampling to apply after this one.
     */
    operator fun plus(other: SamplingOptions): SamplingOptions {
        require(!this.isNone && !other.isNone) {
            "`samplingNone` cannot be combined with other sampling options."
        }
        return SamplingOptions(
            SamplingKind.COMPOSITE,
            emptyMap(),
            this.flatten() + other.flatten()
        )
    }

    private fun flatten(): List<SamplingOptions> = samplings ?: listOf(this)
}
