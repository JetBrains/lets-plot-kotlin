package jetbrains.datalore.jupyter

import java.util.*
import kotlin.streams.asSequence

fun randomString(len : Long) : String {
    val alphabet = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    return Random().ints(len, 0, alphabet.count())
        .asSequence()
        .map(alphabet::get)
        .joinToString("")
}

fun configureScript() : String {

    val baseUrl = "https://dl.bintray.com/jetbrains/lets-plot"
    val version = "1.0.0rc1"
    val suffix = "min.js"

    val id = randomString(6)
    val url = "$baseUrl/lets-plot-$version.$suffix"

    return """<div id="$id"></div>
                   <script type="text/javascript">
                       if(!window.letsPlotCallQueue) {
                           window.letsPlotCallQueue = [];
                       }; 
                       window.letsPlotCall = function(f) {
                           window.letsPlotCallQueue.push(f);
                       };
                       (function() {
                           var script = document.createElement("script");
                           script.type = "text/javascript";
                           script.src = "$url";
                           script.onload = function() {
                               window.letsPlotCall = function(f) {f();};
                               window.letsPlotCallQueue.forEach(function(f) {f();});
                               window.letsPlotCallQueue = [];
                               
                   var div = document.createElement("div");
                   div.style.color = 'darkblue';
                   div.textContent = 'Lets-Plot JS successfully loaded.';
                   document.getElementById("$id").appendChild(div);
               
                           };
                           script.onerror = function(event) {
                               window.letsPlotCall = function(f) {};
                               window.letsPlotCallQueue = [];
                               var div = document.createElement("div");
                               div.style.color = 'darkred';
                               div.textContent = 'Error loading Lets-Plot JS';
                               document.getElementById("$id").appendChild(div);
                           };
                           var e = document.getElementById("$id");
                           e.appendChild(script);
                       })()
                   </script>"""
}