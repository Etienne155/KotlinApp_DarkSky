package com.etien.darksky_kotlin.DataModels

class AllDayData() {
    lateinit var dayList: MutableList<OneDayData>
    lateinit var summary: String
    lateinit var icon: String

    constructor(dayList: MutableList<OneDayData>, summary: String, icon: String) : this() {
        this.dayList = dayList
        this.summary = summary
        this.icon = icon
    }
}