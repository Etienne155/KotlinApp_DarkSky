package com.etien.darksky_kotlin.DataModels

class AllMinuteData(minuteData: List<OneMinuteData>, summary: String?, icon: String?) {
    var minuteData: List<OneMinuteData> = minuteData
    var summary: String? = summary
    var icon: String? = icon
}