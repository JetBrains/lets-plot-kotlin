/*
 * Copyright (c) 2026. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

plugins {
    kotlin("multiplatform")
}

val letsPlotVersion = extra["letsPlot.version"] as String
val kotlinxBrowserVersion = project.extra["kotlinx.browser.version"] as String

kotlin {
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        wasmJsMain {
            dependencies {
                implementation(projects.plotApi)
                implementation("org.jetbrains.lets-plot:wasmjs-package:$letsPlotVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-browser:${kotlinxBrowserVersion}")
            }
        }
    }
}
