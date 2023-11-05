package com.bmi

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bmi.R.*

class MainActivity : AppCompatActivity() {

    lateinit var calculate_btn : Button
    lateinit var status : TextView
    lateinit var etHeight : EditText
    lateinit var etWeight : EditText
    lateinit var im : ImageView
    lateinit var ReCalculate : Button
    lateinit var bmi_tv : TextView
    lateinit var bmi : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        calculate_btn=findViewById(id.calculate_btn)
        status=findViewById(id.status)
        etHeight=findViewById(id.Height)
        etWeight=findViewById(id.Weight)
        ReCalculate=findViewById(id.ReCalculate)
        bmi_tv=findViewById(id.bmi_tv)
        bmi=findViewById(id.bmi)
        im= findViewById(id.im)
        calculate_btn.setOnClickListener {



            if (etHeight.text.isNotEmpty() && etWeight.text.isNotEmpty()) {
                val height = (etHeight.text.toString()).toInt()
                val weight = (etWeight.text.toString()).toInt()

                val BMI = calculateBMI(height, weight)

                bmi.text = BMI.toString()
                bmi.visibility = View.VISIBLE

                if (BMI < 18.5) {
                    status.text = "Under Weight"
                    im.setImageResource(R.drawable.lean)

                } else if (BMI >= 18.5 && BMI < 24.9) {
                    status.text = "Healthy"
                    im.setImageResource(R.drawable.healthy)

                } else if (BMI >= 24.9 && BMI < 30) {
                    status.text = "Overweight"
                    im.setImageResource(R.drawable.overweight)

                } else if (BMI >= 30) {
                    im.setImageResource(R.drawable.obisity)
                    status.text = "Suffering from Obesity"

                }

                im.visibility = View.VISIBLE
                bmi_tv.visibility = View.VISIBLE
                status.visibility = View.VISIBLE

                ReCalculate.visibility = View.VISIBLE
                calculate_btn.visibility = View.GONE

            }


            else {
                Toast.makeText(this, "please enter the valid height and weight", Toast.LENGTH_SHORT)
                    .show()
            }
            ReCalculate.setOnClickListener {
                ResetEverything()
            }
        }

    }
    fun calculateBMI(height: Int, weight: Int): Float {

        val Height_in_metre = height.toFloat() / 100
        val BMI = weight.toFloat() / (Height_in_metre * Height_in_metre)

        return BMI
    }
    fun ResetEverything() {

        calculate_btn.visibility = View.VISIBLE
        ReCalculate.visibility = View.GONE

        im.visibility = View.GONE
        etHeight.text.clear()
        etWeight.text.clear()
        status.text = " "
        bmi.text = " "
        bmi_tv.visibility = View.GONE
    }
}
