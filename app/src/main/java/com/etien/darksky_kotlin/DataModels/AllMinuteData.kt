package com.etien.darksky_kotlin.DataModels

class AllMinuteData() : AllData() {
    lateinit var list: MutableList<OneMinuteData>

    constructor(list: MutableList<OneMinuteData>, summary: String, icon: String) : this() {
        this.list = list
        this.summary = summary
        this.icon = icon
    }
}