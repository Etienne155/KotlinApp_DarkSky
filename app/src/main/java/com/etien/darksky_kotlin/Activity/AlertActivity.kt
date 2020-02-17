package com.etien.darksky_kotlin.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.etien.darksky_kotlin.Constants
import com.etien.darksky_kotlin.R
import com.etien.darksky_kotlin.Service.GeoService
import kotlinx.android.synthetic.main.activity_alert.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class AlertActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert)

        title = getString(R.string.alertTitle)

        val sharedPref: SharedPreferences = getSharedPreferences(
            Constants.PREFERENCE_NAME,
            Constants.PRIVATE_MODE
        )
        val lat = sharedPref.getFloat(Constants.LATITUDE, Constants.LATITUDE_DEFAULT)
        var lng = sharedPref.getFloat(Constants.LONGITUDE, Constants.LONGITUDE_DEFAULT)

        doAsync {
            val data = URL(Constants.URL_DARKSKY + lat + "," + lng).readText()
            val json = JSONObject(data)

            try {
                val alertData = GeoService.getAlert(json)

                uiThread {
                    alertTitleView.setText(alertData.title)
                    alertSeverityView.setText(getString(R.string.alertSeverity) + " : " + alertData.severity)
                    alertDescriptionView.setText(alertData.description)
                }
            } catch (e: Exception) {
                alertTitleView.setText(getString(R.string.noAlert))
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
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
