package com.example.rhino_vs_liquidcore

import org.mozilla.javascript.Context
import org.mozilla.javascript.ScriptableObject
import org.mozilla.javascript.Function

class RhinoExample() {

    private val rhino: Context = Context.enter().also {
        // Turn off optimization to make Rhino Android compatible
        it.optimizationLevel = -1
    }
    private val globalScope: ScriptableObject = rhino.initStandardObjects()

    fun testFun(initialNumber: Int): Int {
        rhino.evaluateString(
            globalScope,
            "function increaseNumber(number) {\n" +
                    "          return number + 10;\n" +
                    "}",
            "JavaScript",
            1,
            null
        )

        // Get the functionName defined in JavaScriptCode
        val function = globalScope.get("increaseNumber") as Function
        val result = function.call(rhino, globalScope, globalScope, arrayOf<Any>(initialNumber))
        return result.toString().toDouble().toInt()
    }
}