/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

plugins {
    kotlin("jvm")
    `maven-publish`
    signing
}

dependencies {
    implementation(kotlin("reflect"))

    val geotools_version: String by project

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

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications.create<MavenPublication>("mavenJava") {
        from(components["java"])
    }
    publications.all {
        this as MavenPublication
        pom {
            name.set("Lets-Plot Kotlin GeoTools Bridge")
            val geotools_version: String by project
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
            // Add dependency on 'geotools': gt-geojson, gt-main
//                withXml {
//                    def deps = asNode().appendNode('dependencies')
//                    def dep = deps.appendNode('dependency')
//                    dep.appendNode('groupId', 'org.geotools')
//                    dep.appendNode('artifactId', 'gt-geojson')
//                    dep.appendNode('version', geotools_version)
//                    dep = deps.appendNode('dependency')
//                    dep.appendNode('groupId', 'org.geotools')
//                    dep.appendNode('artifactId', 'gt-main')
//                    dep.appendNode('version', geotools_version)
//                }
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

signing {
    sign(publishing.publications)
}
