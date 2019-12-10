package com.etien.darksky_kotlin.DataModels

class AllDayData() : AllGivenData() {
    lateinit var list: MutableList<OneDayData>
    override lateinit var summary: String
    override lateinit var icon: String

    constructor(list: MutableList<OneDayData>, summary: String, icon: String) : this() {
        this.list = list
        this.summary = summary
        this.icon = icon
    }
}