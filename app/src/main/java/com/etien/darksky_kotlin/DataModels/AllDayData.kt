package com.etien.darksky_kotlin.DataModels

class AllDayData(dayList: MutableList<OneDayData>?, summary: String?, icon: String?) {
    var dayList: MutableList<OneDayData>? = dayList
    var summary: String? = summary
    var icon: String? = icon
}