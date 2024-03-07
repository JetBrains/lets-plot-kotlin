/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern

import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.annotations.AnnotationOptions
import org.jetbrains.letsPlot.core.plot.base.Aes
import org.jetbrains.letsPlot.core.spec.Option
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


class FeatureList(elements: List<Feature>) : Feature() {
    val elements: List<Feature> = elements.flatMap { element ->
        when (element) {
            is FeatureList -> element.elements
            else -> listOf(element)
        }
    }

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
    val data: Map<*, *>? = null,
    val geom: GeomOptions,
    val stat: StatOptions,
    val position: PosOptions?,
    val showLegend: Boolean,
    val sampling: SamplingOptions? = null,
    val tooltips: TooltipOptions? = null,
    val labels: AnnotationOptions? = null, // currently supported for Pie chart only
    val orientation: String? = null,
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
    breaks: Any? = null,                // List of breaks or Map of labels to breaks
    labels: Any? = null,                // List of labels or Map of breaks to labels
    val lablim: Int? = null,
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

    val breaks: List<Any>?
    val labels: List<String>?

    init {
        var breaksList = breaks?.let {
            when (it) {
                is Map<*, *> -> it.values
                is List<*> -> it
                else -> error("The scale 'breaks' parameter should be specified with a list or dictionary.")
            }
        }

        val labelsList = when (labels) {
            null -> {
                (breaks as? Map<*, *>)?.keys
            }

            is Map<*, *> -> {
                if (breaksList == null) {
                    breaksList = labels.keys
                    labels.values
                } else {
                    val labeledBreaks = breaksList.filter { it in labels }
                    val newBreaks = labeledBreaks.map { it }
                    val newLabels = labeledBreaks.map { labels[it] }
                    val breaksWithoutLabel = breaksList.filterNot { it in labels }
                    breaksList = newBreaks + breaksWithoutLabel
                    newLabels
                }
            }

            is List<*> -> labels
            else -> error("The scale 'labels' parameter should be specified with a list or dictionary.")
        }

        this.breaks = breaksList?.let { list ->
            require(list.all { it != null }) { "'breaks' is non-nullable list, but was: $list" }
            list.map { v -> v as Any }
        }
        this.labels = labelsList?.let { list ->
            require(list.all { it is String }) { "'labels' must contain strings, but was: $list" }
            list.map { v -> v as String }
        }
    }

    companion object {
        fun toAes(option: Any?): Aes<*> {
            return when (option) {
                is Aes<*> -> option
                is String -> Option.Mapping.toAes(option.lowercase())
                else -> error("Wrong 'aesthetic' parameter: should contain aesthetic or its name but was $option")
            }
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

