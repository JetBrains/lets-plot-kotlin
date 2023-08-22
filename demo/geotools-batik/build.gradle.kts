/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

plugins {
    kotlin("jvm")
}

val letsPlotVersion = extra["letsPlot.version"] as String
val geoToolsVersion = extra["geotools.version"] as String

dependencies {
    implementation("org.jetbrains.lets-plot:lets-plot-batik:$letsPlotVersion")

    implementation(projects.plotApi)
    implementation(projects.geotools)
    implementation(projects.demoCommon)

    implementation("org.geotools:gt-main:$geoToolsVersion")
    implementation("org.geotools:gt-geojson:$geoToolsVersion")
    implementation("org.geotools:gt-shapefile:$geoToolsVersion")
}
