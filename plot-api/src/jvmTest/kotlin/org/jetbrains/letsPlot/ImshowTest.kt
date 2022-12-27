/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import jetbrains.datalore.plot.config.Option.Geom.Image.HREF
import org.jetbrains.letsPlot.geom.ImageData
import org.jetbrains.letsPlot.geom.geomImshow
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ImshowTest {

    @Test
    fun `simple test`() {
        val imshow = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(listOf(150, 0, 0), listOf(0, 150, 0)),
                    listOf(listOf(0, 0, 150), listOf(150, 150, 0)),
                )
            )
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAACCAIAAAD91JpzAAAAFElEQVR42mOYxsDAAMIM06ZNYwAAEjgC7yZ6kfMAAAAASUVORK5CYII=",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `gray 2 x 3 matrix of byte`() {
        val imshow = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(0.toByte(), 0.toByte(), 0.toByte()),
                    listOf(255.toByte(), 255.toByte(), 255.toByte())
                )
            )
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAAAAAC4HznGAAAAD0lEQVR42mNgAIL///8DAAYCAv7NldrbAAAAAElFTkSuQmCC",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `gray 2 x 3 matrix of int`() {
        val imshow = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(0, 0, 0),
                    listOf(255, 255, 255)
                )
            )
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAAAAAC4HznGAAAAD0lEQVR42mNgAIL///8DAAYCAv7NldrbAAAAAElFTkSuQmCC",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `gray 2 x 3 matrix of float`() {
        val imshow = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(0.0, 0.0, 0.0),
                    listOf(1.0, 1.0, 1.0)
                )
            )
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAAAAAC4HznGAAAAD0lEQVR42mNgAIL///8DAAYCAv7NldrbAAAAAElFTkSuQmCC",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `gray 2 x 3 array of float`() {
        val imshow = geomImshow(
            ImageData.fromArray(
                arrayOf(
                    0.0, 0.0, 0.0,
                    1.0, 1.0, 1.0
                ),
                width = 3,
                height = 2,
                nChannels = 1
            )
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAAAAAC4HznGAAAAD0lEQVR42mNgAIL///8DAAYCAv7NldrbAAAAAElFTkSuQmCC",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `gray 2 x 3 array of byte`() {
        val imshow = geomImshow(
            ImageData.fromArray(
                arrayOf(
                    0, 0, 0,
                    255.toByte(), 255.toByte(), 255.toByte()
                ),
                width = 3,
                height = 2,
                nChannels = 1
            )
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAAAAAC4HznGAAAAD0lEQVR42mNgAIL///8DAAYCAv7NldrbAAAAAElFTkSuQmCC",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `gray 2 x 3 array of int`() {
        val imshow = geomImshow(
            ImageData.fromArray(
                arrayOf(
                    0, 0, 0,
                    255, 255, 255
                ),
                width = 3,
                height = 2,
                nChannels = 1
            )
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAAAAAC4HznGAAAAD0lEQVR42mNgAIL///8DAAYCAv7NldrbAAAAAElFTkSuQmCC",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `gray 2 x 3 IntArray`() {
        val imshow = geomImshow(
            ImageData.fromArray(
                intArrayOf(
                    0x00, 0x00, 0x00, 0xFF, 0xFF, 0xFF
                ),
                width = 3,
                height = 2,
                nChannels = 1
            )
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAAAAAC4HznGAAAAD0lEQVR42mNgAIL///8DAAYCAv7NldrbAAAAAElFTkSuQmCC",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `rgb 1 x 2 x 3 matrix of ints`() {
        val imshow = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(
                        listOf(0, 0, 0),
                        listOf(255, 255, 255)
                    )
                )
            )
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAIAAAB7QOjdAAAAD0lEQVR42mNgYGD4//8/AAYBAv67yYXpAAAAAElFTkSuQmCC",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `rgb 1 x 2 x 3 matrix of floats`() {
        val imshow = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(
                        listOf(0.0, 0.0, 0.0),
                        listOf(1.0, 1.0, 1.0)
                    )
                )
            )
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAIAAAB7QOjdAAAAD0lEQVR42mNgYGD4//8/AAYBAv67yYXpAAAAAElFTkSuQmCC",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `rgb 1 x 2 x 3 array of ints`() {
        val imshow = geomImshow(
            ImageData.fromArray(
                pixels = arrayOf(
                    0, 0, 0,
                    255, 255, 255
                ),
                width = 2,
                height = 1,
                nChannels = 3
            )
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAIAAAB7QOjdAAAAD0lEQVR42mNgYGD4//8/AAYBAv67yYXpAAAAAElFTkSuQmCC",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `rgb 1 x 2 x 3 array of floats`() {
        val imshow = geomImshow(
            ImageData.fromArray(
                pixels = arrayOf(
                    0.0, 0.0, 0.0,
                    1.0, 1.0, 1.0
                ),
                width = 2,
                height = 1,
                nChannels = 3
            )
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAIAAAB7QOjdAAAAD0lEQVR42mNgYGD4//8/AAYBAv67yYXpAAAAAElFTkSuQmCC",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `rgb 1 x 2 x 3 IntArray`() {
        val imshow = geomImshow(
            ImageData.fromArray(
                pixels = intArrayOf(
                    0x0, 0xFFFFFF
                ),
                width = 2,
                height = 1,
                nChannels = 3
            )
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAIAAAB7QOjdAAAAD0lEQVR42mNgYGD4//8/AAYBAv67yYXpAAAAAElFTkSuQmCC",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `rgba 1 x 2 x 4 matrix of ints`() {
        val imshow = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(
                        listOf(0, 0, 0, 128),
                        listOf(255, 255, 255, 128)
                    )
                )
            )
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAYAAAD0In+KAAAAEUlEQVR42mNgYGBo+P//fwMADAAD/kv6htYAAAAASUVORK5CYII=",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `rgba 1 x 2 x 4 matrix of floats`() {
        val imshow = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(
                        listOf(0.0, 0.0, 0.0, 0.5),
                        listOf(1.0, 1.0, 1.0, 0.5)
                    )
                )
            )
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAYAAAD0In+KAAAAEUlEQVR42mNgYGBo+P//fwMADAAD/kv6htYAAAAASUVORK5CYII=",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `rgba 1 x 2 x 4 array of ints`() {
        val imshow = geomImshow(
            ImageData.fromArray(
                pixels = arrayOf(
                    0, 0, 0, 128,
                    255, 255, 255, 128
                ),
                width = 2,
                height = 1,
                nChannels = 4
            )
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAYAAAD0In+KAAAAEUlEQVR42mNgYGBo+P//fwMADAAD/kv6htYAAAAASUVORK5CYII=",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `rgba 1 x 2 x 4 array of floats`() {
        val imshow = geomImshow(
            ImageData.fromArray(
                pixels = arrayOf(
                    0.0, 0.0, 0.0, 0.5,
                    1.0, 1.0, 1.0, 0.5
                ),
                width = 2,
                height = 1,
                nChannels = 4
            )
        )

        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAYAAAD0In+KAAAAEUlEQVR42mNgYGBo+P//fwMADAAD/kv6htYAAAAASUVORK5CYII=",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `rgba 1 x 2 x 4 IntArray`() {
        val imshow = geomImshow(
            ImageData.fromArray(
                pixels = intArrayOf(
                    0x80000000.toInt(), 0x80FFFFFF.toInt()
                ),
                width = 2,
                height = 1,
                nChannels = 4
            )
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAABCAYAAAD0In+KAAAAEUlEQVR42mNgYGBo+P//fwMADAAD/kv6htYAAAAASUVORK5CYII=",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `nan 2 x 3 matrix of floats`() {
        val imshow = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(50.0, Double.NaN, 200.0),
                    listOf(Double.NaN, 100.0, 50.0)
                )
            )
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAQAAAA3fa6RAAAAFUlEQVR42mNg+M/A8B+IGUL/M/wHACJBBVGLKjzHAAAAAElFTkSuQmCC",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `nan 2 x 3 array of floats`() {
        val imshow = geomImshow(
            ImageData.fromArray(
                pixels = arrayOf(
                    50.0, Double.NaN, 200.0,
                    Double.NaN, 100.0, 50.0
                ),
                width = 3,
                height = 2,
                nChannels = 1
            )
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAQAAAA3fa6RAAAAFUlEQVR42mNg+M/A8B+IGUL/M/wHACJBBVGLKjzHAAAAAElFTkSuQmCC",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `normalization - gray 2 x 3 matrix of ints - normalized (default)`() {
        val s1 = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(0, 0, 0),
                    listOf(255, 255, 255)
                )
            ),
        ).seal().map

        val s2 = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(0, 0, 0),
                    listOf(100, 100, 100)
                )
            )
        ).seal().map

        assertEquals(s1, s2)
    }

    @Test
    fun `normalization - gray 2 x 3 matrix of ints - no normalization`() {
        val s1 = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(0, 0, 0),
                    listOf(255, 255, 255)
                )
            ),
            norm = false
        ).seal().map

        val s2 = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(0, 0, 0),
                    listOf(100, 100, 100)
                )
            ),
            norm = false
        ).seal().map

        assertNotEquals(s1, s2)
    }


    @Test
    fun `normalization - gray 2 x 3 matrix of floats - normalized (default)`() {
        val s1 = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(0.0, 0.0, 0.0),
                    listOf(0.1, 0.1, 0.1)
                )
            ),
        ).seal().map

        val s2 = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(0.0, 0.0, 0.0),
                    listOf(100.0, 100.0, 100.0)
                )
            )
        ).seal().map

        assertEquals(s1, s2)
    }

    @Test
    fun `normalization - rgb _ ints`() {
        val s1 = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(
                        listOf(0, 0, 0),
                        listOf(100, 100, 500)
                    )
                )
            ),
        ).seal().map

        val s2 = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(
                        listOf(0, 0, 0),
                        listOf(100, 100, 255)
                    )
                )
            ),
        ).seal().map

        assertEquals(s1, s2)
    }

    @Test
    fun `normalization - rgb _ floats and ints`() {
        val s1 = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(
                        listOf(0.0, 0.0, 0.0),
                        listOf(1.0, 1.0, 1.2)
                    )
                )
            ),
        ).seal().map

        val s2 = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(
                        listOf(0, 0, 0),
                        listOf(255, 255, 255)
                    )
                )
            ),
        ).seal().map

        assertEquals(s1, s2)
    }

    private val flippingExtent = listOf(1 + .5, 0 - .5, 1 + .5, 0 - .5) // [left, right, bottom, top]

    @Test
    fun `extent - gray 2 x 3 int`() {
        val imshow = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(0, 50, 100),
                    listOf(150, 200, 250)
                )
            ),
            extent = flippingExtent
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAAAAAC4HznGAAAAEElEQVR42mP4f2YmQ5oxAwAQXgL+kl/cwQAAAABJRU5ErkJggg==",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `extent - gray 2 x 3 float`() {
        val imshow = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(0.0, 50 / 255.0, 100 / 255.0),
                    listOf(150 / 255.0, 200 / 255.0, 250 / 255.0)
                )
            ),
            extent = flippingExtent
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAAAAAC4HznGAAAAEElEQVR42mP4f2YmQ5oxAwAQXgL+kl/cwQAAAABJRU5ErkJggg==",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `extent - rgb 2 x 2 int`() {
        val imshow = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(listOf(150, 0, 0), listOf(0, 150, 0)),
                    listOf(listOf(0, 0, 150), listOf(150, 150, 0))
                )
            ),
            extent = flippingExtent
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAACCAIAAAD91JpzAAAAEklEQVR42mOYNo2BgWEaCAEJABgUAu8kyhwjAAAAAElFTkSuQmCC",
            imshow.seal().map[HREF]
        )
    }

    @Test
    fun `extent - rgb 2 x 2 float`() {
        val imshow = geomImshow(
            ImageData.fromMatrix(
                listOf(
                    listOf(listOf(150 / 255.0, 0 / 255.0, 0 / 255.0), listOf(0 / 255.0, 150 / 255.0, 0 / 255.0)),
                    listOf(listOf(0 / 255.0, 0 / 255.0, 150 / 255.0), listOf(150 / 255.0, 150 / 255.0, 0 / 255.0))
                )
            ),
            extent = flippingExtent
        )
        assertEquals(
            "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAACCAIAAAD91JpzAAAAEklEQVR42mOYNo2BgWEaCAEJABgUAu8kyhwjAAAAAElFTkSuQmCC",
            imshow.seal().map[HREF]
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

        val imageData = ImageData.fromMatrix(img)
        geomImshow(imageData)
    }

    @Test
    fun `perf array - gray 2_000 x 2_000 px`() {
        val resolution = 2_000
        val img = FloatArray(resolution * resolution)
        for (i in 0 until resolution * resolution) {
            img[i] = Random().nextInt(255).toFloat()
        }

        val imageData = ImageData.fromArray(img, resolution, resolution, 1)
        geomImshow(imageData)
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

        val imageData = ImageData.fromMatrix(img)
        geomImshow(imageData)
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

        val imageData = ImageData.fromArray(img, resolution, resolution, 3)
        geomImshow(imageData)
    }
}
