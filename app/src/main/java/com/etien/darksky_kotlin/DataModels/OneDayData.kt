package com.etien.darksky_kotlin.DataModels

class OneDayData() : OneData() {
    var summary: String? = null
    var icon: String? = null

    constructor(time: Double?, summary: String?, icon: String?, precipIntensity: Double?, precipProbability: Double?, precipType: String?) : this() {
        this.time = time
        this.summary = summary
        this.icon = icon
        this.precipIntensity = precipIntensity
        this.precipProbability = precipProbability
        this.precipType = precipType
    }
}