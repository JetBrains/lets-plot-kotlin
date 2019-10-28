package frontendApi

class SimpleTextFrontendContext : TextFrontendContext {
    override fun displayText(s: String) {
        println(s)
    }
}