package com.example.rhino_vs_liquidcore

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.rhino_vs_liquidcore.databinding.ActivityMainBinding
import org.liquidplayer.javascript.JSContext
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null

    private val binding get() = _binding!!

    private var config: BallConfig? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.changeColorButton.setOnClickListener {
            easyParsing()
        }
        binding.secondButton.setOnClickListener {
            mediumParsing()
        }
        binding.thirdButton.setOnClickListener {
            increaseAndReprint()
        }
    }

    /**
     * Downloads js
     * Parses js
     * Gets color from js
     * Set color to Square
     */
    private fun easyParsing() {
        val jsString = ConfigRepository(this).getConfig("ball_config_basic")

        val jsContext = JSContext()
        jsContext.evaluateScript(jsString)

        // Get the functionName defined in JavaScript Code
        val mainFunction = jsContext.property("getColor").toFunction()
        mainFunction.call(
            jsContext
        )?.let { jsValue ->
            if (jsValue.isString) {
                val color = Color.parseColor(jsValue.toString())
                binding.coolRedButton.setBackgroundColor(color)
            } else {
                throw IOException("js was corrupted")
            }
        }
    }


    /**
     * Downloads js
     * Parses js
     * Gets color from js
     * Set color to Square
     */

    private fun mediumParsing() {
        val jsString = ConfigRepository(this).getConfig("ball_config_medium")

        val jsContext = JSContext()
        jsContext.evaluateScript(jsString)

        // Get the functionName defined in Java ScriptCode
        val mainFunction = jsContext.property("setConfig").toFunction()
        config = BallConfig(jsContext).also { config ->
            mainFunction.call(
                jsContext, config.jsValue
            )
            val color = Color.parseColor(config.color)
            val size = config.size.toInt()
            binding.coolRedButton.setBackgroundColor(color)
            binding.coolRedButton.layoutParams = ConstraintLayout.LayoutParams(size, size)
        }
    }

    private fun increaseAndReprint() {
        config?.let { config ->
            config.increaseSize()
            val size = config.size.toInt()
            binding.coolRedButton.layoutParams = ConstraintLayout.LayoutParams(size, size)
        }
        Log.d("AlexLog", "${RhinoExample().testFun(90)}")
    }
}
