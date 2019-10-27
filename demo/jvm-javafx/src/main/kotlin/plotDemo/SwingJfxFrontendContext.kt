package plotDemo

import frontendApi.RawObjectFrontendContext
import jetbrains.datalorePlot.intern.Plot

class SwingJfxFrontendContext : RawObjectFrontendContext {
    override fun displayObject(o: Any) {
        when (o) {
            is Plot -> SwingDemoUtil.display(o)
            else -> throw IllegalArgumentException("Unsupported display object: ${o::class.simpleName}")
        }
    }

    override fun info(s: String) {
        println("INFO: $s")
    }

    override fun warning(s: String) {
        println("WARN: $s")
    }

    override fun error(s: String) {
        println("ERROR: $s")
    }

    override fun crash(s: String, t: Throwable) {
        println("SEVERE ERROR: $s")
        t.printStackTrace()
    }
}