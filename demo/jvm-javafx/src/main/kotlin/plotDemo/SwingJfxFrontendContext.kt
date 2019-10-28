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
}