package jetbrains.datalorePlot.intern

import jetbrains.datalorePlot.intern.layer.GeomOptions
import jetbrains.datalorePlot.intern.layer.PosOptions
import jetbrains.datalorePlot.intern.layer.StatOptions
import junit.framework.AssertionFailedError
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue

internal class PlotAssert(private val plot: Plot) : MappingAssert<PlotAssert> {
    companion object {
        internal fun assertThat(plot: Plot) = PlotAssert(plot)
    }

    override val mappingOptions = plot.mapping

    fun features() = FeatureListAssert(plot.features)
    fun layers() = LayerListAssert(plot.layers())
    // ToDo: scales..
}


internal open class FeatureAssert(private val feature: Feature) {
    companion object {
        internal fun assertThat(featureSpec: Feature) = FeatureAssert(featureSpec)
    }

    fun isList(): FeatureListAssert {
        if (feature is FeatureList) {
            return FeatureListAssert(feature.elements)
        }
        throw AssertionFailedError("expected ${FeatureList::class.simpleName} but was ${feature::class.simpleName}")
    }
}

internal open class FeatureListAssert(private val featureList: List<Feature>) {
    open fun length(length: Int): FeatureListAssert {
        assertEquals(length, featureList.size)
        return this
    }

    open fun get(index: Int) = FeatureAssert(featureList[index])
}

internal class LayerListAssert(private val layerList: List<Layer>) : FeatureListAssert(layerList) {
    override fun length(length: Int): LayerListAssert {
        return super.length(length) as LayerListAssert
    }

    override fun get(index: Int) = LayerAssert(layerList[index])
}

internal class LayerAssert(private val layer: Layer) :
    MappingAssert<LayerAssert>,
    ParametersAssert<LayerAssert>,
    FeatureAssert(layer) {

    companion object {
        internal fun assertThat(layer: Layer) = LayerAssert(layer)
    }

    override val mappingOptions = layer.mapping
    override val parameterOptions = layer.parameters

    fun geom() = GeomOptionsAssert(layer.geom)
    fun stat() = StatOptionsAssert(layer.stat)
    fun position(kind: PosKind) = PosOptionsAssert(layer.position, kind)
}

internal class GeomOptionsAssert(private val geom: GeomOptions) : MappingAssert<GeomOptionsAssert>,
    ParametersAssert<GeomOptionsAssert> {
    override val mappingOptions = geom.mapping
    override val parameterOptions = geom.parameters

    fun kind(kind: GeomKind): GeomOptionsAssert {
        assertTrue(geom.kind === kind)
        return this
    }
}

internal class StatOptionsAssert(private val stat: StatOptions) : MappingAssert<StatOptionsAssert>,
    ParametersAssert<StatOptionsAssert> {
    override val mappingOptions = stat.mapping
    override val parameterOptions = stat.parameters

    fun kind(kind: StatKind): StatOptionsAssert {
        assertTrue(stat.kind === kind)
        return this
    }
}

internal class PosOptionsAssert(
    pos: PosOptions,
    kind: PosKind
) : ParametersAssert<PosOptionsAssert> {
    init {
        assertTrue(pos.kind === kind)
    }

    override val parameterOptions = pos.parameters
}

internal interface MappingAssert<T> {
    val mappingOptions: Options

    @Suppress("UNCHECKED_CAST")
    fun aes(name: String, value: String): T {
//        assertTrue(mappingOptions.has(name))
        assertEquals(value, mappingOptions.get(name))
        return this as T
    }

    @Suppress("UNCHECKED_CAST")
    fun noMapping(): T {
        assertTrue(mappingOptions.isEmpty())
        return this as T
    }
}

internal interface ParametersAssert<T> {
    val parameterOptions: Options

    @Suppress("UNCHECKED_CAST")
    fun parameter(name: String, value: Any): T {
//        assertTrue(parameterOptions.has(name))
        assertEquals(value, parameterOptions.get(name))
        return this as T
    }

    @Suppress("UNCHECKED_CAST")
    fun noParameters(): T {
        assertTrue(parameterOptions.isEmpty())
        return this as T
    }
}
