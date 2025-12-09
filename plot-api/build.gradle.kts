/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.dokka")
    `maven-publish`
    signing
}

val letsPlotVersion = extra["letsPlot.version"] as String
val kotlinxDatetimeVersion = extra["kotlinx.datetime.version"] as String
val kotlinLoggingVersion = extra["kotlinLogging.version"] as String
val kotlinxCoroutinesVersion = extra["kotlinx.coroutines.version"] as String
val assertjVersion = extra["assertj.version"] as String

kotlin {
    jvm()
    js().browser()

    sourceSets {
        commonMain {
            dependencies {
                api("org.jetbrains.lets-plot:commons:$letsPlotVersion")
                api("org.jetbrains.lets-plot:datamodel:$letsPlotVersion")
                api("org.jetbrains.lets-plot:plot-base:$letsPlotVersion")
                api("org.jetbrains.lets-plot:plot-builder:$letsPlotVersion")
                api("org.jetbrains.lets-plot:plot-stem:$letsPlotVersion")

                // Required for proper building of Kotlin/JS artifacts.
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:${kotlinxDatetimeVersion}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${kotlinxCoroutinesVersion}")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:${kotlinxDatetimeVersion}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${kotlinxCoroutinesVersion}")
            }
        }

        named("jvmMain") {
            dependencies {
//                implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
                api("org.jetbrains.lets-plot:lets-plot-common:$letsPlotVersion")
                api("io.github.microutils:kotlin-logging-jvm:$kotlinLoggingVersion")

                // Use "-jvm" variant to work around the issue where LPK JS (IR) artifact becomes dependent on
                // the "kotlinx-datetime".
                // See also:
                // https://youtrack.jetbrains.com/issue/KT-52812/JSIR-compiler-error-Could-not-find-orgjetbrainskotlinxkotlinx-datetime-in-USERLibraryApplication-Supportkotlindaemon
//                compileOnly("org.jetbrains.kotlinx:kotlinx-datetime-jvm:$kotlinxDatetimeVersion")

                compileOnly("org.jetbrains.lets-plot:lets-plot-batik:$letsPlotVersion")
                compileOnly("org.jetbrains.lets-plot:lets-plot-jfx:$letsPlotVersion")
            }
        }

        named("jsMain") {
            dependencies {
                implementation("io.github.microutils:kotlin-logging-js:$kotlinLoggingVersion")
            }
        }

        jvmTest {
            dependencies {
                implementation("org.assertj:assertj-core:$assertjVersion")
            }
        }
    }
}

tasks.withType<org.jetbrains.dokka.gradle.DokkaTask>().configureEach {
    dokkaSourceSets.configureEach {
        skipDeprecated.set(true)
        includes.from("${rootProject.projectDir}/docs/dokka/source/packages.md")

        perPackageOption {
            matchingRegex.set(""".*\.frontend.*""")
            suppress.set(true)
        }
        perPackageOption {
            matchingRegex.set(""".*\.intern.*""")
            suppress.set(true)
        }
        perPackageOption {
            matchingRegex.set(""".*\.intern\.layer.*""")
            suppress.set(false)
        }
    }
}


val artifactBaseName = "lets-plot-kotlin"
val artifactGroupId = project.group as String
val artifactVersion = project.version as String

val jarJavaDocs by tasks.creating(Jar::class) {
    archiveClassifier.set("javadoc")
    group = "lets plot"
    from("$rootDir/README.md")
}

afterEvaluate {
    publishing {
        publications {
            // Build artifact "lets-plot-kotlin-kernel" with no dependencies in POM.
            create<MavenPublication>("letsPlotKotlinKernel") {
                artifactId = "$artifactBaseName-kernel"

                val jvmJar: Task by tasks
                val jvmSourcesJar: Task by tasks
                artifact(jvmJar)
                artifact(jvmSourcesJar)

                pom {
                    name.set("Lets-Plot Kotlin API (for Jupyter Kotlin Kernel)")
                    description.set("Lets-Plot Kotlin API without dependencies.")
                }
            }

        }

        publications.forEach {
            with(it as MavenPublication) {
                groupId = artifactGroupId
                version = artifactVersion

                if (!artifactId.startsWith(artifactBaseName)) {
                    // Default multiplatform artifacts: rename.
                    artifactId = artifactId.replace(project.name, artifactBaseName)
                    pom {
                        name.set("Lets-Plot Kotlin API")
                        description.set("Lets-Plot Kotlin API.")
                    }
                }

                // Add "Javadocs" to each publication or Maven won't publish it.
                artifact(jarJavaDocs)

                pom {
                    url.set("https://github.com/JetBrains/lets-plot-kotlin")
                    licenses {
                        license {
                            name.set("MIT")
                            url.set("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/LICENSE")
                        }
                    }
                    developers {
                        developer {
                            id.set("jetbrains")
                            name.set("JetBrains")
                            email.set("lets-plot@jetbrains.com")
                        }
                    }
                    scm {
                        url.set("https://github.com/JetBrains/lets-plot-kotlin")
                    }
                }

                // Sign all publications.
                if (!(project.version as String).contains("SNAPSHOT")) {
                    signing.sign(it)
                }
            }
        }

        repositories {
            mavenLocal {
                val localMavenRepository: String by project
                url = uri(localMavenRepository)
            }
            maven {
                if (version.toString().endsWith("-SNAPSHOT")) {
                    url = uri(rootProject.project.extra["mavenSnapshotPublishUrl"].toString())

                    credentials {
                        username = rootProject.project.extra["sonatypeUsername"].toString()
                        password = rootProject.project.extra["sonatypePassword"].toString()
                    }
                } else {
                    url = uri(rootProject.project.extra["mavenReleasePublishUrl"].toString())
                }
            }
        }
    }
}


tasks {
    // Store versions in properties to later access at runtime.
    val saveVersions by creating {
        doLast {
            File("${projectDir}/src/jvmMain/resources/letsPlotKotlinAPI/", "version.properties").writeText(
                """
                lets_plot.version=$letsPlotVersion
                lets_plot_kotlin_api.version=${project.version}
                """.trimIndent()
            )

        }
    }

//compileKotlin.dependsOn += saveVersions
    val jvmMainClasses by getting {
        dependsOn += saveVersions
    }
}

//task printIt {
//    print("${project.name}: ${uri(project.localMavenRepository)}")
//}

tasks {
    // Copy resources from resources/jvm/main to classes/kotlin/jvm/main to fix:
    // https://youtrack.jetbrains.com/issue/KTIJ-16582/Consumer-Kotlin-JVM-library-cannot-access-a-Kotlin-Multiplatform-JVM-target-resources-in-multi-module-Gradle-project
    val jvmProcessResources by getting
    val fixMissingResources by creating(Copy::class) {
        dependsOn(jvmProcessResources)
        from(layout.buildDirectory.dir("processedResources/jvm/main"))
        into(layout.buildDirectory.dir("classes/kotlin/jvm/main"))
    }
    // Set EXCLUDE strategy for duplicates to avoid conflicts when building DMG deliverable.
    // See: https://github.com/JetBrains/lets-plot-kotlin/issues/279
    val jvmJar by getting(Jar::class) {
        dependsOn(fixMissingResources)
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}

tasks.named("jvmTest") {
    dependsOn("fixMissingResources")
}
