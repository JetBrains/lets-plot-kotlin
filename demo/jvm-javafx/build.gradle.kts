/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

plugins {
    kotlin("jvm")
}

val javaFXVersion = "17"

val letsPlotVersion = extra["letsPlot.version"] as String

dependencies {
    implementation("org.jetbrains.lets-plot:lets-plot-jfx:$letsPlotVersion")
    implementation("org.openjfx:javafx-base:$javaFXVersion:${getJavaFXPlatform()}")
    implementation("org.openjfx:javafx-swing:$javaFXVersion:${getJavaFXPlatform()}")
    implementation("org.openjfx:javafx-graphics:$javaFXVersion:${getJavaFXPlatform()}")

    implementation(projects.plotApi)
    implementation(projects.demoCommon)
}

fun getJavaFXPlatform(): String {
    val currentOS = org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentOperatingSystem();
    return when {
        currentOS.isWindows -> "win"
        currentOS.isLinux -> "linux"
        currentOS.isMacOsX -> "mac"
        else -> throw IllegalStateException("Unexpected OS: ${currentOS.name}")
    }
}
