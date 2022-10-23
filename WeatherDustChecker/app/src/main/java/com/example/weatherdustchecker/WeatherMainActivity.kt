package com.example.weatherdustchecker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class WeatherMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_main_activity)

        supportActionBar?.hide()

        val fragment = DustPageFragment.newInstance(37.5665, 126.9780)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, fragment)
        transaction.commit()

//        val fragment = WeatherPageFragment.newInstance(37.579876, 126.976998)
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.add(R.id.fragment_container, fragment)
//        transaction.commit()
    }
}