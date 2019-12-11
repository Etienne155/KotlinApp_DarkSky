package com.etien.darksky_kotlin.DataModels

class AllDayData() : AllData() {
    lateinit var list: MutableList<OneDayData>

    constructor(list: MutableList<OneDayData>, summary: String, icon: String) : this() {
        this.list = list
        this.summary = summary
        this.icon = icon
    }
}