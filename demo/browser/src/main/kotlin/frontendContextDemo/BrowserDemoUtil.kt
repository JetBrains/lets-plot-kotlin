/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo

import java.awt.Desktop
import java.io.File

object BrowserDemoUtil {
    private const val ROOT_PROJECT = "lets-plot-kotlin"

    fun openInBrowser(file: File) {
        val desktop = Desktop.getDesktop()
        desktop.browse(file.toURI())
    }

    fun createTemporaryFile(demoProjectRelativePath: String): File {
        val rootPath = getRootPath()
        println("Project root: $rootPath")
        val tmpDir = File(rootPath, "$demoProjectRelativePath/build/tmp")
        val file = File.createTempFile("index", ".html", tmpDir)
        println(file.canonicalFile)
        return file
    }

    private fun getRootPath(): String {
        // works when launching from IDEA
        val projectRoot = System.getenv()["PWD"] ?: throw IllegalStateException("'PWD' env variable is not defined")

        if (!projectRoot.contains(ROOT_PROJECT)) {
            throw IllegalStateException("'PWD' is not pointing to $ROOT_PROJECT : $projectRoot")
        }
        return projectRoot
    }
}