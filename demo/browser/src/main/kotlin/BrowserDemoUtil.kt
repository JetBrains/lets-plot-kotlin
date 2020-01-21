/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

import java.awt.Desktop
import java.io.File
import java.io.FileWriter

object BrowserDemoUtil {
    private const val ROOT_PROJECT = "lets-plot-kotlin"
    private const val DEMO_PROJECT_RELATIVE_PATH = "demo/browser"

    fun openInBrowser(html: String) {
        val file = createTemporaryFile()
        FileWriter(file).use {
            it.write(html)
        }
        openInBrowser(file)
    }

    fun openInBrowser(file: File) {
        val desktop = Desktop.getDesktop()
        desktop.browse(file.toURI())
    }

    fun createTemporaryFile(): File {
        val rootPath = getRootPath()
        println("Project root: $rootPath")
        val tmpDir = File(rootPath, "$DEMO_PROJECT_RELATIVE_PATH/build/tmp")
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