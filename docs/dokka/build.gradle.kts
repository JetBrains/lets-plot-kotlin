/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

import org.jetbrains.dokka.gradle.DokkaTask
import java.time.LocalDateTime

plugins {
    id("org.jetbrains.dokka")
}

val libVersion = "4.11.0"

val rootDirectory = rootProject.projectDir.toString().replace("\\", "/")
val docsDirectory = "$rootDirectory/docs"
val dokkaDirectory = projectDir.toString().replace("\\", "/")
val dokkaSrcDirectory = "$dokkaDirectory/source"

val gitHubLink = "https://github.com/JetBrains/lets-plot-kotlin"
val customFooterMessage = "Copyright © 2019-${LocalDateTime.now().year} JetBrains s.r.o."
val customStyleSheet = "$dokkaSrcDirectory/custom.css"
val customScript = "$dokkaSrcDirectory/custom.js"
val logoLightImage = "$docsDirectory/images/logo-icon.svg"
val logoDarkImage = "$docsDirectory/images/logo-dark.svg"
val gitHubImage = "$docsDirectory/images/homepage.svg"

evaluationDependsOn(":plot-api")

rootProject.project(":plot-api")
    .tasks.named<DokkaTask>("dokkaHtml")
    .configure {
        moduleName.set("Lets-Plot-Kotlin")
        outputDirectory.set(File("$docsDirectory/build/api-reference"))
        pluginsMapConfiguration.set(
            mapOf(
                "org.jetbrains.dokka.base.DokkaBase" to """
                {
                  "homepageLink": "$gitHubLink",
                  "footerMessage": "$customFooterMessage",
                  "customAssets": [
                    "$customScript",
                    "$logoLightImage",
                    "$logoDarkImage",
                    "$gitHubImage"
                  ],
                  "customStyleSheets": [
                    "$customStyleSheet"
                  ]
                }
                """.trimIndent()
            )
        )
    }

gradle.taskGraph.whenReady {
    if (hasTask(":plot-api:dokkaHtml")) {
        rootProject.project(":plot-api").version = libVersion
    }
}