package frontendApi

class SimpleTextFrontendContext : TextFrontendContext {
    override fun displayText(s: String) {
        println(s)
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