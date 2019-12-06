package com.etien.darksky_kotlin.DataModels

class AllHourData(hourList: MutableList<OneHourData>?, summary: String?, icon: String?) {
    var hourList: MutableList<OneHourData>? = hourList
    var summary: String? = summary
    var icon: String? = icon
}