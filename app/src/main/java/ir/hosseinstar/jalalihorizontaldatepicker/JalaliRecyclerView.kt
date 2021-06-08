package ir.hosseinstar.jalalihorizontaldatepicker

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class JalaliRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {
    var year = 0
    var month = 0
    var day = 0
    private lateinit var jAdapter: JalaliRecyclerAdapter
    var dayNameTextColor: Int = 0
    var dateTextColor: Int = 0
    var selectedColor: Int = 0
    var disabledColor: Int = 0

    init {
        year = 1970
        month = 0
        day = 1
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        jAdapter = JalaliRecyclerAdapter(this, -1)
        adapter = jAdapter
    }

    fun setOnDateSelectedListener(listener: OnDateSelectedListener?) {
        jAdapter.setDateSelectedListener(listener)
    }

    fun setInitialDate(year: Int, month: Int, day: Int) {
        this.year = year
        this.month = month
        this.day = day
        invalidate()
    }

    fun setActiveDate(activeDate: Calendar) {
        try {
            val initialDate = SimpleDateFormat("yyyy-MM-dd")
                .parse(year.toString() + "-" + (month + 1) + "-" + this.day)
            val diff = activeDate.time.time - initialDate.time
            val position = (diff / (1000 * 60 * 60 * 24)).toInt()
            jAdapter.setSelectedPosition(position)
            invalidate()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
    fun deactivateDates(deactivatedDates: Array<Date>) {
        jAdapter.disableDates(deactivatedDates)
    }

}