@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

plugins {
    kotlin("multiplatform")
}

//val kotlinxCoroutinesVersion = extra["kotlinx.coroutines.version"] as String
//val kotlinxDatetimeVersion = extra["kotlinx.datetime.version"] as String
val kotlinxBrowserVersion = project.extra["kotlinx.browser.version"] as String

kotlin {
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        wasmJsMain {
            dependencies {
//                implementation("io.github.microutils:kotlin-logging-js:$kotlinLoggingVersion")
                implementation(projects.plotApi)
                implementation("org.jetbrains.kotlinx:kotlinx-browser:${kotlinxBrowserVersion}")
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${kotlinxCoroutinesVersion}")
//                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$kotlinxDatetimeVersion")
            }
        }
    }
}
