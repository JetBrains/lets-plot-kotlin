import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    `maven-publish`
    signing
    id("com.google.devtools.ksp")
    kotlin("jupyter.api")
}

repositories {
    mavenCentral()
}

val geoToolsVersion = extra["geotools.version"] as String

dependencies {
    compileOnly(projects.plotApi)
    // basic LPK jupyter integration
    compileOnly(projects.jupyter)

    // geotools implementations
    implementation(projects.geotools)
    implementation("org.geotools:gt-main:$geoToolsVersion")
    implementation("org.geotools:gt-geojson:$geoToolsVersion")

    testImplementation(projects.plotApi)
    testImplementation(projects.jupyter)
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(11)
}
tasks.withType<KotlinCompile>().all {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

tasks.withType<JavaCompile>().all {
    sourceCompatibility = JavaVersion.VERSION_11.toString()
    targetCompatibility = JavaVersion.VERSION_11.toString()
}

tasks.processJupyterApiResources {
    libraryProducers = listOf("org.jetbrains.letsPlot.toolkit.geotools.jupyter.Integration")
}

val artifactBaseName = "lets-plot-kotlin-geotools-jupyter"
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
            create<MavenPublication>("letsPlotKotlinGeotoolsJupyter") {

                groupId = artifactGroupId
                artifactId = artifactBaseName
                version = artifactVersion

                from(components["java"])
                artifact(jarJavaDocs)

                pom {
                    name.set("Lets-Plot Kotlin Geotools Jupyter Integration")
                    description.set(
                        "Lets-Plot Kotlin Geotools Integration For Kotlin Jupyter Kernel."
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
