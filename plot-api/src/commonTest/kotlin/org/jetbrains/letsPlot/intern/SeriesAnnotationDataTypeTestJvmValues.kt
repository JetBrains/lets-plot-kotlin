package org.jetbrains.letsPlot.intern

expect object SeriesAnnotationDataTypeTestJvmValues {
    fun getTestValues(): Map<String, Any>
    fun getExpectedValues(): List<Map<String, String>>
}