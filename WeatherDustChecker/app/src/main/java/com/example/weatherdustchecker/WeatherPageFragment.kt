package com.example.weatherdustchecker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class WeatherPageFragment : Fragment() {
    lateinit var weatherImage: ImageView
    lateinit var statusText: TextView
    lateinit var temperatureText: TextView

    companion object {
        fun newInstance(status: String, temperature: Double): WeatherPageFragment {
            val fragment = WeatherPageFragment()
            val args = Bundle()
            args.putString("status", status)
            args.putDouble("temperature", temperature)
            args.putInt("res_id", R.drawable.sun)
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

        weatherImage.setImageResource(requireArguments().getInt("res_id"))
        statusText.text = requireArguments().getString("status")
        temperatureText.text = "${requireArguments().getDouble("temperature")} &deg;"
        return view
    }
}