package com.etien.darksky_kotlin.DataModels

class OneMinuteData() : OneData() {
    var precipIntensityError: Double? = null

    constructor(time: Double?, precipIntensity: Double?, precipIntensityError: Double?, precipProbability: Double?, precipType: String?) : this() {
        this.time = time
        this.precipIntensity = precipIntensity
        this.precipIntensityError = precipIntensityError
        this.precipProbability = precipProbability
        this.precipType = precipType
    }
}