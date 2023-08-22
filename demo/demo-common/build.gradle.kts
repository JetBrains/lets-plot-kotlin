/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

plugins {
    kotlin("jvm")
}

val slf4jVersion = extra["slf4j.version"] as String

dependencies {
    implementation(projects.plotApi)

    implementation("org.slf4j:slf4j-simple:$slf4jVersion")  // Enable logging to console
}

