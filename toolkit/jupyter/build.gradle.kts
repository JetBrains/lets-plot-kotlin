plugins {
    kotlin("jvm")
    `maven-publish`
    signing
    id("com.google.devtools.ksp")
    kotlin("jupyter.api")
}

val letsPlotVersion = extra["letsPlot.version"] as String
//val letsPlotKotlinVersion = project.version as String
//val kotlinLoggingVersion = extra["kotlinLogging.version"] as String

dependencies {
    implementation(projects.plotApi)
    implementation(projects.json)

    implementation("org.jetbrains.lets-plot:platf-awt:$letsPlotVersion")
    implementation("org.jetbrains.lets-plot:lets-plot-image-export:$letsPlotVersion")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

tasks.withType<JavaCompile>().all {
    sourceCompatibility = JavaVersion.VERSION_11.toString()
    targetCompatibility = JavaVersion.VERSION_11.toString()
}

tasks.processJupyterApiResources {
    libraryProducers = listOf("org.jetbrains.letsPlot.toolkit.jupyter.Integration")
}

val artifactBaseName = "lets-plot-kotlin-jupyter"
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

afterEvaluate {

    publishing {
        publications {
            // Build artifact with all dependencies.
            create<MavenPublication>("letsPlotKotlinJupyter") {

                groupId = artifactGroupId
                artifactId = artifactBaseName
                version = artifactVersion

                from(components["java"])
                artifact(jarJavaDocs)

                pom {
                    name.set("Lets-Plot Kotlin Jupyter Integration")
                    description.set(
                        "Lets-Plot Kotlin Integration For Kotlin Jupyter Kernel."
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
signing {
    if (!(project.version as String).contains("SNAPSHOT")) {
        sign(publishing.publications)
    }
}

