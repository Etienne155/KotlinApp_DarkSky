package com.etien.darksky_kotlin.DataModels

class AllHourData() : AllGivenData() {
    lateinit var list: MutableList<OneHourData>
    override lateinit var summary: String
    override lateinit var icon: String

    constructor(list: MutableList<OneHourData>, summary: String, icon: String) : this() {
        this.list = list
        this.summary = summary
        this.icon = icon
    }
}
