/*
 * Copyright (c) 2026. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.intern

import org.jetbrains.letsPlot.commons.values.Color
import org.jetbrains.letsPlot.core.commons.data.DataType
import org.jetbrains.letsPlot.core.plot.base.Aes
import org.jetbrains.letsPlot.core.plot.builder.scale.PaletteGenerator
import org.jetbrains.letsPlot.core.spec.config.ScaleConfig
import org.jetbrains.letsPlot.core.spec.conversion.AesOptionConversion
import org.jetbrains.letsPlot.core.spec.conversion.ColorOptionConverter

class ColorScale(
    aesthetic: Any,
    name: String? = null,
    breaks: Any? = null,
    labels: Any? = null,
    lablim: Int? = null,
    limits: Any? = null,
    expand: Any? = null,
    naValue: Any? = null,
    format: String? = null,
    guide: Any? = null,
    trans: String? = null,
    position: String? = null,
    otherOptions: Options = Options.empty()
) : Scale(aesthetic, name, breaks, labels, lablim, limits, expand, naValue, format, guide, trans, position, otherOptions) {

    /**
     * Generate a list of hex color codes from this color scale specification.
     *
     * @param n Number of colors to generate.
     * @return List of hex color codes.
     */
    fun palette(n: Int): List<String> {
        val scaleOptions = toSpec().first()
        val scaleConfig = ScaleConfig<Color>(
            aes = Aes.COLOR,
            options = scaleOptions,
            aopConversion = AesOptionConversion(ColorOptionConverter(null, null, null)),
            dataType = DataType.UNKNOWN,
            tz = null
        )
        val paletteGenerator = scaleConfig.createMapperProvider() as? PaletteGenerator
            ?: throw IllegalArgumentException("This scale does not support palette generation.")
        return paletteGenerator.generatePalette(n)
    }
}
