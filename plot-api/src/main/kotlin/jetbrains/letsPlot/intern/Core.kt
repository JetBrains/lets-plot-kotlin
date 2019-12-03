package jetbrains.letsPlot.intern

import frontendApi.Figure
import jetbrains.datalore.plot.base.Aes
import jetbrains.letsPlot.GlobalSettings
import jetbrains.letsPlot.intern.frontendContext.FrontendContextUtil
import jetbrains.letsPlot.intern.layer.GeomOptions
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.SamplingOptions
import jetbrains.letsPlot.intern.layer.StatOptions


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
        return features.filterIsInstance<Layer>()
    }

    fun scales(): List<Scale> {
        return features.filterIsInstance<Scale>()
    }

    fun otherFeatures(): List<OtherPlotFeature> {
        return features.filterIsInstance<OtherPlotFeature>()
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
    val sampling: SamplingOptions?
) : Feature() {

    // layer mapping has precedence over geom and stat
    val mapping by lazy {
        geom.mapping + stat.mapping + mapping
    }

    abstract val parameters: Options
}

class Scale internal constructor(
    val aesthetic: Aes<*>,
    val name: String? = null,
    val breaks: List<Any>? = null,
    val labels: List<String>? = null,
    val limits: List<Any>? = null,
    val expand: Any? = null,
    val na_value: Any? = null,
    val guide: Any? = null,
    val trans: Any? = null,
    val otherOptions: Options = Options.empty()

) : Feature()

class OtherPlotFeature internal constructor(
    val kind: String,
    val options: Map<String, Any>
) : Feature()

abstract class NotLayer : Feature()

