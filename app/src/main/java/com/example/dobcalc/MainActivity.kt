package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvMilliseconds: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateButton: Button = findViewById(R.id.button1)
        tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
        tvMilliseconds = findViewById(R.id.tvMilliseconds)

        dateButton.setOnClickListener{
            clickDatePicker()
        }
    }
        private fun clickDatePicker(){
            var myCalendar = Calendar.getInstance()
            var date = myCalendar.get(Calendar.DAY_OF_MONTH)
            var month = myCalendar.get(Calendar.MONTH)
            var year = myCalendar.get(Calendar.YEAR)

            var dbd = DatePickerDialog(this,
                { _, year, month, date ->

                    Toast.makeText(this,
                        "It works",
                        Toast.LENGTH_SHORT).show()

                    var selectedDate = "$date/${month+1}/$year"
                    tvSelectedDate?.text = selectedDate

                    var sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                    var theDate = sdf.parse(selectedDate)

                    theDate?.let {
                        val selectedDateInDays = theDate.time/86400000

                        var currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                        currentDate?.let {
                            val currentDateInDays = currentDate.time/86400000

                            val differenceInDays = currentDateInDays - selectedDateInDays

                            tvMilliseconds?.text= differenceInDays.toString()
                        }


                    }



                },
                date,
                month,
                year
            )

            dbd.datePicker.maxDate = System.currentTimeMillis() - 86400000
            dbd.show()

        }
}
