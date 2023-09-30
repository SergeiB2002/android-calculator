package com.example.calc

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.DateFormatSymbols

class MainActivity : AppCompatActivity() {

    lateinit var i1: EditText
    lateinit var i2: EditText
    lateinit var o: TextView
    lateinit var res: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        i1 = findViewById(R.id.input1)
        i1.requestFocus()
        i1.showSoftInputOnFocus = false
        i2 = findViewById(R.id.input2)
        i2.showSoftInputOnFocus = false
        o = findViewById(R.id.oper)
        res = findViewById(R.id.Result)
        val b0: Button=findViewById(R.id.btn0)
        val b1: Button=findViewById(R.id.btn1)
        val b2: Button=findViewById(R.id.btn2)
        val b3: Button=findViewById(R.id.btn3)
        val b4: Button=findViewById(R.id.btn4)
        val b5: Button=findViewById(R.id.btn5)
        val b6: Button=findViewById(R.id.btn6)
        val b7: Button=findViewById(R.id.btn7)
        val b8: Button=findViewById(R.id.btn8)
        val b9: Button=findViewById(R.id.btn9)
        val bDot: Button=findViewById(R.id.btnDot)
        val bR: Button=findViewById(R.id.btnRes)
        val bP: Button=findViewById(R.id.btnPlus)
        val bD: Button=findViewById(R.id.btnDiv)
        val bMin: Button=findViewById(R.id.btnMin)
        val bMul: Button=findViewById(R.id.btnMul)
        val bAc: Button=findViewById(R.id.btnAC)
        val bC: Button=findViewById(R.id.btnC)

        b0.setOnClickListener{inputNumDot("0")}
        b1.setOnClickListener{inputNumDot("1")}
        b2.setOnClickListener{inputNumDot("2")}
        b3.setOnClickListener{inputNumDot("3")}
        b4.setOnClickListener{inputNumDot("4")}
        b5.setOnClickListener{inputNumDot("5")}
        b6.setOnClickListener{inputNumDot("6")}
        b7.setOnClickListener{inputNumDot("7")}
        b8.setOnClickListener{inputNumDot("8")}
        b9.setOnClickListener{inputNumDot("9")}
        bDot.setOnClickListener{inputNumDot(".")}
        bP.setOnClickListener{operationButton("+")}
        bMin.setOnClickListener{operationButton("-")}
        bMul.setOnClickListener{operationButton("*")}
        bD.setOnClickListener{operationButton("/")}
        bR.setOnClickListener{equalButton()}
        bAc.setOnClickListener{buttonAC()}
        bC.setOnClickListener{buttonC()}

    }

    private fun buttonC(){
        if (i1.isFocused && i1.text.isNotEmpty()) {
            var l: Int = i1.text.length
            i1.text.delete(l-1, l)
        }
        else if (i2.isFocused && i2.text.isNotEmpty()) {
            var l: Int = i2.text.length
            i2.text.delete(l-1, l)
        }
    }

    private fun buttonAC(){
        i1.text.clear()
        i2.text.clear()
        o.text = ""
        res.text = ""
        i1.requestFocus()
    }

    private fun inputNumDot(s: String){
        if (i1.isFocused)
            i1.append(s)
        else if (i2.isFocused)
            i2.append(s)
    }

    private fun operationButton(op:String){
        var r:String

        if(i1.text.isNotEmpty()
            && o.text.isEmpty()
            && i2.text.isEmpty()
            && res.text.isEmpty()){
            o.text = op
            i2.requestFocus()
        }

        else if(i1.text.isNotEmpty()
            && o.text.isNotEmpty()
            && i2.text.isEmpty()
            && res.text.isEmpty()){
            o.text = op
            i2.requestFocus()
        }

        else if(i1.text.isNotEmpty()
            && o.text.isEmpty()
            && i2.text.isNotEmpty()
            && res.text.isEmpty()){
            o.setError(null)
            o.text = op
        }

        else if(i1.text.isNotEmpty()
            && o.text.isNotEmpty()
            && i2.text.isNotEmpty()
            && res.text.isEmpty()){
            o.setError(null)
            equalButton()
            r = res.text.toString()
            res.text = ""
            i1.text.clear()
            i1.text.append(r)
            o.text = op
            i2.text.clear()
            i2.requestFocus()
        }

        else if(i1.text.isNotEmpty()
            && res.text.isNotEmpty()){
            o.setError(null)
            res.text=""
            o.text = op
            i2.requestFocus()
        }

        else if(i2.text.isNotEmpty()
            && res.text.isNotEmpty()){
            o.setError(null)
            r = res.text.toString()
            res.text = ""
            i1.text.clear()
            i1.text.append(r)
            o.text = op
        }

        else if(res.text.isNotEmpty()){
            o.setError(null)
            r = res.text.toString()
            res.text = ""
            i1.text.clear()
            i1.text.append(r)
            o.text = op
            i2.text.clear()
            i2.requestFocus()
        }
    }

    private fun equalButton(){
        i1.requestFocus()
        i2.clearFocus()
        var r: Float = 0.0F
        if(i1.text.isEmpty()){
            i1.error = "введите число"
            i1.requestFocus()
        }

        else if(i2.text.isEmpty()){
            i2.error = "введите число"
            i2.requestFocus()
        }

        else if(o.text.isEmpty()){
            o.error = "введите знак"
        }

        else{
            var intRes: Int
            var num1 = i1.text.toString().toFloat()
            var num2 = i2.text.toString().toFloat()
            var operator = o.text.toString()
            when(operator){
                "+" -> r = num1+num2
                "-" -> r = num1-num2
                "*" -> r = num1*num2
                "/" -> {
                    if(num2 == 0f) {
                        res.text = ""
                        i2.error = "на ноль делить нельзя"
                        return
                    }
                    else
                        r = num1/num2
                    }
                }
            i1.text.clear()
            i2.text.clear()
            o.text = ""
            res.text = ""
            if(r.isInt()){
                intRes = r.toInt()
                res.text = intRes.toString()
            }
            else{
                res.text = r.toString()
            }
            }
        }

    private fun Float.isInt(): Boolean{
        return this % 1 == 0f
    }

}