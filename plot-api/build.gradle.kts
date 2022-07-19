/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

import java.time.LocalDateTime

plugins {
    kotlin("multiplatform")
    `maven-publish`
    signing
    id("org.jetbrains.dokka")
}

val publicVersion: String = "3.3.0"
val rootDir = "${projectDir.toString().replace("\\", "/")}/.."
val docsDir = "$rootDir/docs"
val customFooterMessage = "Copyright © 2019-${LocalDateTime.now().year} JetBrains s.r.o."
val customStyleSheet = "$docsDir/source/custom.css"
val customScript = "$docsDir/source/custom.js"

tasks.dokkaHtml {
    moduleName.set("Lets-Plot-Kotlin $publicVersion")
    outputDirectory.set(File("$docsDir/api-reference"))
    pluginsMapConfiguration.set(mapOf("org.jetbrains.dokka.base.DokkaBase" to """{ "footerMessage": "$customFooterMessage", "customStyleSheets": ["$customStyleSheet"], "customAssets": ["$customScript"]}"""))
    dokkaSourceSets {
        configureEach {
            skipDeprecated.set(true)
            includes.from("$docsDir/source/packages.md")
            perPackageOption {
                matchingRegex.set(".*\\.frontend.*")
                suppress.set(true)
            }
            perPackageOption {
                matchingRegex.set(".*\\.intern.*")
                suppress.set(true)
            }
        }
    }
}

@Suppress("PropertyName")
val kotlinLogging_version: String by project

@Suppress("PropertyName")
val lets_plot_version: String by project
val datetime_version: String by project

kotlin {
    jvm()
    js(BOTH) {
        browser()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation("io.github.microutils:kotlin-logging:$kotlinLogging_version")

                api("org.jetbrains.lets-plot:base-portable:$lets_plot_version")
                api("org.jetbrains.lets-plot:plot-base-portable:$lets_plot_version")
                api("org.jetbrains.lets-plot:plot-common-portable:$lets_plot_version")
                api("org.jetbrains.lets-plot:plot-builder-portable:$lets_plot_version")
                api("org.jetbrains.lets-plot:plot-config-portable:$lets_plot_version")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$datetime_version")
            }
        }
        val jvmMain by getting {
            dependencies {
                api("org.jetbrains.lets-plot:lets-plot-common:$lets_plot_version")
                // Use "-jvm" variant to work around the issue where LPK JS (IR) artefact becomes dependent on
                // the "kotlinx-datetime".
                // See also:
                // https://youtrack.jetbrains.com/issue/KT-52812/JSIR-compiler-error-Could-not-find-orgjetbrainskotlinxkotlinx-datetime-in-USERLibraryApplication-Supportkotlindaemon
                compileOnly("org.jetbrains.kotlinx:kotlinx-datetime-jvm:$datetime_version")

                compileOnly("org.jetbrains.lets-plot:lets-plot-batik:$lets_plot_version")
                compileOnly("org.jetbrains.lets-plot:lets-plot-jfx:$lets_plot_version")
                compileOnly("org.jetbrains.lets-plot:lets-plot-image-export:$lets_plot_version")
            }
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

                // Add "javadocs" to each publication or Maven won't publish it.
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
                signing.sign(it)
            }
        }

        repositories {
            maven {
                val sonatypeUrl: String by extra
                url = uri(sonatypeUrl)

                val buildSettings: Map<String, Any?> by project

                @Suppress("UNCHECKED_CAST")
                val sonatype = (buildSettings["sonatype"] as? Map<String, String?>) ?: emptyMap()
                credentials {
                    username = sonatype["username"]
                    password = sonatype["password"]
                }
            }
            mavenLocal {
                val localMavenRepository: String by project
                url = uri(localMavenRepository)
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
                lets_plot.version=$lets_plot_version
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

