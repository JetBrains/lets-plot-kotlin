/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.letsPlot.bistro.corr

internal class LayerParams {
    var added: Boolean = false
        private set
    var type: String? = null
        set(v) {
            added = true
            field = v
        }
    var diag: Boolean? = null
        set(v) {
            added = true
            field = v
        }
    var color: String? = null
        set(v) {
            added = true
            field = v
        }
    var mapSize: Boolean? = null
        set(v) {
            added = true
            field = v
        }

    fun copy(): LayerParams {
        return LayerParams().apply {
            val wasAdded = this@LayerParams.added
            type = this@LayerParams.type
            diag = this@LayerParams.diag
            color = this@LayerParams.color
            mapSize = this@LayerParams.mapSize
            added = wasAdded
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
        if (!(other is LayerParams)) return false

//        other as LayerParams

        if (added != other.added) return false
        if (type != other.type) return false
        if (diag != other.diag) return false
        if (color != other.color) return false
        if (mapSize != other.mapSize) return false

        return true
    }

    override fun hashCode(): Int {
        var result = added.hashCode()
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (diag?.hashCode() ?: 0)
        result = 31 * result + (color?.hashCode() ?: 0)
        result = 31 * result + (mapSize?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "LayerParams(added=$added, type=$type, diag=$diag, color=$color, mapSize=$mapSize)"
    }
}