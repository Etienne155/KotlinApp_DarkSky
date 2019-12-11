package com.etien.darksky_kotlin.DataModels

open class OneData() {
    open var time: Double? = null
    open var precipIntensity: Double? = null
    open var precipProbability: Double? = null
    open var precipType: String? = null

    constructor(time: Double?, precipIntensity: Double?, precipProbability: Double?, precipType: String?) : this() {
        if (time != null) {
            this.time = time
        }
        if (precipIntensity != null) {
            this.precipIntensity = precipIntensity
        }
        if (precipProbability != null) {
            this.precipProbability = precipProbability
        }
        if (precipType != null) {
            this.precipType = precipType
        }
    }
}