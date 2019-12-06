package com.etien.darksky_kotlin.DataModels

class AllMinuteData(minuteList: MutableList<OneMinuteData>?, summary: String?, icon: String?) {
    var minuteList: MutableList<OneMinuteData>? = minuteList
    var summary: String? = summary
    var icon: String? = icon
}