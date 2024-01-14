package com.bmi

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bmi.R.*

class MainActivity : AppCompatActivity() {

    lateinit var calculateBtn : Button
    lateinit var status : TextView
    lateinit var name : EditText
    lateinit var etHeight : EditText
    lateinit var etWeight : EditText
    lateinit var im : ImageView
    private lateinit var reCalculate : Button
    private lateinit var bmiTv : TextView
    lateinit var bmi : TextView
    lateinit var reset : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)


        calculateBtn=findViewById(id.calculate_btn)
        status=findViewById(id.status)
        etHeight=findViewById(id.Height)
        etWeight=findViewById(id.Weight)
        reCalculate=findViewById(id.ReCalculate)
        bmiTv=findViewById(id.bmi_tv)
        bmi=findViewById(id.bmi)
        im= findViewById(id.im)
        reset = findViewById(id.Reset)

        calculateBtn.setOnClickListener {
            bmi()
        }



    }

    fun bmi() : Int {
        if (etHeight.text.isNotEmpty() && etWeight.text.isNotEmpty()) {
            val bmiVal = calculateBMI(etHeight.text.toString().toInt(), etWeight.text.toString().toInt())

            bmi.text = bmiVal.toString()
            bmi.visibility = View.VISIBLE

            if (bmiVal < 18.5) {
                status.text = "Under Weight"
                im.setImageResource(drawable.lean)

            } else if (bmiVal >= 18.5 && bmiVal < 24.9) {
                status.text = "Healthy"
                im.setImageResource(drawable.healthy)

            } else if (bmiVal >= 24.9 && bmiVal < 30) {
                status.text = "Overweight"
                im.setImageResource(drawable.overweight)

            } else if (bmiVal >= 30) {
                im.setImageResource(drawable.obisity)
                status.text = "Suffering from Obesity"

            }



            im.visibility = View.VISIBLE
            bmiTv.visibility = View.VISIBLE
            status.visibility = View.VISIBLE

            reCalculate.visibility = View.VISIBLE
            reset.visibility = View.VISIBLE
            calculateBtn.visibility = View.GONE

        }

        else {
            Toast.makeText(this, "please enter the valid height and weight", Toast.LENGTH_SHORT)
                .show()
        }
        reCalculate.setOnClickListener {
            bmi()
        }
        reset.setOnClickListener{
            resetEverything()
        }
        return 0
    }
    private fun calculateBMI(height: Int, weight: Int): Float {

        val heightInMetre = height.toFloat() / 100

        return weight.toFloat() / (heightInMetre * heightInMetre)
    }
    private fun resetEverything() {

        calculateBtn.visibility = View.VISIBLE
        reCalculate.visibility = View.GONE
        im.visibility = View.GONE
        etHeight.text.clear()
        etWeight.text.clear()
        status.text = " "
        bmi.text = " "
        bmiTv.visibility = View.GONE
        reset.visibility =View.GONE
    }
   override fun onCreateOptionsMenu(menu : Menu?): Boolean {
       menuInflater.inflate(R.menu.menu_br,menu)
       return true
   }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){

            id.history ->{
                startActivity(Intent(this,About::class.java))
                true
            }
            else -> {true}
        }
    }

}
