package com.etien.darksky_kotlin.Service

import com.etien.darksky_kotlin.DataModels.AlertData
import com.etien.darksky_kotlin.DataModels.AllDayData
import com.etien.darksky_kotlin.DataModels.AllHourData
import com.etien.darksky_kotlin.DataModels.AllMinuteData
import org.json.JSONObject

interface GeoInterface {
    fun getMinutesData(json: JSONObject): AllMinuteData
    fun getHoursData(json: JSONObject): AllHourData
    fun getDaysData(json: JSONObject): AllDayData
    fun getAlert(json: JSONObject): AlertData
}