/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.geom

import java.awt.image.BufferedImage

object Extensions {
    fun RasterData.Companion.create(img: BufferedImage): RasterData {
        val nChannels = if (img.colorModel.hasAlpha()) 4 else 3
        val byteArrayData = ByteArray(img.height * img.width * nChannels)
        var px = 0
        for (j in (0 until img.height)) {
            for (i in 0 until img.width) {
                val rgb = img.getRGB(i, j)
                val a = ((rgb shr 24) and 0xFF).toByte()
                val r = ((rgb shr 16) and 0xFF).toByte()
                val g = ((rgb shr 8) and 0xFF).toByte()
                val b = (rgb and 0xFF).toByte()

                byteArrayData[px++] = r
                byteArrayData[px++] = g
                byteArrayData[px++] = b
                if (nChannels == 4) {
                    byteArrayData[px++] = a
                }
            }
        }

        return RasterData.Companion.create(byteArrayData, img.width, img.height, nChannels)
    }
}
