plugins {
    kotlin("jvm")
    `maven-publish`
    signing
}

val serializationVersion = extra["serialization.version"] as String

dependencies {
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

    testImplementation(kotlin("test"))
}


val artifactBaseName = "lets-plot-kotlin-json"
val artifactGroupId = project.group as String
val artifactVersion = project.version as String

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("letsPlotKotlinJson") {
                groupId = artifactGroupId
                artifactId = artifactBaseName
                version = artifactVersion

                from(components["java"])

                pom {
                    name.set("Lets-Plot Kotlin Util")
                    description.set(
                        "Lets-Plot Kotlin Util For Json Serialization."
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
