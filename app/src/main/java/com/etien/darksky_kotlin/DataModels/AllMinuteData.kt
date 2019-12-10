package com.etien.darksky_kotlin.DataModels

class AllMinuteData() : AllGivenData() {
    lateinit var list: MutableList<OneMinuteData>
    override lateinit var summary: String
    override lateinit var icon: String

    constructor(list: MutableList<OneMinuteData>, summary: String, icon: String) : this() {
        this.list = list
        this.summary = summary
        this.icon = icon
    }
}