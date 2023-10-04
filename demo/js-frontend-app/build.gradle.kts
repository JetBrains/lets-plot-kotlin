/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

plugins {
    kotlin("multiplatform")
}

kotlin {
    js {
        browser()
        binaries.executable()
    }

    sourceSets {
        named("jsMain") {
            dependencies {
//                implementation("io.github.microutils:kotlin-logging-js:$kotlinLoggingVersion")
                implementation(projects.plotApi)
            }
        }
    }
}
