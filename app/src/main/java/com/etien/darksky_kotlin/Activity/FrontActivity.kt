package com.etien.darksky_kotlin.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.etien.darksky_kotlin.R


class FrontActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_front)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        val handler = Handler()
        handler.postDelayed(Runnable {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }, 3500)
    }


}
