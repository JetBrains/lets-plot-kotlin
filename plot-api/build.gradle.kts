/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

plugins {
    kotlin("multiplatform")
    java
    `maven-publish`
    signing
    id("org.jetbrains.dokka")
}

tasks.dokkaHtml {
    outputDirectory.set(File("$projectDir/../docs/api-reference"))
    pluginsMapConfiguration.set(mapOf("org.jetbrains.dokka.base.DokkaBase" to """{ "footerMessage": "Copyright © 2019-2021 JetBrains s.r.o." }"""))
    dokkaSourceSets {
        configureEach {
            skipDeprecated.set(true)
        }
    }
}

@Suppress("PropertyName")
val kotlinLogging_version: String by project

@Suppress("PropertyName")
val lets_plot_version: String by project

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
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("reflect"))

                api("org.jetbrains.lets-plot:lets-plot-common:$lets_plot_version")

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

java {
    withSourcesJar()
}

publishing {
    publications {
        // Build artifact "lets-plot-kotlin-kernel" with no dependencies in POM.
        create<MavenPublication>("letsPlotKotlinKernel") {
            artifactId = "$artifactBaseName-kernel"

//            artifact(jvmJar)
//            artifact jvmSourcesJar
            from(components["java"])

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
                        url.set("https://opensource.org/licenses/MIT")
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

