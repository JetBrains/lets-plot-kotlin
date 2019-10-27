package frontendApi

interface FrontendContext {
    fun info(s: String)
    fun warning(s: String)
    fun error(s: String)
    fun crash(s: String, t: Throwable)
}