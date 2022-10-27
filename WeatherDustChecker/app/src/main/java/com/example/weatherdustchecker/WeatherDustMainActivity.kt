package com.example.weatherdustchecker

import android.annotation.SuppressLint
import android.content.Context
import android.content.SyncRequest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import java.util.jar.Manifest

class WeatherDustMainActivity : AppCompatActivity() {
    private lateinit var mPager: ViewPager
    private var lat: Double = 0.0
    private var lon: Double = 0.0

    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    private val PERMISSION_REQUEST_CODE: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_dust_main_activity)
        supportActionBar?.hide()

        mPager = findViewById(R.id.pager)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                lat = location.latitude
                lon = location.longitude
                locationManager.removeUpdates(this)

                val pagerAdapter = MyPagerAdapter(supportFragmentManager)
                mPager.adapter = pagerAdapter
                mPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrollStateChanged(state: Int) {}
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                    }

                    override fun onPageSelected(position: Int) {
                        if (position === 0) {
                            Toast.makeText(applicationContext, "Weather page", Toast.LENGTH_SHORT)
                                .show()
                        } else if (position === 1) {
                            Toast.makeText(applicationContext, "Fine dust page", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                })
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                0,
                0f,
                locationListener
            )
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                PERMISSION_REQUEST_CODE
            )
        }

        @SuppressLint("MassingPermission")
        override fun onRequestPermissionsResult(
            requestCode: Int,
            permission: Array<String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permission, grantResults)
            when (requestCode) {
                PERMISSION_REQUEST_CODE -> {
                    var allPermissionsGranted = true
                    for (result in grantResults) {
                        allPermissionsGranted = (result == PackageManager.PERMISSION_GRANTED)
                        if (!allPermissionsGranted) break
                    }
                    if (allPermissionsGranted) {
                        locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            0,
                            0f,
                            locationListener
                        )
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Need permission for your location info",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                }
            }

        }


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