/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern

import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.config.Option
import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.annotations.AnnotationOptions
import org.jetbrains.letsPlot.frontend.CurrentFrontendContext
import org.jetbrains.letsPlot.intern.layer.GeomOptions
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.SamplingOptions
import org.jetbrains.letsPlot.intern.layer.StatOptions
import org.jetbrains.letsPlot.tooltips.TooltipOptions

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
        CurrentFrontendContext.display(this.toSpec())
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
    /**
     * @suppress
     */
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
    val position: PosOptions?,
    val showLegend: Boolean,
    val sampling: SamplingOptions?,
    val tooltips: TooltipOptions?,
    val labels: AnnotationOptions?, // currently supported for Pie chart only
    val orientation: String?
) : OptionsCapsule, Feature() {

    // layer mapping has precedence over geom and stat
    val mapping by lazy {
        geom.mapping + stat.mapping + mapping
    }

    //    abstract val parameters: Options
    // layer parameters has precedence over geom and stat
    val parameters by lazy {
        geom.parameters + stat.parameters + this.seal()
    }
}

class Scale(
    aesthetic: Any,
    val name: String? = null,
    val breaks: List<Any>? = null,
    val labels: List<String>? = null,
    val limits: Any? = null,            // Any type convertable to list.
    val expand: Any? = null,
    val naValue: Any? = null,
    val format: String? = null,
    val guide: Any? = null,
    val trans: String? = null,
    val position: String? = null,
    val otherOptions: Options = Options.empty()

) : Feature() {
    val aesthetic: List<Aes<*>> = when (aesthetic) {
        is List<*> -> aesthetic.map(::toAes)
        else -> listOf(toAes(aesthetic))
    }

    private fun toAes(option: Any?): Aes<*> {
        return when (option) {
            is Aes<*> -> option
            is String -> Option.Mapping.toAes(option.lowercase())
            else -> error("Wrong 'aesthetic' parameter: should contain aesthetic or its name but was $option")
        }
    }
}

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

