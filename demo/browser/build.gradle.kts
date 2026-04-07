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

val letsPlotVersion = extra["letsPlot.version"] as String
val slf4jVersion = extra["slf4j.version"] as String

dependencies {
    implementation(projects.plotApi)

    implementation("org.jetbrains.lets-plot:lets-plot-common:$letsPlotVersion")
    implementation("org.jetbrains.lets-plot:platf-awt:$letsPlotVersion")

    implementation("org.slf4j:slf4j-simple:$slf4jVersion")  // Enable logging to console
}
