package com.example.rhino_vs_liquidcore

import org.liquidplayer.javascript.JSObject
import org.liquidplayer.javascript.JSValue

fun JSObject.propertyOrNull(prop: String): JSValue? {
    val value = this.property(prop)
    return if (value.isUndefined) {
        null
    } else {
        value
    }
}