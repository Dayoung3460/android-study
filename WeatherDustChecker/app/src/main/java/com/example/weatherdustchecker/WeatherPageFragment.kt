package com.example.weatherdustchecker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URL

class WeatherPageFragment : Fragment() {
    @JsonIgnoreProperties(ignoreUnknown = true)
    data class OpenWeatherAPIJSONResponse(val main: Map<String, String>, val weather: List<Map<String, String>>)
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
                var mapper = jacksonObjectMapper()
                var data = mapper?.readValue<OpenWeatherAPIJSONResponse>(result)
                val temp = data?.main?.get("temp")
                temperatureText.text = temp

                val id = data?.weather?.get(0)?.get("id")
                if(id != null) {
                    statusText.text = when {
                        id.startsWith("2") -> {
                            weatherImage.setImageResource(R.drawable.flash)
                            "Thunder, lightning"
                        }
                        id.startsWith("3") -> {
                            weatherImage.setImageResource(R.drawable.rain)
                            "Drizzling"
                        }
                        id.startsWith("5") -> {
                            weatherImage.setImageResource(R.drawable.rain)
                            "Rain"
                        }
                        id.startsWith("6") -> {
                            weatherImage.setImageResource(R.drawable.snow)
                            "Snow"
                        }
                        id.startsWith("7") -> {
                            weatherImage.setImageResource(R.drawable.cloudy)
                            "Cloudy"
                        }
                        id.equals("800") -> {
                            weatherImage.setImageResource(R.drawable.sun)
                            "Sunny"
                        }
                        id.startsWith("8") -> {
                            weatherImage.setImageResource(R.drawable.cloud)
                            "Cloud"
                        }
                        else -> "Unknown"
                    }
                }
            }

        }).execute(URL(url))
    }
}