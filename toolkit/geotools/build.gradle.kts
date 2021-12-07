/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

plugins {
    kotlin("jvm")
    `maven-publish`
    signing
}

@Suppress("PropertyName", "SpellCheckingInspection")
val geotools_version: String by project

dependencies {
//    implementation(kotlin("reflect"))

    implementation(projects.plotApi)

    compileOnly("org.geotools:gt-main:$geotools_version")
    compileOnly("org.geotools:gt-geojson:$geotools_version")

    testImplementation(kotlin("test"))
    testImplementation("org.geotools:gt-main:$geotools_version")
    testImplementation("org.geotools:gt-geojson:$geotools_version")
}

val artifactBaseName = "lets-plot-kotlin-geotools"
val artifactGroupId = project.group as String
val artifactVersion = project.version as String

val jarClasses by tasks.creating(Jar::class) {
    group = "lets plot"
    from(sourceSets.main.get().output)
}

val jarSources by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    group = "lets plot"
    from(sourceSets.main.get().allSource)
}

val jarJavaDocs by tasks.creating(Jar::class) {
    archiveClassifier.set("javadoc")
    group = "lets plot"
    from("$rootDir/README.md")
}

afterEvaluate {
    publishing {
        publications {
            // Build artifact with no dependencies in POM.
            create<MavenPublication>("letsPlotKotlinGeoTools") {
                groupId = artifactGroupId
                artifactId = artifactBaseName
                version = artifactVersion

                // This leads to 'maven-publish' failure: "Publishing is not able to resolve a dependency
                // on a project with multiple publications that have different coordinates."
//            from(components["java"])

                artifact(jarClasses)
                artifact(jarSources)
                artifact(jarJavaDocs)

                pom {
                    name.set("Lets-Plot Kotlin GeoTools Bridge")
                    description.set(
                        "Lets-Plot Kotlin GeoTools Bridge." +
                                "\nRequires GeoTools artifacts:" +
                                "\n - org.geotools:gt-main:$geotools_version" +
                                "\n - org.geotools:gt-geojson:$geotools_version"
                    )
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
signing {
    sign(publishing.publications)
}
