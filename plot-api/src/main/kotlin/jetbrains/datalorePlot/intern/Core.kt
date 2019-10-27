package jetbrains.datalorePlot.intern

import frontendApi.Figure
import jetbrains.datalore.plot.base.Aes
import jetbrains.datalorePlot.GlobalSettings
import jetbrains.datalorePlot.intern.frontendContext.FrontendContextUtil
import jetbrains.datalorePlot.intern.layer.GeomOptions
import jetbrains.datalorePlot.intern.layer.PosOptions
import jetbrains.datalorePlot.intern.layer.StatOptions


class Plot internal constructor(val data: Any?, val mapping: Options, val features: List<Feature>) : Figure {
    constructor() : this(null, GenericAesMapping().seal(), emptyList())

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

    fun scales(): List<Scale> {
        @Suppress("UNCHECKED_CAST")
        return features.filter { it is Scale } as List<Scale>
    }

    override fun toString(): String {
        return "Plot(data=$data, mapping=$mapping, features=$features)"
    }

    override fun show() {
        FrontendContextUtil.display(this, GlobalSettings.frontendContext)
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

abstract class Layer(
    mapping: Options,
    val data: Any?,
    val geom: GeomOptions,
    val stat: StatOptions,
    val position: PosOptions,
    val show_legend: Boolean,
    val sampling: Any?
) : Feature() {

    // layer mapping has precedence over geom and stat
    val mapping by lazy {
        geom.mapping + stat.mapping + mapping
    }

    abstract val parameters: Options
}

class Scale internal constructor(
    val aesthetic: Aes<*>,
    val name: String?,
    val breaks: List<Any>?,
    val labels: List<String>?,
    val limits: List<Any>?,
    val expand: Any?,
    val na_value: Any?,
    val guide: Any?,
    val trans: Any?,
    val otherOptions: Options

) : Feature()

abstract class NotLayer : Feature()

