/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

plugins {
    kotlin("jvm")
}

dependencies {
    val lets_plot_version: String by project
    implementation("org.jetbrains.lets-plot:lets-plot-jfx:$lets_plot_version")

    implementation(projects.plotApi)
    implementation(projects.demoCommon)
}

