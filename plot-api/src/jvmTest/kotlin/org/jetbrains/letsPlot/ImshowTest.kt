/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.letsPlot.commons.encoding.Png
import org.jetbrains.letsPlot.core.spec.Option.Geom.Image.HREF
import org.jetbrains.letsPlot.geom.RasterData
import org.jetbrains.letsPlot.geom.geomImshow
import org.jetbrains.letsPlot.intern.Feature
import org.jetbrains.letsPlot.intern.FeatureList
import org.jetbrains.letsPlot.intern.Layer
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ImshowTest {

    private fun getImageData(imshow: Feature): String {
        val layer = when (imshow) {
            is Layer -> imshow
            is FeatureList -> imshow.elements[0] as Layer // layer + scale spec
            else -> throw IllegalArgumentException("Unexpected ${imshow::class.simpleName}")
        }

        return layer.seal().map.getValue(HREF) as String
    }

    private fun getLayerSettings(imshow: Feature): Map<*, *> {
        val layer = when (imshow) {
            is Layer -> imshow
            is FeatureList -> imshow.elements[0] as Layer // layer + scale spec
            else -> throw IllegalArgumentException("Unexpected ${imshow::class.simpleName}")
        }

        return layer.seal().map
    }

    @Test
    fun `simple test`() {
        val imshow = geomImshow(
            RasterData.create(
                listOf(
                    listOf(listOf(150, 0, 0), listOf(0, 150, 0)),
                    listOf(listOf(0, 0, 150), listOf(150, 150, 0)),
                )
            )
        )

        // "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAACCAIAAAD91JpzAAAAFElEQVR42mOYxsDAAMIM06ZNYwAAEjgC7yZ6kfMAAAAASUVORK5CYII="
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFF960000.toInt(), 0xFF009600.toInt(),
            0xFF000096.toInt(), 0xFF969600.toInt()
        )
    }

    @Test
    fun `gray 2 x 3 matrix of byte`() {
        val imshow = geomImshow(
            RasterData.create(
                listOf(
                    listOf(0.toByte(), 15.toByte(), 240.toByte()),
                    listOf(255.toByte(), 170.toByte(), 1.toByte())
                )
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAYAAACddGYaAAAAIUlEQVR42g3BAQEAAAjCMEhA/wqko8F1kySSsA3x2mKbA6QzDfbcagSFAAAAAElFTkSuQmCC
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFF000000.toInt(),0xFF0F0F0F.toInt(),0xFFF0F0F0.toInt(),
            0xFFFFFFFF.toInt(), 0xFFAAAAAA.toInt(), 0xFF010101.toInt(),
        )
    }

    @Test
    fun `gray 2 x 3 matrix of int`() {
        val imshow = geomImshow(
            RasterData.create(
                listOf(
                    listOf(0, 15, 240),
                    listOf(255, 170, 1)
                )
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAYAAACddGYaAAAAIUlEQVR42g3BAQEAAAjCMEhA/wqko8F1kySSsA3x2mKbA6QzDfbcagSFAAAAAElFTkSuQmCC
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFF000000.toInt(),0xFF0F0F0F.toInt(),0xFFF0F0F0.toInt(),
            0xFFFFFFFF.toInt(), 0xFFAAAAAA.toInt(), 0xFF010101.toInt(),
        )
    }

    @Test
    fun `gray 2 x 3 matrix of float`() {
        val imshow = geomImshow(
            RasterData.create(
                listOf(
                    listOf(0 / 255f, 15 / 255f, 240 / 255f),
                    listOf(255 / 255f, 170 / 255f , 1 / 255f)
                )
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAYAAACddGYaAAAAIUlEQVR42g3BAQEAAAjCMEhA/wqko8F1kySSsA3x2mKbA6QzDfbcagSFAAAAAElFTkSuQmCC
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFF000000.toInt(),0xFF0F0F0F.toInt(),0xFFF0F0F0.toInt(),
            0xFFFFFFFF.toInt(), 0xFFAAAAAA.toInt(), 0xFF010101.toInt(),
        )
    }

    @Test
    fun `gray 2 x 3 array of float`() {
        val imshow = geomImshow(
            RasterData.create(
                arrayOf(
                    0 / 255f, 15 / 255f, 240 / 255f,
                    255 / 255f, 170 / 255f , 1 / 255f
                ),
                width = 3,
                height = 2,
                nChannels = 1
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAYAAACddGYaAAAAIUlEQVR42g3BAQEAAAjCMEhA/wqko8F1kySSsA3x2mKbA6QzDfbcagSFAAAAAElFTkSuQmCC
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFF000000.toInt(),0xFF0F0F0F.toInt(),0xFFF0F0F0.toInt(),
            0xFFFFFFFF.toInt(), 0xFFAAAAAA.toInt(), 0xFF010101.toInt(),
        )
    }

    @Test
    fun `gray 2 x 3 array of byte`() {
        val imshow = geomImshow(
            RasterData.create(
                arrayOf(
                    0.toByte(), 15.toByte(), 240.toByte(),
                    255.toByte(), 170.toByte() , 1.toByte()
                ),
                width = 3,
                height = 2,
                nChannels = 1
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAYAAACddGYaAAAAIUlEQVR42g3BAQEAAAjCMEhA/wqko8F1kySSsA3x2mKbA6QzDfbcagSFAAAAAElFTkSuQmCC
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFF000000.toInt(),0xFF0F0F0F.toInt(),0xFFF0F0F0.toInt(),
            0xFFFFFFFF.toInt(), 0xFFAAAAAA.toInt(), 0xFF010101.toInt(),
        )
    }

    @Test
    fun `gray 2 x 3 array of int`() {
        val imshow = geomImshow(
            RasterData.create(
                arrayOf(
                    0, 15, 240,
                    255, 170 , 1
                ),
                width = 3,
                height = 2,
                nChannels = 1
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAYAAACddGYaAAAAIUlEQVR42g3BAQEAAAjCMEhA/wqko8F1kySSsA3x2mKbA6QzDfbcagSFAAAAAElFTkSuQmCC
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFF000000.toInt(),0xFF0F0F0F.toInt(),0xFFF0F0F0.toInt(),
            0xFFFFFFFF.toInt(), 0xFFAAAAAA.toInt(), 0xFF010101.toInt(),
        )
    }

    @Test
    fun `DEM 2 x 3 array of int `() {
        // Values higher than 255. normalization expected.
        val imshow = geomImshow(
            RasterData.create(
                arrayOf(
                    0, 15, 240,
                    255 * 4, 170 * 4, 1 * 4,
                ),
                width = 3,
                height = 2,
                nChannels = 1
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAYAAACddGYaAAAAIElEQVR42g3BAQ0AAAzCMEiQhWJM7m8liSS0Rbxt2OYAf98LuRHNQbEAAAAASUVORK5CYII=
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFF000000.toInt(),0xFF040404.toInt(),0xFF3C3C3C.toInt(),  // 15 scaled to 5, 240 scaled to 60
            0xFFFFFFFF.toInt(), 0xFFAAAAAA.toInt(), 0xFF010101.toInt(),
        )
    }

    @Test
    fun `gray 2 x 3 IntArray`() {
        val imshow = geomImshow(
            RasterData.create(
                intArrayOf(
                    0, 15, 240,
                    255, 170 , 1
                ),
                width = 3,
                height = 2,
                nChannels = 1
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAYAAACddGYaAAAAIUlEQVR42g3BAQEAAAjCMEhA/wqko8F1kySSsA3x2mKbA6QzDfbcagSFAAAAAElFTkSuQmCC
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFF000000.toInt(),0xFF0F0F0F.toInt(),0xFFF0F0F0.toInt(),
            0xFFFFFFFF.toInt(), 0xFFAAAAAA.toInt(), 0xFF010101.toInt(),
        )
    }

    @Test
    fun `rgb 1 x 2 x 3 matrix of ints`() {
        val imshow = geomImshow(
            RasterData.create(
                listOf(
                    listOf(
                        listOf(0, 0, 0),
                        listOf(255, 255 , 255)
                    )
                )
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAIAAAB7QOjdAAAAD0lEQVR42mNgYGD4//8/AAYBAv67yYXpAAAAAElFTkSuQmCC
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFF000000.toInt(),
            0xFFFFFFFF.toInt(),
        )
    }

    @Test
    fun `rgb 1 x 2 x 3 matrix of floats`() {
        val imshow = geomImshow(
            RasterData.create(
                listOf(
                    listOf(
                        listOf(0.0, 0.0, 0.0),
                        listOf(1.0, 1.0, 1.0)
                    )
                )
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAIAAAB7QOjdAAAAD0lEQVR42mNgYGD4//8/AAYBAv67yYXpAAAAAElFTkSuQmCC
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFF000000.toInt(),
            0xFFFFFFFF.toInt(),
        )
    }

    @Test
    fun `rgb 1 x 2 x 3 array of ints`() {
        val imshow = geomImshow(
            RasterData.create(
                arr = arrayOf(
                    0, 0, 0,
                    255, 255, 255
                ),
                width = 2,
                height = 1,
                nChannels = 3
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAIAAAB7QOjdAAAAD0lEQVR42mNgYGD4//8/AAYBAv67yYXpAAAAAElFTkSuQmCC
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFF000000.toInt(),
            0xFFFFFFFF.toInt(),
        )
    }

    @Test
    fun `rgb 1 x 2 x 3 array of floats`() {
        val imshow = geomImshow(
            RasterData.create(
                arr = arrayOf(
                    0.0, 0.0, 0.0,
                    1.0, 1.0, 1.0
                ),
                width = 2,
                height = 1,
                nChannels = 3
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAIAAAB7QOjdAAAAD0lEQVR42mNgYGD4//8/AAYBAv67yYXpAAAAAElFTkSuQmCC
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFF000000.toInt(),
            0xFFFFFFFF.toInt(),
        )
    }

    @Test
    fun `rgb 1 x 2 x 3 IntArray`() {
        val imshow = geomImshow(
            RasterData.create(
                arr = intArrayOf(
                    0x0, 0x00, 0x00,
                    0xFF, 0xFF, 0xFF
                ),
                width = 2,
                height = 1,
                nChannels = 3
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAIAAAB7QOjdAAAAD0lEQVR42mNgYGD4//8/AAYBAv67yYXpAAAAAElFTkSuQmCC
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFF000000.toInt(),
            0xFFFFFFFF.toInt(),
        )
    }

    @Test
    fun `rgba 1 x 2 x 4 matrix of ints`() {
        val imshow = geomImshow(
            RasterData.create(
                listOf(
                    listOf(
                        listOf(0, 60, 190, 128),
                        listOf(255, 80, 1, 128)
                    )
                )
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAYAAAD0In+KAAAAEUlEQVR42mNgsNnX8D+AsQEADg8DS9L6MGgAAAAASUVORK5CYII=
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)
        assertThat(actualBitmap.argbInts).containsOnly(
            0x80003CBE.toInt(),
            0x80FF5001.toInt(),
        )
    }

    @Test
    fun `rgba 1 x 2 x 4 matrix of floats`() {
        val imshow = geomImshow(
            RasterData.create(
                listOf(
                    listOf(
                        listOf(0 / 255f, 60 / 255f, 190 / 255f, 128 / 255f),
                        listOf(255 / 255f, 80 / 255f, 1 / 255f, 128 / 255f)
                    )
                )
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAYAAAD0In+KAAAAEUlEQVR42mNgsNnX8D+AsQEADg8DS9L6MGgAAAAASUVORK5CYII=
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)
        assertThat(actualBitmap.argbInts).containsOnly(
            0x80003CBE.toInt(),
            0x80FF5001.toInt(),
        )
    }

    @Test
    fun `rgba 1 x 2 x 4 array of ints`() {
        val imshow = geomImshow(
            RasterData.create(
                arr = arrayOf(
                    0, 60, 190, 128,
                    255, 80, 1, 128
                ),
                width = 2,
                height = 1,
                nChannels = 4
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAYAAAD0In+KAAAAEUlEQVR42mNgsNnX8D+AsQEADg8DS9L6MGgAAAAASUVORK5CYII=
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)
        assertThat(actualBitmap.argbInts).containsOnly(
            0x80003CBE.toInt(),
            0x80FF5001.toInt(),
        )
    }

    @Test
    fun `rgba 1 x 2 x 4 array of floats`() {
        val imshow = geomImshow(
            RasterData.create(
                arr = arrayOf(
                    0 / 255f, 60 / 255f, 190 / 255f, 128 / 255f,
                    255 / 255f, 80 / 255f, 1 / 255f, 128 / 255f
                ),
                width = 2,
                height = 1,
                nChannels = 4
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAYAAAD0In+KAAAAEUlEQVR42mNgsNnX8D+AsQEADg8DS9L6MGgAAAAASUVORK5CYII=
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)
        assertThat(actualBitmap.argbInts).containsOnly(
            0x80003CBE.toInt(),
            0x80FF5001.toInt(),
        )
    }

    @Test
    fun `rgba 1 x 2 x 4 IntArray`() {
        val imshow = geomImshow(
            RasterData.create(
                arr = intArrayOf(
                    0x00, 0x3C, 0xBE, 0x80,
                    0xFF, 0x50, 0x01, 128
                ),
                width = 2,
                height = 1,
                nChannels = 4
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAYAAAD0In+KAAAAEUlEQVR42mNgsNnX8D+AsQEADg8DS9L6MGgAAAAASUVORK5CYII=
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)
        assertThat(actualBitmap.argbInts).containsOnly(
            0x80003CBE.toInt(),
            0x80FF5001.toInt(),
        )
    }

    @Test
    fun `nan 2 x 3 matrix of floats`() {
        val imshow = geomImshow(
            RasterData.create(
                listOf(
                    listOf(50.0, Double.NaN, 200.0),
                    listOf(Double.NaN, 100.0, 50.0)
                )
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAQAAAA3fa6RAAAAFUlEQVR42mNg+M/A8B+IGUL/M/wHACJBBVGLKjzHAAAAAElFTkSuQmCC
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFF000000.toInt(), 0x000000000, 0xFFFFFFFF.toInt(),
            0x00000000, 0xFF555555.toInt(), 0xFF000000.toInt(),
        )
    }

    @Test
    fun `nan 2 x 3 array of floats`() {
        val imshow = geomImshow(
            RasterData.create(
                arr = arrayOf(
                    50.0, Double.NaN, 200.0,
                    Double.NaN, 100.0, 50.0
                ),
                width = 3,
                height = 2,
                nChannels = 1
            )
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAQAAAA3fa6RAAAAFUlEQVR42mNg+M/A8B+IGUL/M/wHACJBBVGLKjzHAAAAAElFTkSuQmCC
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFF000000.toInt(), 0x000000000, 0xFFFFFFFF.toInt(),
            0x00000000, 0xFF555555.toInt(), 0xFF000000.toInt(),
        )
    }

    @Test
    fun `normalization - gray 2 x 3 matrix of ints - normalized (default)`() {
        val s1 = geomImshow(
            RasterData.create(
                listOf(
                    listOf(0, 0, 0),
                    listOf(255, 255, 255)
                )
            ),
        ).let {
            getLayerSettings(it)
        }

        val s2 = geomImshow(
            RasterData.create(
                listOf(
                    listOf(0, 0, 0),
                    listOf(100, 100, 100)
                )
            )
        ).let {
            getLayerSettings(it)
        }

        assertEquals(s1, s2)
    }

    @Test
    fun `normalization - gray 2 x 3 matrix of ints - no normalization`() {
        val s1 = geomImshow(
            RasterData.create(
                listOf(
                    listOf(0, 0, 0),
                    listOf(255, 255, 255)
                )
            ),
            norm = false
        ).let {
            getLayerSettings(it)
        }

        val s2 = geomImshow(
            RasterData.create(
                listOf(
                    listOf(0, 0, 0),
                    listOf(100, 100, 100)
                )
            ),
            norm = false
        ).let {
            getLayerSettings(it)
        }

        assertNotEquals(s1, s2)
    }


    @Test
    fun `normalization - gray 2 x 3 matrix of floats - normalized (default)`() {
        val s1 = geomImshow(
            RasterData.create(
                listOf(
                    listOf(0.0, 0.0, 0.0),
                    listOf(0.1, 0.1, 0.1)
                )
            ),
        ).let {
            getLayerSettings(it)
        }

        val s2 = geomImshow(
            RasterData.create(
                listOf(
                    listOf(0.0, 0.0, 0.0),
                    listOf(100.0, 100.0, 100.0)
                )
            )
        ).let {
            getLayerSettings(it)
        }

        assertEquals(s1, s2)
    }

    @Test
    fun `normalization - rgb _ ints`() {
        val s1 = geomImshow(
            RasterData.create(
                listOf(
                    listOf(
                        listOf(0, 0, 0),
                        listOf(100, 100, 500)
                    )
                )
            ),
        ).let {
            getLayerSettings(it)
        }

        val s2 = geomImshow(
            RasterData.create(
                listOf(
                    listOf(
                        listOf(0, 0, 0),
                        listOf(100, 100, 255)
                    )
                )
            ),
        ).let {
            getLayerSettings(it)
        }

        assertEquals(s1, s2)
    }

    @Test
    fun `normalization - rgb _ floats and ints`() {
        val s1 = geomImshow(
            RasterData.create(
                listOf(
                    listOf(
                        listOf(0.0, 0.0, 0.0),
                        listOf(1.0, 1.0, 1.2)
                    )
                )
            ),
        ).let {
            getLayerSettings(it)
        }

        val s2 = geomImshow(
            RasterData.create(
                listOf(
                    listOf(
                        listOf(0, 0, 0),
                        listOf(255, 255, 255)
                    )
                )
            ),
        ).let {
            getLayerSettings(it)
        }

        assertEquals(s1, s2)
    }

    private val flippingExtent = listOf(1 + .5, 0 - .5, 1 + .5, 0 - .5) // [left, right, bottom, top]

    @Test
    fun `extent - gray 2 x 3 int`() {
        val imshow = geomImshow(
            RasterData.create(
                listOf(
                    listOf(0, 50, 100),
                    listOf(150, 200, 250)
                )
            ),
            extent = flippingExtent
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAYAAACddGYaAAAAIUlEQVR42g3BAQEAAAjCMDrRiU50otN1E28bbVESbCOJA+piDvIKexMQAAAAAElFTkSuQmCC
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFFFFFFFF.toInt(), 0xFFCCCCCC.toInt(), 0xFF999999.toInt(),
            0xFF666666.toInt(), 0xFF333333.toInt(), 0xFF000000.toInt(),
        )
    }

    @Test
    fun `extent - gray 2 x 3 float`() {
        val imshow = geomImshow(
            RasterData.create(
                listOf(
                    listOf(0.0, 50 / 255.0, 100 / 255.0),
                    listOf(150 / 255.0, 200 / 255.0, 250 / 255.0)
                )
            ),
            extent = flippingExtent
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAYAAACddGYaAAAAIUlEQVR42g3BAQEAAAjCMDrRiU50otN1E28bbVESbCOJA+piDvIKexMQAAAAAElFTkSuQmCC
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFFFFFFFF.toInt(), 0xFFCCCCCC.toInt(), 0xFF999999.toInt(),
            0xFF666666.toInt(), 0xFF333333.toInt(), 0xFF000000.toInt(),
        )
    }

    @Test
    fun `extent - rgb 2 x 2 int`() {
        val imshow = geomImshow(
            RasterData.create(
                listOf(
                    listOf(listOf(150, 0, 0), listOf(0, 150, 0)),
                    listOf(listOf(0, 0, 150), listOf(150, 150, 0))
                )
            ),
            extent = flippingExtent
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAACCAIAAAD91JpzAAAAEklEQVR42mOYNo2BgWEaCAEJABgUAu8kyhwjAAAAAElFTkSuQmCC
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFF969600.toInt(), 0xFF000096.toInt(),
            0xFF009600.toInt(), 0xFF960000.toInt()
        )
    }

    @Test
    fun `extent - rgb 2 x 2 float`() {
        val imshow = geomImshow(
            RasterData.create(
                listOf(
                    listOf(listOf(150 / 255.0, 0 / 255.0, 0 / 255.0), listOf(0 / 255.0, 150 / 255.0, 0 / 255.0)),
                    listOf(listOf(0 / 255.0, 0 / 255.0, 150 / 255.0), listOf(150 / 255.0, 150 / 255.0, 0 / 255.0))
                )
            ),
            extent = flippingExtent
        )

        // data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAACCAIAAAD91JpzAAAAEklEQVR42mOYNo2BgWEaCAEJABgUAu8kyhwjAAAAAElFTkSuQmCC
        val actualDataImage = getImageData(imshow)
        val actualBitmap = Png.decodeDataImage(actualDataImage)

        assertThat(actualBitmap.argbInts).containsOnly(
            0xFF969600.toInt(), 0xFF000096.toInt(),
            0xFF009600.toInt(), 0xFF960000.toInt()
        )
    }

    @Test
    fun `perf matrix - gray 2_000 x 2_000 px`() {
        val resolution = 2_000
        val img = mutableListOf<MutableList<Int>>()
        for (j in 0 until resolution) {
            val row = mutableListOf<Int>()
            for (i in 0 until resolution) {
                row.add(i % 255)
            }
            img.add(row)
        }

        val rasterData = RasterData.create(img)
        geomImshow(rasterData)
    }

    @Test
    fun `perf array - gray 2_000 x 2_000 px`() {
        val resolution = 2_000
        val img = FloatArray(resolution * resolution)
        for (i in 0 until resolution * resolution) {
            img[i] = Random().nextInt(255).toFloat()
        }

        val rasterData = RasterData.create(img, resolution, resolution, 1)
        geomImshow(rasterData)
    }

    @Test
    fun `perf matrix - rgb 1_000 x 1_000 px`() {
        val resolution = 1_000
        val img = mutableListOf<MutableList<List<Int>>>()
        for (j in 0 until resolution) {
            val row = mutableListOf<List<Int>>()
            for (i in 0 until resolution) {
                row.add(
                    listOf((j) % 256, (j) % 256, (j) % 256)
                )
            }
            img.add(row)
        }

        val rasterData = RasterData.create(img)
        geomImshow(rasterData)
    }

    @Test
    fun `perf array - rgb 1_000 x 1_000 px`() {
        val resolution = 1_000
        val img = ByteArray(resolution * resolution * 3)

        for (j in 0 until resolution) {
            for (i in 0 until resolution) {
                var idx = (j * resolution + i) * 3
                img[idx++] = (j % 256).toByte()
                img[idx++] = (j % 256).toByte()
                img[idx] = (j % 256).toByte()
            }
        }

        val rasterData = RasterData.create(img, resolution, resolution, 3)
        geomImshow(rasterData)
    }
}
