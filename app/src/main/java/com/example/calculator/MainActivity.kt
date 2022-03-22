package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    private var lastDot: Boolean = false
    private var lastNumeric: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.display)
    }

    fun onDigit(view: View){
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view: View){
        tvInput?.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastDot = true
            lastNumeric = false
        }
    }

    fun onEquals(view: View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try{
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                    print("\n\nValue = $tvValue\n\n")
                }

                if(tvValue?.contains("-")){
                    var tvSplit = tvValue?.split("-")

                    var one = prefix + tvSplit[0]
                    var two = tvSplit[1]
                    var result = (one.toDouble() - two.toDouble()).toString()
                    tvInput?.text = removeZero(result)

                }else if(tvValue?.contains("+")){
                    var tvSplit = tvValue?.split("+")

                    var one = prefix + tvSplit[0]
                    var two = tvSplit[1]
                    var result = (one.toDouble() + two.toDouble()).toString()
                    tvInput?.text = removeZero(result)

                }else if(tvValue?.contains("*")){
                    var tvSplit = tvValue?.split("*")

                    var one = prefix + tvSplit[0]
                    var two = tvSplit[1]
                    var result = (one.toDouble() * two.toDouble()).toString()
                    tvInput?.text = removeZero(result)

                }else if(tvValue?.contains("/")){
                    var tvSplit = tvValue?.split("/")

                    var one = prefix + tvSplit[0]
                    var two = tvSplit[1]
                    var result = (one.toDouble() / two.toDouble()).toString()
                    tvInput?.text = removeZero(result)
                }

            }catch(e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZero(result: String): String {
        var value = result
        if(value.contains(".0"))
            value.substring(0, value.length - 2)
        return value
    }

    fun onOperator(view: View){
        tvInput?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    private fun isOperatorAdded(textInput: String) : Boolean{
        return if(textInput.startsWith("-")){
            false
        }else{
            (textInput.contains("+") || textInput.contains("-") || textInput.contains("*") || textInput.contains("/"))
        }
    }


}

