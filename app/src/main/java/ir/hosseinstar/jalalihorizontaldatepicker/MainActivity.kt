package ir.hosseinstar.jalalihorizontaldatepicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val picker = findViewById<DatePickerTimeLine>(R.id.ac_main_picker)
        picker.setInitialDate(2021,6,8)
        picker.setOnDateSelectedListener(object : OnDateSelectedListener {
            override fun onDateSelected(year: Int, month: Int, day: Int) {
                Toast.makeText(applicationContext, "$year/$month/$day Selected!", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onDisabledDateSelected(
                year: Int,
                month: Int,
                day: Int,
                isDisabled: Boolean
            ) {
                TODO("Not yet implemented")
            }

        })
    }
}