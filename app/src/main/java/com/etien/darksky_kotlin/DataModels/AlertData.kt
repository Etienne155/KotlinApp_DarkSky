package com.etien.darksky_kotlin.DataModels

class AlertData(title: String, severity: String, time: Double, expires: Double, description: String) {
    var title: String = title
    var severity: String = severity
    var time: Double = time
    var expires: Double = expires
    var description: String = description
}