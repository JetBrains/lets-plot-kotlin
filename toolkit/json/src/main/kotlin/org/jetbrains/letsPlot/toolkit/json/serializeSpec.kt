package org.jetbrains.letsPlot.toolkit.json

import kotlinx.serialization.json.*

typealias JsonMap = Map<String, Any>

fun serializeJsonMap(map: JsonMap): JsonElement {
    return serialize(map)
}

private fun serializeAny(obj: Any?): JsonElement {
    return when (obj) {
        null -> JsonNull
        is Map<*, *> -> serialize(obj)
        is List<*> -> serialize(obj)
        is String -> JsonPrimitive(obj)
        is Boolean -> JsonPrimitive(obj)
        is Number -> JsonPrimitive(obj)
        else -> error("Don't know how to serialize object [$obj] of class ${obj::class}")
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

fun deserializeJsonMap(json: JsonElement): JsonMap {
    if (json !is JsonObject) error("Input json should be a key-value object, but it's $json")
    val map = deserializeMap(json)

    // TODO: add null handling
    for (value in map.values) {
        if (value == null) error("Input json shouldn't have null values on the top level")
    }

    @Suppress("UNCHECKED_CAST")
    return map as JsonMap
}

private fun deserializeAny(json: JsonElement): Any? {
    return when (json) {
        is JsonObject -> deserializeMap(json)
        is JsonArray -> deserializeList(json)
        is JsonPrimitive -> deserializePrimitive(json)
    }
}

private fun deserializePrimitive(json: JsonPrimitive): Any? {
    return when {
        json is JsonNull -> null
        json.isString -> json.content
        else -> {
            json.booleanOrNull ?: json.doubleOrNull ?: error("Unknown JSON primitive type: [$json]")
        }
    }
}

private fun deserializeMap(json: JsonObject): Map<String, Any?> {
    return buildMap {
        for ((key, value) in json) {
            put(key, deserializeAny(value))
        }
    }
}

private fun deserializeList(jsonArray: JsonArray): List<Any?> {
    return buildList {
        for (el in jsonArray) {
            add(deserializeAny(el))
        }
    }
}
