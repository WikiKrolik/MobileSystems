package com.example.task2

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    private var diceNumber: Int = 1
    val dices = arrayOf("1", "2", "3")

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )

        val spinner: Spinner = findViewById(R.id.spinner2)
        val arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dices)

        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                diceNumber = p2 + 1
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

    }

    override fun onSensorChanged(event: SensorEvent?) {
        val accelerometerText: TextView = findViewById(R.id.accelerometer_data)
        //val diceNumber: EditText = findViewById(R.id.diceNumber)
        try{
            if (event != null) {
                if(event.values[0] >= 5){
                    diceRoll(diceNumber)
                }
            }
        }
        catch (e : Exception){
            accelerometerText.text = "Enter integer number"
        }

    }
    private fun diceRoll(number: Int){
        val _dice1: Dice = Dice(6)
        val _dice2: Dice = Dice(6)
        val _dice3: Dice = Dice(6)

        val dice1: ImageView = findViewById(R.id.dice1)
        val dice2: ImageView = findViewById(R.id.dice2)
        val dice3: ImageView = findViewById(R.id.dice3)

        val dice1text : TextView = findViewById(R.id.dice1text)
        val dice2text : TextView = findViewById(R.id.dice2text)
        val dice3text : TextView = findViewById(R.id.dice3text)

        when (number){
            1 -> {
                val diceRoll : Int = _dice1.roll()

                dice1text.text = diceRoll.toString()
                dice2text.text = ""
                dice3text.text = ""

                dice2.setImageResource(0)
                dice3.setImageResource(0)

                when (diceRoll) {
                    1 -> dice1.setImageResource(R.drawable.dice_1)
                    2 -> dice1.setImageResource(R.drawable.dice_2)
                    3 -> dice1.setImageResource(R.drawable.dice_3)
                    4 -> dice1.setImageResource(R.drawable.dice_4)
                    5 -> dice1.setImageResource(R.drawable.dice_5)
                    6 -> dice1.setImageResource(R.drawable.dice_6)
                }
            }
            2 -> {
                val diceRoll1 : Int = _dice1.roll()
                val diceRoll2 : Int = _dice2.roll()

                dice1text.text = diceRoll1.toString()
                dice2text.text = diceRoll2.toString()
                dice3text.text = ""

                dice3.setImageResource(0)

                when (diceRoll1) {
                    1 -> dice1.setImageResource(R.drawable.dice_1)
                    3 -> dice1.setImageResource(R.drawable.dice_3)
                    2 -> dice1.setImageResource(R.drawable.dice_2)
                    4 -> dice1.setImageResource(R.drawable.dice_4)
                    5 -> dice1.setImageResource(R.drawable.dice_5)
                    6 -> dice1.setImageResource(R.drawable.dice_6)
                }
                when (diceRoll2) {
                    1 -> dice2.setImageResource(R.drawable.dice_1)
                    2 -> dice2.setImageResource(R.drawable.dice_2)
                    3 -> dice2.setImageResource(R.drawable.dice_3)
                    4 -> dice2.setImageResource(R.drawable.dice_4)
                    5 -> dice2.setImageResource(R.drawable.dice_5)
                    6 -> dice2.setImageResource(R.drawable.dice_6)
                }

            }
            3 -> {
                val diceRoll1 : Int = _dice1.roll()
                val diceRoll2 : Int = _dice2.roll()
                val diceRoll3 : Int = _dice3.roll()

                dice1text.text = diceRoll1.toString()
                dice2text.text = diceRoll2.toString()
                dice3text.text = diceRoll3.toString()

                when (diceRoll1) {
                    1 -> dice1.setImageResource(R.drawable.dice_1)
                    2 -> dice1.setImageResource(R.drawable.dice_2)
                    3 -> dice1.setImageResource(R.drawable.dice_3)
                    4 -> dice1.setImageResource(R.drawable.dice_4)
                    5 -> dice1.setImageResource(R.drawable.dice_5)
                    6 -> dice1.setImageResource(R.drawable.dice_6)
                }
                when (diceRoll2) {
                    1 -> dice2.setImageResource(R.drawable.dice_1)
                    2 -> dice2.setImageResource(R.drawable.dice_2)
                    3 -> dice2.setImageResource(R.drawable.dice_3)
                    4 -> dice2.setImageResource(R.drawable.dice_4)
                    5 -> dice2.setImageResource(R.drawable.dice_5)
                    6 -> dice2.setImageResource(R.drawable.dice_6)
                }
                when (diceRoll3) {
                    1 -> dice3.setImageResource(R.drawable.dice_1)
                    2 -> dice3.setImageResource(R.drawable.dice_2)
                    3 -> dice3.setImageResource(R.drawable.dice_3)
                    4 -> dice3.setImageResource(R.drawable.dice_4)
                    5 -> dice3.setImageResource(R.drawable.dice_5)
                    6 -> dice3.setImageResource(R.drawable.dice_6)
                }
            }
            else -> Toast.makeText(this, "Enter integer number from 1 to 3", Toast.LENGTH_SHORT).show()
        }

    }
}
class Dice(val number: Int) {
    fun roll(): Int {
        return (1..number).random();

    }
}