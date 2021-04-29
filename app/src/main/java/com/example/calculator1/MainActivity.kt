package com.example.calculator1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.buttons.*
import android.widget.Toast
import kotlinx.android.synthetic.main.input_layout.*
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btn0.setOnClickListener {
            try {
                val firstChar = tvInput.text.subSequence(1, 1)
                if (firstChar != "0") {
                    appendOnClick(false, "0")
                }
            } catch (e: Exception) {
                Toast.makeText(this, "First Char is Zero!", Toast.LENGTH_SHORT).show()
            }

        }
        btn1.setOnClickListener { appendOnClick(true, "1") }
        btn2.setOnClickListener { appendOnClick(true, "2") }
        btn3.setOnClickListener { appendOnClick(true, "3") }
        btn4.setOnClickListener { appendOnClick(true, "4") }
        btn5.setOnClickListener { appendOnClick(true, "5") }
        btn6.setOnClickListener { appendOnClick(true, "6") }
        btn7.setOnClickListener { appendOnClick(true, "7") }
        btn8.setOnClickListener { appendOnClick(true, "8") }
        btn9.setOnClickListener { appendOnClick(true, "9") }
        btnDot.setOnClickListener {
            val misol = tvInput.text.toString()
            var amalIndex = -1
            for (i in misol.indices) {
                if (misol[i] == '+' || misol[i] == '-' || misol[i] == '*' || misol[i] == '/') {
                    amalIndex = i
                }
            }
            if (amalIndex == -1) {
                if (!tvInput.text.toString().contains('.')) {
                    tvInput.text = "${tvInput.text}."
                }
            } else {
                val ekranLength = tvInput.text.length
                val matn = tvInput.text.toString().subSequence(amalIndex, ekranLength)
                if (!matn.contains('.')) {
                    tvInput.text = "${tvInput.text}."
                }
            }
        }
        btnBracketLeft.setOnClickListener { appendOnClick(true, "(") }
        btnBracketRight.setOnClickListener { appendOnClick(true, ")") }
        btnDelete.setOnClickListener {
            val txtQuiz = tvInput.text.subSequence(0, tvInput.text.length - 1)
            tvInput.text = txtQuiz
        }

        btnPlus.setOnClickListener { appendOnClick(false, "+") }
        btnMinus.setOnClickListener { appendOnClick(false, "-") }
        btnMultiply.setOnClickListener { appendOnClick(false, "*") }
        btnDivision.setOnClickListener { appendOnClick(false, "÷") }

        btnClear.setOnClickListener {
            clear()
        }

        btnEqual.setOnClickListener {
            calculate()
        }
    }


    private fun appendOnClick(clear: Boolean, string: String) {

        if (clear) {
            tvOutput.text = ""
            tvInput.append(string)
        } else {
            tvInput.append(tvOutput.text)
            tvInput.append(string)
            tvOutput.text = ""
        }
    }

    private fun clear() {
        tvInput.text = ""
        tvOutput.text = ""

    }

    private fun calculate() {

        try {
            val input = ExpressionBuilder(tvInput.text.toString()).build()
            val output = input.evaluate()
            val longOutput = output.toLong()
            if (output == longOutput.toDouble()) {
                tvOutput.text = longOutput.toString()
            } else {
                tvOutput.text = output.toString()
            }

        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
        }
    }

}


