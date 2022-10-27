package com.example.task1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception
import kotlin.math.sqrt
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calculateButton: Button = (findViewById(R.id.button))
        calculateButton.setOnClickListener{
            Toast.makeText(this, "Enter numbers", Toast.LENGTH_SHORT).show()
            val output: TextView = findViewById(R.id.output)

            val squareText: EditText = findViewById(R.id.square)
            val xText: EditText = findViewById(R.id.x)
            val constantText: EditText = findViewById(R.id.constant)

            var square : Double?
            var x : Double?
            var constant : Double?

            try{
                square = squareText.text.toString().toDouble()
                x = xText.text.toString().toDouble()
                constant = constantText.text.toString().toDouble()
                output.text = CalculateSquareRoots(square, x, constant)
            }catch (e : Exception){
                output.text = "Please enter correct numbers!"
            }

/*
                //square = squareText.text.toString().toDouble()
                //x  = xText.text.toString().toDouble()
                //constant = constantText.text.toString().toDouble()
                output.text = CalculateSquareRoots(square, x, constant)
                Toast.makeText(this, "Enter numbers", Toast.LENGTH_SHORT).show()
*/


        }
    }



    fun CalculateSquareRoots(square: Double, x: Double, constant: Double): String {
        var output: String = ""
        var root1: Double
        var root2: Double
        val delta: Double = Math.pow(x, 2.0) - 4 * square * constant
        if ( square != 0.0){
            if ( delta < 0){
                output = "Delta is negative"
            }
            else if (delta == 0.0){
                root1 = (((-x) / (2 * square)) * 100.0).roundToInt() / 100.0
                output = " Square root is equal to " + root1.toString()
            }
            else {
                root1 =(((-x + sqrt(delta)) / (2 * square)) * 100.0).roundToInt() / 100.0
                root2 = (((-x - sqrt(delta)) / (2 * square)) * 100.0).roundToInt() / 100.0
                output = " Square roots are equal to " + root1.toString() + " and " + root2.toString()
            }
        }
        else
            output = "Equation is linear"

        return output
    }
}