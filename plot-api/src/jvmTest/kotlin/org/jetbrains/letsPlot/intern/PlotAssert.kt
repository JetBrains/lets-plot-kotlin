/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern

import junit.framework.AssertionFailedError
import junit.framework.TestCase.*
import org.jetbrains.letsPlot.intern.layer.GeomOptions
import org.jetbrains.letsPlot.intern.layer.PosOptions
import org.jetbrains.letsPlot.intern.layer.StatOptions

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

    fun isOptionsMap(): OptionsMapAssert {
        if (feature is OptionsMap) {
            return OptionsMapAssert(feature)
        }
        throw AssertionFailedError("expected OptionsMap but was ${feature::class.simpleName}")
    }

    fun isList(): FeatureListAssert {
        if (feature is FeatureList) {
            return FeatureListAssert(feature.elements)
        }
        throw AssertionFailedError("expected FeatureList but was ${feature::class.simpleName}")
    }
}

internal class OptionsMapAssert(private val optionsMap: OptionsMap) {
    val options = optionsMap.options

    fun kind(kind: String): OptionsMapAssert {
        assertEquals(kind, optionsMap.kind)
        return this
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

internal class GeomOptionsAssert(private val geom: GeomOptions) :
    MappingAssert<GeomOptionsAssert>,
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
    pos: PosOptions?,
    kind: PosKind
) : ParametersAssert<PosOptionsAssert> {
    init {
        assertNotNull(pos)
        assertTrue(pos!!.kind === kind)
    }

    override val parameterOptions = pos!!.parameters
}

internal interface MappingAssert<T> {
    val mappingOptions: Options

    @Suppress("UNCHECKED_CAST")
    fun aes(name: String, value: String): T {
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
        assertEquals(value, parameterOptions.get(name))
        return this as T
    }

    @Suppress("UNCHECKED_CAST")
    fun noParameters(): T {
        assertTrue(parameterOptions.isEmpty())
        return this as T
    }
}
