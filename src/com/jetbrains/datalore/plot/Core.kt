package com.jetbrains.datalore.plot


class Plot internal constructor(val data: Any?, val mapping: DefaultAesMapping, val features: List<Feature>) {
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

    constructor() : this(null, MutableDefaultAesMapping().toFrozen(), emptyList())

    operator fun plus(other: Feature): Plot {
        return when (other) {
            is DummyFeature -> this  // nothing
            is FeatureList -> withFeatureList(this, other)
            else -> withFeature(this, other)
        }
    }

    fun layers(): List<Layer> {
        @Suppress("UNCHECKED_CAST")
        return features.filter { it is Layer } as List<Layer>
    }

    override fun toString(): String {
        return "Plot(data=$data, mapping=$mapping, features=$features)"
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


internal class FeatureList(val elements: List<Feature>) : Feature() {
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

open class Layer(val mapping: Options) : Feature() {
}

class MutableDefaultAesMapping(
    var x: Any? = null,
    var y: Any? = null,
    var alpha: Any? = null,
    var color: Any? = null,
    var fill: Any? = null,
    var group: Any? = null
) {

    fun toFrozen() = DefaultAesMapping(
        mapOf(
            "x" to x,
            "y" to y,
            "alpha" to alpha,
            "color" to color,
            "fill" to fill,
            "group" to group
        )
    )
}

class DefaultAesMapping(map: Map<String, Any?>) : Options(map)