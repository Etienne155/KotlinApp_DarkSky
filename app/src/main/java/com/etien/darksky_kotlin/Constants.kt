package com.etien.darksky_kotlin

class Constants {
    companion object {
        const val DELAY: Long = 3000

        const val PRIVATE_MODE = 0
        const val PREFERENCE_NAME = "DARK_SKY_PREFS"

        const val MODE_INDEX = "MODE_INDEX"
        const val MODE_INDEX_DEFAULT = 2
        const val MODE_MINUTE = 0
        const val MODE_HOUR = 1
        const val MODE_DAY = 2

        const val LATITUDE = "LATITUDE"
        const val LONGITUDE = "LONGITUDE"

        const val LATITUDE_DEFAULT: Float = 45F
        const val LONGITUDE_DEFAULT: Float = -73F

        const val PERMISSION_ID = 42

        const val URL_DARKSKY = "https://api.darksky.net/forecast/8ba0b77be6803b377006280d1bddeba3/"
    }
}