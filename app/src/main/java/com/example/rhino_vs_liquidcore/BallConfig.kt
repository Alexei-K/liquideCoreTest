package com.example.rhino_vs_liquidcore

import com.google.gson.Gson
import org.json.JSONObject
import org.liquidplayer.javascript.JSContext
import org.liquidplayer.javascript.JSFunction
import org.liquidplayer.javascript.JSValue

class BallConfig(val jsContext: JSContext) {

    val jsValue = JSValue(jsContext, JSONObject())

    val color: String
        get() = JSONObject(jsValue.toJSON()).optString("color", "#000000")

    val size: Long
        get() = JSONObject(jsValue.toJSON()).optLong("size", 0L)

    fun increaseSize() {
       jsValue.toObject().propertyOrNull("increaseSize")?.toFunction()?.call(jsContext)
    }
}