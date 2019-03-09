package com.jetbrains.datalore.plot


class Plot internal constructor(val data: Any?, val mapping: DefaultAesMapping?, val features: List<Feature>) {
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

    constructor() : this(null, null, emptyList())

    operator fun plus(other: Feature): Plot {
        return when (other) {
            is DummyFeature -> this  // nothing
            is FeatureList -> withFeatureList(this, other)
            else -> withFeature(this, other)
        }
    }

    override fun toString(): String {
        return "PlotSpec(data=$data, mapping=$mapping, features=$features)"
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

open class Layer : Feature() {
}

abstract class AestheticMapping()

class MutableDefaultAesMapping(
    var x: Any? = null,
    var y: Any? = null,
    var alpha: Any? = null,
    var colour: Any? = null,
    var fill: Any? = null,
    var group: Any? = null
) {
    override fun toString(): String {
        return "GenericMapping(x=$x, y=$y, alpha=$alpha, colour=$colour, fill=$fill, group=$group)"
    }

    fun toFrozen() = DefaultAesMapping(x, y, alpha, colour, fill, group)
}

data class DefaultAesMapping(
    val x: Any?,
    val y: Any?,
    val alpha: Any?,
    val colour: Any?,
    val fill: Any?,
    val group: Any?
) : Options<DefaultAesMapping>()
