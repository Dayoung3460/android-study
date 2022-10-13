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

@JsonDeserialize(using=MyComplexJSONDataDeserializer::class)
data class ComplexJSONData2(val innerData: String?, val data1: Int?, val data2: String?, val list:List<Int>?)

class MyComplexJSONDataDeserializer: StdDeserializer<ComplexJSONData2>(ComplexJSONData2::class.java) {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): ComplexJSONData2 {
        val node: JsonNode? = p?.codec?.readTree<JsonNode>(p)
        val nestedNode: JsonNode? = node?.get("nested")
        val innerDataValue = nestedNode?.get("inner_data")?.asText()
        val innerNestedNode = nestedNode?.get("inner_nested")
        val innerNestedData1Node = innerNestedNode?.get("data1")?.asInt()
        val innerNestedData2Node = innerNestedNode?.get("data2")?.asText()

        val list = mutableListOf<Int>()
        innerNestedNode?.get("list")?.elements()?.forEach {
            list.add(it.asInt())
        }
        return ComplexJSONData2(innerDataValue, innerNestedData1Node, innerNestedData2Node, list)
    }
}