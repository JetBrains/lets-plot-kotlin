/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

plugins {
    kotlin("jvm")
    `maven-publish`
    signing
    // Add the KSP plugin before the Jupyter API to avoid ksp versions incompatibility.
    // May be removed when using further versions of the jupyter api
    id("com.google.devtools.ksp")
    // Use jupyter API version compatible with java 1.8
    kotlin("jupyter.api") version "0.12.0-139"
}

val geoToolsVersion = extra["geotools.version"] as String

dependencies {
    implementation(projects.plotApi)

    compileOnly("org.geotools:gt-main:$geoToolsVersion")
    compileOnly("org.geotools:gt-geojson:$geoToolsVersion")

    testImplementation(kotlin("test"))
    testImplementation("org.geotools:gt-main:$geoToolsVersion")
    testImplementation("org.geotools:gt-geojson:$geoToolsVersion")
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
                                "\n - org.geotools:gt-main:$geoToolsVersion" +
                                "\n - org.geotools:gt-geojson:$geoToolsVersion"
                    )
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
            }
        }

        repositories {
            mavenLocal {
                val localMavenRepository: String by project
                url = uri(localMavenRepository)
            }
        }
    }
}
signing {
    if (!(project.version as String).contains("SNAPSHOT")) {
        sign(publishing.publications)
    }
}

tasks.processJupyterApiResources {
    libraryProducers = listOf("org.jetbrains.letsPlot.toolkit.geotools.jupyter.Integration")
}
