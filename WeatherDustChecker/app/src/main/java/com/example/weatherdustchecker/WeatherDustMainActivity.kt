package com.example.weatherdustchecker

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class WeatherDustMainActivity : AppCompatActivity() {
    private lateinit var mPager: ViewPager
    private var lat: Double = 0.0
    private var lon: Double = 0.0

    private lateinit var locationManager: FragmentManager
    private lateinit var locationListener: LocationListener

    private val PERMISSION_REQUEST_CODE: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_dust_main_activity)
        supportActionBar?.hide()

        mPager = findViewById(R.id.pager)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object: LocationListener {
            override fun onLocationChanged(p0: Location) {
                lat = location.latitude
                lon = location.longitude
                locationManager.removeUpdates(this)

                val pagerAdapter = MyPagerAdapter(supportFragmentManager)
                mPager.adapter = pagerAdapter
                mPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
                    override fun onPageScrollStateChanged(state: Int) {}
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {}

                    override fun onPageSelected(position: Int) {
                        if(position === 0) {
                             Toast.makeText(applicationContext, "Weather page", Toast.LENGTH_SHORT).show()
                        } else if(position === 1) {
                            Toast.makeText(applicationContext, "Fine dust page", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }

        }

        val pagerAdapter = MyPagerAdapter(supportFragmentManager)
        mPager.adapter = pagerAdapter

        mPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                if(position === 0) {
                    Toast.makeText(applicationContext, "Weather page", Toast.LENGTH_SHORT).show()
                } else if(position === 1) {
                    Toast.makeText(applicationContext, "Fine dust page", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private inner class MyPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = 2
        override fun getItem(position: Int): Fragment {
            return when(position) {
                0 -> WeatherPageFragment.newInstance(lat, lon)
                1 -> DustPageFragment.newInstance(lat, lon)
                else -> {
                    throw Exception("Not found")
                }
            }
        }
    }
}