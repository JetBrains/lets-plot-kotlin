/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import org.jetbrains.kotlin.gradle.dsl.KotlinCommonCompilerOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
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
        "dokka" -> "4.11.0"
        else -> "4.11.0-SNAPSHOT"
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

// Sonatype Central Repository settings:
val sonatypeUsername by extra { localProps["sonatype.username"] ?: "" }
val sonatypePassword by extra { localProps["sonatype.password"] ?: "" }

// Define the Maven Repository URL. Currently set to a local path for uploading
// artifacts to the Sonatype Central Repository.
val mavenReleasePublishUrl by extra { layout.buildDirectory.dir("maven/artifacts").get().toString() }

// define Maven Snapshot repository URL.
val mavenSnapshotPublishUrl by extra { "https://central.sonatype.com/repository/maven-snapshots/" }

// Configure a workaround tasks for publishing to the Sonatype Central Repository,
// as there is currently no official Gradle plugin support.
// Refer to documentation: https://central.sonatype.org/publish/publish-portal-gradle/
val packageMavenArtifacts by tasks.registering(Zip::class) {
    from(mavenReleasePublishUrl)
    archiveFileName.set("${project.name}-artifacts.zip")
    destinationDirectory.set(layout.buildDirectory)
}
val uploadMavenArtifacts by tasks.registering {
    dependsOn(packageMavenArtifacts)

    doLast {
        val uriBase = "https://central.sonatype.com/api/v1/publisher/upload"
        val publishingType = "USER_MANAGED"
        val deploymentName = "${project.name}-$version"
        val uri = "$uriBase?name=$deploymentName&publishingType=$publishingType"

        val userName = sonatypeUsername as String
        val password = sonatypePassword as String
        val base64Auth = Base64.getEncoder().encode("$userName:$password".toByteArray()).toString(Charsets.UTF_8)
        val bundleFile = packageMavenArtifacts.get().archiveFile.get().asFile

        println("Sending request to $uri...")

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(uri)
            .header("Authorization", "Bearer $base64Auth")
            .post(
                MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("bundle", bundleFile.name, bundleFile.asRequestBody())
                    .build()
            )
            .build()

        client.newCall(request).execute().use { response ->
            val statusCode = response.code
            println("Upload status code: $statusCode")
            println("Upload result: ${response.body!!.string()}")
            if (statusCode != 201) {
                error("Upload error to Central repository. Status code $statusCode.")
            }
        }
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
        maven(url = mavenSnapshotPublishUrl)

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
        tasks.filterIsInstance<org.gradle.jvm.tasks.Jar>()
            .forEach {
                it.metaInf {
                    from("$rootDir") {
                        include("LICENSE")
                    }
                }
            }
    }
}

// Fix warnings in all projects.
subprojects {
    fun KotlinCommonCompilerOptions.configCompilerWarnings() {
        freeCompilerArgs.addAll(
            // Suppress expect/actual classes are in Beta warning.
            "-Xexpect-actual-classes",

        )
    }
    plugins.withId("org.jetbrains.kotlin.multiplatform") {
        extensions.configure<KotlinMultiplatformExtension> {
            targets.configureEach {
                compilations.configureEach {
                    compileTaskProvider.get().compilerOptions {
                        configCompilerWarnings()
                    }
                }
            }
        }
    }

    plugins.withId("org.jetbrains.kotlin.jvm") {
        extensions.configure<KotlinJvmProjectExtension> {
            compilerOptions {
                configCompilerWarnings()
            }
        }
    }
}
