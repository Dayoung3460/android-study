package com.example.weatherdustchecker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.net.URL

class WeatherPageFragment : Fragment() {
    private val APP_ID = "18f451857d0d7a022967387c8306eeab"

    lateinit var weatherImage: ImageView
    lateinit var statusText: TextView
    lateinit var temperatureText: TextView

    companion object {
        fun newInstance(lat: Double, lon: Double): WeatherPageFragment {
            val fragment = WeatherPageFragment()
            val args = Bundle()
            args.putDouble("lat", lat)
            args.putDouble("lon", lon)
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.weather_page_fragment, container, false)

        weatherImage = view.findViewById<ImageView>(R.id.weather_icon)
        statusText = view.findViewById<TextView>(R.id.weather_status_text)
        temperatureText = view.findViewById<TextView>(R.id.weather_temp_text)

//        weatherImage.setImageResource(requireArguments().getInt("res_id"))
//        statusText.text = requireArguments().getString("status")
//        temperatureText.text = "${requireArguments().getDouble("temperature")} ° "
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lat = requireArguments().getDouble("lat")
        val lon = requireArguments().getDouble("lon")
        val url = "http://api.openweathermap.org/data/2.5/weather?units=metric&appid=${APP_ID}&lat=${lat}&lon=${lon}"
        APICall(object: APICall.APICallback {
            override fun onComplete(result: String) {
                Log.d("mytag", result)
            }

        }).execute(URL(url))
    }
}