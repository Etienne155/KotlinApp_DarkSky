package com.etien.darksky_kotlin.Service

import com.etien.darksky_kotlin.DataModels.*
import org.json.JSONArray
import org.json.JSONObject

class GeoService {
    companion object {
        fun getMinutesData(json: JSONObject): AllMinuteData {
            var allMinuteData: AllMinuteData = AllMinuteData()

            val minutely: JSONObject = json.getJSONObject("minutely")
            val summary: String = minutely.get("summary").toString()
            val icon: String = minutely.get("icon").toString()

            allMinuteData.summary = summary
            allMinuteData.icon = icon

            var minuteList: MutableList<OneMinuteData> = mutableListOf<OneMinuteData>()

            val data: JSONArray = minutely.getJSONArray("data")
            for(x in 0..25) {
                val item: JSONObject = data.getJSONObject(x)
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

            allMinuteData.list = minuteList

            return allMinuteData
        }

        fun getHoursData(json: JSONObject): AllHourData {
            var allHourData: AllHourData = AllHourData()

            val hourly: JSONObject = json.getJSONObject("hourly")
            val summary: String = hourly.get("summary").toString()
            val icon: String = hourly.get("icon").toString()

            allHourData.summary = summary
            allHourData.icon = icon

            var hourList: MutableList<OneHourData> = mutableListOf<OneHourData>()

            val data: JSONArray = hourly.getJSONArray("data")
            for(x in 0..25) {
                val item: JSONObject = data.getJSONObject(x)
                val time: Double = item.getDouble("time")

                var summary: String = ""
                if(item.has("summary")) {
                    summary = item.getString("summary")
                }

                var icon: String = ""
                if(item.has("icon")) {
                    icon = item.getString("icon")
                }

                var precipIntensity: Double = 0.0
                if(item.has("precipIntensity")) {
                    precipIntensity = item.getDouble("precipIntensity")
                }

                var precipProbability: Double = 0.0
                if(item.has("precipProbability")) {
                    precipProbability = item.getDouble("precipProbability")
                }

                var precipType: String = ""
                if(item.has("precipType")) {
                    precipType = item.getString("precipType")
                }

                var oneHourData: OneHourData = OneHourData(
                    time,
                    summary,
                    icon,
                    precipIntensity,
                    precipProbability,
                    precipType)

                hourList.add(oneHourData)
            }

            allHourData.list = hourList

            return allHourData
        }

        fun getDaysData(json: JSONObject): AllDayData {
            var allDayData: AllDayData = AllDayData()

            val daily: JSONObject = json.getJSONObject("daily")
            val summary: String = daily.get("summary").toString()
            val icon: String = daily.get("icon").toString()

            allDayData.summary = summary
            allDayData.icon = icon

            var dayList: MutableList<OneDayData> = mutableListOf<OneDayData>()

            val data: JSONArray = daily.getJSONArray("data")
            for(x in 0..7) {
                val item: JSONObject = data.getJSONObject(x)
                val time: Double = item.getDouble("time")

                var summary: String = ""
                if(item.has("summary")) {
                    summary = item.getString("summary")
                }

                var icon: String = ""
                if(item.has("icon")) {
                    icon = item.getString("icon")
                }

                var precipIntensity: Double = 0.0
                if(item.has("precipIntensity")) {
                    precipIntensity = item.getDouble("precipIntensity")
                }

                var precipProbability: Double = 0.0
                if(item.has("precipProbability")) {
                    precipProbability = item.getDouble("precipProbability")
                }

                var precipType: String = ""
                if(item.has("precipType")) {
                    precipType = item.getString("precipType")
                }

                var oneDayData: OneDayData = OneDayData(
                    time,
                    summary,
                    icon,
                    precipIntensity,
                    precipProbability,
                    precipType)

                dayList.add(oneDayData)
            }

            allDayData.list = dayList

            return allDayData
        }
    }
}