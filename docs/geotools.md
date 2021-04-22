# GeoTools Support

- [Creating maps in JVM-based applications](#maps-in-jvm) 
    - [Maven artifacts](#maven-artifacts) 
    - [JVM-based examples](#examples-jvm) 
- [Creating maps in notebooks](#maps-in-notebooks) 
    - [Example notebooks](#example-notebooks) 
    
------

[GeoTools](https://www.geotools.org/) is an open source Java GIS Toolkit.

Lets-Plot supports visualization of `SimpleFeature` objects organized in `SimpleFeatureCollection`, as well as 
individual `Geometry`  (`org.locationtech.jts.geom`) and `ReferencedEnvelope` (`org.geotools.geometry.jts`) objects. 

Lets-Plot API includes the `SpatialDataset` (`jetbrains.letsPlot.spatial`) class serving as a bridge between external
spatial data-types and Lets-Plot geometry layers. 

A set of Kotlin extension methods with the signature `toSpatialDataset(decimals: Int = 10): SpatialDataset` is provided
to facilitate converting `GeoTools` objects to an instance of the `SpatialDataset` class. 

```
import jetbrains.letsPlot.spatial.SpatialDataset
import jetbrains.letsPlot.toolkit.geotools.toSpatialDataset

val data: SpatialDataset = featureCollection.toSpatialDataset()
```   

The instance of `SpatialDataset` then can be passed to a plot geometry layer via the `map` or `data` parameters.

```python
letsPlot() + geomPolygon(map = data, fill = "white", color = "gray")
```

The Lets-Plot library recognizes the following three 2D-geometry types:

- Points
- Lines
- Polygons

These shapes can be plotted using various geometry layers that depend on the type of the shape:

- Points : `geomPoint, geomText`
- Lines : `geomPath`
- Polygons : `geomPolygon, geomMap`. `geomRect` when used with Polygon shapes displays corresponding bounding boxes.

All coordinates must be in decimal degree units, in "WGS 84" coordinates. 

<a id="maps-in-jvm"></a> 
### Creating maps in JVM-based applications

For general information on using the Lets-Plot library in JVM-based application, see: [README_DEV.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/README_DEV.md) 

<a id="maven-artifacts"></a> 
#### Maven artifacts

In addition to the Maven artifacts that are [required](https://github.com/JetBrains/lets-plot-kotlin/blob/master/README_DEV.md#creating-plots-in-jvm-based-applications) 
for regular plots,
the artifact `lets-plot-kotlin-geotools` must be included to make the `toSpatialDataset()` method available.

You can include it into a Gradle project.
```groovy
implementation 'org.jetbrains.lets-plot-kotlin:lets-plot-kotlin-geotools:1.1.0'
```  

[ ![Download](https://api.bintray.com/packages/jetbrains/lets-plot-maven/lets-plot-kotlin-jars/images/download.svg)](https://bintray.com/jetbrains/lets-plot-maven/lets-plot-kotlin-jars/_latestVersion)

The `gt-geojson` artifact from GeoTools must be also included.
```groovy
dependencies {   
    ...
    implementation "org.jetbrains.lets-plot-kotlin:lets-plot-kotlin-geotools:1.1.0"
    implementation "org.geotools:gt-geojson:$geotools_version"               
    ...
}
```

The `lets-plot-kotlin-api` artifact v1.1.0 was compiled with GeoTools v24.1. However, it doesn't declare any run-time dependency, so 
you are free to use other versions of the GeoTools toolkit. 

<a id="examples-jvm"></a> 
#### JVM-based examples

The ['geotools-batik'](https://github.com/JetBrains/lets-plot-kotlin/tree/master/demo/geotools-batik) sub-project
contains a set runnable examples that use [Apache Batik SVG Toolkit](https://xmlgraphics.apache.org/batik/)
for rendering.


<a id="maps-in-notebooks"></a> 
### Creating maps in notebooks

Lets-Plot can visualize maps in any notebook application that supports the [Kotlin Jupyter Kernel](https://github.com/Kotlin/kotlin-jupyter).

At this moment, the applications that support Kotlin kernel are [Jupyther](https://jupyter.org/) itself and JetBrains [Datalore](https://datalore.jetbrains.com/).  

You can include all necessary dependencies into your notebook using the following "line magics": 

```
%use lets-plot
%use lets-plot-gt
``` 

By default, the `lets-plot-gt` magic installs the latest version of GeoTools artifacts - it uses "[23,)" version specification.

Thus, when declaring additional GeoTools dependencies in your notebook, you can do it as follows: 
```
@file:DependsOn("org.geotools:gt-shapefile:[23,)")
@file:DependsOn("org.geotools:gt-cql:[23,)")
```   

If you have to use any different than the latest version of GeoTools, use the `gt` parameter in 
the `lets-plot-gt` magic to avoid possible compatibility issues.

```
%use lets-plot
%use lets-plot-gt(gt=22.5)
``` 
```
@file:DependsOn("org.geotools:gt-shapefile:22.5")
@file:DependsOn("org.geotools:gt-cql:22.5")
```   

<a id="example-notebooks"></a> 
#### Example notebooks

* The world map with *Lets-Plot* and *GeoTools*: 
[geotools_naturalearth.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks-dev/geotools_naturalearth.ipynb)

<img src="https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/images/naturalearth_world.png" alt="Couldn't load naturalearth_world.png" width="480" height="339"><br><br>

