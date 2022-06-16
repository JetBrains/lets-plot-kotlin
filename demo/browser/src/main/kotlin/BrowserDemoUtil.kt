/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

import java.awt.Desktop
import java.io.File
import java.io.FileWriter

object BrowserDemoUtil {
    private const val DEMO_OUTPUT_RELATIVE_PATH = "demo/browser/build/tmp"

    fun openInBrowser(html: String) {
        val file = createTemporaryFile()
        FileWriter(file).use {
            it.write(html)
        }
        openInBrowser(file)
    }

    private fun openInBrowser(file: File) {
        val desktop = Desktop.getDesktop()
        desktop.browse(file.toURI())
    }

    private fun createTemporaryFile(): File {
        val tmpDir = File(getOutputPath())
        if (!tmpDir.exists()) {
            tmpDir.mkdir()
        }

        val file = File.createTempFile("index", ".html", tmpDir)
        println(file.canonicalFile)
        return file
    }

    fun getOutputPath(): String {
        return "${getRootPath()}/$DEMO_OUTPUT_RELATIVE_PATH"
    }

    private fun getRootPath(): String {
        return System.getProperty("user.dir")
    }
}