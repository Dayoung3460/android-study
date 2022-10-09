package com.example.jsondeserializationstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

data class ComplexJSONData(val nested: ComplexJSONNested)
data class ComplexJSONNested(@JsonProperty("inner_data") val innerData: String,
@JsonProperty("inner_nested") val innerNested: ComplexJSONInnerNested)
data class ComplexJSONInnerNested(val data1: Int, val data2: String, val list: List<Int>)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mapper = jacksonObjectMapper()

        val complexJsonString = """{"nested":{ "inner_data": "Hello from inner", "inner_nested":{ "data1": 1234, "data2": "Hello", "list": [1, 2, 3]}}}"""
        var d4 = mapper?.readValue<ComplexJSONData>(complexJsonString)
        Log.d("mytag", "${d4!!.nested.innerData}")
        Log.d("mytag", "${d4.nested.innerNested.data1}")
        Log.d("mytag", "${d4.nested.innerNested.data2}")
        Log.d("mytag", "${d4.nested.innerNested.list}")
    }
}