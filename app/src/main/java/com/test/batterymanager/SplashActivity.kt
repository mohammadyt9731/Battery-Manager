package com.test.batterymanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        gotoMainActivity();
    }

    private fun gotoMainActivity(){

        Timer().schedule(timerTask {

            startActivity(Intent(this@SplashActivity,MainActivity::class.java))
            finish()
        },3000)

    }
}