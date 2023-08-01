/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

buildscript {
    dependencies {
        classpath("org.yaml:snakeyaml:1.25")
    }
}

plugins {
    kotlin("multiplatform") apply false
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}

val buildSettingsFile = File(rootDir, "build_settings.yml")
if (!buildSettingsFile.canRead()) {
    error("Couldn't read build_settings.yml")
}
val settings: Map<String, Any?> = org.yaml.snakeyaml.Yaml().load(buildSettingsFile.inputStream())
val sonatypeSettings = settings["sonatype"] as Map<String, String?>


allprojects {
    group = "org.jetbrains.lets-plot"
    version = when (name) {
        "dokka" -> "4.4.0"
        else -> "4.4.2-alpha2"
    }

    val version = version as String
    var versionIsDev: Boolean by extra
    versionIsDev = (version.contains("SNAPSHOT")
            || version.contains("rc")
            || version.contains("alpha")
            || version.contains("beta"))

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    tasks.withType<JavaCompile>().all {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }

    repositories {
        // GeoTools repository must be before Maven Central
        // See: https://stackoverflow.com/questions/26993105/i-get-an-error-downloading-javax-media-jai-core1-1-3-from-maven-central
        // See also Jupyter Kotlin issue: https://github.com/Kotlin/kotlin-jupyter/issues/107
        maven(url = "https://repo.osgeo.org/repository/release")

        mavenCentral()

        // local
//        maven {
//            url = uri("/Users/Igor/Work/lets-plot/.maven-publish-dev-repo")
//        }

        // SNAPSHOTS
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    }

    // Maven publication settings

    // define local Maven Repository path:
    var localMavenRepository: String by extra
    localMavenRepository = "$rootDir/.maven-publish-dev-repo"

    // define Sonatype nexus repository manager settings:
    val sonatypeSnapshotUrl = "https://oss.sonatype.org/content/repositories/snapshots/"
    val sonatypeReleaseUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2/"
    project.extra["sonatypeUrl"] = if (version.contains("SNAPSHOT")) sonatypeSnapshotUrl else sonatypeReleaseUrl
}

subprojects {
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
nexusPublishing {
    repositories{
        sonatype() {
            stagingProfileId.set("11c25ff9a87b89")
            username.set(sonatypeSettings["username"])
            password.set(sonatypeSettings["password"])
        }
    }
}
