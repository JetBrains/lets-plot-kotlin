# Saving Plot in a File

The `ggsave()` function is a convenient way of saving a plot or a GGBunch object in a file.

The supported export formats are: `SVG`, `HTML`, `PNG`, `JPEG` and `TIFF`.

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

<img src="ggsave_demo.png" alt="Couldn't load ggsave_demo.png" width="500" height="250"/>

See `ggsave()` [documentation](%api_export%/ggsave.html) for more information about the function arguments and default values.

Example notebook: [ggsave demo.ipynb](%nb-export_to_file%).