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

    val baseUrl = "https://dl.bintray.com/jetbrains/datalore-plot"
    val version = "0.0.1rc4"
    val suffix = "min.js"

    val id = randomString(6)
    val url = "$baseUrl/datalore-plot-$version.$suffix"

    return """<div id="$id"></div>
                   <script type="text/javascript">
                       if(!window.datalorePlotCallQueue) {
                           window.datalorePlotCallQueue = [];
                       }; 
                       window.datalorePlotCall = function(f) {
                           window.datalorePlotCallQueue.push(f);
                       };
                       (function() {
                           var script = document.createElement("script");
                           script.type = "text/javascript";
                           script.src = "$url";
                           script.onload = function() {
                               window.datalorePlotCall = function(f) {f();};
                               window.datalorePlotCallQueue.forEach(function(f) {f();});
                               window.datalorePlotCallQueue = [];
                               
                   var div = document.createElement("div");
                   div.style.color = 'darkblue';
                   div.textContent = 'Datalore Plot JS successfully loaded.';
                   document.getElementById("$id").appendChild(div);
               
                           };
                           script.onerror = function(event) {
                               window.datalorePlotCall = function(f) {};
                               window.datalorePlotCallQueue = [];
                               var div = document.createElement("div");
                               div.style.color = 'darkred';
                               div.textContent = 'Error loading Datalore Plot JS';
                               document.getElementById("$id").appendChild(div);
                           };
                           var e = document.getElementById("$id");
                           e.appendChild(script);
                       })()
                   </script>"""
}