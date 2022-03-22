package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.Double.parseDouble

class MainActivity : AppCompatActivity() {

    private var display: TextView? = null
    private var displayText: String = ""

    private var button0: Button? = null
    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null
    private var button4: Button? = null
    private var button5: Button? = null
    private var button6: Button? = null
    private var button7: Button? = null
    private var button8: Button? = null
    private var button9: Button? = null

    private var buttonPlus: Button? = null
    private var buttonMinus: Button? = null
    private var buttonTimes: Button? = null
    private var buttonDivide: Button? = null
    private var buttonEquals: Button? = null
    private var buttonClr: Button? = null

    private var ans: Double = 0.0
    private var operator: Char? = null
    private var mode: Char? = null
    private var term: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        //Digits
        button0 = findViewById(R.id.button0)
        button0?.setOnClickListener {
            updateTerm("0")
        }

        button1 = findViewById(R.id.button1)
        button1?.setOnClickListener {
            updateTerm("1")
        }

        button2 = findViewById(R.id.button2)
        button2?.setOnClickListener {
            updateTerm("2")
        }

        button3 = findViewById(R.id.button3)
        button3?.setOnClickListener {
            updateTerm("3")
        }

        button4 = findViewById(R.id.button4)
        button4?.setOnClickListener {
            updateTerm("4")
        }

        button5 = findViewById(R.id.button5)
        button5?.setOnClickListener {
            updateTerm("5")
        }

        button6 = findViewById(R.id.button6)
        button6?.setOnClickListener {
            updateTerm("6")
        }

        button7 = findViewById(R.id.button7)
        button7?.setOnClickListener {
            updateTerm("7")
        }

        button8 = findViewById(R.id.button8)
        button8?.setOnClickListener {
            updateTerm("8")
        }

        button9 = findViewById(R.id.button9)
        button9?.setOnClickListener {
            updateTerm("9")
        }


        //Operators
        buttonPlus = findViewById(R.id.buttonPlus)
        buttonPlus?.setOnClickListener {
            updateTotal('+')
        }

        buttonMinus = findViewById(R.id.buttonMinus)
        buttonMinus?.setOnClickListener {
            updateTotal('-')
        }

        buttonTimes = findViewById(R.id.buttonTimes)
        buttonTimes?.setOnClickListener {
            updateTotal('*')
        }

        buttonDivide = findViewById(R.id.buttonDivide)
        buttonDivide?.setOnClickListener {
            updateTotal('/')
        }

        buttonEquals = findViewById(R.id.buttonEquals)
        buttonEquals?.setOnClickListener {
            updateTotal('=')
        }

        buttonClr = findViewById(R.id.buttonClr)
        buttonClr?.setOnClickListener {
            displayText = ""
            display?.text = displayText
            mode = null
            operator = null
            ans = 0.0
            term = null
        }


    }

    private fun updateTerm(digit: String){
        println("UpdateTerm exec\nterm : $term, digit pressed : $digit\n\n")
        if(term == null){
            term = digit
//            displayText += digit
        }else {
            term += digit
//            displayText += digit
        }
//        display?.text = displayText
        display?.append(digit)
    }

    private fun updateTotal(op: Char){
        println("UpdateTotal exec\nterm: $term\n mode : $mode\n operator: $operator\n\n")
        operator = op

        if(mode != null){
            when(mode) {
                '+' -> {
                    term?.let {
                        ans += parseDouble(it)
                    }
                }
                '-' -> {
                    term?.let {
                        ans -= parseDouble(it)
                    }
                }
                '*' -> {
                    term?.let {
                        ans *= parseDouble(it)
                    }
                }
                '/' -> {
                    if (term != "0") {
                        term?.let {
                            ans /= parseDouble(it)
                        }
                    } else {
                        display?.text = getString(R.string.DivideByZeroError)
                        operator = null
                        mode = null
                        term = null
//                        displayText = ""
                        return
                    }
                }
            }
            mode = operator
            operator = null
            term = null

        }else{
            term?.let{
                ans = parseDouble(it)
                term = null
                println("mode is null, adding term to ans. ans: $ans")
            }
            mode = operator
        }

        if(op != '='){
//            displayText += op
//            display?.text = displayText
            display?.append(op.toString())
        }else{
//            displayText = ans.toString()
            display?.text = ans.toString()
            operator = null
            mode = null
            term = null
            return
        }
    }
}
