/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

plugins {
    kotlin("js")
}

repositories {
    mavenCentral()

//    maven {
//        url = uri("/Users/Igor/Work/lets-plot-kotlin/.maven-publish-dev-repo")
//    }
}

dependencies {
//    implementation "org.jetbrains.lets-plot:lets-plot-kotlin-js:2.0.2-SNAPSHOT"

    implementation(projects.plotApi)
    testImplementation(kotlin("test"))
}

kotlin {
    js(LEGACY) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
//        useCommonJs()
    }
}
