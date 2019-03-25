package com.nyinyihtunlwin.club.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.nyinyihtunlwin.club.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            startActivity(MainActivity.newInstance(applicationContext))
        }, 2000)
    }
}
