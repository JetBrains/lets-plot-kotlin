/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

import java.time.LocalDateTime

plugins {
    id("org.jetbrains.dokka")
}

val rootDir = rootProject.projectDir.toString().replace("\\", "/")
val docsDir = "$rootDir/docs"
val buildDir = "$docsDir/build"
val dokkaDir = projectDir.toString().replace("\\", "/")
val dokkaSrcDir = "$dokkaDir/source"

val gitHubLink = "https://github.com/JetBrains/lets-plot-kotlin"
val customFooterMessage = "Copyright © 2019-${LocalDateTime.now().year} JetBrains s.r.o."
val customStyleSheet = "$dokkaSrcDir/custom.css"
val customScript = "$dokkaSrcDir/custom.js"
val logoLightImage = "$docsDir/images/logo-icon.svg"
val logoDarkImage = "$docsDir/images/logo-dark.svg"
val gitHubImage = "$docsDir/images/homepage.svg"

tasks.dokkaHtml {
    moduleName.set("Lets-Plot-Kotlin")
    outputDirectory.set(File("$buildDir/api-reference"))
    pluginsMapConfiguration.set(
        mapOf(
            "org.jetbrains.dokka.base.DokkaBase" to """{
                |"customAssets": ["$customScript", "$logoLightImage", "$logoDarkImage", "$gitHubImage"],
                |"customStyleSheets": ["$customStyleSheet"],
                |"homepageLink": "$gitHubLink",
                |"footerMessage": "$customFooterMessage"
                |}""".trimMargin()
        )
    )
    dokkaSourceSets {
        configureEach {
            skipDeprecated.set(true)
            includes.from("$dokkaSrcDir/packages.md")
            perPackageOption {
                matchingRegex.set(""".*\.frontend.*""")
                suppress.set(true)
            }
            perPackageOption {
                matchingRegex.set(""".*\.intern.*""")
                suppress.set(true)
            }
        }
        register("plotAPI") {
            displayName.set("Plot API")
            sourceRoots.from("$rootDir/plot-api/src/commonMain", "$rootDir/plot-api/src/jvmMain")
        }
    }
}