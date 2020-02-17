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
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.etien.darksky_kotlin.Adapters.DaysAdapter
import com.etien.darksky_kotlin.Adapters.HoursAdapter
import com.etien.darksky_kotlin.Adapters.MinutesAdapter
import com.etien.darksky_kotlin.Constants
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

        title = getString(R.string.mainTitle)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()
        getDarkSkyData()

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

    private fun getDarkSkyData() {
        doAsync {
            val sharedPref: SharedPreferences = getSharedPreferences(
                Constants.PREFERENCE_NAME,
                Constants.PRIVATE_MODE
            )

            val lat = sharedPref.getFloat(Constants.LATITUDE, Constants.LATITUDE_DEFAULT)
            val lng = sharedPref.getFloat(Constants.LONGITUDE, Constants.LONGITUDE_DEFAULT)
            val current_time_mode_index = sharedPref.getInt(Constants.MODE_INDEX, Constants.MODE_INDEX_DEFAULT)

            val data = URL(Constants.URL_DARKSKY + lat + "," + lng).readText()
            val json = JSONObject(data)

            if(current_time_mode_index == Constants.MODE_MINUTE) {
                try {
                    var model = GeoService.getMinutesData(json)
                    val adapter = MinutesAdapter(
                        this@MainActivity,
                        model.list
                    )
                    uiThread {
                        summaryView.setText(model.summary)
                        listView.adapter = adapter
                    }
                } catch (e: Exception) {
                    summaryView.setText(getString(R.string.noDataAvailable))
                    alertIcon.visibility = View.GONE
                }
            } else if(current_time_mode_index == Constants.MODE_HOUR) {
                try {
                    var model = GeoService.getHoursData(json)
                    val adapter = HoursAdapter(
                        this@MainActivity,
                        model.list
                    )
                    uiThread {
                        summaryView.setText(model.summary)
                        listView.adapter = adapter
                    }
                } catch (e: Exception) {
                    summaryView.setText(getString(R.string.noDataAvailable))
                    alertIcon.visibility = View.GONE
                }
            } else if(current_time_mode_index == Constants.MODE_DAY) {
                try {
                    var model = GeoService.getDaysData(json)
                    val adapter = DaysAdapter(
                        this@MainActivity,
                        model.list
                    )
                    uiThread {
                        summaryView.setText(model.summary)
                        listView.adapter = adapter
                    }
                } catch (e: Exception) {
                    summaryView.setText(getString(R.string.noDataAvailable))
                    alertIcon.visibility = View.GONE
                }
            }
        }
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

                        val sharedPref: SharedPreferences = getSharedPreferences(
                            Constants.PREFERENCE_NAME,
                            Constants.PRIVATE_MODE
                        )

                        val editor = sharedPref.edit()
                        editor.putFloat(Constants.LATITUDE, location.latitude.toFloat())
                        editor.putFloat(Constants.LONGITUDE, location.longitude.toFloat())
                        editor.apply()
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
