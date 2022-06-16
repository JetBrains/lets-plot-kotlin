/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

plugins {
    kotlin("jvm")
}

// Let demo load resources from 'plot-api' module.
//sourceSets {
//    main {
//        resources {
//            srcDir("$rootDir/plot-api/src/jvmMain/resources")
//        }
//    }
//}

// Copy resources from 'plot-api' module.
val copyVersionInfo by tasks.creating(Copy::class) {
    from("$rootDir/plot-api/src/jvmMain/resources/letsPlotKotlinAPI")
    into("$rootDir/demo/browser/src/main/resources/letsPlotKotlinAPI")
}
tasks.getByPath(":browser:processResources").dependsOn.add(copyVersionInfo)

dependencies {
    implementation(projects.plotApi)

    val lets_plot_version: String by project
    implementation("org.jetbrains.lets-plot:lets-plot-common:$lets_plot_version")
    implementation("org.jetbrains.lets-plot:lets-plot-image-export:$lets_plot_version")

    val slf4j_version: String by project
    implementation("org.slf4j:slf4j-simple:$slf4j_version")  // Enable logging to console
}
