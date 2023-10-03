/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }

    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        val dokkaVersion = extra["dokka.version"] as String
        val nexusStagingVersion = extra["nexusStaging.version"] as String
        val nexusPublishVersion = extra["nexusPublish.version"] as String

        kotlin("multiplatform") version kotlinVersion
        kotlin("jvm").version(kotlinVersion)
        kotlin("js").version(kotlinVersion)

        id("org.jetbrains.dokka") version dokkaVersion

        id("io.codearte.nexus-staging") version nexusStagingVersion
        id("io.github.gradle-nexus.publish-plugin") version nexusPublishVersion
    }
}

dependencyResolutionManagement {
    repositories {
        // GeoTools repository must be before Maven Central
        // See: https://stackoverflow.com/questions/26993105/i-get-an-error-downloading-javax-media-jai-core1-1-3-from-maven-central
        // See also Jupyter Kotlin issue: https://github.com/Kotlin/kotlin-jupyter/issues/107
        maven(url = "https://repo.osgeo.org/repository/release")

        mavenCentral()
        google()

        // SNAPSHOTS
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")

        mavenLocal()
    }
}

rootProject.name = "lets-plot-kotlin"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include("plot-api")
include("demo-common")
include("jvm-javafx")
include("jvm-batik")
include("browser")
include("js-frontend-app")
include("geotools")
include("geotools-batik")
include("dokka")

project(":demo-common").projectDir = File("./demo/demo-common")
project(":jvm-javafx").projectDir = File("./demo/jvm-javafx")
project(":jvm-batik").projectDir = File("./demo/jvm-batik")
project(":browser").projectDir = File("./demo/browser")
project(":js-frontend-app").projectDir = File("./demo/js-frontend-app")

project(":geotools").projectDir = File("./toolkit/geotools")
project(":geotools-batik").projectDir = File("./demo/geotools-batik")

project(":dokka").projectDir = File("./docs/dokka")
