package com.example.weatherdustchecker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class WeatherMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_main_activity)

        supportActionBar?.hide()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, WeatherPageFragment())
        transaction.commit()
    }
}