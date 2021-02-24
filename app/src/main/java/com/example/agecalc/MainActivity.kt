package com.example.agecalc

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        val textView = findViewById<TextView>(R.id.tv1)

        // Refresh function for the layout
        swipeRefreshLayout.setOnRefreshListener{

            // Your code goes here
            // In this code, we are just changing the text in the
            // textbox
//            textView.text = "Refreshed"

            // This line is important as it explicitly refreshes only once
            // If "true" it implicitly refreshes forever
            swipeRefreshLayout.isRefreshing = false
        }
    }

    fun openDateTimePicker(view: View) {

        var c = Calendar.getInstance()

        DatePickerDialog(this,DatePickerDialog.OnDateSetListener{datePicker, yy, mm, dd ->
            var dt = "$dd/${mm+1}/$yy"
            //editTextTextPersonName.setText(dt)
//            TimePickerDialog(this,TimePickerDialog.OnTimeSetListener{timePicker, hh, mm ->
//                dt+=" $hh:$mm"
                editTextTextPersonName.setText(dt)
//            },//10,15,false ).show()   //static input
//                c.get(Calendar.HOUR) , c.get(Calendar.MINUTE) , false).show()
        },
            //2020,7,5).show()    //static input
            c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show()
    }

    fun calculateAge(view: View) {
        val uname = editTextTextPersonName2.text.toString().trim()
        if(uname=="")
        {
            Toast.makeText(this,"please enter your name" ,Toast.LENGTH_SHORT).show()
        }
        else {


            val udob = editTextTextPersonName.text.toString().trim()
            if (udob == "") {
                Toast.makeText(this, "please enter your date of birth", Toast.LENGTH_SHORT).show()
            } else {
                var today = Date()
                var dobs = editTextTextPersonName.text.toString()
                var sdf = SimpleDateFormat("dd/MM/yyyy")
                var dob = sdf.parse(dobs)
                var years = (today.time - dob.time) / (31536000000)
                var days = (today.time - dob.time) / 86400000
//        var hours = (today.time - dob.time)%8640000/3600000
//        var minutes = (today.time - dob.time)%8640000%3600000/60000
//        var seconds = (today.time - dob.time)%8640000%3600000%60000/1000
                textView.visibility = View.VISIBLE
//        TextView textView = (TextView)findViewById(R.id.textView)
//        textView.setText("HELLO")
//        val btn = findViewById(R.id.button2) as Button
//        val txt = findViewById(R.id.textView) as TextView
//        btn.setOnClickListener {
                textView.setText(" $uname , you are $years years young ✨")
//        }
            }
        }


    }
}