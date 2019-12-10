package com.etien.darksky_kotlin.DataModels

class AllMinuteData() {
    lateinit var minuteList: MutableList<OneMinuteData>
    lateinit var summary: String
    lateinit var icon: String

    constructor(minuteList: MutableList<OneMinuteData>, summary: String, icon: String) : this() {
        this.minuteList = minuteList
        this.summary = summary
        this.icon = icon
    }
}