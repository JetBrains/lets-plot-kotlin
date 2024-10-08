package org.jetbrains.letsPlot.jupyter

import kotlinx.serialization.json.*

/**
 * Serialize Lets-Plot spec into `JsonElement`
 */
internal fun serializeSpec(spec: Map<*, *>): JsonElement {
    return serialize(spec)
}

private fun serializeAny(obj: Any?): JsonElement {
    return when (obj) {
        null -> JsonNull
        is Map<*, *> -> serialize(obj)
        is List<*> -> serialize(obj)
        is String -> JsonPrimitive(obj)
        is Boolean -> JsonPrimitive(obj)
        is Number -> JsonPrimitive(obj)
        else -> error("Don't know how to parse object [$obj] of class ${obj::class}")
    }
}

private fun serialize(map: Map<*, *>): JsonObject {
    return buildJsonObject {
        for ((key, value) in map) {
            if (key !is String) error("Map key [$key] is of type ${key?.let { it::class }}. Don't know how to serialize it.")
            put(key, serializeAny(value))
        }
    }
}

private fun serialize(list: List<*>): JsonArray {
    return buildJsonArray {
        for (value in list) {
            add(serializeAny(value))
        }
    }
}
