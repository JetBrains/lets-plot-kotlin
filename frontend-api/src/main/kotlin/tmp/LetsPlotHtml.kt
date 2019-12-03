/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package tmp

import java.util.*
import kotlin.streams.asSequence

// TODO: This object is NOT part of `frontend API` and is here temporarily, only because it is used by `configure.kt`
object LetsPlotHtml {
    private const val baseUrl = "https://dl.bintray.com/jetbrains/lets-plot"
    private const val version = "1.0.0"
    private const val suffix = "min.js"

    @Suppress("SameParameterValue")
    private fun randomString(len: Long): String {
        val alphabet = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return Random().ints(len, 0, alphabet.count())
            .asSequence()
            .map(alphabet::get)
            .joinToString("")
    }

    fun getDynamicScriptLoadingHtml(verbose: Boolean): String {
        val outputId = randomString(6)
        val url = "$baseUrl/lets-plot-$version.$suffix"

        val successMessage = if (verbose) {
            """
            |   var div = document.createElement("div");
            |   div.style.color = 'darkblue';
            |   div.textContent = 'Lets-Plot JS successfully loaded.';
            |   document.getElementById("$outputId").appendChild(div);
            """.trimMargin()
        } else ""

        return """
            |   <div id="$outputId"></div>
            |   <script type="text/javascript">
            |       if(!window.letsPlotCallQueue) {
            |           window.letsPlotCallQueue = [];
            |       }; 
            |       window.letsPlotCall = function(f) {
            |           window.letsPlotCallQueue.push(f);
            |       };
            |       (function() {
            |           var script = document.createElement("script");
            |           script.type = "text/javascript";
            |           script.src = "$url";
            |           script.onload = function() {
            |               window.letsPlotCall = function(f) {f();};
            |               window.letsPlotCallQueue.forEach(function(f) {f();});
            |               window.letsPlotCallQueue = [];
            |               
            |               $successMessage
            |           };
            |           script.onerror = function(event) {
            |               window.letsPlotCall = function(f) {};
            |               window.letsPlotCallQueue = [];
            |               var div = document.createElement("div");
            |               div.style.color = 'darkred';
            |               div.textContent = 'Error loading Lets-Plot JS';
            |               document.getElementById("$outputId").appendChild(div);
            |           };
            |           var e = document.getElementById("$outputId");
            |           e.appendChild(script);
            |       })();
            |   </script>
        """.trimMargin()
    }

    fun getDynamicScriptPlotDisplayHtml(plotSpecAsJsObjectInitializer: String): String {
        val outputId = randomString(6)
        return """
            |   <div id="$outputId"></div>
            |   <script type="text/javascript">
            |       (function() {
            |           var plotSpec=$plotSpecAsJsObjectInitializer;
            |           var plotContainer = document.getElementById("$outputId");
            |           window.letsPlotCall(function() {{
            |               LetsPlot.buildPlotFromProcessedSpecs(plotSpec, -1, -1, plotContainer);
            |           }});
            |       })();    
            |   </script>
        """.trimMargin()
    }

    fun getStaticScriptLoadingHtml(): String {
        val url = "$baseUrl/lets-plot-$version.$suffix"
        return "<script type=\"text/javascript\" src=\"$url\"></script>"
    }

    fun getStaticScriptPlotDisplayHtml(plotSpecAsJsObjectInitializer: String): String {
        val outputId = randomString(6)
        return """
            |   <div id="$outputId"></div>
            |   <script type="text/javascript">
            |       var plotSpec=$plotSpecAsJsObjectInitializer;
            |       var plotContainer = document.getElementById("$outputId");
            |       LetsPlot.buildPlotFromProcessedSpecs(plotSpec, -1, -1, plotContainer);
            |   </script>
        """.trimMargin()
    }
}