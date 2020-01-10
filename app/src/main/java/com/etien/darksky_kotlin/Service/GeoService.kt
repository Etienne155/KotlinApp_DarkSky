package com.etien.darksky_kotlin.Service

import com.etien.darksky_kotlin.DataModels.*
import org.json.JSONArray
import org.json.JSONObject

class GeoService {
    companion object : GeoInterface {
        override fun getMinutesData(json: JSONObject): AllMinuteData {
            val allMinuteData = AllMinuteData()

            val minutely: JSONObject = json.getJSONObject("minutely")
            val summary: String = minutely.get("summary").toString()
            val icon: String = minutely.get("icon").toString()

            allMinuteData.summary = summary
            allMinuteData.icon = icon

            val minuteList: MutableList<OneMinuteData> = mutableListOf<OneMinuteData>()

            val data: JSONArray = minutely.getJSONArray("data")
            for (x in 0..60) {
                val item: JSONObject = data.getJSONObject(x)
                val time: Double = item.getDouble("time")

                var precipIntensity = 0.0
                if (item.has("precipIntensity")) {
                    precipIntensity = item.getDouble("precipIntensity")
                }

                var precipIntensityError = 0.0
                if (item.has("precipIntensityError")) {
                    precipIntensityError = item.getDouble("precipIntensityError")
                }

                var precipProbability = 0.0
                if (item.has("precipProbability")) {
                    precipProbability = item.getDouble("precipProbability")
                }

                var precipType = ""
                if (item.has("precipType")) {
                    precipType = item.getString("precipType")
                }

                val oneMinuteData = OneMinuteData(
                    time,
                    precipIntensity,
                    precipIntensityError,
                    precipProbability,
                    precipType
                )

                minuteList.add(oneMinuteData)
            }

            allMinuteData.list = minuteList

            return allMinuteData
        }

        override fun getHoursData(json: JSONObject): AllHourData {
            val allHourData = AllHourData()

            val hourly: JSONObject = json.getJSONObject("hourly")
            val summary: String = hourly.get("summary").toString()
            val icon: String = hourly.get("icon").toString()

            allHourData.summary = summary
            allHourData.icon = icon

            val hourList: MutableList<OneHourData> = mutableListOf<OneHourData>()

            val data: JSONArray = hourly.getJSONArray("data")
            for (x in 0..48) {
                val item: JSONObject = data.getJSONObject(x)
                val time: Double = item.getDouble("time")

                var summary = ""
                if (item.has("summary")) {
                    summary = item.getString("summary")
                }

                var icon = ""
                if (item.has("icon")) {
                    icon = item.getString("icon")
                }

                var precipIntensity = 0.0
                if (item.has("precipIntensity")) {
                    precipIntensity = item.getDouble("precipIntensity")
                }

                var precipProbability = 0.0
                if (item.has("precipProbability")) {
                    precipProbability = item.getDouble("precipProbability")
                }

                var precipType = ""
                if (item.has("precipType")) {
                    precipType = item.getString("precipType")
                }

                val oneHourData = OneHourData(
                    time,
                    summary,
                    icon,
                    precipIntensity,
                    precipProbability,
                    precipType
                )

                hourList.add(oneHourData)
            }

            allHourData.list = hourList

            return allHourData
        }

        override fun getDaysData(json: JSONObject): AllDayData {
            val allDayData = AllDayData()

            val daily: JSONObject = json.getJSONObject("daily")
            val summary: String = daily.get("summary").toString()
            val icon: String = daily.get("icon").toString()

            allDayData.summary = summary
            allDayData.icon = icon

            val dayList: MutableList<OneDayData> = mutableListOf<OneDayData>()

            val data: JSONArray = daily.getJSONArray("data")
            for (x in 0..7) {
                val item: JSONObject = data.getJSONObject(x)
                val time: Double = item.getDouble("time")

                var summary = ""
                if (item.has("summary")) {
                    summary = item.getString("summary")
                }

                var icon = ""
                if (item.has("icon")) {
                    icon = item.getString("icon")
                }

                var precipIntensity = 0.0
                if (item.has("precipIntensity")) {
                    precipIntensity = item.getDouble("precipIntensity")
                }

                var precipProbability = 0.0
                if (item.has("precipProbability")) {
                    precipProbability = item.getDouble("precipProbability")
                }

                var precipType = ""
                if (item.has("precipType")) {
                    precipType = item.getString("precipType")
                }

                val oneDayData = OneDayData(
                    time,
                    summary,
                    icon,
                    precipIntensity,
                    precipProbability,
                    precipType
                )

                dayList.add(oneDayData)
            }

            allDayData.list = dayList

            return allDayData
        }

        override fun getAlert(json: JSONObject): AlertData {
            val alerts: JSONArray = json.getJSONArray("alerts")
            val firstAlert: JSONObject = alerts.getJSONObject(0)

            val title: String = firstAlert.getString("title")
            val severity: String = firstAlert.getString("severity")
            val time: Double = firstAlert.getDouble("time")
            val expires: Double = firstAlert.getDouble("expires")
            val description: String = firstAlert.getString("description")

            val alertData = AlertData(title, severity, time, expires, description)

            return alertData
        }
    }
}