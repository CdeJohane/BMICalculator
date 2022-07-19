package com.example.bmicalculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import java.lang.IllegalStateException
import kotlin.math.pow

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialisations
        val textWeight = findViewById<EditText>(R.id.etWeight)
        val textHeight = findViewById<EditText>(R.id.etHeight)
        val calBtn = findViewById<Button>(R.id.calculateBtn)


        //When Calculate Button is Pressed
        calBtn.setOnClickListener {
            if ((textHeight.text.toString() != "")&&(textWeight.text.toString() !="")) {

                //Get Values from input
                val metreHeight = (textHeight.text.toString().toFloat())/100
                val kgWeight = textWeight.text.toString().toFloat()

                //Calculate BMI
                val bmi = kgWeight / (metreHeight.pow(2))

                //Place BMI result to 2dp
                val bmiTwoDp = String.format("%.2f", bmi).toFloat()

                //call display BMI
                displayBmi(bmiTwoDp)
            }
            else{
                Toast.makeText(applicationContext,"Please Input Your Height And Your Weight", Toast.LENGTH_LONG).show()
            }
        }
    }

    //Function to display bmi
    private fun displayBmi(bmi:Float){
        Log.i("MYTAG", "MainActivity: DisplayBMI run")
        //Initialisations
        val resultIndex = findViewById<TextView>(R.id.tvBMIvalue)
        val resultText = findViewById<TextView>(R.id.tvResult)

        //Get Text
        resultIndex.text = bmi.toString()

        //Set define colour and result test
        var resultTextString = ""

        //Assign Final Values to them
        resultTextString = bmiWord(resultIndex.text.toString().toFloat())
        resultText.setTextColor(bmiColour(resultTextString))
        resultText.text = resultTextString

    }

    //Function to return text based off of bmi
    private fun bmiWord(value: Float): String{
        Log.i("MYTAG", "BMI Word Run")
        when{
            value.equals(0) ->return "Invalid BMI"
            value<18.5 -> return "Underweight"
            (value >=18.5)&&(value<24.5) -> return "Normal"
            (value > 24.5)&&(value<30.0) -> return "Overweight"
            value > 30.0 -> return "Obese"
        }
        return ""
    }

    //Return Color based off of bmiWord
    private fun bmiColour(resultName: String):Int{
        when(resultName){
            //How to get the color of a certain thing from colors.xml file
            "Invalid BMI" -> return ContextCompat.getColor(applicationContext,R.color.under)
            "Underweight" -> return ContextCompat.getColor(applicationContext,R.color.under)
            "Normal" -> return ContextCompat.getColor(applicationContext,R.color.fine)
            "Overweight" -> return ContextCompat.getColor(applicationContext,R.color.over)
            "Obese" -> return ContextCompat.getColor(applicationContext,R.color.obese)
        }
        return ContextCompat.getColor(applicationContext,R.color.teal_700)
    }
}