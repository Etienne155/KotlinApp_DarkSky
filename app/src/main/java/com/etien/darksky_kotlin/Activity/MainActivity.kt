package com.etien.darksky_kotlin.Activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.core.app.ActivityCompat
import com.etien.darksky_kotlin.*
import com.etien.darksky_kotlin.Adapters.DaysAdapter
import com.etien.darksky_kotlin.Adapters.HoursAdapter
import com.etien.darksky_kotlin.Adapters.MinutesAdapter
import com.etien.darksky_kotlin.DataModels.*
import com.etien.darksky_kotlin.R
import com.etien.darksky_kotlin.Service.GeoService
import com.google.android.gms.location.*

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var listView: ListView
    private lateinit var summaryView: TextView
    private lateinit var alertIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setTitle(getString(R.string.mainTitle))

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()

        listView = findViewById<ListView>(R.id.forecast_list_view)
        summaryView = findViewById<TextView>(R.id.main_summary)
        alertIcon = findViewById<ImageView>(R.id.alertIcon)

        alertIcon.setOnClickListener {
            val intent = Intent(this, AlertActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == Constants.PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Granted. Start getting the location information
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        finishAffinity()
    }



    /* Geolocalisation Utils Functions */

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            Constants.PERMISSION_ID
        )
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        var coords = GeoCoordinates(
                            location.latitude,
                            location.longitude
                        )

                        val sharedPref: SharedPreferences = getSharedPreferences(
                            Constants.MODE_INDEX,
                            Constants.PRIVATE_MODE
                        )
                        val editor = sharedPref.edit()
                        editor.putFloat(Constants.LATITUDE, coords.lat.toFloat())
                        editor.putFloat(Constants.LONGITUDE, coords.lng.toFloat())
                        editor.apply()

                        /* DARK SKY API */

                        doAsync {
                            val data = URL(Constants.URL_DARKSKY + coords.lat + "," + coords.lng).readText()
                            val json = JSONObject(data)

                            val current_time_mode_index = sharedPref.getInt(Constants.MODE_INDEX, Constants.MODE_INDEX_DEFAULT)

                            if(current_time_mode_index == 0) {
                                val model: AllMinuteData = GeoService.getMinutesData(json)
                                val adapter = MinutesAdapter(
                                    this@MainActivity,
                                    model.list
                                )
                                uiThread {
                                    summaryView.setText(model.summary)
                                    listView.adapter = adapter
                                }
                            } else if(current_time_mode_index == 1) {
                                val model: AllHourData = GeoService.getHoursData(json)
                                val adapter = HoursAdapter(
                                    this@MainActivity,
                                    model.list
                                )
                                uiThread {
                                    summaryView.setText(model.summary)
                                    listView.adapter = adapter
                                }
                            } else if(current_time_mode_index == 2) {
                                val model: AllDayData = GeoService.getDaysData(json)
                                val adapter = DaysAdapter(
                                    this@MainActivity,
                                    model.list
                                )
                                uiThread {
                                    summaryView.setText(model.summary)
                                    listView.adapter = adapter
                                }
                            }
                        }

                    }
                }
            } else {
                Toast.makeText(this, getString(R.string.turn_on_location), Toast.LENGTH_LONG).show()
            }
        } else {
            requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient!!.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {

        }
    }
}
