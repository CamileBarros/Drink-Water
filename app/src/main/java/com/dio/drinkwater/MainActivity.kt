package com.dio.drinkwater

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.dio.drinkwater.model.ResultCalcule
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var result: ResultCalcule
    private var resultMl = 0.0

    lateinit var timePickerDialog: TimePickerDialog
    lateinit var calendar: Calendar
    var actualHour = 0
    var actualMinutes = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
        result = ResultCalcule()

        btCalcule.setOnClickListener {
            if (scrnPound.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.toast_pound, Toast.LENGTH_SHORT).show()
            } else if (scrnAge.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.toast_age, Toast.LENGTH_SHORT).show()
            }else{
                val pound = scrnPound.text.toString().toDouble()
                val age = scrnAge.text.toString().toInt()
                result.totalCalculeMl(pound, age)
                resultMl = result.resultMl()
                val change = NumberFormat.getNumberInstance(Locale("pt, BR"))
                change.isGroupingUsed = false
                textResult.text = change.format(resultMl) + " " + "ml"
            }

        }

        icRefresh.setOnClickListener {

            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_description)
                .setPositiveButton("Ok") { dialogInterface, i ->
                    scrnPound.setText("")
                    scrnAge.setText("")
                    textResult.text = ""

                }

            alertDialog.setNegativeButton("Cancelar") { dialogInterface, i ->

            }

            val dialog = alertDialog.create()
            dialog.show()
        }

        btRemember.setOnClickListener {

            calendar = Calendar.getInstance()
            actualHour = calendar.get(Calendar.HOUR_OF_DAY)
            actualMinutes = calendar.get(Calendar.MINUTE)
            timePickerDialog = TimePickerDialog(this,{timePicker: TimePicker, hourOfDay: Int, minutes: Int ->
                textHour.text = String.format("%02d", hourOfDay)
                textMinutes.text = String.format("%02d", minutes)
            }, actualHour, actualMinutes, true)
            timePickerDialog.show()
        }

        btClock.setOnClickListener {

            if (!textHour.text.toString().isEmpty() && !textMinutes.text.toString().isEmpty()){
                val intent = Intent(AlarmClock.ACTION_SET_ALARM)
                intent.putExtra(AlarmClock.EXTRA_HOUR, textHour.text.toString().toInt())
                intent.putExtra(AlarmClock.EXTRA_MINUTES, textMinutes.text.toString().toInt())
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, getString(R.string.message_alarm))
                startActivity(intent)

                if(intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
            }
        }
    }
}