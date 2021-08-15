/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(projects.plotApi)

    val slf4j_version: String by project
    implementation("org.slf4j:slf4j-simple:$slf4j_version")  // Enable logging to console
}

