package com.etien.darksky_kotlin.DataModels

class OneMinuteData(time: Double, precipIntensity: Double, precipIntensityError: Double, precipProbability: Double, precipType: String) {
    var time: Double = time
    var precipIntensity: Double = precipIntensity
    var precipIntensityError: Double = precipIntensityError
    var precipProbability: Double = precipProbability
    var precipType: String = precipType
}