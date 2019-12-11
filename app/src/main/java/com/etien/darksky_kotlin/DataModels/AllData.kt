package com.etien.darksky_kotlin.DataModels

open class AllData() {
    open lateinit var summary: String
    open lateinit var icon: String

    constructor(summary: String, icon: String) : this() {
        this.summary = summary
        this.icon = icon
    }
}