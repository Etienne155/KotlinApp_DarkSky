package com.etien.darksky_kotlin.DataModels

class AllHourData() : AllData() {
    lateinit var list: MutableList<OneHourData>

    constructor(list: MutableList<OneHourData>, summary: String, icon: String) : this() {
        this.list = list
        this.summary = summary
        this.icon = icon
    }
}
