package plotDemo

import java.awt.Desktop
import java.io.File
import java.io.FileWriter

object BrowserDemoUtil {
    private const val ROOT_PROJECT = "datalore-plot-kotlin"

    fun openInBrowser(demoProjectRelativePath: String, html: () -> String) {

        val rootPath = getRootPath()
        println("Project root: $rootPath")
        val tmpDir = File(rootPath, "$demoProjectRelativePath/build/tmp")
        val file = File.createTempFile("index", ".html", tmpDir)
        println(file.canonicalFile)

        FileWriter(file).use {
            it.write(html())
        }

        val desktop = Desktop.getDesktop()
        desktop.browse(file.toURI())
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