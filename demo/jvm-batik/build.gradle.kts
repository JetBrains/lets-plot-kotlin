/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

plugins {
    kotlin("jvm")
}

val letsPlotVersion = extra["letsPlot.version"] as String
val slf4jVersion = extra["slf4j.version"] as String

dependencies {
    implementation("org.jetbrains.lets-plot:lets-plot-swing:$letsPlotVersion")
    implementation("org.jetbrains.lets-plot:lets-plot-gis:$letsPlotVersion")
    implementation(projects.plotApi)

    implementation(projects.demoCommon)

    implementation("org.slf4j:slf4j-simple:$slf4jVersion")  // Enable logging to console
}

