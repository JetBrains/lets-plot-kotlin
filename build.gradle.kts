/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

import java.util.*

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    kotlin("multiplatform") apply false
    kotlin("jvm") apply false
    id("org.jetbrains.dokka") apply false
    id("io.codearte.nexus-staging") apply false

    id("io.github.gradle-nexus.publish-plugin")

    // Add the KSP plugin before the Jupyter API to avoid ksp versions incompatibility.
    // May be removed when using further versions of the jupyter api
    id("com.google.devtools.ksp") apply false
    kotlin("jupyter.api") apply false
}

val localProps = Properties()
if (project.file("local.properties").exists()) {
    localProps.load(project.file("local.properties").inputStream())
}


allprojects {
    group = "org.jetbrains.lets-plot"
    version = when (name) {
        "dokka" -> "4.9.2"
        else -> "4.9.3-SNAPSHOT"
//        else -> "0.0.0-SNAPSHOT"  // for local publishing only
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    tasks.withType<JavaCompile>().all {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }
}

subprojects {
    repositories {
        // GeoTools repository must be before Maven Central
        // See: https://stackoverflow.com/questions/26993105/i-get-an-error-downloading-javax-media-jai-core1-1-3-from-maven-central
        // See also Jupyter Kotlin issue: https://github.com/Kotlin/kotlin-jupyter/issues/107
        maven(url = "https://repo.osgeo.org/repository/release")

        mavenCentral()
        google()

        // Repositories where other projects publish their artifacts locally to.
        localProps["maven.repo.local"]?.let {
            (it as String).split(",").forEach { repo ->
                mavenLocal {
                    url = uri(repo)
                }
            }
        }

        // SNAPSHOTS
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")

        mavenLocal()
    }

    // Repository path for "MavenLocalRepository"
    var localMavenRepository: String by extra
    localMavenRepository = "$rootDir/.maven-publish-dev-repo"

    // ------------------------------------------
    // Workaround for the error when signing published artifacts.
    // It seems to appear after switching to Gradle 8.3
    // For details see: https://github.com/gradle/gradle/issues/26091 :
    // Publishing a KMP project with signing fails with "Task ... uses this output of task ... without declaring an explicit or implicit dependency"
    // https://github.com/gradle/gradle/issues/26091
    tasks.withType<AbstractPublishToMaven>().configureEach {
        val signingTasks = tasks.withType<Sign>()
        mustRunAfter(signingTasks)
    }

    afterEvaluate {
        // Add LICENSE file to the META-INF folder inside published JAR files
        tasks.filterIsInstance(org.gradle.jvm.tasks.Jar::class.java)
            .forEach {
                it.metaInf {
                    from("$rootDir") {
                        include("LICENSE")
                    }
                }
            }
    }
}

// Nexus publish plugin settings:
val sonatypeUsername = localProps["sonatype.username"] as String?
val sonatypePassword = localProps["sonatype.password"] as String?
val sonatypeProfileId = localProps["sonatype.profileID"] as String?
if (!(sonatypeUsername.isNullOrBlank()
            || sonatypePassword.isNullOrBlank()
            || sonatypeProfileId.isNullOrBlank())) {
    nexusPublishing.repositories {
        sonatype {
            username.set(sonatypeUsername)
            password.set(sonatypePassword)
            stagingProfileId.set(sonatypeProfileId)

            nexusUrl.set(uri("https://oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}
