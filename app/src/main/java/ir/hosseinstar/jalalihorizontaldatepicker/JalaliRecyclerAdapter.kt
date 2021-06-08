package ir.hosseinstar.jalalihorizontaldatepicker

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class JalaliRecyclerAdapter(
     val jalaliRecyclerView: JalaliRecyclerView,
     var selectedPos: Int
) : RecyclerView.Adapter<JalaliRecyclerAdapter.ViewHolder>() {
    private lateinit var listener: OnDateSelectedListener
    private var selectedView: View? = null
    private val calendar = Calendar.getInstance()
    private lateinit var deactivatedDates: Array<Date>
    private val persianDate = PersianDate()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_picker_item,parent,false)
        return ViewHolder(v)
    }



    override fun getItemCount(): Int = Integer.MAX_VALUE

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        resetCalendar()
        calendar.add(Calendar.DAY_OF_YEAR,position)
        val dateModel = DateConverter.GregorianToJalali(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))
        persianDate.initJalaliDate(dateModel.year,dateModel.month,dateModel.day)
        Log.e("Log__","${calendar.get(Calendar.YEAR)}/${calendar[Calendar.MONTH]}/${calendar[Calendar.DAY_OF_MONTH]}")
        Log.e("Log_______","${persianDate.grgYear}/${persianDate.grgMonth}/${persianDate.grgDay}")
        val year = persianDate.shYear
        val month = persianDate.shMonth
        val day = persianDate.shDay
        val isDisabled = holder.bind(month,day,year,position)
        holder.rootView.setOnClickListener {
            if (selectedView != null) {
                selectedView!!.background = null
            }
            if (!isDisabled) {
                it.background = ResourcesCompat.getDrawable(jalaliRecyclerView.resources,R.drawable.background_shape,holder.itemView.context.theme)
                selectedPos = position
                selectedView = it
                if (listener != null) listener.onDateSelected(year, month, day)
            } else {
                if (listener != null) listener.onDisabledDateSelected(
                    year,
                    month,
                    day,
                    isDisabled
                )
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dayName: TextView = itemView.findViewById(R.id.picker_item_day_Name)
        private val date: TextView = itemView.findViewById(R.id.picker_item_day)
        val rootView: View = itemView.findViewById(R.id.picker_item_root)
        fun bind(
            month: Int,
            day: Int,
            year: Int,
            position: Int
        ): Boolean {
            persianDate.initJalaliDate(year,month, day)
            dayName.setTextColor(jalaliRecyclerView.dayNameTextColor)
            date.setTextColor(jalaliRecyclerView.dateTextColor)
            dayName.text = persianDate.dayName()
            date.text = day.toString()
            if (selectedPos == position) {
                rootView.background =
                    ResourcesCompat.getDrawable(jalaliRecyclerView.resources,R.drawable.background_shape,itemView.context.theme)
                selectedView = rootView
            } else {
                rootView.background = null
            }
            for (dates in deactivatedDates) {
                val tempCalendar = Calendar.getInstance()
                tempCalendar.time = dates
                if (tempCalendar[Calendar.DAY_OF_MONTH] == day && tempCalendar[Calendar.MONTH] == month && tempCalendar[Calendar.YEAR] == year
                ) {
                    date.setTextColor(jalaliRecyclerView.disabledColor)
                    dayName.setTextColor(jalaliRecyclerView.disabledColor)
                    rootView.background = null
                    return true
                }
            }
            return false
        }
    }

    private fun resetCalendar() {
        calendar[jalaliRecyclerView.year, jalaliRecyclerView.month,jalaliRecyclerView.day, 1, 0] = 0
    }

    fun setSelectedPosition(selectedPosition: Int) {
        this.selectedPos = selectedPosition
    }
    fun disableDates(dates: Array<Date>) {
        deactivatedDates = dates
        notifyDataSetChanged()
    }

    fun setDateSelectedListener(listener: OnDateSelectedListener?) {
        this.listener = listener!!
    }

    fun setTodayClicked(){
        val pDate = PersianDate()


    }
}
