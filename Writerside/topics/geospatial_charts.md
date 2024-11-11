# Geospatial Charts

[GeoTools](https://www.geotools.org/) is an open source Java GIS Toolkit.

*Lets-Plot* supports visualization of `SimpleFeature` objects organized in `SimpleFeatureCollection`, as well as
individual `Geometry`  (`org.locationtech.jts.geom`) and `ReferencedEnvelope` (`org.geotools.geometry.jts`) objects.

*Lets-Plot* API includes the [`SpatialDataset`](%api_spatial%) class serving as a bridge between external
spatial data-types and *Lets-Plot* geometry layers.

A set of Kotlin extension methods with the signature `toSpatialDataset(decimals: Int = 10): SpatialDataset` is provided
to facilitate converting `GeoTools` objects to an instance of the `SpatialDataset` class.

```kotlin
import org.jetbrains.letsPlot.spatial.SpatialDataset
import org.jetbrains.letsPlot.toolkit.geotools.toSpatialDataset

val data: SpatialDataset = featureCollection.toSpatialDataset()
```

The instance of `SpatialDataset` then can be passed to a plot geometry layer via the `map` or `data` parameters.

```kotlin
letsPlot() + geomPolygon(map = data, fill = "white", color = "gray")
```

The *Lets-Plot* library recognizes the following three 2D-geometry types:

- Points
- Lines
- Polygons

These shapes can be plotted using various geometry layers that depend on the type of the shape:

- Points : [`geomPoint`](%api_geom%/geom-point/index.html), [`geomText`](%api_geom%/geom-text/index.html), [`geomLabel`](%api_geom%/geom-label/index.html), [`geomPie`](%api_geom%/geom-pie/index.html)
- Lines : [`geomPath`](%api_geom%/geom-path/index.html)
- Polygons : [`geomPolygon`](%api_geom%/geom-polygon/index.html), [`geomMap`](%api_geom%/geom-map/index.html). [`geomRect`](%api_geom%/geom-rect/index.html) when used with Polygon shapes displays corresponding bounding boxes.

All coordinates must be in decimal degree units, in "WGS 84" coordinates.

## Creating Maps in JVM-Based Applications

For general information on using the *Lets-Plot* library in JVM-based application, see: [USAGE_BATIK_JFX_JS.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/USAGE_BATIK_JFX_JS.md)

### Maven Artifacts

In addition to the Maven artifacts that are [required](https://github.com/JetBrains/lets-plot-kotlin/blob/master/USAGE_BATIK_JFX_JS.md#dependencies)
for regular plots, the artifact `lets-plot-kotlin-geotools` must be included to make the `toSpatialDataset()` method available.

You can include it into a Gradle project.

```groovy
implementation 'org.jetbrains.lets-plot-kotlin:lets-plot-kotlin-geotools:%version%'
```

The `gt-geojson` artifact from GeoTools must be also included.

```groovy
dependencies {   
    ...
    implementation "org.jetbrains.lets-plot-kotlin:lets-plot-kotlin-geotools:%version%"
    implementation "org.geotools:gt-geojson:[30,)"               
    ...
}
```

The `lets-plot-kotlin-api` artifact was compiled with GeoTools v30.1. However, it doesn't declare any run-time dependency,
so you are free to use other versions of the GeoTools toolkit.

> Since v4.6.0 *Lets-Plot* is only compatible with GeoTools v30 and later.

### JVM-Based Examples

The ['geotools-batik'](https://github.com/JetBrains/lets-plot-kotlin/tree/master/demo/geotools-batik) subproject
contains a set runnable examples that use [Apache Batik SVG Toolkit](https://xmlgraphics.apache.org/batik/)
for rendering.

## Creating Maps in Notebooks

*Lets-Plot* can visualize maps in [Kotlin Notebook](https://plugins.jetbrains.com/plugin/16340-kotlin-notebook), [Datalore](https://datalore.jetbrains.com) or [Jupyter with Kotlin Kernel](https://github.com/Kotlin/kotlin-jupyter#readme).

You can include all necessary dependencies into your notebook using the following "line magics":

```
%use lets-plot
%use lets-plot-gt
```

By default, the `lets-plot-gt` magic installs the latest version of GeoTools artifacts - it uses "[30,)" version specification.

Thus, when declaring additional GeoTools dependencies in your notebook, you can do it as follows:

```
@file:DependsOn("org.geotools:gt-shapefile:[30,)")
@file:DependsOn("org.geotools:gt-cql:[30,)")
```

If you have to use any different then the latest version of GeoTools, use the `gt` parameter in the `lets-plot-gt`
magic to avoid possible compatibility issues.

```
%use lets-plot
%use lets-plot-gt(gt=31)
```

```
@file:DependsOn("org.geotools:gt-shapefile:31")
@file:DependsOn("org.geotools:gt-cql:31")
```

### Example Notebooks

- The world map with *Lets-Plot* and *GeoTools*:
  [geotools_naturalearth.ipynb](%nb-geotools_naturalearth%)

  <img src="geotools_naturalearth.png" alt="Using Lets-Plot with GeoTools to create maps" width="480" height="339"/>

- Inset map of Kotlin island:
  [spatialdataset_kotlin_isl.ipynb](%nb-spatialdataset_kotlin_isl%)

  <img src="spatialdataset_kotlin_isl.png" alt="Inset map of Kotlin island" width="480"/>

- Using exotic map projections:
  [projection_provided.ipynb](%nb-projection_provided%)

  <img src="projection_provided.png" alt="Using exotic map projections" height="339"/>

- Label geometry:
  [text_geoms.ipynb](%nb-text_geoms%)

  <img src="text_geoms.png" alt="Text geoms" width="480"/>