## Lets-Plot Kotlin API 
     
* Lets-Plot Kotlin API reference: https://lets-plot.org/kotlin

* The "Example Notebooks" reference: [examples.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/examples.md)

* Example notebooks in the Binder: [mybinder.org](https://mybinder.org/v2/gh/JetBrains/lets-plot-kotlin/v4.3.0demos?filepath=docs/examples/jupyter-notebooks/)


<a id="toc"></a>
### Table of Contents

- [Tooltip Customization](#tooltip-customization)
- [Formatting](#formatting)
- [Data Sampling](#sampling)
- [Saving Plot in a File](#export)
- [GeoTools Support](#geotools)


<a id="tooltip-customization"></a>
### Tooltip Customization

You can customize the content, values formatting and appearance of tooltip for any geometry layer in your plot.

Learn more: [Tooltip Customization](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/tooltips.md).

<a id="formatting"></a>
### Formatting

Formatting of numeric and date-time values in tooltips, legends, on the axes and *text geometry* layer.

Learn more: [Formatting](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md).

<a id="sampling"></a>
### Data Sampling

Sampling is a special technique of data transformation, which helps to deal with large datasets and overplotting.

Learn more: [Data Sampling](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/sampling.md).

<a id="export"></a>
### Saving Plot in a File

The `ggsave()` function is a convenient way of saving a plot or a GGBunch object in a file.

The supported export formats are: `SVG, HTML, PNG, JPEG and TIFF`.

For example, the code below will save plot as a PNG image in the file `<user dir>//lets-plot-images/density.png`:

```kotlin
val rand = java.util.Random(123)
val n = 400
val data = mapOf (
    "rating" to List(n/2) { rand.nextGaussian() } + List(n/2) { rand.nextGaussian() * 1.5 + 1.5 },
    "cond" to List(n/2) { "A" } + List(n/2) { "B" }
)

var p = letsPlot(data) +
        geomDensity { x = "rating"; color = "cond" } + ggsize(500, 250)
        
ggsave(p, "density.png")        
``` 

<img src="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/images/ggsave_demo.png" alt="Couldn't load ggsave_demo.png" width="500" height="250"/>
<br/>

See `ggsave()` [documentation](https://lets-plot.org/kotlin/-lets--plot--kotlin/org.jetbrains.letsPlot.export/ggsave.html)
for more information about the function arguments and default values.
        
Example notebook: [ggsave demo](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/export_to_file.ipynb).

<a id="geotools"></a>
### GeoTools support

<img src="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/images/geotools_thumb.png" alt="geotools_thumb.png" width="74" height="123"/>
<br/>


[GeoTools](https://www.geotools.org/) is an open source Java GIS Toolkit.

Lets-Plot supports visualization of a set of `SimpleFeature`-s stored in `SimpleFeatureCollection`, as well as
individual `Geometry` and `ReferencedEnvelope` objects.

Learn more: [GeoTools Support](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/geotools.md).

