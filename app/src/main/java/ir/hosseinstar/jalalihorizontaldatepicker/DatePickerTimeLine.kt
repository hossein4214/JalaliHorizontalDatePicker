package ir.hosseinstar.jalalihorizontaldatepicker

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import java.util.*

class DatePickerTimeLine @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private lateinit var jalaliRecyclerView: JalaliRecyclerView

    init {
        val view = View.inflate(context, R.layout.horizontal_picker, this)
        jalaliRecyclerView = view.findViewById(R.id.picker_jalaliRecyclerView)

        //Load Default Values
        val a: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.DatePickerTimeLine, defStyleAttr, 0)
        jalaliRecyclerView.dayNameTextColor = a.getColor(
            R.styleable.DatePickerTimeLine_dayTextColor,
            ContextCompat.getColor(context, R.color.black)
        )
        jalaliRecyclerView.dateTextColor = a.getColor(
            R.styleable.DatePickerTimeLine_dateTextColor,
            ContextCompat.getColor(context, R.color.black)
        )
        jalaliRecyclerView.disabledColor = a.getColor(
            R.styleable.DatePickerTimeLine_disabledColor,
            ContextCompat.getColor(context, R.color.black)
        )


//        timelineView.setMonthTextSize(a.getDimension(R.styleable.DatePickerTimeline_monthTextSize, getResources().getDimension(R.dimen.monthTextSize)));
//        timelineView.setDateTextSize(a.getDimension(R.styleable.DatePickerTimeline_dateTextSize, getResources().getDimension(R.dimen.dateTextSize)));
//        timelineView.setDayTextSize(a.getDimension(R.styleable.DatePickerTimeline_dayTextSize, getResources().getDimension(R.dimen.dayTextSize)));
        jalaliRecyclerView.deactivateDates(emptyArray())
        a.recycle()
        jalaliRecyclerView.invalidate()
    }

    fun setDateTextColor(color: Int) {
        jalaliRecyclerView.dateTextColor = color
    }

    fun setDayNameTextColor(color: Int) {
        jalaliRecyclerView.dayNameTextColor = color
    }

    fun setDisabledDateColor(color: Int) {
        jalaliRecyclerView.disabledColor = color
    }


    fun setOnDateSelectedListener(listener: OnDateSelectedListener?) {
        jalaliRecyclerView.setOnDateSelectedListener(listener)
    }

    fun setInitialDate(year: Int, month: Int, date: Int) {
        jalaliRecyclerView.setInitialDate(year, month, date)
    }

    fun setActiveDate(date: Calendar) {
        jalaliRecyclerView.setActiveDate(date)
    }

    fun deactivateDates(dates: Array<Date>) {
        jalaliRecyclerView.deactivateDates(dates)
    }

}