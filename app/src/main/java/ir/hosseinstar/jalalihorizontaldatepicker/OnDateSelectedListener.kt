package ir.hosseinstar.jalalihorizontaldatepicker

interface OnDateSelectedListener {
    fun onDateSelected(
        year: Int,
        month: Int,
        day: Int
    )

    fun onDisabledDateSelected(
        year: Int,
        month: Int,
        day: Int,
        isDisabled: Boolean
    )
}
