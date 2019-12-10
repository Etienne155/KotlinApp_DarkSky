package com.etien.darksky_kotlin.DataModels

class OneHourData() : OneData() {
    var time: Double? = null
    var summary: String? = null
    var icon: String? = null
    var precipIntensity: Double? = null
    var precipProbability: Double? = null
    var precipType: String? = null

    constructor(time: Double?, summary: String?, icon: String?, precipIntensity: Double?, precipProbability: Double?, precipType: String?) : this() {
        this.time = time
        this.summary = summary
        this.icon = icon
        this.precipIntensity = precipIntensity
        this.precipProbability = precipProbability
        this.precipType = precipType
    }
}