package com.example.bmicalculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weight = findViewById<EditText>(R.id.etWeight)
        val height = findViewById<EditText>(R.id.etHeight)

        val btnCal = findViewById<Button>(R.id.btnCalc)

        btnCal.setOnClickListener{
            if(validateInputs(weight.text.toString(), height.text.toString())) {


                val result = findViewById<TextView>(R.id.tvResult)
                val resultHere = calculateBMI(
                    Integer.parseInt(weight.text.toString()),
                    Integer.parseInt(height.text.toString())
                )
                val emojis = findViewById<TextView>(R.id.tvEmoji)
                emojis.text = setEmojis(resultHere, result)
                result.text = resultHere.toString()
            }
        }
    }

    private fun calculateBMI(weight: Int = 0, height: Int = 0) : Double{
        return (weight / ((height.toDouble()/100).pow(2)))
    }

    private fun setEmojis(bmi: Double, result: TextView): String {
        if(bmi < 18.5) {
            result.setTextColor(Color.parseColor("#f44336"))
            return "💀"

        }
        if(bmi >= 18.5 && bmi < 25) {
            result.setTextColor(Color.parseColor("#6aa84f"))
            return "👏"
        }
        return if(bmi >= 25 && bmi < 29.9){
            result.setTextColor(Color.parseColor("#fda900"))
            "😥"
        } else {
            result.setTextColor(Color.parseColor("#f44336"))
            "😨"
        }

    }

    private fun validateInputs(weight: String, height: String) : Boolean {
        return when{
            weight.isNullOrEmpty() -> {

                Toast.makeText(this, "Bruh, I am not a weighing machine to know your weight!", Toast.LENGTH_SHORT)
                    .show()
                return false
            }

                 height.isNullOrEmpty() -> {
                     Toast.makeText(this, "Bruh, I am not a inch tape to know your height!",Toast.LENGTH_SHORT).show()
                 return false
                 }
            else -> {
                return true
            }

        }
    }


}