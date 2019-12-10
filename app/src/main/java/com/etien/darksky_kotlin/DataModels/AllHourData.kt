package com.etien.darksky_kotlin.DataModels

class AllHourData() {
    lateinit var hourList: MutableList<OneHourData>
    lateinit var summary: String
    lateinit var icon: String

    constructor(hourList: MutableList<OneHourData>, summary: String, icon: String) : this() {
        this.hourList = hourList
        this.summary = summary
        this.icon = icon
    }
}
