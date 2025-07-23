# Named Colors Reference

You can specify a color for theme elements and geometries by its name from the table below.

<note>
Named colors are case-insensitive and can be written in various formats: "LightGreen", "lightgreen", "light green", or "light-green" are all accepted and treated equivalently. You can also use either "gray" or "grey" spelling for grayscale colors.
</note>

## Example {collapsible="true"}

```kotlin
val rand = java.util.Random(42)
val groups = listOf("A", "B", "C")
val dataMap = mapOf<String, Any>(
    "x" to List(20) { rand.nextDouble() }
               .map { v -> ((groups.size - 1) * v).roundToInt() }
               .map { i -> groups[i] }
)
letsPlot(dataMap) { x = "x" } +
        geomBar(fill = "salmon", size = 0, tooltips = tooltipsNone) +
        theme(
            plotBackground = elementRect(fill = "navy"),
            text = elementText(color = "white"),
            axis = elementLine(color = "azure"),
            panelGrid = elementLine(color = "azure")
        )
```

<img src="named_colors_example.png" alt="Named colors example"/>

## Named Colors Table

<tabs>
    <tab id="by-shade" title="By shade">
        <table class="named-colors-table by-shade" >
        <thead><tr><td colspan="3">Brown colors</td></tr></thead>
        <tr>
        <td class="light-text" style="background-color:#800000;">maroon</td>
        <td class="light-text" style="background-color:#800000;">#800000</td>
        <td class="light-text" style="background-color:#800000;">128, 0, 0</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#8B0000;">dark_red</td>
        <td class="light-text" style="background-color:#8B0000;">#8B0000</td>
        <td class="light-text" style="background-color:#8B0000;">139, 0, 0</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#8B4513;">saddle_brown</td>
        <td class="light-text" style="background-color:#8B4513;">#8B4513</td>
        <td class="light-text" style="background-color:#8B4513;">139, 69, 19</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#A52A2A;">brown</td>
        <td class="light-text" style="background-color:#A52A2A;">#A52A2A</td>
        <td class="light-text" style="background-color:#A52A2A;">165, 42, 42</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#B22222;">firebrick</td>
        <td class="light-text" style="background-color:#B22222;">#B22222</td>
        <td class="light-text" style="background-color:#B22222;">178, 34, 34</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#A0522D;">sienna</td>
        <td class="light-text" style="background-color:#A0522D;">#A0522D</td>
        <td class="light-text" style="background-color:#A0522D;">160, 82, 45</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#CD5C5C;">indian_red</td>
        <td class="light-text" style="background-color:#CD5C5C;">#CD5C5C</td>
        <td class="light-text" style="background-color:#CD5C5C;">205, 92, 92</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#D2691E;">chocolate</td>
        <td class="light-text" style="background-color:#D2691E;">#D2691E</td>
        <td class="light-text" style="background-color:#D2691E;">210, 105, 30</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#CD853F;">peru</td>
        <td class="light-text" style="background-color:#CD853F;">#CD853F</td>
        <td class="light-text" style="background-color:#CD853F;">205, 133, 63</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#BC8F8F;">rosy_brown</td>
        <td class="light-text" style="background-color:#BC8F8F;">#BC8F8F</td>
        <td class="light-text" style="background-color:#BC8F8F;">188, 143, 143</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#D2B48C;">tan</td>
        <td class="light-text" style="background-color:#D2B48C;">#D2B48C</td>
        <td class="light-text" style="background-color:#D2B48C;">210, 180, 140</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#DEB887;">burly_wood</td>
        <td class="dark-text" style="background-color:#DEB887;">#DEB887</td>
        <td class="dark-text" style="background-color:#DEB887;">222, 184, 135</td>
        </tr>
        </table>
        <table class="named-colors-table by-shade" >
        <thead><tr><td colspan="3">Red colors</td></tr></thead>
        <tr>
        <td class="light-text" style="background-color:#FF0000;">red</td>
        <td class="light-text" style="background-color:#FF0000;">#FF0000</td>
        <td class="light-text" style="background-color:#FF0000;">255, 0, 0</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#FF4500;">orange_red</td>
        <td class="light-text" style="background-color:#FF4500;">#FF4500</td>
        <td class="light-text" style="background-color:#FF4500;">255, 69, 0</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#FF6347;">tomato</td>
        <td class="light-text" style="background-color:#FF6347;">#FF6347</td>
        <td class="light-text" style="background-color:#FF6347;">255, 99, 71</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#F08080;">light_coral</td>
        <td class="light-text" style="background-color:#F08080;">#F08080</td>
        <td class="light-text" style="background-color:#F08080;">240, 128, 128</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#FA8072;">salmon</td>
        <td class="light-text" style="background-color:#FA8072;">#FA8072</td>
        <td class="light-text" style="background-color:#FA8072;">250, 128, 114</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#FF7F50;">coral</td>
        <td class="light-text" style="background-color:#FF7F50;">#FF7F50</td>
        <td class="light-text" style="background-color:#FF7F50;">255, 127, 80</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#E9967A;">dark_salmon</td>
        <td class="light-text" style="background-color:#E9967A;">#E9967A</td>
        <td class="light-text" style="background-color:#E9967A;">233, 150, 122</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFA07A;">light_salmon</td>
        <td class="dark-text" style="background-color:#FFA07A;">#FFA07A</td>
        <td class="dark-text" style="background-color:#FFA07A;">255, 160, 122</td>
        </tr>
        </table>
        <table class="named-colors-table by-shade" >
        <thead><tr><td colspan="3">Orange colors</td></tr></thead>
        <tr>
        <td class="light-text" style="background-color:#B8860B;">dark_goldenrod</td>
        <td class="light-text" style="background-color:#B8860B;">#B8860B</td>
        <td class="light-text" style="background-color:#B8860B;">184, 134, 11</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#FF8C00;">dark_orange</td>
        <td class="light-text" style="background-color:#FF8C00;">#FF8C00</td>
        <td class="light-text" style="background-color:#FF8C00;">255, 140, 0</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#DAA520;">goldenrod</td>
        <td class="light-text" style="background-color:#DAA520;">#DAA520</td>
        <td class="light-text" style="background-color:#DAA520;">218, 165, 32</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#F4A460;">sandy_brown</td>
        <td class="dark-text" style="background-color:#F4A460;">#F4A460</td>
        <td class="dark-text" style="background-color:#F4A460;">244, 164, 96</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFA500;">orange</td>
        <td class="dark-text" style="background-color:#FFA500;">#FFA500</td>
        <td class="dark-text" style="background-color:#FFA500;">255, 165, 0</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFDAB9;">peach_puff</td>
        <td class="dark-text" style="background-color:#FFDAB9;">#FFDAB9</td>
        <td class="dark-text" style="background-color:#FFDAB9;">255, 218, 185</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#F5DEB3;">wheat</td>
        <td class="dark-text" style="background-color:#F5DEB3;">#F5DEB3</td>
        <td class="dark-text" style="background-color:#F5DEB3;">245, 222, 179</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFDEAD;">navajo_white</td>
        <td class="dark-text" style="background-color:#FFDEAD;">#FFDEAD</td>
        <td class="dark-text" style="background-color:#FFDEAD;">255, 222, 173</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFE4B5;">moccasin</td>
        <td class="dark-text" style="background-color:#FFE4B5;">#FFE4B5</td>
        <td class="dark-text" style="background-color:#FFE4B5;">255, 228, 181</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFE4C4;">bisque</td>
        <td class="dark-text" style="background-color:#FFE4C4;">#FFE4C4</td>
        <td class="dark-text" style="background-color:#FFE4C4;">255, 228, 196</td>
        </tr>
        </table>
        <table class="named-colors-table by-shade" >
        <thead><tr><td colspan="3">Yellow colors</td></tr></thead>
        <tr>
        <td class="light-text" style="background-color:#808000;">olive</td>
        <td class="light-text" style="background-color:#808000;">#808000</td>
        <td class="light-text" style="background-color:#808000;">128, 128, 0</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#BDB76B;">dark_khaki</td>
        <td class="light-text" style="background-color:#BDB76B;">#BDB76B</td>
        <td class="light-text" style="background-color:#BDB76B;">189, 183, 107</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFD700;">gold</td>
        <td class="dark-text" style="background-color:#FFD700;">#FFD700</td>
        <td class="dark-text" style="background-color:#FFD700;">255, 215, 0</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#EEDD82;">light_goldenrod</td>
        <td class="dark-text" style="background-color:#EEDD82;">#EEDD82</td>
        <td class="dark-text" style="background-color:#EEDD82;">238, 221, 130</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#F0E68C;">khaki</td>
        <td class="dark-text" style="background-color:#F0E68C;">#F0E68C</td>
        <td class="dark-text" style="background-color:#F0E68C;">240, 230, 140</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#EEE8AA;">pale_goldenrod</td>
        <td class="dark-text" style="background-color:#EEE8AA;">#EEE8AA</td>
        <td class="dark-text" style="background-color:#EEE8AA;">238, 232, 170</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFFF00;">yellow</td>
        <td class="dark-text" style="background-color:#FFFF00;">#FFFF00</td>
        <td class="dark-text" style="background-color:#FFFF00;">255, 255, 0</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFFACD;">lemon_chiffon</td>
        <td class="dark-text" style="background-color:#FFFACD;">#FFFACD</td>
        <td class="dark-text" style="background-color:#FFFACD;">255, 250, 205</td>
        </tr>
        </table>
        <table class="named-colors-table by-shade" >
        <thead><tr><td colspan="3">Green colors</td></tr></thead>
        <tr>
        <td class="light-text" style="background-color:#006400;">dark_green</td>
        <td class="light-text" style="background-color:#006400;">#006400</td>
        <td class="light-text" style="background-color:#006400;">0, 100, 0</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#556B2F;">dark_olive_green</td>
        <td class="light-text" style="background-color:#556B2F;">#556B2F</td>
        <td class="light-text" style="background-color:#556B2F;">85, 107, 47</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#008000;">green</td>
        <td class="light-text" style="background-color:#008000;">#008000</td>
        <td class="light-text" style="background-color:#008000;">0, 128, 0</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#228B22;">forest_green</td>
        <td class="light-text" style="background-color:#228B22;">#228B22</td>
        <td class="light-text" style="background-color:#228B22;">34, 139, 34</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#2E8B57;">sea_green</td>
        <td class="light-text" style="background-color:#2E8B57;">#2E8B57</td>
        <td class="light-text" style="background-color:#2E8B57;">46, 139, 87</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#6B8E23;">olive_drab</td>
        <td class="light-text" style="background-color:#6B8E23;">#6B8E23</td>
        <td class="light-text" style="background-color:#6B8E23;">107, 142, 35</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#3CB371;">medium_sea_green</td>
        <td class="light-text" style="background-color:#3CB371;">#3CB371</td>
        <td class="light-text" style="background-color:#3CB371;">60, 179, 113</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#32CD32;">lime_green</td>
        <td class="light-text" style="background-color:#32CD32;">#32CD32</td>
        <td class="light-text" style="background-color:#32CD32;">50, 205, 50</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#8FBC8F;">dark_sea_green</td>
        <td class="light-text" style="background-color:#8FBC8F;">#8FBC8F</td>
        <td class="light-text" style="background-color:#8FBC8F;">143, 188, 143</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#66CDAA;">medium_aquamarine</td>
        <td class="light-text" style="background-color:#66CDAA;">#66CDAA</td>
        <td class="light-text" style="background-color:#66CDAA;">102, 205, 170</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#9ACD32;">yellow_green</td>
        <td class="light-text" style="background-color:#9ACD32;">#9ACD32</td>
        <td class="light-text" style="background-color:#9ACD32;">154, 205, 50</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#90EE90;">light_green</td>
        <td class="dark-text" style="background-color:#90EE90;">#90EE90</td>
        <td class="dark-text" style="background-color:#90EE90;">144, 238, 144</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#00FA9A;">medium_spring_green</td>
        <td class="dark-text" style="background-color:#00FA9A;">#00FA9A</td>
        <td class="dark-text" style="background-color:#00FA9A;">0, 250, 154</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#00FF00;">lime</td>
        <td class="dark-text" style="background-color:#00FF00;">#00FF00</td>
        <td class="dark-text" style="background-color:#00FF00;">0, 255, 0</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#00FF7F;">spring_green</td>
        <td class="dark-text" style="background-color:#00FF7F;">#00FF7F</td>
        <td class="dark-text" style="background-color:#00FF7F;">0, 255, 127</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#7CFC00;">lawn_green</td>
        <td class="dark-text" style="background-color:#7CFC00;">#7CFC00</td>
        <td class="dark-text" style="background-color:#7CFC00;">124, 252, 0</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#7FFF00;">chartreuse</td>
        <td class="dark-text" style="background-color:#7FFF00;">#7FFF00</td>
        <td class="dark-text" style="background-color:#7FFF00;">127, 255, 0</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#98FB98;">pale_green</td>
        <td class="dark-text" style="background-color:#98FB98;">#98FB98</td>
        <td class="dark-text" style="background-color:#98FB98;">152, 251, 152</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#7FFFD4;">aquamarine</td>
        <td class="dark-text" style="background-color:#7FFFD4;">#7FFFD4</td>
        <td class="dark-text" style="background-color:#7FFFD4;">127, 255, 212</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#ADFF2F;">green_yellow</td>
        <td class="dark-text" style="background-color:#ADFF2F;">#ADFF2F</td>
        <td class="dark-text" style="background-color:#ADFF2F;">173, 255, 47</td>
        </tr>
        </table>
        <table class="named-colors-table by-shade" >
        <thead><tr><td colspan="3">Cyan colors</td></tr></thead>
        <tr>
        <td class="light-text" style="background-color:#008080;">teal</td>
        <td class="light-text" style="background-color:#008080;">#008080</td>
        <td class="light-text" style="background-color:#008080;">0, 128, 128</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#008B8B;">dark_cyan</td>
        <td class="light-text" style="background-color:#008B8B;">#008B8B</td>
        <td class="light-text" style="background-color:#008B8B;">0, 139, 139</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#5F9EA0;">cadet_blue</td>
        <td class="light-text" style="background-color:#5F9EA0;">#5F9EA0</td>
        <td class="light-text" style="background-color:#5F9EA0;">95, 158, 160</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#20B2AA;">light_sea_green</td>
        <td class="light-text" style="background-color:#20B2AA;">#20B2AA</td>
        <td class="light-text" style="background-color:#20B2AA;">32, 178, 170</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#00CED1;">dark_turquoise</td>
        <td class="light-text" style="background-color:#00CED1;">#00CED1</td>
        <td class="light-text" style="background-color:#00CED1;">0, 206, 209</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#48D1CC;">medium_turquoise</td>
        <td class="light-text" style="background-color:#48D1CC;">#48D1CC</td>
        <td class="light-text" style="background-color:#48D1CC;">72, 209, 204</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#40E0D0;">turquoise</td>
        <td class="dark-text" style="background-color:#40E0D0;">#40E0D0</td>
        <td class="dark-text" style="background-color:#40E0D0;">64, 224, 208</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#B0E0E6;">powderblue</td>
        <td class="dark-text" style="background-color:#B0E0E6;">#B0E0E6</td>
        <td class="dark-text" style="background-color:#B0E0E6;">176, 224, 230</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#AFEEEE;">pale_turquoise</td>
        <td class="dark-text" style="background-color:#AFEEEE;">#AFEEEE</td>
        <td class="dark-text" style="background-color:#AFEEEE;">175, 238, 238</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#00FFFF;">aqua</td>
        <td class="dark-text" style="background-color:#00FFFF;">#00FFFF</td>
        <td class="dark-text" style="background-color:#00FFFF;">0, 255, 255</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#00FFFF;">cyan</td>
        <td class="dark-text" style="background-color:#00FFFF;">#00FFFF</td>
        <td class="dark-text" style="background-color:#00FFFF;">0, 255, 255</td>
        </tr>
        </table>
        <table class="named-colors-table by-shade" >
        <thead><tr><td colspan="3">Blue colors</td></tr></thead>
        <tr>
        <td class="light-text" style="background-color:#000080;">navy</td>
        <td class="light-text" style="background-color:#000080;">#000080</td>
        <td class="light-text" style="background-color:#000080;">0, 0, 128</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#191970;">midnight_blue</td>
        <td class="light-text" style="background-color:#191970;">#191970</td>
        <td class="light-text" style="background-color:#191970;">25, 25, 112</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#00008B;">dark_blue</td>
        <td class="light-text" style="background-color:#00008B;">#00008B</td>
        <td class="light-text" style="background-color:#00008B;">0, 0, 139</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#0000CD;">medium_blue</td>
        <td class="light-text" style="background-color:#0000CD;">#0000CD</td>
        <td class="light-text" style="background-color:#0000CD;">0, 0, 205</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#0000FF;">blue</td>
        <td class="light-text" style="background-color:#0000FF;">#0000FF</td>
        <td class="light-text" style="background-color:#0000FF;">0, 0, 255</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#4169E1;">royal_blue</td>
        <td class="light-text" style="background-color:#4169E1;">#4169E1</td>
        <td class="light-text" style="background-color:#4169E1;">65, 105, 225</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#4682B4;">steel_blue</td>
        <td class="light-text" style="background-color:#4682B4;">#4682B4</td>
        <td class="light-text" style="background-color:#4682B4;">70, 130, 180</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#118ED8;">pacific_blue</td>
        <td class="light-text" style="background-color:#118ED8;">#118ED8</td>
        <td class="light-text" style="background-color:#118ED8;">17, 142, 216</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#1E90FF;">dodger_blue</td>
        <td class="light-text" style="background-color:#1E90FF;">#1E90FF</td>
        <td class="light-text" style="background-color:#1E90FF;">30, 144, 255</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#6495ED;">cornflower_blue</td>
        <td class="light-text" style="background-color:#6495ED;">#6495ED</td>
        <td class="light-text" style="background-color:#6495ED;">100, 149, 237</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#00BFFF;">deep_sky_blue</td>
        <td class="light-text" style="background-color:#00BFFF;">#00BFFF</td>
        <td class="light-text" style="background-color:#00BFFF;">0, 191, 255</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#B0C4DE;">light_steel_blue</td>
        <td class="dark-text" style="background-color:#B0C4DE;">#B0C4DE</td>
        <td class="dark-text" style="background-color:#B0C4DE;">176, 196, 222</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#87CEEB;">sky_blue</td>
        <td class="dark-text" style="background-color:#87CEEB;">#87CEEB</td>
        <td class="dark-text" style="background-color:#87CEEB;">135, 206, 235</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#87CEFA;">light_sky_blue</td>
        <td class="dark-text" style="background-color:#87CEFA;">#87CEFA</td>
        <td class="dark-text" style="background-color:#87CEFA;">135, 206, 250</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#ADD8E6;">light_blue</td>
        <td class="dark-text" style="background-color:#ADD8E6;">#ADD8E6</td>
        <td class="dark-text" style="background-color:#ADD8E6;">173, 216, 230</td>
        </tr>
        </table>
        <table class="named-colors-table by-shade" >
        <thead><tr><td colspan="3">Violet colors</td></tr></thead>
        <tr>
        <td class="light-text" style="background-color:#4B0082;">indigo</td>
        <td class="light-text" style="background-color:#4B0082;">#4B0082</td>
        <td class="light-text" style="background-color:#4B0082;">75, 0, 130</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#483D8B;">dark_slate_blue</td>
        <td class="light-text" style="background-color:#483D8B;">#483D8B</td>
        <td class="light-text" style="background-color:#483D8B;">72, 61, 139</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#663399;">rebeccapurple</td>
        <td class="light-text" style="background-color:#663399;">#663399</td>
        <td class="light-text" style="background-color:#663399;">102, 51, 153</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#9400D3;">dark_violet</td>
        <td class="light-text" style="background-color:#9400D3;">#9400D3</td>
        <td class="light-text" style="background-color:#9400D3;">148, 0, 211</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#8A2BE2;">blue_violet</td>
        <td class="light-text" style="background-color:#8A2BE2;">#8A2BE2</td>
        <td class="light-text" style="background-color:#8A2BE2;">138, 43, 226</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#9932CC;">dark_orchid</td>
        <td class="light-text" style="background-color:#9932CC;">#9932CC</td>
        <td class="light-text" style="background-color:#9932CC;">153, 50, 204</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#6A5ACD;">slate_blue</td>
        <td class="light-text" style="background-color:#6A5ACD;">#6A5ACD</td>
        <td class="light-text" style="background-color:#6A5ACD;">106, 90, 205</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#7B68EE;">medium_slate_blue</td>
        <td class="light-text" style="background-color:#7B68EE;">#7B68EE</td>
        <td class="light-text" style="background-color:#7B68EE;">123, 104, 238</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#9370DB;">medium_purple</td>
        <td class="light-text" style="background-color:#9370DB;">#9370DB</td>
        <td class="light-text" style="background-color:#9370DB;">147, 112, 219</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#8470FF;">light_slate_blue</td>
        <td class="light-text" style="background-color:#8470FF;">#8470FF</td>
        <td class="light-text" style="background-color:#8470FF;">132, 112, 255</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#E6E6FA;">lavender</td>
        <td class="dark-text" style="background-color:#E6E6FA;">#E6E6FA</td>
        <td class="dark-text" style="background-color:#E6E6FA;">230, 230, 250</td>
        </tr>
        </table>
        <table class="named-colors-table by-shade" >
        <thead><tr><td colspan="3">Purple colors</td></tr></thead>
        <tr>
        <td class="light-text" style="background-color:#800080;">purple</td>
        <td class="light-text" style="background-color:#800080;">#800080</td>
        <td class="light-text" style="background-color:#800080;">128, 0, 128</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#8B008B;">dark_magenta</td>
        <td class="light-text" style="background-color:#8B008B;">#8B008B</td>
        <td class="light-text" style="background-color:#8B008B;">139, 0, 139</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#BA55D3;">medium_orchid</td>
        <td class="light-text" style="background-color:#BA55D3;">#BA55D3</td>
        <td class="light-text" style="background-color:#BA55D3;">186, 85, 211</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#DA70D6;">orchid</td>
        <td class="light-text" style="background-color:#DA70D6;">#DA70D6</td>
        <td class="light-text" style="background-color:#DA70D6;">218, 112, 214</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#FF00FF;">fuchsia</td>
        <td class="light-text" style="background-color:#FF00FF;">#FF00FF</td>
        <td class="light-text" style="background-color:#FF00FF;">255, 0, 255</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#FF00FF;">magenta</td>
        <td class="light-text" style="background-color:#FF00FF;">#FF00FF</td>
        <td class="light-text" style="background-color:#FF00FF;">255, 0, 255</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#EE82EE;">violet</td>
        <td class="light-text" style="background-color:#EE82EE;">#EE82EE</td>
        <td class="light-text" style="background-color:#EE82EE;">238, 130, 238</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#DDA0DD;">plum</td>
        <td class="dark-text" style="background-color:#DDA0DD;">#DDA0DD</td>
        <td class="dark-text" style="background-color:#DDA0DD;">221, 160, 221</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#D8BFD8;">thistle</td>
        <td class="dark-text" style="background-color:#D8BFD8;">#D8BFD8</td>
        <td class="dark-text" style="background-color:#D8BFD8;">216, 191, 216</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFD2FF;">light_magenta</td>
        <td class="dark-text" style="background-color:#FFD2FF;">#FFD2FF</td>
        <td class="dark-text" style="background-color:#FFD2FF;">255, 210, 255</td>
        </tr>
        </table>
        <table class="named-colors-table by-shade" >
        <thead><tr><td colspan="3">Rose colors</td></tr></thead>
        <tr>
        <td class="light-text" style="background-color:#C71585;">medium_violet_red</td>
        <td class="light-text" style="background-color:#C71585;">#C71585</td>
        <td class="light-text" style="background-color:#C71585;">199, 21, 133</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#DC143C;">crimson</td>
        <td class="light-text" style="background-color:#DC143C;">#DC143C</td>
        <td class="light-text" style="background-color:#DC143C;">220, 20, 60</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#D02090;">violet_red</td>
        <td class="light-text" style="background-color:#D02090;">#D02090</td>
        <td class="light-text" style="background-color:#D02090;">208, 32, 144</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#FF1493;">deep_pink</td>
        <td class="light-text" style="background-color:#FF1493;">#FF1493</td>
        <td class="light-text" style="background-color:#FF1493;">255, 20, 147</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#DB7093;">pale_violet_red</td>
        <td class="light-text" style="background-color:#DB7093;">#DB7093</td>
        <td class="light-text" style="background-color:#DB7093;">219, 112, 147</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#FF69B4;">hot_pink</td>
        <td class="light-text" style="background-color:#FF69B4;">#FF69B4</td>
        <td class="light-text" style="background-color:#FF69B4;">255, 105, 180</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFB6C1;">light_pink</td>
        <td class="dark-text" style="background-color:#FFB6C1;">#FFB6C1</td>
        <td class="dark-text" style="background-color:#FFB6C1;">255, 182, 193</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFC0CB;">pink</td>
        <td class="dark-text" style="background-color:#FFC0CB;">#FFC0CB</td>
        <td class="dark-text" style="background-color:#FFC0CB;">255, 192, 203</td>
        </tr>
        </table>
        <table class="named-colors-table by-shade" >
        <thead><tr><td colspan="3">Very light colors</td></tr></thead>
        <tr>
        <td class="dark-text" style="background-color:#FFFFFF;">white</td>
        <td class="dark-text" style="background-color:#FFFFFF;">#FFFFFF</td>
        <td class="dark-text" style="background-color:#FFFFFF;">255, 255, 255</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFFAFA;">snow</td>
        <td class="dark-text" style="background-color:#FFFAFA;">#FFFAFA</td>
        <td class="dark-text" style="background-color:#FFFAFA;">255, 250, 250</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#F5F5F5;">white_smoke</td>
        <td class="dark-text" style="background-color:#F5F5F5;">#F5F5F5</td>
        <td class="dark-text" style="background-color:#F5F5F5;">245, 245, 245</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFE4E1;">misty_rose</td>
        <td class="dark-text" style="background-color:#FFE4E1;">#FFE4E1</td>
        <td class="dark-text" style="background-color:#FFE4E1;">255, 228, 225</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFF5EE;">sea_shell</td>
        <td class="dark-text" style="background-color:#FFF5EE;">#FFF5EE</td>
        <td class="dark-text" style="background-color:#FFF5EE;">255, 245, 238</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FAF0E6;">linen</td>
        <td class="dark-text" style="background-color:#FAF0E6;">#FAF0E6</td>
        <td class="dark-text" style="background-color:#FAF0E6;">250, 240, 230</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FAEBD7;">antique_white</td>
        <td class="dark-text" style="background-color:#FAEBD7;">#FAEBD7</td>
        <td class="dark-text" style="background-color:#FAEBD7;">250, 235, 215</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFEBCD;">blanched_almond</td>
        <td class="dark-text" style="background-color:#FFEBCD;">#FFEBCD</td>
        <td class="dark-text" style="background-color:#FFEBCD;">255, 235, 205</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFEFD5;">papaya_whip</td>
        <td class="dark-text" style="background-color:#FFEFD5;">#FFEFD5</td>
        <td class="dark-text" style="background-color:#FFEFD5;">255, 239, 213</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFFAF0;">floral_white</td>
        <td class="dark-text" style="background-color:#FFFAF0;">#FFFAF0</td>
        <td class="dark-text" style="background-color:#FFFAF0;">255, 250, 240</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FDF5E6;">old_lace</td>
        <td class="dark-text" style="background-color:#FDF5E6;">#FDF5E6</td>
        <td class="dark-text" style="background-color:#FDF5E6;">253, 245, 230</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFF8DC;">cornsilk</td>
        <td class="dark-text" style="background-color:#FFF8DC;">#FFF8DC</td>
        <td class="dark-text" style="background-color:#FFF8DC;">255, 248, 220</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFFFF0;">ivory</td>
        <td class="dark-text" style="background-color:#FFFFF0;">#FFFFF0</td>
        <td class="dark-text" style="background-color:#FFFFF0;">255, 255, 240</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFFFE0;">light_yellow</td>
        <td class="dark-text" style="background-color:#FFFFE0;">#FFFFE0</td>
        <td class="dark-text" style="background-color:#FFFFE0;">255, 255, 224</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FAFAD2;">light_goldenrod_yellow</td>
        <td class="dark-text" style="background-color:#FAFAD2;">#FAFAD2</td>
        <td class="dark-text" style="background-color:#FAFAD2;">250, 250, 210</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#F5F5DC;">beige</td>
        <td class="dark-text" style="background-color:#F5F5DC;">#F5F5DC</td>
        <td class="dark-text" style="background-color:#F5F5DC;">245, 245, 220</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#F0FFF0;">honey_dew</td>
        <td class="dark-text" style="background-color:#F0FFF0;">#F0FFF0</td>
        <td class="dark-text" style="background-color:#F0FFF0;">240, 255, 240</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#F5FFFA;">mint_cream</td>
        <td class="dark-text" style="background-color:#F5FFFA;">#F5FFFA</td>
        <td class="dark-text" style="background-color:#F5FFFA;">245, 255, 250</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#F0FFFF;">azure</td>
        <td class="dark-text" style="background-color:#F0FFFF;">#F0FFFF</td>
        <td class="dark-text" style="background-color:#F0FFFF;">240, 255, 255</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#E0FFFF;">light_cyan</td>
        <td class="dark-text" style="background-color:#E0FFFF;">#E0FFFF</td>
        <td class="dark-text" style="background-color:#E0FFFF;">224, 255, 255</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#F0F8FF;">alice_blue</td>
        <td class="dark-text" style="background-color:#F0F8FF;">#F0F8FF</td>
        <td class="dark-text" style="background-color:#F0F8FF;">240, 248, 255</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#F8F8FF;">ghost_white</td>
        <td class="dark-text" style="background-color:#F8F8FF;">#F8F8FF</td>
        <td class="dark-text" style="background-color:#F8F8FF;">248, 248, 255</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#FFF0F5;">lavender_blush</td>
        <td class="dark-text" style="background-color:#FFF0F5;">#FFF0F5</td>
        <td class="dark-text" style="background-color:#FFF0F5;">255, 240, 245</td>
        </tr>
        </table>
        <table class="named-colors-table by-shade" >
        <thead><tr><td colspan="3">Gray colors</td></tr></thead>
        <tr>
        <td class="light-text" style="background-color:#000000;">black</td>
        <td class="light-text" style="background-color:#000000;">#000000</td>
        <td class="light-text" style="background-color:#000000;">0, 0, 0</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#1A1A1A;">gray10</td>
        <td class="light-text" style="background-color:#1A1A1A;">#1A1A1A</td>
        <td class="light-text" style="background-color:#1A1A1A;">26, 26, 26</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#333333;">gray20</td>
        <td class="light-text" style="background-color:#333333;">#333333</td>
        <td class="light-text" style="background-color:#333333;">51, 51, 51</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#2F4F4F;">dark_slate_gray</td>
        <td class="light-text" style="background-color:#2F4F4F;">#2F4F4F</td>
        <td class="light-text" style="background-color:#2F4F4F;">47, 79, 79</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#4D4D4D;">gray30</td>
        <td class="light-text" style="background-color:#4D4D4D;">#4D4D4D</td>
        <td class="light-text" style="background-color:#4D4D4D;">77, 77, 77</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#555555;">dark_gray</td>
        <td class="light-text" style="background-color:#555555;">#555555</td>
        <td class="light-text" style="background-color:#555555;">85, 85, 85</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#666666;">gray40</td>
        <td class="light-text" style="background-color:#666666;">#666666</td>
        <td class="light-text" style="background-color:#666666;">102, 102, 102</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#696969;">dim_gray</td>
        <td class="light-text" style="background-color:#696969;">#696969</td>
        <td class="light-text" style="background-color:#696969;">105, 105, 105</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#708090;">slate_gray</td>
        <td class="light-text" style="background-color:#708090;">#708090</td>
        <td class="light-text" style="background-color:#708090;">112, 128, 144</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#808080;">gray</td>
        <td class="light-text" style="background-color:#808080;">#808080</td>
        <td class="light-text" style="background-color:#808080;">128, 128, 128</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#808080;">gray50</td>
        <td class="light-text" style="background-color:#808080;">#808080</td>
        <td class="light-text" style="background-color:#808080;">128, 128, 128</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#778899;">light_slate_gray</td>
        <td class="light-text" style="background-color:#778899;">#778899</td>
        <td class="light-text" style="background-color:#778899;">119, 136, 153</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#999999;">gray60</td>
        <td class="light-text" style="background-color:#999999;">#999999</td>
        <td class="light-text" style="background-color:#999999;">153, 153, 153</td>
        </tr>
        <tr>
        <td class="light-text" style="background-color:#B3B3B3;">gray70</td>
        <td class="light-text" style="background-color:#B3B3B3;">#B3B3B3</td>
        <td class="light-text" style="background-color:#B3B3B3;">179, 179, 179</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#C0C0C0;">silver</td>
        <td class="dark-text" style="background-color:#C0C0C0;">#C0C0C0</td>
        <td class="dark-text" style="background-color:#C0C0C0;">192, 192, 192</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#CCCCCC;">gray80</td>
        <td class="dark-text" style="background-color:#CCCCCC;">#CCCCCC</td>
        <td class="dark-text" style="background-color:#CCCCCC;">204, 204, 204</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#D3D3D3;">light_gray</td>
        <td class="dark-text" style="background-color:#D3D3D3;">#D3D3D3</td>
        <td class="dark-text" style="background-color:#D3D3D3;">211, 211, 211</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#DCDCDC;">gainsboro</td>
        <td class="dark-text" style="background-color:#DCDCDC;">#DCDCDC</td>
        <td class="dark-text" style="background-color:#DCDCDC;">220, 220, 220</td>
        </tr>
        <tr>
        <td class="dark-text" style="background-color:#E6E6E6;">gray90</td>
        <td class="dark-text" style="background-color:#E6E6E6;">#E6E6E6</td>
        <td class="dark-text" style="background-color:#E6E6E6;">230, 230, 230</td>
        </tr>
        </table>
    </tab>
    <tab id="by-name" title="By name">
		<table class="named-colors-table by-name" >
		<tr>
		<td class="dark-text" style="background-color:#F0F8FF;">alice_blue</td>
		<td class="dark-text" style="background-color:#F0F8FF;">#F0F8FF</td>
		<td class="dark-text" style="background-color:#F0F8FF;">240, 248, 255</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FAEBD7;">antique_white</td>
		<td class="dark-text" style="background-color:#FAEBD7;">#FAEBD7</td>
		<td class="dark-text" style="background-color:#FAEBD7;">250, 235, 215</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#00FFFF;">aqua</td>
		<td class="dark-text" style="background-color:#00FFFF;">#00FFFF</td>
		<td class="dark-text" style="background-color:#00FFFF;">0, 255, 255</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#7FFFD4;">aquamarine</td>
		<td class="dark-text" style="background-color:#7FFFD4;">#7FFFD4</td>
		<td class="dark-text" style="background-color:#7FFFD4;">127, 255, 212</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#F0FFFF;">azure</td>
		<td class="dark-text" style="background-color:#F0FFFF;">#F0FFFF</td>
		<td class="dark-text" style="background-color:#F0FFFF;">240, 255, 255</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#F5F5DC;">beige</td>
		<td class="dark-text" style="background-color:#F5F5DC;">#F5F5DC</td>
		<td class="dark-text" style="background-color:#F5F5DC;">245, 245, 220</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFE4C4;">bisque</td>
		<td class="dark-text" style="background-color:#FFE4C4;">#FFE4C4</td>
		<td class="dark-text" style="background-color:#FFE4C4;">255, 228, 196</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#000000;">black</td>
		<td class="light-text" style="background-color:#000000;">#000000</td>
		<td class="light-text" style="background-color:#000000;">0, 0, 0</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFEBCD;">blanched_almond</td>
		<td class="dark-text" style="background-color:#FFEBCD;">#FFEBCD</td>
		<td class="dark-text" style="background-color:#FFEBCD;">255, 235, 205</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#0000FF;">blue</td>
		<td class="light-text" style="background-color:#0000FF;">#0000FF</td>
		<td class="light-text" style="background-color:#0000FF;">0, 0, 255</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#8A2BE2;">blue_violet</td>
		<td class="light-text" style="background-color:#8A2BE2;">#8A2BE2</td>
		<td class="light-text" style="background-color:#8A2BE2;">138, 43, 226</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#A52A2A;">brown</td>
		<td class="light-text" style="background-color:#A52A2A;">#A52A2A</td>
		<td class="light-text" style="background-color:#A52A2A;">165, 42, 42</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#DEB887;">burly_wood</td>
		<td class="dark-text" style="background-color:#DEB887;">#DEB887</td>
		<td class="dark-text" style="background-color:#DEB887;">222, 184, 135</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#5F9EA0;">cadet_blue</td>
		<td class="light-text" style="background-color:#5F9EA0;">#5F9EA0</td>
		<td class="light-text" style="background-color:#5F9EA0;">95, 158, 160</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#7FFF00;">chartreuse</td>
		<td class="dark-text" style="background-color:#7FFF00;">#7FFF00</td>
		<td class="dark-text" style="background-color:#7FFF00;">127, 255, 0</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#D2691E;">chocolate</td>
		<td class="light-text" style="background-color:#D2691E;">#D2691E</td>
		<td class="light-text" style="background-color:#D2691E;">210, 105, 30</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#FF7F50;">coral</td>
		<td class="light-text" style="background-color:#FF7F50;">#FF7F50</td>
		<td class="light-text" style="background-color:#FF7F50;">255, 127, 80</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#6495ED;">cornflower_blue</td>
		<td class="light-text" style="background-color:#6495ED;">#6495ED</td>
		<td class="light-text" style="background-color:#6495ED;">100, 149, 237</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFF8DC;">cornsilk</td>
		<td class="dark-text" style="background-color:#FFF8DC;">#FFF8DC</td>
		<td class="dark-text" style="background-color:#FFF8DC;">255, 248, 220</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#DC143C;">crimson</td>
		<td class="light-text" style="background-color:#DC143C;">#DC143C</td>
		<td class="light-text" style="background-color:#DC143C;">220, 20, 60</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#00FFFF;">cyan</td>
		<td class="dark-text" style="background-color:#00FFFF;">#00FFFF</td>
		<td class="dark-text" style="background-color:#00FFFF;">0, 255, 255</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#00008B;">dark_blue</td>
		<td class="light-text" style="background-color:#00008B;">#00008B</td>
		<td class="light-text" style="background-color:#00008B;">0, 0, 139</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#008B8B;">dark_cyan</td>
		<td class="light-text" style="background-color:#008B8B;">#008B8B</td>
		<td class="light-text" style="background-color:#008B8B;">0, 139, 139</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#B8860B;">dark_goldenrod</td>
		<td class="light-text" style="background-color:#B8860B;">#B8860B</td>
		<td class="light-text" style="background-color:#B8860B;">184, 134, 11</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#555555;">dark_gray</td>
		<td class="light-text" style="background-color:#555555;">#555555</td>
		<td class="light-text" style="background-color:#555555;">85, 85, 85</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#006400;">dark_green</td>
		<td class="light-text" style="background-color:#006400;">#006400</td>
		<td class="light-text" style="background-color:#006400;">0, 100, 0</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#BDB76B;">dark_khaki</td>
		<td class="light-text" style="background-color:#BDB76B;">#BDB76B</td>
		<td class="light-text" style="background-color:#BDB76B;">189, 183, 107</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#8B008B;">dark_magenta</td>
		<td class="light-text" style="background-color:#8B008B;">#8B008B</td>
		<td class="light-text" style="background-color:#8B008B;">139, 0, 139</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#556B2F;">dark_olive_green</td>
		<td class="light-text" style="background-color:#556B2F;">#556B2F</td>
		<td class="light-text" style="background-color:#556B2F;">85, 107, 47</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#FF8C00;">dark_orange</td>
		<td class="light-text" style="background-color:#FF8C00;">#FF8C00</td>
		<td class="light-text" style="background-color:#FF8C00;">255, 140, 0</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#9932CC;">dark_orchid</td>
		<td class="light-text" style="background-color:#9932CC;">#9932CC</td>
		<td class="light-text" style="background-color:#9932CC;">153, 50, 204</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#8B0000;">dark_red</td>
		<td class="light-text" style="background-color:#8B0000;">#8B0000</td>
		<td class="light-text" style="background-color:#8B0000;">139, 0, 0</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#E9967A;">dark_salmon</td>
		<td class="light-text" style="background-color:#E9967A;">#E9967A</td>
		<td class="light-text" style="background-color:#E9967A;">233, 150, 122</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#8FBC8F;">dark_sea_green</td>
		<td class="light-text" style="background-color:#8FBC8F;">#8FBC8F</td>
		<td class="light-text" style="background-color:#8FBC8F;">143, 188, 143</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#483D8B;">dark_slate_blue</td>
		<td class="light-text" style="background-color:#483D8B;">#483D8B</td>
		<td class="light-text" style="background-color:#483D8B;">72, 61, 139</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#2F4F4F;">dark_slate_gray</td>
		<td class="light-text" style="background-color:#2F4F4F;">#2F4F4F</td>
		<td class="light-text" style="background-color:#2F4F4F;">47, 79, 79</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#00CED1;">dark_turquoise</td>
		<td class="light-text" style="background-color:#00CED1;">#00CED1</td>
		<td class="light-text" style="background-color:#00CED1;">0, 206, 209</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#9400D3;">dark_violet</td>
		<td class="light-text" style="background-color:#9400D3;">#9400D3</td>
		<td class="light-text" style="background-color:#9400D3;">148, 0, 211</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#FF1493;">deep_pink</td>
		<td class="light-text" style="background-color:#FF1493;">#FF1493</td>
		<td class="light-text" style="background-color:#FF1493;">255, 20, 147</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#00BFFF;">deep_sky_blue</td>
		<td class="light-text" style="background-color:#00BFFF;">#00BFFF</td>
		<td class="light-text" style="background-color:#00BFFF;">0, 191, 255</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#696969;">dim_gray</td>
		<td class="light-text" style="background-color:#696969;">#696969</td>
		<td class="light-text" style="background-color:#696969;">105, 105, 105</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#1E90FF;">dodger_blue</td>
		<td class="light-text" style="background-color:#1E90FF;">#1E90FF</td>
		<td class="light-text" style="background-color:#1E90FF;">30, 144, 255</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#B22222;">firebrick</td>
		<td class="light-text" style="background-color:#B22222;">#B22222</td>
		<td class="light-text" style="background-color:#B22222;">178, 34, 34</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFFAF0;">floral_white</td>
		<td class="dark-text" style="background-color:#FFFAF0;">#FFFAF0</td>
		<td class="dark-text" style="background-color:#FFFAF0;">255, 250, 240</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#228B22;">forest_green</td>
		<td class="light-text" style="background-color:#228B22;">#228B22</td>
		<td class="light-text" style="background-color:#228B22;">34, 139, 34</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#FF00FF;">fuchsia</td>
		<td class="light-text" style="background-color:#FF00FF;">#FF00FF</td>
		<td class="light-text" style="background-color:#FF00FF;">255, 0, 255</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#DCDCDC;">gainsboro</td>
		<td class="dark-text" style="background-color:#DCDCDC;">#DCDCDC</td>
		<td class="dark-text" style="background-color:#DCDCDC;">220, 220, 220</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#F8F8FF;">ghost_white</td>
		<td class="dark-text" style="background-color:#F8F8FF;">#F8F8FF</td>
		<td class="dark-text" style="background-color:#F8F8FF;">248, 248, 255</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFD700;">gold</td>
		<td class="dark-text" style="background-color:#FFD700;">#FFD700</td>
		<td class="dark-text" style="background-color:#FFD700;">255, 215, 0</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#DAA520;">goldenrod</td>
		<td class="light-text" style="background-color:#DAA520;">#DAA520</td>
		<td class="light-text" style="background-color:#DAA520;">218, 165, 32</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#808080;">gray</td>
		<td class="light-text" style="background-color:#808080;">#808080</td>
		<td class="light-text" style="background-color:#808080;">128, 128, 128</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#000000;">gray0</td>
		<td class="light-text" style="background-color:#000000;">#000000</td>
		<td class="light-text" style="background-color:#000000;">0, 0, 0</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#030303;">gray1</td>
		<td class="light-text" style="background-color:#030303;">#030303</td>
		<td class="light-text" style="background-color:#030303;">3, 3, 3</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#050505;">gray2</td>
		<td class="light-text" style="background-color:#050505;">#050505</td>
		<td class="light-text" style="background-color:#050505;">5, 5, 5</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#080808;">gray3</td>
		<td class="light-text" style="background-color:#080808;">#080808</td>
		<td class="light-text" style="background-color:#080808;">8, 8, 8</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#0A0A0A;">gray4</td>
		<td class="light-text" style="background-color:#0A0A0A;">#0A0A0A</td>
		<td class="light-text" style="background-color:#0A0A0A;">10, 10, 10</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#0D0D0D;">gray5</td>
		<td class="light-text" style="background-color:#0D0D0D;">#0D0D0D</td>
		<td class="light-text" style="background-color:#0D0D0D;">13, 13, 13</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#0F0F0F;">gray6</td>
		<td class="light-text" style="background-color:#0F0F0F;">#0F0F0F</td>
		<td class="light-text" style="background-color:#0F0F0F;">15, 15, 15</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#121212;">gray7</td>
		<td class="light-text" style="background-color:#121212;">#121212</td>
		<td class="light-text" style="background-color:#121212;">18, 18, 18</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#141414;">gray8</td>
		<td class="light-text" style="background-color:#141414;">#141414</td>
		<td class="light-text" style="background-color:#141414;">20, 20, 20</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#171717;">gray9</td>
		<td class="light-text" style="background-color:#171717;">#171717</td>
		<td class="light-text" style="background-color:#171717;">23, 23, 23</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#1A1A1A;">gray10</td>
		<td class="light-text" style="background-color:#1A1A1A;">#1A1A1A</td>
		<td class="light-text" style="background-color:#1A1A1A;">26, 26, 26</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#1C1C1C;">gray11</td>
		<td class="light-text" style="background-color:#1C1C1C;">#1C1C1C</td>
		<td class="light-text" style="background-color:#1C1C1C;">28, 28, 28</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#1F1F1F;">gray12</td>
		<td class="light-text" style="background-color:#1F1F1F;">#1F1F1F</td>
		<td class="light-text" style="background-color:#1F1F1F;">31, 31, 31</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#212121;">gray13</td>
		<td class="light-text" style="background-color:#212121;">#212121</td>
		<td class="light-text" style="background-color:#212121;">33, 33, 33</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#242424;">gray14</td>
		<td class="light-text" style="background-color:#242424;">#242424</td>
		<td class="light-text" style="background-color:#242424;">36, 36, 36</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#262626;">gray15</td>
		<td class="light-text" style="background-color:#262626;">#262626</td>
		<td class="light-text" style="background-color:#262626;">38, 38, 38</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#292929;">gray16</td>
		<td class="light-text" style="background-color:#292929;">#292929</td>
		<td class="light-text" style="background-color:#292929;">41, 41, 41</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#2B2B2B;">gray17</td>
		<td class="light-text" style="background-color:#2B2B2B;">#2B2B2B</td>
		<td class="light-text" style="background-color:#2B2B2B;">43, 43, 43</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#2E2E2E;">gray18</td>
		<td class="light-text" style="background-color:#2E2E2E;">#2E2E2E</td>
		<td class="light-text" style="background-color:#2E2E2E;">46, 46, 46</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#303030;">gray19</td>
		<td class="light-text" style="background-color:#303030;">#303030</td>
		<td class="light-text" style="background-color:#303030;">48, 48, 48</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#333333;">gray20</td>
		<td class="light-text" style="background-color:#333333;">#333333</td>
		<td class="light-text" style="background-color:#333333;">51, 51, 51</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#363636;">gray21</td>
		<td class="light-text" style="background-color:#363636;">#363636</td>
		<td class="light-text" style="background-color:#363636;">54, 54, 54</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#383838;">gray22</td>
		<td class="light-text" style="background-color:#383838;">#383838</td>
		<td class="light-text" style="background-color:#383838;">56, 56, 56</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#3B3B3B;">gray23</td>
		<td class="light-text" style="background-color:#3B3B3B;">#3B3B3B</td>
		<td class="light-text" style="background-color:#3B3B3B;">59, 59, 59</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#3D3D3D;">gray24</td>
		<td class="light-text" style="background-color:#3D3D3D;">#3D3D3D</td>
		<td class="light-text" style="background-color:#3D3D3D;">61, 61, 61</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#404040;">gray25</td>
		<td class="light-text" style="background-color:#404040;">#404040</td>
		<td class="light-text" style="background-color:#404040;">64, 64, 64</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#424242;">gray26</td>
		<td class="light-text" style="background-color:#424242;">#424242</td>
		<td class="light-text" style="background-color:#424242;">66, 66, 66</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#454545;">gray27</td>
		<td class="light-text" style="background-color:#454545;">#454545</td>
		<td class="light-text" style="background-color:#454545;">69, 69, 69</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#474747;">gray28</td>
		<td class="light-text" style="background-color:#474747;">#474747</td>
		<td class="light-text" style="background-color:#474747;">71, 71, 71</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#4A4A4A;">gray29</td>
		<td class="light-text" style="background-color:#4A4A4A;">#4A4A4A</td>
		<td class="light-text" style="background-color:#4A4A4A;">74, 74, 74</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#4D4D4D;">gray30</td>
		<td class="light-text" style="background-color:#4D4D4D;">#4D4D4D</td>
		<td class="light-text" style="background-color:#4D4D4D;">77, 77, 77</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#4F4F4F;">gray31</td>
		<td class="light-text" style="background-color:#4F4F4F;">#4F4F4F</td>
		<td class="light-text" style="background-color:#4F4F4F;">79, 79, 79</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#525252;">gray32</td>
		<td class="light-text" style="background-color:#525252;">#525252</td>
		<td class="light-text" style="background-color:#525252;">82, 82, 82</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#545454;">gray33</td>
		<td class="light-text" style="background-color:#545454;">#545454</td>
		<td class="light-text" style="background-color:#545454;">84, 84, 84</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#575757;">gray34</td>
		<td class="light-text" style="background-color:#575757;">#575757</td>
		<td class="light-text" style="background-color:#575757;">87, 87, 87</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#595959;">gray35</td>
		<td class="light-text" style="background-color:#595959;">#595959</td>
		<td class="light-text" style="background-color:#595959;">89, 89, 89</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#5C5C5C;">gray36</td>
		<td class="light-text" style="background-color:#5C5C5C;">#5C5C5C</td>
		<td class="light-text" style="background-color:#5C5C5C;">92, 92, 92</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#5E5E5E;">gray37</td>
		<td class="light-text" style="background-color:#5E5E5E;">#5E5E5E</td>
		<td class="light-text" style="background-color:#5E5E5E;">94, 94, 94</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#616161;">gray38</td>
		<td class="light-text" style="background-color:#616161;">#616161</td>
		<td class="light-text" style="background-color:#616161;">97, 97, 97</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#636363;">gray39</td>
		<td class="light-text" style="background-color:#636363;">#636363</td>
		<td class="light-text" style="background-color:#636363;">99, 99, 99</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#666666;">gray40</td>
		<td class="light-text" style="background-color:#666666;">#666666</td>
		<td class="light-text" style="background-color:#666666;">102, 102, 102</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#696969;">gray41</td>
		<td class="light-text" style="background-color:#696969;">#696969</td>
		<td class="light-text" style="background-color:#696969;">105, 105, 105</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#6B6B6B;">gray42</td>
		<td class="light-text" style="background-color:#6B6B6B;">#6B6B6B</td>
		<td class="light-text" style="background-color:#6B6B6B;">107, 107, 107</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#6E6E6E;">gray43</td>
		<td class="light-text" style="background-color:#6E6E6E;">#6E6E6E</td>
		<td class="light-text" style="background-color:#6E6E6E;">110, 110, 110</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#707070;">gray44</td>
		<td class="light-text" style="background-color:#707070;">#707070</td>
		<td class="light-text" style="background-color:#707070;">112, 112, 112</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#737373;">gray45</td>
		<td class="light-text" style="background-color:#737373;">#737373</td>
		<td class="light-text" style="background-color:#737373;">115, 115, 115</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#757575;">gray46</td>
		<td class="light-text" style="background-color:#757575;">#757575</td>
		<td class="light-text" style="background-color:#757575;">117, 117, 117</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#787878;">gray47</td>
		<td class="light-text" style="background-color:#787878;">#787878</td>
		<td class="light-text" style="background-color:#787878;">120, 120, 120</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#7A7A7A;">gray48</td>
		<td class="light-text" style="background-color:#7A7A7A;">#7A7A7A</td>
		<td class="light-text" style="background-color:#7A7A7A;">122, 122, 122</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#7D7D7D;">gray49</td>
		<td class="light-text" style="background-color:#7D7D7D;">#7D7D7D</td>
		<td class="light-text" style="background-color:#7D7D7D;">125, 125, 125</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#808080;">gray50</td>
		<td class="light-text" style="background-color:#808080;">#808080</td>
		<td class="light-text" style="background-color:#808080;">128, 128, 128</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#828282;">gray51</td>
		<td class="light-text" style="background-color:#828282;">#828282</td>
		<td class="light-text" style="background-color:#828282;">130, 130, 130</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#858585;">gray52</td>
		<td class="light-text" style="background-color:#858585;">#858585</td>
		<td class="light-text" style="background-color:#858585;">133, 133, 133</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#878787;">gray53</td>
		<td class="light-text" style="background-color:#878787;">#878787</td>
		<td class="light-text" style="background-color:#878787;">135, 135, 135</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#8A8A8A;">gray54</td>
		<td class="light-text" style="background-color:#8A8A8A;">#8A8A8A</td>
		<td class="light-text" style="background-color:#8A8A8A;">138, 138, 138</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#8C8C8C;">gray55</td>
		<td class="light-text" style="background-color:#8C8C8C;">#8C8C8C</td>
		<td class="light-text" style="background-color:#8C8C8C;">140, 140, 140</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#8F8F8F;">gray56</td>
		<td class="light-text" style="background-color:#8F8F8F;">#8F8F8F</td>
		<td class="light-text" style="background-color:#8F8F8F;">143, 143, 143</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#919191;">gray57</td>
		<td class="light-text" style="background-color:#919191;">#919191</td>
		<td class="light-text" style="background-color:#919191;">145, 145, 145</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#949494;">gray58</td>
		<td class="light-text" style="background-color:#949494;">#949494</td>
		<td class="light-text" style="background-color:#949494;">148, 148, 148</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#969696;">gray59</td>
		<td class="light-text" style="background-color:#969696;">#969696</td>
		<td class="light-text" style="background-color:#969696;">150, 150, 150</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#999999;">gray60</td>
		<td class="light-text" style="background-color:#999999;">#999999</td>
		<td class="light-text" style="background-color:#999999;">153, 153, 153</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#9C9C9C;">gray61</td>
		<td class="light-text" style="background-color:#9C9C9C;">#9C9C9C</td>
		<td class="light-text" style="background-color:#9C9C9C;">156, 156, 156</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#9E9E9E;">gray62</td>
		<td class="light-text" style="background-color:#9E9E9E;">#9E9E9E</td>
		<td class="light-text" style="background-color:#9E9E9E;">158, 158, 158</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#A1A1A1;">gray63</td>
		<td class="light-text" style="background-color:#A1A1A1;">#A1A1A1</td>
		<td class="light-text" style="background-color:#A1A1A1;">161, 161, 161</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#A3A3A3;">gray64</td>
		<td class="light-text" style="background-color:#A3A3A3;">#A3A3A3</td>
		<td class="light-text" style="background-color:#A3A3A3;">163, 163, 163</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#A6A6A6;">gray65</td>
		<td class="light-text" style="background-color:#A6A6A6;">#A6A6A6</td>
		<td class="light-text" style="background-color:#A6A6A6;">166, 166, 166</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#A8A8A8;">gray66</td>
		<td class="light-text" style="background-color:#A8A8A8;">#A8A8A8</td>
		<td class="light-text" style="background-color:#A8A8A8;">168, 168, 168</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#ABABAB;">gray67</td>
		<td class="light-text" style="background-color:#ABABAB;">#ABABAB</td>
		<td class="light-text" style="background-color:#ABABAB;">171, 171, 171</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#ADADAD;">gray68</td>
		<td class="light-text" style="background-color:#ADADAD;">#ADADAD</td>
		<td class="light-text" style="background-color:#ADADAD;">173, 173, 173</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#B0B0B0;">gray69</td>
		<td class="light-text" style="background-color:#B0B0B0;">#B0B0B0</td>
		<td class="light-text" style="background-color:#B0B0B0;">176, 176, 176</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#B3B3B3;">gray70</td>
		<td class="light-text" style="background-color:#B3B3B3;">#B3B3B3</td>
		<td class="light-text" style="background-color:#B3B3B3;">179, 179, 179</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#B5B5B5;">gray71</td>
		<td class="light-text" style="background-color:#B5B5B5;">#B5B5B5</td>
		<td class="light-text" style="background-color:#B5B5B5;">181, 181, 181</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#B8B8B8;">gray72</td>
		<td class="light-text" style="background-color:#B8B8B8;">#B8B8B8</td>
		<td class="light-text" style="background-color:#B8B8B8;">184, 184, 184</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#BABABA;">gray73</td>
		<td class="light-text" style="background-color:#BABABA;">#BABABA</td>
		<td class="light-text" style="background-color:#BABABA;">186, 186, 186</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#BDBDBD;">gray74</td>
		<td class="dark-text" style="background-color:#BDBDBD;">#BDBDBD</td>
		<td class="dark-text" style="background-color:#BDBDBD;">189, 189, 189</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#BFBFBF;">gray75</td>
		<td class="dark-text" style="background-color:#BFBFBF;">#BFBFBF</td>
		<td class="dark-text" style="background-color:#BFBFBF;">191, 191, 191</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#C2C2C2;">gray76</td>
		<td class="dark-text" style="background-color:#C2C2C2;">#C2C2C2</td>
		<td class="dark-text" style="background-color:#C2C2C2;">194, 194, 194</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#C4C4C4;">gray77</td>
		<td class="dark-text" style="background-color:#C4C4C4;">#C4C4C4</td>
		<td class="dark-text" style="background-color:#C4C4C4;">196, 196, 196</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#C7C7C7;">gray78</td>
		<td class="dark-text" style="background-color:#C7C7C7;">#C7C7C7</td>
		<td class="dark-text" style="background-color:#C7C7C7;">199, 199, 199</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#C9C9C9;">gray79</td>
		<td class="dark-text" style="background-color:#C9C9C9;">#C9C9C9</td>
		<td class="dark-text" style="background-color:#C9C9C9;">201, 201, 201</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#CCCCCC;">gray80</td>
		<td class="dark-text" style="background-color:#CCCCCC;">#CCCCCC</td>
		<td class="dark-text" style="background-color:#CCCCCC;">204, 204, 204</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#CFCFCF;">gray81</td>
		<td class="dark-text" style="background-color:#CFCFCF;">#CFCFCF</td>
		<td class="dark-text" style="background-color:#CFCFCF;">207, 207, 207</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#D1D1D1;">gray82</td>
		<td class="dark-text" style="background-color:#D1D1D1;">#D1D1D1</td>
		<td class="dark-text" style="background-color:#D1D1D1;">209, 209, 209</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#D4D4D4;">gray83</td>
		<td class="dark-text" style="background-color:#D4D4D4;">#D4D4D4</td>
		<td class="dark-text" style="background-color:#D4D4D4;">212, 212, 212</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#D6D6D6;">gray84</td>
		<td class="dark-text" style="background-color:#D6D6D6;">#D6D6D6</td>
		<td class="dark-text" style="background-color:#D6D6D6;">214, 214, 214</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#D9D9D9;">gray85</td>
		<td class="dark-text" style="background-color:#D9D9D9;">#D9D9D9</td>
		<td class="dark-text" style="background-color:#D9D9D9;">217, 217, 217</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#DBDBDB;">gray86</td>
		<td class="dark-text" style="background-color:#DBDBDB;">#DBDBDB</td>
		<td class="dark-text" style="background-color:#DBDBDB;">219, 219, 219</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#DEDEDE;">gray87</td>
		<td class="dark-text" style="background-color:#DEDEDE;">#DEDEDE</td>
		<td class="dark-text" style="background-color:#DEDEDE;">222, 222, 222</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#E0E0E0;">gray88</td>
		<td class="dark-text" style="background-color:#E0E0E0;">#E0E0E0</td>
		<td class="dark-text" style="background-color:#E0E0E0;">224, 224, 224</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#E3E3E3;">gray89</td>
		<td class="dark-text" style="background-color:#E3E3E3;">#E3E3E3</td>
		<td class="dark-text" style="background-color:#E3E3E3;">227, 227, 227</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#E6E6E6;">gray90</td>
		<td class="dark-text" style="background-color:#E6E6E6;">#E6E6E6</td>
		<td class="dark-text" style="background-color:#E6E6E6;">230, 230, 230</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#E8E8E8;">gray91</td>
		<td class="dark-text" style="background-color:#E8E8E8;">#E8E8E8</td>
		<td class="dark-text" style="background-color:#E8E8E8;">232, 232, 232</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#EBEBEB;">gray92</td>
		<td class="dark-text" style="background-color:#EBEBEB;">#EBEBEB</td>
		<td class="dark-text" style="background-color:#EBEBEB;">235, 235, 235</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#EDEDED;">gray93</td>
		<td class="dark-text" style="background-color:#EDEDED;">#EDEDED</td>
		<td class="dark-text" style="background-color:#EDEDED;">237, 237, 237</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#F0F0F0;">gray94</td>
		<td class="dark-text" style="background-color:#F0F0F0;">#F0F0F0</td>
		<td class="dark-text" style="background-color:#F0F0F0;">240, 240, 240</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#F2F2F2;">gray95</td>
		<td class="dark-text" style="background-color:#F2F2F2;">#F2F2F2</td>
		<td class="dark-text" style="background-color:#F2F2F2;">242, 242, 242</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#F5F5F5;">gray96</td>
		<td class="dark-text" style="background-color:#F5F5F5;">#F5F5F5</td>
		<td class="dark-text" style="background-color:#F5F5F5;">245, 245, 245</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#F7F7F7;">gray97</td>
		<td class="dark-text" style="background-color:#F7F7F7;">#F7F7F7</td>
		<td class="dark-text" style="background-color:#F7F7F7;">247, 247, 247</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FAFAFA;">gray98</td>
		<td class="dark-text" style="background-color:#FAFAFA;">#FAFAFA</td>
		<td class="dark-text" style="background-color:#FAFAFA;">250, 250, 250</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FCFCFC;">gray99</td>
		<td class="dark-text" style="background-color:#FCFCFC;">#FCFCFC</td>
		<td class="dark-text" style="background-color:#FCFCFC;">252, 252, 252</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFFFFF;">gray100</td>
		<td class="dark-text" style="background-color:#FFFFFF;">#FFFFFF</td>
		<td class="dark-text" style="background-color:#FFFFFF;">255, 255, 255</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#008000;">green</td>
		<td class="light-text" style="background-color:#008000;">#008000</td>
		<td class="light-text" style="background-color:#008000;">0, 128, 0</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#ADFF2F;">green_yellow</td>
		<td class="dark-text" style="background-color:#ADFF2F;">#ADFF2F</td>
		<td class="dark-text" style="background-color:#ADFF2F;">173, 255, 47</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#F0FFF0;">honey_dew</td>
		<td class="dark-text" style="background-color:#F0FFF0;">#F0FFF0</td>
		<td class="dark-text" style="background-color:#F0FFF0;">240, 255, 240</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#FF69B4;">hot_pink</td>
		<td class="light-text" style="background-color:#FF69B4;">#FF69B4</td>
		<td class="light-text" style="background-color:#FF69B4;">255, 105, 180</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#CD5C5C;">indian_red</td>
		<td class="light-text" style="background-color:#CD5C5C;">#CD5C5C</td>
		<td class="light-text" style="background-color:#CD5C5C;">205, 92, 92</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#4B0082;">indigo</td>
		<td class="light-text" style="background-color:#4B0082;">#4B0082</td>
		<td class="light-text" style="background-color:#4B0082;">75, 0, 130</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFFFF0;">ivory</td>
		<td class="dark-text" style="background-color:#FFFFF0;">#FFFFF0</td>
		<td class="dark-text" style="background-color:#FFFFF0;">255, 255, 240</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#F0E68C;">khaki</td>
		<td class="dark-text" style="background-color:#F0E68C;">#F0E68C</td>
		<td class="dark-text" style="background-color:#F0E68C;">240, 230, 140</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#E6E6FA;">lavender</td>
		<td class="dark-text" style="background-color:#E6E6FA;">#E6E6FA</td>
		<td class="dark-text" style="background-color:#E6E6FA;">230, 230, 250</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFF0F5;">lavender_blush</td>
		<td class="dark-text" style="background-color:#FFF0F5;">#FFF0F5</td>
		<td class="dark-text" style="background-color:#FFF0F5;">255, 240, 245</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#7CFC00;">lawn_green</td>
		<td class="dark-text" style="background-color:#7CFC00;">#7CFC00</td>
		<td class="dark-text" style="background-color:#7CFC00;">124, 252, 0</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFFACD;">lemon_chiffon</td>
		<td class="dark-text" style="background-color:#FFFACD;">#FFFACD</td>
		<td class="dark-text" style="background-color:#FFFACD;">255, 250, 205</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#ADD8E6;">light_blue</td>
		<td class="dark-text" style="background-color:#ADD8E6;">#ADD8E6</td>
		<td class="dark-text" style="background-color:#ADD8E6;">173, 216, 230</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#F08080;">light_coral</td>
		<td class="light-text" style="background-color:#F08080;">#F08080</td>
		<td class="light-text" style="background-color:#F08080;">240, 128, 128</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#E0FFFF;">light_cyan</td>
		<td class="dark-text" style="background-color:#E0FFFF;">#E0FFFF</td>
		<td class="dark-text" style="background-color:#E0FFFF;">224, 255, 255</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#EEDD82;">light_goldenrod</td>
		<td class="dark-text" style="background-color:#EEDD82;">#EEDD82</td>
		<td class="dark-text" style="background-color:#EEDD82;">238, 221, 130</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FAFAD2;">light_goldenrod_yellow</td>
		<td class="dark-text" style="background-color:#FAFAD2;">#FAFAD2</td>
		<td class="dark-text" style="background-color:#FAFAD2;">250, 250, 210</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#D3D3D3;">light_gray</td>
		<td class="dark-text" style="background-color:#D3D3D3;">#D3D3D3</td>
		<td class="dark-text" style="background-color:#D3D3D3;">211, 211, 211</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#90EE90;">light_green</td>
		<td class="dark-text" style="background-color:#90EE90;">#90EE90</td>
		<td class="dark-text" style="background-color:#90EE90;">144, 238, 144</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFD2FF;">light_magenta</td>
		<td class="dark-text" style="background-color:#FFD2FF;">#FFD2FF</td>
		<td class="dark-text" style="background-color:#FFD2FF;">255, 210, 255</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFB6C1;">light_pink</td>
		<td class="dark-text" style="background-color:#FFB6C1;">#FFB6C1</td>
		<td class="dark-text" style="background-color:#FFB6C1;">255, 182, 193</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFA07A;">light_salmon</td>
		<td class="dark-text" style="background-color:#FFA07A;">#FFA07A</td>
		<td class="dark-text" style="background-color:#FFA07A;">255, 160, 122</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#20B2AA;">light_sea_green</td>
		<td class="light-text" style="background-color:#20B2AA;">#20B2AA</td>
		<td class="light-text" style="background-color:#20B2AA;">32, 178, 170</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#87CEFA;">light_sky_blue</td>
		<td class="dark-text" style="background-color:#87CEFA;">#87CEFA</td>
		<td class="dark-text" style="background-color:#87CEFA;">135, 206, 250</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#8470FF;">light_slate_blue</td>
		<td class="light-text" style="background-color:#8470FF;">#8470FF</td>
		<td class="light-text" style="background-color:#8470FF;">132, 112, 255</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#778899;">light_slate_gray</td>
		<td class="light-text" style="background-color:#778899;">#778899</td>
		<td class="light-text" style="background-color:#778899;">119, 136, 153</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#B0C4DE;">light_steel_blue</td>
		<td class="dark-text" style="background-color:#B0C4DE;">#B0C4DE</td>
		<td class="dark-text" style="background-color:#B0C4DE;">176, 196, 222</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFFFE0;">light_yellow</td>
		<td class="dark-text" style="background-color:#FFFFE0;">#FFFFE0</td>
		<td class="dark-text" style="background-color:#FFFFE0;">255, 255, 224</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#00FF00;">lime</td>
		<td class="dark-text" style="background-color:#00FF00;">#00FF00</td>
		<td class="dark-text" style="background-color:#00FF00;">0, 255, 0</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#32CD32;">lime_green</td>
		<td class="light-text" style="background-color:#32CD32;">#32CD32</td>
		<td class="light-text" style="background-color:#32CD32;">50, 205, 50</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FAF0E6;">linen</td>
		<td class="dark-text" style="background-color:#FAF0E6;">#FAF0E6</td>
		<td class="dark-text" style="background-color:#FAF0E6;">250, 240, 230</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#FF00FF;">magenta</td>
		<td class="light-text" style="background-color:#FF00FF;">#FF00FF</td>
		<td class="light-text" style="background-color:#FF00FF;">255, 0, 255</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#800000;">maroon</td>
		<td class="light-text" style="background-color:#800000;">#800000</td>
		<td class="light-text" style="background-color:#800000;">128, 0, 0</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#66CDAA;">medium_aquamarine</td>
		<td class="light-text" style="background-color:#66CDAA;">#66CDAA</td>
		<td class="light-text" style="background-color:#66CDAA;">102, 205, 170</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#0000CD;">medium_blue</td>
		<td class="light-text" style="background-color:#0000CD;">#0000CD</td>
		<td class="light-text" style="background-color:#0000CD;">0, 0, 205</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#BA55D3;">medium_orchid</td>
		<td class="light-text" style="background-color:#BA55D3;">#BA55D3</td>
		<td class="light-text" style="background-color:#BA55D3;">186, 85, 211</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#9370DB;">medium_purple</td>
		<td class="light-text" style="background-color:#9370DB;">#9370DB</td>
		<td class="light-text" style="background-color:#9370DB;">147, 112, 219</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#3CB371;">medium_sea_green</td>
		<td class="light-text" style="background-color:#3CB371;">#3CB371</td>
		<td class="light-text" style="background-color:#3CB371;">60, 179, 113</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#7B68EE;">medium_slate_blue</td>
		<td class="light-text" style="background-color:#7B68EE;">#7B68EE</td>
		<td class="light-text" style="background-color:#7B68EE;">123, 104, 238</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#00FA9A;">medium_spring_green</td>
		<td class="dark-text" style="background-color:#00FA9A;">#00FA9A</td>
		<td class="dark-text" style="background-color:#00FA9A;">0, 250, 154</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#48D1CC;">medium_turquoise</td>
		<td class="light-text" style="background-color:#48D1CC;">#48D1CC</td>
		<td class="light-text" style="background-color:#48D1CC;">72, 209, 204</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#C71585;">medium_violet_red</td>
		<td class="light-text" style="background-color:#C71585;">#C71585</td>
		<td class="light-text" style="background-color:#C71585;">199, 21, 133</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#191970;">midnight_blue</td>
		<td class="light-text" style="background-color:#191970;">#191970</td>
		<td class="light-text" style="background-color:#191970;">25, 25, 112</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#F5FFFA;">mint_cream</td>
		<td class="dark-text" style="background-color:#F5FFFA;">#F5FFFA</td>
		<td class="dark-text" style="background-color:#F5FFFA;">245, 255, 250</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFE4E1;">misty_rose</td>
		<td class="dark-text" style="background-color:#FFE4E1;">#FFE4E1</td>
		<td class="dark-text" style="background-color:#FFE4E1;">255, 228, 225</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFE4B5;">moccasin</td>
		<td class="dark-text" style="background-color:#FFE4B5;">#FFE4B5</td>
		<td class="dark-text" style="background-color:#FFE4B5;">255, 228, 181</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFDEAD;">navajo_white</td>
		<td class="dark-text" style="background-color:#FFDEAD;">#FFDEAD</td>
		<td class="dark-text" style="background-color:#FFDEAD;">255, 222, 173</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#000080;">navy</td>
		<td class="light-text" style="background-color:#000080;">#000080</td>
		<td class="light-text" style="background-color:#000080;">0, 0, 128</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FDF5E6;">old_lace</td>
		<td class="dark-text" style="background-color:#FDF5E6;">#FDF5E6</td>
		<td class="dark-text" style="background-color:#FDF5E6;">253, 245, 230</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#808000;">olive</td>
		<td class="light-text" style="background-color:#808000;">#808000</td>
		<td class="light-text" style="background-color:#808000;">128, 128, 0</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#6B8E23;">olive_drab</td>
		<td class="light-text" style="background-color:#6B8E23;">#6B8E23</td>
		<td class="light-text" style="background-color:#6B8E23;">107, 142, 35</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFA500;">orange</td>
		<td class="dark-text" style="background-color:#FFA500;">#FFA500</td>
		<td class="dark-text" style="background-color:#FFA500;">255, 165, 0</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#FF4500;">orange_red</td>
		<td class="light-text" style="background-color:#FF4500;">#FF4500</td>
		<td class="light-text" style="background-color:#FF4500;">255, 69, 0</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#DA70D6;">orchid</td>
		<td class="light-text" style="background-color:#DA70D6;">#DA70D6</td>
		<td class="light-text" style="background-color:#DA70D6;">218, 112, 214</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#118ED8;">pacific_blue</td>
		<td class="light-text" style="background-color:#118ED8;">#118ED8</td>
		<td class="light-text" style="background-color:#118ED8;">17, 142, 216</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#EEE8AA;">pale_goldenrod</td>
		<td class="dark-text" style="background-color:#EEE8AA;">#EEE8AA</td>
		<td class="dark-text" style="background-color:#EEE8AA;">238, 232, 170</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#98FB98;">pale_green</td>
		<td class="dark-text" style="background-color:#98FB98;">#98FB98</td>
		<td class="dark-text" style="background-color:#98FB98;">152, 251, 152</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#AFEEEE;">pale_turquoise</td>
		<td class="dark-text" style="background-color:#AFEEEE;">#AFEEEE</td>
		<td class="dark-text" style="background-color:#AFEEEE;">175, 238, 238</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#DB7093;">pale_violet_red</td>
		<td class="light-text" style="background-color:#DB7093;">#DB7093</td>
		<td class="light-text" style="background-color:#DB7093;">219, 112, 147</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFEFD5;">papaya_whip</td>
		<td class="dark-text" style="background-color:#FFEFD5;">#FFEFD5</td>
		<td class="dark-text" style="background-color:#FFEFD5;">255, 239, 213</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFDAB9;">peach_puff</td>
		<td class="dark-text" style="background-color:#FFDAB9;">#FFDAB9</td>
		<td class="dark-text" style="background-color:#FFDAB9;">255, 218, 185</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#CD853F;">peru</td>
		<td class="light-text" style="background-color:#CD853F;">#CD853F</td>
		<td class="light-text" style="background-color:#CD853F;">205, 133, 63</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFC0CB;">pink</td>
		<td class="dark-text" style="background-color:#FFC0CB;">#FFC0CB</td>
		<td class="dark-text" style="background-color:#FFC0CB;">255, 192, 203</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#DDA0DD;">plum</td>
		<td class="dark-text" style="background-color:#DDA0DD;">#DDA0DD</td>
		<td class="dark-text" style="background-color:#DDA0DD;">221, 160, 221</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#B0E0E6;">powderblue</td>
		<td class="dark-text" style="background-color:#B0E0E6;">#B0E0E6</td>
		<td class="dark-text" style="background-color:#B0E0E6;">176, 224, 230</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#800080;">purple</td>
		<td class="light-text" style="background-color:#800080;">#800080</td>
		<td class="light-text" style="background-color:#800080;">128, 0, 128</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#663399;">rebeccapurple</td>
		<td class="light-text" style="background-color:#663399;">#663399</td>
		<td class="light-text" style="background-color:#663399;">102, 51, 153</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#FF0000;">red</td>
		<td class="light-text" style="background-color:#FF0000;">#FF0000</td>
		<td class="light-text" style="background-color:#FF0000;">255, 0, 0</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#BC8F8F;">rosy_brown</td>
		<td class="light-text" style="background-color:#BC8F8F;">#BC8F8F</td>
		<td class="light-text" style="background-color:#BC8F8F;">188, 143, 143</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#4169E1;">royal_blue</td>
		<td class="light-text" style="background-color:#4169E1;">#4169E1</td>
		<td class="light-text" style="background-color:#4169E1;">65, 105, 225</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#8B4513;">saddle_brown</td>
		<td class="light-text" style="background-color:#8B4513;">#8B4513</td>
		<td class="light-text" style="background-color:#8B4513;">139, 69, 19</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#FA8072;">salmon</td>
		<td class="light-text" style="background-color:#FA8072;">#FA8072</td>
		<td class="light-text" style="background-color:#FA8072;">250, 128, 114</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#F4A460;">sandy_brown</td>
		<td class="dark-text" style="background-color:#F4A460;">#F4A460</td>
		<td class="dark-text" style="background-color:#F4A460;">244, 164, 96</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#2E8B57;">sea_green</td>
		<td class="light-text" style="background-color:#2E8B57;">#2E8B57</td>
		<td class="light-text" style="background-color:#2E8B57;">46, 139, 87</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFF5EE;">sea_shell</td>
		<td class="dark-text" style="background-color:#FFF5EE;">#FFF5EE</td>
		<td class="dark-text" style="background-color:#FFF5EE;">255, 245, 238</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#A0522D;">sienna</td>
		<td class="light-text" style="background-color:#A0522D;">#A0522D</td>
		<td class="light-text" style="background-color:#A0522D;">160, 82, 45</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#C0C0C0;">silver</td>
		<td class="dark-text" style="background-color:#C0C0C0;">#C0C0C0</td>
		<td class="dark-text" style="background-color:#C0C0C0;">192, 192, 192</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#87CEEB;">sky_blue</td>
		<td class="dark-text" style="background-color:#87CEEB;">#87CEEB</td>
		<td class="dark-text" style="background-color:#87CEEB;">135, 206, 235</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#6A5ACD;">slate_blue</td>
		<td class="light-text" style="background-color:#6A5ACD;">#6A5ACD</td>
		<td class="light-text" style="background-color:#6A5ACD;">106, 90, 205</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#708090;">slate_gray</td>
		<td class="light-text" style="background-color:#708090;">#708090</td>
		<td class="light-text" style="background-color:#708090;">112, 128, 144</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFFAFA;">snow</td>
		<td class="dark-text" style="background-color:#FFFAFA;">#FFFAFA</td>
		<td class="dark-text" style="background-color:#FFFAFA;">255, 250, 250</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#00FF7F;">spring_green</td>
		<td class="dark-text" style="background-color:#00FF7F;">#00FF7F</td>
		<td class="dark-text" style="background-color:#00FF7F;">0, 255, 127</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#4682B4;">steel_blue</td>
		<td class="light-text" style="background-color:#4682B4;">#4682B4</td>
		<td class="light-text" style="background-color:#4682B4;">70, 130, 180</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#D2B48C;">tan</td>
		<td class="light-text" style="background-color:#D2B48C;">#D2B48C</td>
		<td class="light-text" style="background-color:#D2B48C;">210, 180, 140</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#008080;">teal</td>
		<td class="light-text" style="background-color:#008080;">#008080</td>
		<td class="light-text" style="background-color:#008080;">0, 128, 128</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#D8BFD8;">thistle</td>
		<td class="dark-text" style="background-color:#D8BFD8;">#D8BFD8</td>
		<td class="dark-text" style="background-color:#D8BFD8;">216, 191, 216</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#FF6347;">tomato</td>
		<td class="light-text" style="background-color:#FF6347;">#FF6347</td>
		<td class="light-text" style="background-color:#FF6347;">255, 99, 71</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#40E0D0;">turquoise</td>
		<td class="dark-text" style="background-color:#40E0D0;">#40E0D0</td>
		<td class="dark-text" style="background-color:#40E0D0;">64, 224, 208</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#EE82EE;">violet</td>
		<td class="light-text" style="background-color:#EE82EE;">#EE82EE</td>
		<td class="light-text" style="background-color:#EE82EE;">238, 130, 238</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#D02090;">violet_red</td>
		<td class="light-text" style="background-color:#D02090;">#D02090</td>
		<td class="light-text" style="background-color:#D02090;">208, 32, 144</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#F5DEB3;">wheat</td>
		<td class="dark-text" style="background-color:#F5DEB3;">#F5DEB3</td>
		<td class="dark-text" style="background-color:#F5DEB3;">245, 222, 179</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFFFFF;">white</td>
		<td class="dark-text" style="background-color:#FFFFFF;">#FFFFFF</td>
		<td class="dark-text" style="background-color:#FFFFFF;">255, 255, 255</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#F5F5F5;">white_smoke</td>
		<td class="dark-text" style="background-color:#F5F5F5;">#F5F5F5</td>
		<td class="dark-text" style="background-color:#F5F5F5;">245, 245, 245</td>
		</tr>
		<tr>
		<td class="dark-text" style="background-color:#FFFF00;">yellow</td>
		<td class="dark-text" style="background-color:#FFFF00;">#FFFF00</td>
		<td class="dark-text" style="background-color:#FFFF00;">255, 255, 0</td>
		</tr>
		<tr>
		<td class="light-text" style="background-color:#9ACD32;">yellow_green</td>
		<td class="light-text" style="background-color:#9ACD32;">#9ACD32</td>
		<td class="light-text" style="background-color:#9ACD32;">154, 205, 50</td>
		</tr>
		</table>
    </tab>
</tabs>