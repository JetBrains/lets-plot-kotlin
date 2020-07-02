/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.intern

import jetbrains.datalore.plot.base.Aes
import jetbrains.letsPlot.Figure
import jetbrains.letsPlot.LetsPlot
import jetbrains.letsPlot.intern.layer.GeomOptions
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.StatOptions


class Plot internal constructor(
    val data: Map<*, *>? = null,
    val mapping: Options = GenericAesMapping().seal(),
    val features: List<Feature> = emptyList(),
    val widthScale: Int? = null
) : Figure {

    operator fun plus(other: Feature): Plot {
        return when (other) {
            is DummyFeature -> this  // nothing
            is FeatureList -> withFeatureList(this, other)
            else -> withFeature(this, other)
        }
    }

    fun layers(): List<Layer> {
        return features.filterIsInstance<Layer>()
    }

    fun scales(): List<Scale> {
        return features.filterIsInstance<Scale>()
    }

    fun otherFeatures(): List<OptionsMap> {
        return features.filterIsInstance<OptionsMap>()
    }

    override fun toString(): String {
        return "Plot(data=$data, mapping=$mapping, features=$features)"
    }

    override fun show() {
        LetsPlot.frontendContext.display(this.toSpec())
    }

    companion object {
        fun withFeature(plot: Plot, feature: Feature): Plot {
            return Plot(
                data = plot.data,
                mapping = plot.mapping,
                features = plot.features + listOf(feature)
            )
        }

        private fun withFeatureList(plot: Plot, featureList: FeatureList): Plot {
            return Plot(
                data = plot.data,
                mapping = plot.mapping,
                features = plot.features + featureList.elements
            )
        }
    }
}


sealed class Feature {
    open operator fun plus(other: Feature): Feature {
        return when (other) {
            is DummyFeature -> this // nothing
            is FeatureList -> FeatureList(listOf(this) + other.elements)
            else -> FeatureList(listOf(this, other))
        }
    }
}


class FeatureList(val elements: List<Feature>) : Feature() {
    override operator fun plus(other: Feature): Feature {
        return when (other) {
            is DummyFeature -> this // nothing
            is FeatureList -> FeatureList(this.elements + other.elements)
            else -> FeatureList(this.elements + listOf(other))
        }
    }
}

internal object DummyFeature : Feature() {
    override fun plus(other: Feature): Feature {
        return other
    }
}

abstract class Layer(
    mapping: Options,
    val data: Map<*, *>?,
    val geom: GeomOptions,
    val stat: StatOptions,
    val position: PosOptions,
    val showLegend: Boolean,
    val sampling: SamplingOptions?
) : Feature() {

    // layer mapping has precedence over geom and stat
    val mapping by lazy {
        geom.mapping + stat.mapping + mapping
    }

    abstract val parameters: Options
}

class Scale(
    val aesthetic: Aes<*>,
    val name: String? = null,
    val breaks: List<Any>? = null,
    val labels: List<String>? = null,
    val limits: Any? = null,            // Any type convertable to list.
    val expand: Any? = null,
    val naValue: Any? = null,
    val guide: Any? = null,
    val trans: String? = null,
    val otherOptions: Options = Options.empty()

) : Feature()

open class OptionsMap internal constructor(
    val kind: String,
    val options: Map<String, Any>
) : Feature() {
    internal constructor(
        kind: String,
        name: String,
        options: Map<String, Any>
    ) : this(
        kind = kind,
        options = mapOf("name" to name) + options
    )
}

abstract class NotLayer : Feature()

