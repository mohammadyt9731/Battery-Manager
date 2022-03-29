package com.test.batterymanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.batterymanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val PROGRESS_ANIMATOIN_DURATION: Long = 2000
    private lateinit var batteryReceiver: BroadcastReceiver
    private var batteryLevel = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)


        config()


    }

    private fun config() {

        batteryReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {

                batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)

                binding.tvChargePercentage.text = "$batteryLevel%"

                setUpCpb()

                binding.tvTemperature.text =
                    (intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10).toString()

                binding.tvVoltage.text =
                    (intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0) / 1000).toString()


                binding.tvCapacity.text = getBatteryCapacity().toInt().toString()


                binding.tvTechnology.text =
                    intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY)

                binding.tvPlugState.text =
                    if (intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0) == 0)
                        resources.getString(R.string.plug_out)
                    else
                        resources.getString(R.string.plug_in)

            }
        }

        registerReceiver(batteryReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    private fun setUpCpb(){
        binding.circularProgressBar.apply {

            setProgressWithAnimation(batteryLevel.toFloat(), PROGRESS_ANIMATOIN_DURATION)
            progress = batteryLevel.toFloat()
        }

    }

    fun getBatteryCapacity(): Double {
        val mPowerProfile: Any
        var batteryCapacity = 0.0
        val POWER_PROFILE_CLASS = "com.android.internal.os.PowerProfile"
        try {
            mPowerProfile = Class.forName(POWER_PROFILE_CLASS)
                .getConstructor(Context::class.java)
                .newInstance(this)
            batteryCapacity = Class
                .forName(POWER_PROFILE_CLASS)
                .getMethod("getBatteryCapacity")
                .invoke(mPowerProfile) as Double
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return batteryCapacity
    }
}