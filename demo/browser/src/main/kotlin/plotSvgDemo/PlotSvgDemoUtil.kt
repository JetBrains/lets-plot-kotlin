/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package plotSvgDemo

import BrowserDemoUtil
import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.plot.MonolithicAwt
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import java.io.StringWriter

object PlotSvgDemoUtil {
    private const val DEMO_PROJECT = "plot-demo"

    fun show(
        title: String,
        plotSpecList: List<MutableMap<String, Any>>,
        plotSize: DoubleVector?
    ) {
        BrowserDemoUtil.openInBrowser(
            getHtml(
                title,
                plotSpecList,
                plotSize
            )
        )
    }

    private fun getHtml(
        title: String,
        plotSpecList: List<MutableMap<String, Any>>,
        plotSize: DoubleVector?
    ): String {

        val writer = StringWriter().appendHTML().html {
            lang = "en"
            head {
                title(title)
                style {
                    unsafe {
                        +"""
                            div.demo {
                                border: 1px solid orange;
                                margin: 20px;
                                display: inline-block;
                            }
                        """.trimIndent()
                    }
                }
            }
            body {
                for (plotSpec in plotSpecList) {
                    // Generate SVG images from plot or bunch specifications
                    val svgImages = MonolithicAwt.buildSvgImagesFromRawSpecs(plotSpec, plotSize) {
                        println(it)
                    }
                    for (svgImage in svgImages) {
                        div("demo") {
                            unsafe { +svgImage }
                        }
                    }
                }
            }
        }

        return writer.toString()
    }
}