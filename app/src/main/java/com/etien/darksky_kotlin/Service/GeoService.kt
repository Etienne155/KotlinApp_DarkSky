package com.etien.darksky_kotlin.Service

import com.etien.darksky_kotlin.DataModels.AllMinuteData
import com.etien.darksky_kotlin.DataModels.OneMinuteData
import org.json.JSONArray
import org.json.JSONObject

class GeoService {
    companion object {
        fun getMinutesData(json: JSONObject): AllMinuteData {
            var allMinuteData: AllMinuteData = AllMinuteData(null, null, null)

            val minutely: JSONObject = json.getJSONObject("minutely")
            val summary: String = minutely.get("summary").toString()
            val icon: String = minutely.get("icon").toString()

            allMinuteData.summary = summary
            allMinuteData.icon = icon

            var minuteList: MutableList<OneMinuteData> = mutableListOf<OneMinuteData>()

            val data: JSONArray = minutely.getJSONArray("data")
            for(x in 0..25) {
                val item: JSONObject = data.getJSONObject(0)
                val time: Double = item.getDouble("time")

                var precipIntensity: Double = 0.0
                if(item.has("precipIntensity")) {
                    precipIntensity = item.getDouble("precipIntensity")
                }

                var precipIntensityError: Double = 0.0
                if(item.has("precipIntensityError")) {
                    precipIntensityError = item.getDouble("precipIntensityError")
                }

                var precipProbability: Double = 0.0
                if(item.has("precipProbability")) {
                    precipProbability = item.getDouble("precipProbability")
                }

                var precipType: String = ""
                if(item.has("precipType")) {
                    precipType = item.getString("precipType")
                }

                var oneMinuteData: OneMinuteData = OneMinuteData(
                    time,
                    precipIntensity,
                    precipIntensityError,
                    precipProbability,
                    precipType)

                minuteList.add(oneMinuteData)
            }

            allMinuteData.minuteList = minuteList

            return allMinuteData
        }

        fun getHoursData(json: JSONObject) {

        }

        fun getDaysData(json: JSONObject) {

        }
    }
}