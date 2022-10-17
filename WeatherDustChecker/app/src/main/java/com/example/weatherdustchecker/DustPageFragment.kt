package com.example.weatherdustchecker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URL

class DustPageFragment: Fragment() {
    private val APP_TOKEN = "e728fdb0ea613d3e028d92d2d51590114a283536"

    lateinit var statusImage: ImageView
    lateinit var pm25StatusText: TextView
    lateinit var pm25IntensityText: TextView
    lateinit var pm10StatusText: TextView
    lateinit var pm10IntensityText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.dust_page_fragment, container, false)
        statusImage = view.findViewById<ImageView>(R.id.dust_status_icon)
        pm25StatusText = view.findViewById(R.id.dust_pm25_status_text)
        pm25IntensityText = view.findViewById(R.id.dust_pm25_intensity_text)
        pm10StatusText = view.findViewById(R.id.dust_pm10_status_text)
        pm10IntensityText = view.findViewById(R.id.dust_pm10_intensity_text)

        return view
    }

    companion object {
        fun newInstance(lat: Double, lon: Double): DustPageFragment {
            val fragment = DustPageFragment()
            val args = Bundle()
            args.putDouble("lat", lat)
            args.putDouble("log", lon)
            fragment.arguments = args
            return fragment
        }
    }

    @JsonDeserialize(using=DustCheckerResponseDeserializer::class)
    data class DustCheckResponse(val pm10: Int?, val pm25: Int?)

    class DustCheckerResponseDeserializer: StdDeserializer<DustCheckResponse>(DustCheckResponse::class.java) {
        override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): DustCheckResponse {
            var node: JsonNode? = p?.codec?.readTree<JsonNode>(p)
            var dataNode: JsonNode? = node?.get("data")
            var iaqiNode = dataNode?.get("iaqi")
            var pm10Node = iaqiNode?.get("pm10")
            var pm25Node = iaqiNode?.get("pm25")
            var pm10 = pm10Node?.get("v")?.asInt()
            var pm25 = pm25Node?.get("v")?.asInt()

            return DustCheckResponse(pm10, pm25)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lat = requireArguments().getDouble("lat")
        val lon = requireArguments().getDouble("lon")
        val url = "https://api.waqi.info/feed/geo:${lat};${lon}/?token=${APP_TOKEN}"

        APICall(object : APICall.APICallback {
            override fun onComplete(result: String) {
                var mapper = jacksonObjectMapper()
                val data = mapper?.readValue<DustCheckResponse>(result)

                pm25IntensityText.text = data?.pm25?.toString()?: "Unknown"
                pm10IntensityText.text = data?.pm10?.toString()?: "Unknown"
            }

        }).execute(URL(url))
    }

}