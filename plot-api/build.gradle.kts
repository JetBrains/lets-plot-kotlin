/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

plugins {
    kotlin("multiplatform")
    `maven-publish`
    signing
    id("org.jetbrains.dokka")
}

tasks.dokkaHtml {
    outputDirectory.set(File("$projectDir/../docs/api-reference"))
}

kotlin {
    jvm()
    js {
        browser()
    }

    sourceSets {
        commonMain {
            dependencies {
                val kotlinLogging_version: String by project
                implementation("io.github.microutils:kotlin-logging:$kotlinLogging_version")

                val lets_plot_version: String by project
                api("org.jetbrains.lets-plot:base-portable:$lets_plot_version")
                api("org.jetbrains.lets-plot:plot-base-portable:$lets_plot_version")
                api("org.jetbrains.lets-plot:plot-common-portable:$lets_plot_version")
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

                val lets_plot_version: String by project
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

val task_group = "lets plot"

val jarJavaDocs by tasks.creating(Jar::class) {
    classifier = "javadoc"
    group = task_group
    from("$rootDir/README.md")
}

publishing {
    publications.forEach {
        with(it as MavenPublication) {
            // Since 3.0.0:
            // Artifact "lets-plot-kotlin-api"
            // replaced with multi-platform artifact "lets-plot-kotlin-jvm"
//        letsPlotKotlinApi(MavenPublication) {
//            groupId artifactGroupId
//            artifactId "$artifactBaseName-api"
//            version artifactVersion
//
//            artifact jvmJar
//            artifact jvmSourcesJar
//
//            pom {
//                name = "Lets-Plot Kotlin API"
//                description = "Lets-Plot Kotlin API."
//                // Add dependency on lets-plot-common
//                withXml {
//                    def deps = asNode().appendNode('dependencies')
//                    def dep = deps.appendNode('dependency')
//                    dep.appendNode('groupId', 'org.jetbrains.lets-plot')
//                    dep.appendNode('artifactId', 'lets-plot-common')
//                    dep.appendNode('version', lets_plot_version)
//                }
//            }
//        }

            // Since 3.0.0:
            // Artifact   "lets-plot-kotlin-api-kernel"
            // renamed to "lets-plot-kotlin-kernel"
            groupId = artifactGroupId
            artifactId = "$artifactBaseName-kernel"
            version = artifactVersion

            pom {
                name.set("Lets-Plot Kotlin API (for Jupyter Kotlin Kernel)")
                description.set("Lets-Plot Kotlin API without dependencies.")
            }

            if (!artifactId.startsWith(artifactBaseName)) {
                // Default multiplatform artifacts: rename.
                artifactId = artifactId.replace(project.name, artifactBaseName)
                pom {
                    name.set("Lets-Plot Kotlin API")
                    description.set("Lets-Plot Kotlin API.")
                }
            }

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

//signing {
//    sign publishing.publications.letsPlotKotlinApi
//    sign publishing.publications.letsPlotKotlinApiKernel
//}

tasks {
// Store versions in properties to later access at runtime.
    val saveVersions by creating {
        val lets_plot_version: String by project
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

