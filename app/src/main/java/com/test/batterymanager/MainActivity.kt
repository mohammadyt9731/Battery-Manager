package com.test.batterymanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.test.batterymanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val PROGRESS_ANIMATOIN_DURATION:Long=2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        val rootView=binding.root
        setContentView(rootView)

        config()



    }

    private fun config(){
        binding.circularProgressBar.apply {

            setProgressWithAnimation(70f,PROGRESS_ANIMATOIN_DURATION)
            progress=70f
        }

        binding.tvChargePercentage.text="${binding.circularProgressBar.progress.toInt()}%"



    }
}