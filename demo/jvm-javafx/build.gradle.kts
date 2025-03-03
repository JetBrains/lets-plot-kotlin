/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

plugins {
    kotlin("jvm")
    id ("org.openjfx.javafxplugin") version "0.1.0"
}

val letsPlotVersion = extra["letsPlot.version"] as String

javafx {
    version = "22"
    modules = listOf("javafx.controls", "javafx.swing")
}

dependencies {
    implementation("org.jetbrains.lets-plot:lets-plot-jfx:$letsPlotVersion")

    implementation(projects.plotApi)
    implementation(projects.demoCommon)
}

