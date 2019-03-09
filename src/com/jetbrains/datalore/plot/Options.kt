package com.jetbrains.datalore.plot

import kotlin.reflect.full.memberProperties

open class Options<T> {
    private var optionsMapInitialized = false
    private val optionsMap = mutableMapOf<String, Any>()

    init {
        val properties = this::class.memberProperties
        if (properties.size > 20) throw IllegalStateException("Too many options: ${properties.size}")
    }

    open operator fun component1(): Any? = null
    open operator fun component2(): Any? = null
    open operator fun component3(): Any? = null
    open operator fun component4(): Any? = null
    open operator fun component5(): Any? = null
    open operator fun component6(): Any? = null
    open operator fun component7(): Any? = null
    open operator fun component8(): Any? = null
    open operator fun component9(): Any? = null
    open operator fun component10(): Any? = null
    open operator fun component11(): Any? = null
    open operator fun component12(): Any? = null
    open operator fun component13(): Any? = null
    open operator fun component14(): Any? = null
    open operator fun component15(): Any? = null
    open operator fun component16(): Any? = null
    open operator fun component17(): Any? = null
    open operator fun component18(): Any? = null
    open operator fun component19(): Any? = null
    open operator fun component20(): Any? = null

    fun put(k: String, v: Any?) {
        ensureInitialized()
        putIntern(k, v)
    }

    fun has(k: String) = optionsMap.containsKey(k)
    fun get(k: String) = optionsMap[k] ?: throw IllegalArgumentException("No such option: '$k'")

    private fun ensureInitialized() {
        if (!optionsMapInitialized) {
            optionsMapInitialized = true

            val optionNames: List<String> = this::class.memberProperties.map { it.name }.toList()
            val count = optionNames.size
            val (
                c1,
                c2,
                c3,
                c4,
                c5,
                c6,
                c7,
                c8,
                c9,
                c10,
                c11,
                c12,
                c13,
                c14,
                c15,
                c16,
                c17,
                c18,
                c19,
                c20
            ) = this

            fun put_(index: Int, value: Any?) {
                if (count > index) {
                    putIntern(optionNames[index], value)
                }
            }

            put_(0, c1)
            put_(1, c2)
            put_(2, c3)
            put_(3, c4)
            put_(4, c5)
            put_(5, c6)
            put_(6, c7)
            put_(7, c8)
            put_(8, c9)
            put_(9, c10)
            put_(10, c11)
            put_(11, c12)
            put_(12, c13)
            put_(13, c14)
            put_(14, c15)
            put_(15, c16)
            put_(16, c17)
            put_(17, c18)
            put_(18, c19)
            put_(19, c20)
        }
    }

    private fun putIntern(k: String, v: Any?) {
        if (v == null) {
            optionsMap.remove(k)
        } else {
            optionsMap[k] = v
        }
    }
}
