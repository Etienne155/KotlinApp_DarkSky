package com.etien.darksky_kotlin.Activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.etien.darksky_kotlin.Constants
import com.etien.darksky_kotlin.R

class SettingsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setTitle("Réglages")

        val sharedPref: SharedPreferences = getSharedPreferences(
            Constants.MODE_INDEX,
            Constants.PRIVATE_MODE
        )

        val time_Modes = resources.getStringArray(R.array.time_Modes)
        val current_time_mode_index = sharedPref.getInt(Constants.MODE_INDEX, 0)

        val spinner = findViewById<Spinner>(R.id.spinnerSettings)

        if (spinner != null) {

            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item, time_Modes
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    val editor = sharedPref.edit()
                    editor.putInt(Constants.MODE_INDEX, position)
                    editor.apply()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }

            spinner.setSelection(current_time_mode_index)

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
