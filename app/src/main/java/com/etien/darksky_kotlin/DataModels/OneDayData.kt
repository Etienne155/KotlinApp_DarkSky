package com.etien.darksky_kotlin.DataModels

class OneDayData(time: Double?, summary: String?, icon: String?, precipIntensity: Double?, precipProbability: Double?, precipType: String?) {
    var time: Double? = time
    var summary: String? = summary
    var icon: String? = icon
    var precipIntensity: Double? = precipIntensity
    var precipProbability: Double? = precipProbability
    var precipType: String? = precipType
}